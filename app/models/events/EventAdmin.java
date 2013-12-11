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
import models.User;
import play.db.jpa.GenericModel;

/**
 *
 * @author Sissoko
 */
@Entity
@Table(name="AS_EVENT_ADMIN")
@IdClass(EventAdminId.class)
public class EventAdmin extends GenericModel{
    
    @Id
    public long user_id;
    @Id
    public long event_id;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", updatable = false, insertable = false, referencedColumnName = "id")
    public User user;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Event event;
    
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

    public EventAdmin(long user_id, long event_id) {
        this.user_id = user_id;
        this.event_id = event_id;
        this.created = new Date();
        this.modified = new Date();
    }
    
    
    
}
