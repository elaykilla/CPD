/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.Application.RECENTS_MAX_SIZE;
import static controllers.Application.connected;
import static controllers.Application.index;
import static controllers.Application.redirection;
import static controllers.Articles.comments;
import static controllers.Articles.recents;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import play.data.validation.Required;
import play.data.validation.Valid;
import models.Dendrite;
import models.Searchable;
import models.User;
import models.articles.Announce;
import models.articles.AnnounceComment;
import models.articles.AnnounceSubscription;
import models.articles.Comment;
import notifiers.Mails;
import play.data.validation.Validation;
import play.db.jpa.GenericModel;
import play.mvc.Before;
import play.mvc.Http;

/**
 *
 * @author Issa
 */
public class Announces extends Application {

    @Before
    public static void setup() {
        User user = connected();
        if (user != null && !user.active) {
            String message = "La page des évènements";
            render("Application/desactive.html", message, user);
        }
    }

    public static void announces() {
        List<Announce> allAnnounces = Announce.find("order by modified desc").fetch();
        List<Searchable> recents = recents(RECENTS_MAX_SIZE);
        Dendrite laCellule = Cellule.laCellule();
        String ext = "Announces";
        render(allAnnounces, recents, laCellule, ext);
    }

    public static void announce(Long id, String title) {
        User connected = connected();
        Announce announce = Announce.findById(id);
        Announce article = announce;
        if (announce != null) {
            Dendrite dendrite = announce.dendrite;
            List<AnnounceComment> comments;
            int maxSize;
            int length = 5;
            int commentPage = 1;
            String type = "announce";
            GenericModel.JPAQuery query = AnnounceComment.find("announce_id = ? order by created asc", id);
            comments = query.fetch();
            maxSize = comments.size();
            length = (length <= maxSize ? length : maxSize);
            int start = maxSize - length;
            comments = query.from(start).fetch();
            int commentSize = comments.size();
            announce.incermentViewed();
            if (connected == null) {
                List<Searchable> recents = recents(RECENTS_MAX_SIZE);
                render(announce, dendrite, recents, comments, maxSize, commentSize, article, type, commentPage);
            } else {
                render(announce, dendrite, comments, maxSize, commentSize, article, type, commentPage);
            }
        } else {
            redirection();
        }
    }

    public static void announcesByUser(Long id) {
        User usr = connected();
        render();
    }

    public static void announcesByDendrite(Long id) {
        Dendrite dendrite = Dendrite.findById(id);
        List<Announce> dendriteAnnounces = dendrite.announces();
        render(dendriteAnnounces);
    }

    public static void deleteAnnounce(Long announceId) {
        Announce announce = Announce.findById(announceId);
        announce.delete();
        announces();
    }

    public static void showDendriteAnnounces(Long dendriteId) {
        Dendrite dendrite = Dendrite.findById(dendriteId);
        List announces = Announce.find("byDendrite", dendrite).fetch();
        render(announces);
    }

    public static void showUserAnnounces() {
    }

    public static void modifyAnnounce(Long announceId, String content) {
        User usr = connected();
        Announce announce = Announce.findById(announceId);
        if (usr.id.longValue() == announce.author.id.longValue()) {
            announce.content = content;
        }
    }

    /**
     * find announces by Dendrite 10/page.
     *
     * @param dendriteCode
     * @param page
     * @return
     */
    public static List<Announce> announcesFrom(String dendriteCode, int page) {
        List<Announce> announces = null;
        Dendrite dendrite = Dendrite.findByCode(dendriteCode);
        announces = Announce.find("byDendrite", dendrite).fetch(page, 10);
        return announces;
    }

    public static void create() {
        render();
    }

    public static void createAnnounce(@Valid Announce announce) {
        User usr = connected();
        if (usr == null) {
            Users.login();
        } else {
            announce.author = usr;
            announce.dendrite = usr.dendrite;
            announce.save();
            announces();
        }
    }

