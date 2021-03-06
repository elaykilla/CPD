/**
 *
 */
package controllers;

import static controllers.Application.RECENTS_MAX_SIZE;
import static controllers.Application.connected;
import static controllers.Application.index;
import static controllers.Application.listByPage;
import static controllers.Application.redirection;
import static controllers.Application.sort;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Dendrite;
import models.RightEnum;
import models.SearchField;
import models.Searchable;
import models.User;
import models.articles.Announce;
import models.articles.AnnounceComment;
import models.articles.AnnounceSubscription;

import models.articles.Article;
import models.articles.ArticleComment;
import models.articles.ArticleSubscription;
import models.articles.Comment;
import models.events.Event;
import models.events.EventComment;
import notifiers.Mails;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.db.jpa.GenericModel;
import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.Before;
import services.CompareFunction;
import services.StandardComparable;
import services.TriGenerique;

/**
 * @author Sissoko && Elay
 * @date 12 mai 2013 12:27:11
 */
public class Articles extends Application {

    @Before
    public static void setup() {
        User user = connected();
        if (user != null && !user.active) {
            String message = "La page des dendrites";
            render("Application/desactive.html", message, user);
        }
    }

    /**
     *
     * @param id
     */
    public static void article(Long id, String title) {
        Article article = Article.findById(id);
        List<Searchable> recents = recents(RECENTS_MAX_SIZE);
        List<Dendrite> dendrites = Dendrite.findDendrites("name asc");
        List<ArticleComment> comments;
        int maxSize;
        int length = 5;
        int commentPage = 1;
        String type = "article";
        JPAQuery query = ArticleComment.find("article_id = ? order by created asc", id);
        comments = query.fetch();
        maxSize = comments.size();
        length = (length <= maxSize ? length : maxSize);
        int start = maxSize - length;
        comments = query.from(start).fetch();
        int commentSize = comments.size();
        article.incermentViewed();
        render(article, recents, dendrites, comments, maxSize, commentSize, commentPage, type);
    }

