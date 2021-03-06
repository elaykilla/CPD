/**
 *
 */
package models.articles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import models.Dendrite;
import models.Searchable;
import models.User;
import models.events.Event;
import models.events.EventComment;
import services.CompareFunction;
import services.Sortable;
import services.StandardComparable;
import services.TriGenerique;

/**
 * @author IssaCamara
 * @date 1 mai 2013 21:22:12
 */
@Entity
@Table(name = "T_ANNOUNCES")
public class Announce extends AbstractArticle implements Searchable {

    /**
     *
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Dendrite dendrite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "announce")
    public List<AnnounceComment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "announce")
    public List<AnnounceSubscription> subscriptions;
    /**
     *
     */
    public Announce() {
        super();
        comments = new ArrayList<AnnounceComment>();
        subscriptions = new ArrayList<AnnounceSubscription>();
    }

    /**
     * add new comment on this article
     *
     * @param comment
     */
    public Announce addComment(Comment comment) {
        comment.save();
        AnnounceComment ac = new AnnounceComment(id, comment.id);
        ac.save();
        save();
        return this;
    }

    /**
     * remove comment from this article
     *
     * @param c
     */
    public Announce removeComment(Comment comment) {
        AnnounceCommentId acid = new AnnounceCommentId(id, comment.id);
        AnnounceComment ac = AnnounceComment.findById(acid);
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
    public Announce subscribe(User user) {
        if(user == null)
            return null;
        AnnounceSubscription as = AnnounceSubscription.findById(new AnnounceSubscriptionId(id, user.id));
        if(as != null) {
            return this;
        }
        as = new AnnounceSubscription(id, user.id);
        as.save();
        return this;
    }
    
    /**
     * unsubscribes a user.
     * @param user
     * @return 
     */
    public Announce unsubscribe(User user) {
        if(user == null)
            return null;
        AnnounceSubscriptionId asid = new AnnounceSubscriptionId(id, user.id);
        AnnounceSubscription as = AnnounceSubscription.findById(asid);
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
        AnnounceSubscriptionId asid = new AnnounceSubscriptionId(id, user.id);
        AnnounceSubscription as = AnnounceSubscription.findById(asid);
        return as != null;
    }

    /**
     *
     * @return
     */
    public Announce previous() {
        return Announce.find("created < ? and dendrite = ? order by created desc", created, dendrite)
                .first();
    }

    /**
     *
     * @return
     */
    public Announce next() {
        return Announce.find("created > ? and dendrite = ? order by created asc", created, dendrite)
                .first();
    }

    /**
     *
     * @param user
     * @return
     */
    public boolean isMine(User user) {
        if (user == null) {
            return false;
        }
        return user.equals(this.author);
    }

    /**
     * 
     * @param i
     * @return 
     */
    public static List<Announce> recents(int i) {
       List<Announce> recents = Announce.find("order by created desc").fetch(i);
        return recents;
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
        return "announce";
    }
    
    
    public List<AnnounceComment> getComments(){
        TriGenerique<AnnounceComment> tri = new TriGenerique<AnnounceComment>();
        CompareFunction sd = new StandardComparable();
        return tri.mergeSort(comments, sd);
    }
}
