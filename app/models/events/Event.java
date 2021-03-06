/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.events;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import models.Dendrite;
import models.Photo;
import models.Searchable;
import models.User;
import models.articles.AbstractArticle;
import models.articles.Announce;
import models.articles.Article;
import models.articles.ArticleComment;
import models.articles.Comment;
import models.cvs.Adresse;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.db.jpa.JPABase;
import services.CompareFunction;
import services.Sortable;
import services.StandardComparable;
import services.TriGenerique;

/**
 *
 * @author Sissoko
 */
@Entity
@Table(name = "T_EVENTS")
public class Event extends AbstractArticle implements Searchable {

    /**
     *
     */
    @Column(name = "BEGINNING", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date beginning;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    public List<EventInvitation> eventInvitations;
    @Required(message = "Put an amount, if it's free put 0")
    @Column(name = "COST", nullable = false)
    public Double cost;
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Dendrite dendrite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    public List<Photo> photos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    public List<EventComment> comments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    public List<EventSubscription> subscriptions;
    @OneToOne(cascade = CascadeType.ALL)
    public Adresse adresse;

    public Event() {
        super();
        eventInvitations = new ArrayList<EventInvitation>();
        comments = new ArrayList<EventComment>();
        cost = 0.0;
        adresse = new Adresse();
    }

    /**
     *
     * @param cost
     * @param title
     * @param content
     */
    public Event(Double cost, String title, String content) {
        super();
        this.title = title;
        this.content = content;
        this.cost = cost;
        eventInvitations = new ArrayList<EventInvitation>();
    }

    /**
     *
     * @param photo
     * @return
     */
    public Event addPhoto(Photo photo) {
        if (photo == null) {
            return null;
        }
        photo.event = this;
        photo.save();
        //photos.add(photo);
        //save();
        return this;
    }

    /**
     *
     * @param photo
     * @return
     */
    public Event removePhoto(Photo photo) {
        if (photo == null) {
            return null;
        }
        photo.delete();
//        save();
        return this;
    }

    /**
     * add a guest
     *
     * @param guest
     * @return
     */
    public Event addInvitation(User guest) {
        if (guest == null) {
            return null;
        }
        EventInvitationId eiid = new EventInvitationId(guest.id, id);
        EventInvitation eventInvitation = EventInvitation.findById(eiid);
        if (eventInvitation == null) {
            eventInvitation = new EventInvitation(guest.id, this.id);
        }
        eventInvitation.state = InvitationStateEnum.GOING;
        if (cost == 0.0) {
            eventInvitation.paymentState = InvitationPaymentStateEnum.PAID;
        }
        eventInvitation.save();
        return this;
    }

    /**
     *
     * @param guest
     * @return
     */
    public Event removeGuest(User guest) {
        EventInvitationId eiid = new EventInvitationId(guest.id, id);
        EventInvitation eventInvitation = EventInvitation.findById(eiid);
        if (eventInvitation != null) {
            eventInvitation.delete();
        }
        return this;
    }

    public static List<Event> search(String content) {
        List<Event> events = all().fetch();
        if (content == null || content.isEmpty()) {
            return events;
        }
        List<Event> result = new ArrayList<Event>();
        for (Event e : events) {
            if (e.title.contains(content) || e.content.contains(content) || e.author.fullName().contains(content) || content.contains(e.author.firstName)) {
                result.add(e);
            }
        }
        return result;
    }

    /**
     * add new comment on this article
     *
     * @param comment
     */
    public Event addComment(Comment comment) {
        comment.save();
        EventComment ec = new EventComment(id, comment.id);
        ec.save();
        save();
        return this;
    }

    /**
     * remove comment from this article
     *
     * @param c
     */
    public Event removeComment(Comment comment) {
        EventCommentId ecid = new EventCommentId(id, comment.id);
        EventComment ec = EventComment.findById(ecid);
        if (ec != null) {
            ec.delete();
        }
        return this;
    }

    /**
     * Subscribes new user.
     *
     * @param user
     * @return
     */
    public Event subscribe(User user) {
        if (user == null) {
            return null;
        }
        EventSubscription as = EventSubscription.findById(new EventSubscriptionId(id, user.id));
        if (as != null) {
            return this;
        }
        as = new EventSubscription(id, user.id);
        as.save();
        return this;
    }

    /**
     * unsubscribes a user.
     *
     * @param user
     * @return
     */
    public Event unsubscribe(User user) {
        if (user == null) {
            return null;
        }
        EventSubscriptionId asid = new EventSubscriptionId(id, user.id);
        EventSubscription as = EventSubscription.findById(asid);
        if (as == null) {
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
        if (user == null) {
            return false;
        }
        EventSubscriptionId asid = new EventSubscriptionId(id, user.id);
        EventSubscription as = EventSubscription.findById(asid);
        return as != null;
    }

    public Event previous() {
        return Event.find("beginning < ? and dendrite = ? order by beginning desc", beginning, dendrite).first();
    }

    public Event next() {
        return Event.find("beginning > ? and dendrite = ? order by beginning asc", beginning, dendrite).first();
    }

    public boolean isMine(User user) {
        if (user == null) {
            return false;
        }
        return user.equals(this.author);
    }

    public static List<Event> recents(int i) {
        List<Event> recents = Event.find("order by created desc").fetch(i);
        return recents;
    }
    
    public static List<Event> upcomingEvents(){
    	List<Event> upcomingEvents = Event.find("order by beginning asc").fetch(5);
    	return upcomingEvents;
    }
    
    public String resume() {
        if (content == null) {
            return "";
        }
        return content.length() <= 100 ? content : content.substring(0, 100)
                + "...";
    }

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
        return "event";
    }

    public List<EventComment> getComments() {
        TriGenerique<EventComment> tri = new TriGenerique<EventComment>();
        CompareFunction sd = new StandardComparable();
        return tri.mergeSort(comments, sd);
    }
}