    /**
     *
     * @param articleId
     * @param content
     */
    public static void postComment(Long id, @Required String content) {
        Article article = Article.findById(id);
        if (Validation.hasErrors()) {
            renderJSON(new String[]{"error", "Le commentaire est obligatoire."});
            return;
        }
        User connected = connected();
        if (connected == null) {
            renderJSON(new String[]{"error", "Veuillez vous connecter pour continuer"});
            return;
        }
        Comment comment = new Comment();
        comment.author = connected;
        comment.content = content.replaceAll("\n", "<br/>");
        article.addComment(comment);
        for (ArticleSubscription as : article.subscriptions) {
            try {
                if (!connected.equals(as.user)) {
                    Mails.notificationComment(as);
                    as.incrementNotify();
                }
            } catch (Exception ex) {
                Logger.getLogger(Announces.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        String ass = "article";
//        render("Application/comment.html", ass, article, comment);
        comments(id, "article", 1);
    }

    /**
     *
     */
    public static void editArticle(Long id) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veillez vous connecter s'il vous plait.");
            Users.login();
            return;
        }
        List<Searchable> recents = recents(RECENTS_MAX_SIZE);
        Article article = null;
        if (id != null) {
            article = Article.findById(id);
            if (!connected.contains(RightEnum.EDIT_ARTICLE.name())) {
                if (!connected.isMine(article)) {
                    flash.error("Vous n'avez pas le droit de modifier cet article");
                    articles(null);
                    return;
                }
            }
        }
        if (article == null) {
            article = new Article();
        }
        String ext = "Edition Article";
        render(article, recents, ext);
    }

    /**
     *
     * @param article
     */
    public static void postArticle(Article article) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veillez vous connecter s'il vous plait.");
            Users.login();
            return;
        }
        if (Validation.hasErrors()) {
            flash.error("Les champs comportent des erreurs.");
            render("@editArticle", article);
            return;
        }
        if (article.id == null) {
            article.author = connected();
        }
        article.save();
        article.subscribe(connected);
        flash.success("Thanks for posting %s", article.author.fullName());
        article(article.id, article.title);
    }

    /**
     *
     * @param id
     */
    public static void deleteArticle(Long id) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veillez vous connecter s'il vous plait.");
            Users.login();
            return;
        }
        Article article = Article.findById(id);
        if (!connected.contains(RightEnum.DELETE_ARTICLE.name())) {
            if (!connected.isMine(article)) {
                flash.error("Vous n'avez pas le droit de supprimer cet article");
                articles(null);
                return;
            }
        }
        article.delete();
        articles(null);
    }

    /**
     *
     * @param id
     * @param article_id
     */
    public static void deleteComment(Long commentId, Long id) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veillez vous connecter s'il vous plait.");
            Users.login();
            return;
        }
        Article article = Article.findById(id);
        Comment c = Comment.findById(commentId);
        if (!connected.isMine(c)) {
            flash.error("Vous n'avez pas le droit de supprimer ce commentaire");
            index();
            return;
        }
        article.removeComment(c);
        comments(id, "article", 1);
    }

    /**
     *
     */
    public static void articles(Long id) {
        List<Article> articles;
        if (id == null) {
            articles = Article.find("order by modified desc").fetch();
        } else {
            User user = User.findById(id);
            articles = user.articles();
        }
        List<Dendrite> dendrites = Dendrite.findDendrites("name asc");
        List<Searchable> recents = recents(RECENTS_MAX_SIZE);
        render(articles, recents, dendrites);
    }

    public static void search(String q, int page) {
        if (q == null || q.isEmpty() || q.matches("[ ]+")) {
            redirection();
            return;
        }
        q = q.replaceAll("'", "''");
        String[] req = q.split(" ");
        SearchField sf = new SearchField();
        sf.request = req;
        sf.type = "article";
        List<Article> articles = Article.find(sf.createRequest()).fetch();
        sf.type = "event";
        List<Event> events = Event.find(sf.createRequest()).fetch();
        sf.type = "announce";
        List<Announce> announces = Announce.find(sf.createRequest()).fetch();
        List<Searchable> all = new ArrayList<Searchable>();
        all.addAll(articles);
        all.addAll(events);
        all.addAll(announces);
        all = sort(all);
        long size = all.size();
        int nbPagesMax = 10;
        int nbPages = (int) (size / nbPagesMax);
        if (nbPagesMax * nbPages < size) {
            nbPages = nbPages + 1;
        }
        articles.clear();
        announces.clear();
        events.clear();
        if (page == 0) {
            page = 1;
        }
        List<Searchable> searchs = (List<Searchable>) listByPage(page, nbPagesMax, all);
        for (int i = 0; i < searchs.size() && i < nbPagesMax; i++) {
            Searchable s = searchs.get(i);
            if (s instanceof Article) {
                articles.add((Article) s);
            } else if (s instanceof Announce) {
                announces.add((Announce) s);
            } else if (s instanceof Event) {
                events.add((Event) s);
            }
        }
        List<Searchable> recents = recents(RECENTS_MAX_SIZE);
        render("Application/search.html", articles, events, announces, recents, size, page, nbPagesMax, nbPages);
    }

    public static List<Searchable> recents(int length) {
        List<Searchable> recents = new ArrayList<Searchable>();
        List<Article> arr = Article.recents(length);
        List<Announce> anr = Announce.recents(length);
        List<Event> evr = Event.recents(length);
        recents.addAll(arr);
        recents.addAll(anr);
        recents.addAll(evr);
        TriGenerique<Searchable> tri = new TriGenerique<Searchable>();
        CompareFunction sd = new StandardComparable();
        recents = tri.mergeSort(recents, sd);
        if (recents.size() > length) {
            recents = recents.subList(0, length);
        }
        return recents;
    }

    /**
     *
     * @param id
     */
    public static void subscribe(Long id) {
        User connected = connected();
        if (connected == null) {
            renderJSON(new String[]{"Error", "Il faut se connecter pour s'abonner"});
            return;
        }
        Article article = Article.findById(id);
        if (article == null) {
            renderJSON(new String[]{"Error", "L'article est introuvable"});
            return;
        }
        article.subscribe(connected);
        renderJSON(new String[]{"Success", "Vous êtes abonné(e)"});
    }

    /**
     *
     * @param id
     */
    public static void unsubscribe(Long id) {
        User connected = connected();
        if (connected == null) {
            renderJSON(new String[]{"Error", "Il faut se connecter pour s'abonner"});
            return;
        }
        Article article = Article.findById(id);
        if (article == null) {
            renderJSON(new String[]{"Error", "L'article est introuvable"});
            return;
        }
        article.unsubscribe(connected);
        renderJSON(new String[]{"Success", "Vous êtes désabonné(e)"});
    }

    public static void comments(Long id, String type, int page) {
        if (page <= 0) {
            page = 1;
        }
        List<GenericModel> comments = new ArrayList<GenericModel>();
        int maxSize = 0;
        int length = page * 5;
        int commentPage = page;
        GenericModel article = null;
        if ("article".equalsIgnoreCase(type)) {
            article = Article.findById(id);
            JPAQuery query = ArticleComment.find("article_id = ? order by created asc", id);
            comments = query.fetch();
            maxSize = comments.size();
            length = (length <= maxSize ? length : maxSize);
            int start = maxSize - length;
            comments = query.from(start).fetch();
        } else if ("announce".equalsIgnoreCase(type)) {
            article = Announce.findById(id);
            JPAQuery query = AnnounceComment.find("announce_id = ? order by created asc", id);
            comments = query.fetch();
            maxSize = comments.size();
            length = (length <= maxSize ? length : maxSize);
            int start = maxSize - length;
            comments = query.from(start).fetch();
        } else if ("event".equalsIgnoreCase(type)) {
            article = Event.findById(id);
            JPAQuery query = ArticleComment.find("event_id = ? order by created asc", id);
            comments = query.fetch();
            maxSize = comments.size();
            length = (length <= maxSize ? length : maxSize);
            int start = maxSize - length;
            comments = query.from(start).fetch();
        }
        int commentSize = comments.size();
        render(article, commentSize, commentPage, maxSize, comments, type);
    }
}