    public static void editAnnounce(Long dendriteId, Long announceId) {
        //===============Begin rightifing==================\\
        if (dendriteId == null) {
            dendriteId = Cellule.dendriteId();
        }
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.login();
            return;
        }
        Announce announce = new Announce();
        Dendrite dendrite = Dendrite.findById(dendriteId);
        announce.dendrite = dendrite;
        announce.author = connected;
        if (announceId != null) {
            announce = Announce.findById(announceId);
        }

        if (dendrite == null) {
            flash.error("Pas de dendrite envoyé!!!");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (!connected.canEdit(announce)) {
            flash.error("Désolé %s, Vous n'avez pas le droit de modifier ou de créer un évènement dans cette dendrite contacter un administrateur", connected.fullName());
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        List<Searchable> recents = recents(RECENTS_MAX_SIZE);
        //===============End rightifing==================\\
        //List<Dendrite> dendrites = Dendrite.findAll("named asc");
        render(dendrite, announce, recents);
    }

    public static void postAnnounce(@Valid Announce announce, Long dendriteId) {
        //===============Begin rightifing==================\\
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.acelogin();
            return;
        }
        Dendrite dendrite = Dendrite.findById(dendriteId);

        if (dendrite == null) {
            flash.error("Pas de dendrite envoyé!!!");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (announce.id == null) {
            announce.author = connected();
            announce.dendrite = dendrite;
        }

        if (!connected.canEdit(announce)) {
            flash.error("Désolé %s, Vous n'avez pas le droit de modifier ou de créer un évènement dans cette dendrite contacter un administrateur", connected.fullName());
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        //===============End rightifing==================\\

        if (Validation.hasErrors()) {
            flash.error("Veuillez corriger les erreurs pour continuer");
            render("@editAnnounce", announce, dendrite);
            return;
        }
        announce.save();
        announce.subscribe(connected);
        for (User user : announce.dendrite.users) {
            try {
                Mails.notificationNew(user, announce);
            } catch (Exception ex) {
                Logger.getLogger(Announces.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        flash.success("Thanks for posting %s", announce.author.fullName());
        announce(announce.id, announce.title);
    }

    /**
     *
     * @param content
     * @param id
     */
    public static void postComment(@Required String content, Long id) {
        if (Validation.hasErrors()) {
            renderJSON(new String[]{"error", "Le commentaire est obligatoire."});
            return;
        }
        User connected = connected();
        if (connected == null) {
            renderJSON(new String[]{"error", "Veuillez vous connecter pour continuer"});
        } else {
            Comment comment = new Comment();
            comment.author = connected;
            comment.content = content.replaceAll("\n", "<br/>");
            Announce announce = Announce.findById(id);
            announce.addComment(comment);
            for (AnnounceSubscription as : announce.subscriptions) {
                try {
                    if (!connected.equals(as.user)) {
                        Mails.notificationComment(as);
                        as.incrementNotify();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Announces.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            String ass = "announce";
//            render("Application/comment.html", ass, announce, comment);
            Articles.comments(id, "announce", 1);
        }
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
        Announce announce = Announce.findById(id);
        Comment c = Comment.findById(commentId);
        if (!connected.isMine(c)) {
            flash.error("Vous n'avez pas le droit de supprimer ce commentaire");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        announce.removeComment(c);
        comments(id, "announce", 1);
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
        Announce announce = Announce.findById(id);
        if (announce == null) {
            renderJSON(new String[]{"Error", "L'annonce est introuvable"});
            return;
        }
        announce.subscribe(connected);
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
        Announce announce = Announce.findById(id);
        if (announce == null) {
            renderJSON(new String[]{"Error", "L'annonce est introuvable"});
            return;
        }
        announce.unsubscribe(connected);
        renderJSON(new String[]{"Success", "Vous êtes désabonné(e)"});
    }
}
