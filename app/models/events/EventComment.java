/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.events;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import models.Searchable;
import models.User;
import models.articles.AnnounceComment;
import models.articles.Comment;
import play.db.jpa.GenericModel;
import services.Sortable;

/**
 *
 * @author Sissoko
 */
@Entity
@Table(name = "AS_EVENTS_COMMENTS")
@IdClass(EventCommentId.class)
public class EventComment extends GenericModel implements Searchable{

    @Id
    public long event_id;
    @Id
    public long comment_id;
    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    public Date created;
    /**
     *
     */
    @Column(name = "MODIFIED")
    public Date modified;
    /**
     *
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User modifier;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Event event;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Comment comment;

    public EventComment() {
        created = new Date();
    }

    public EventComment(long event_id, long comment_id) {
        this();
        this.event_id = event_id;
        this.comment_id = comment_id;
    }
    
    public int compareTo(Sortable o) {
        EventComment c = (EventComment)o;
        if(comment == null)
            return 1;
        if(c == null)
            return -1;
        return comment.compareTo(c.comment);
    }

    public String myType() {
        return "comment";
    }
}
