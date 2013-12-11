/**
 *
 */
package models.articles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import models.SearchField;
import models.Searchable;
import models.User;
import models.events.Event;
import services.CompareFunction;
import services.Sortable;
import services.StandardComparable;
import services.TriGenerique;

/**
 * @author Sissoko && Elay
 * @date 1 mai 2013 21:21:44
 */
@Entity
@Table(name = "T_ARTICLES")
public class Article extends AbstractArticle implements Searchable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    public List<ArticleComment> comments;
    
    /**
     * This set allows to send emails to the interested user by the articles.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    public List<ArticleSubscription> subscriptions;

    /**
     *
     */
    public Article() {
        super();
        comments = new ArrayList<ArticleComment>();
        subscriptions = new ArrayList<ArticleSubscription>();
    }

    /**
     * add new comment on this article
     *
     * @param comment
     */
    public Article addComment(Comment comment) {
        comment.save();
        ArticleComment ac = new ArticleComment(id, comment.id);
        ac.save();
        save();
        return this;
    }

    /**
     * remove comment from this article
     *
     * @param c
     */
    public Article removeComment(Comment comment) {
        ArticleCommentId acid = new ArticleCommentId(id, comment.id);
        ArticleComment ac = ArticleComment.findById(acid);
        if(ac != null) {
            ac.delete();
        }
        return this;
    }
    
    /**
     * Subscribes new user.
     * @param user
     * @return 
     */
    public Article subscribe(User user) {
        if(user == null)
            return null;
        ArticleSubscription as = ArticleSubscription.findById(new ArticleSubscriptionId(id, user.id));
        if(as != null) {
            return this;
        }
        as = new ArticleSubscription(id, user.id);
        as.save();
        return this;
    }
    
    /**
     * unsubscribes a user.
     * @param user
     * @return 
     */
    public Article unsubscribe(User user) {
        if(user == null)
            return null;
        ArticleSubscriptionId asid = new ArticleSubscriptionId(id, user.id);
        ArticleSubscription as = ArticleSubscription.findById(asid);
        if(as == null) {
            return this;
        }
        as.delete();
        return this;
    }
    /**
     * 
     * @param user
     * @return 
     */
    public boolean isSubscribed(User user) {
        if(user == null)
            return false;
        ArticleSubscriptionId asid = new ArticleSubscriptionId(id, user.id);
        ArticleSubscription as = ArticleSubscription.findById(asid);
        return as != null;
    }

    public static List<Article> articles(int page) {
        List<Article> articles = find("order by created desc")
                .fetch(page, 10);
        return articles;
    }

    /**
     *
     * @return
     */
    public String resume() {
        if (content == null) {
            return "";
        }
        return content.length() <= 100 ? content : content.substring(0, 100)
                + "...";
    }

    /**
     *
     * @return
     */
    public Article previous() {
        return Article.find("created < ? order by created desc", created)
                .first();
    }

    /**
     *
     * @return
     */
    public Article next() {
        return Article.find("created > ? order by created asc", created)
                .first();
    }

    /**
     *
     * @param author
     * @return
     */
    public boolean isMine(User user) {
        if (user == null) {
            return false;
        }
        return user.equals(this.author);
    }

    /**
     * This is a list of the i most recent Articles
     * @param i
     * @return 
     */
    public static List<Article> recents(int i) {
        List<Article> recents = Article.find("order by created desc").fetch(i);
        return recents;
    }

    /**
     *
     * @param search
     * @return
     */
    public static List<User> find(SearchField search) {
        List<User> all = User.find("select distinct a from Article a where "
                + "a.title like ? and a.content like ? "
                + "order by title asc",
                "%" + search.title + "%", "%" + search.content + "%").fetch();
        return all;
    }

    /**
     * 
     * @param o
     * @return 
     */
    public int compareTo(Sortable o) {
        if (o instanceof User) {
            return title.compareTo(((User) o).firstName);
        }
        if (o instanceof Article) {
            return -created.compareTo(((Article) o).created);
        }
        if (o instanceof Event) {
            return -created.compareTo(((Event) o).created);
        }
        if (o instanceof Announce) {
            return -created.compareTo(((Announce) o).created);
        }
        return -1;
    }

    public String myType() {
        return "article";
    }
    
    public List<ArticleComment> getComments(){
        TriGenerique<ArticleComment> tri = new TriGenerique<ArticleComment>();
        CompareFunction sd = new StandardComparable();
        return tri.mergeSort(comments, sd);
    }
}
