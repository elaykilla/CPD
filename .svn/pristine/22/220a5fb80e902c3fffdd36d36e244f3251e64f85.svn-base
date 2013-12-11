/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.events;

import models.articles.*;
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
import play.db.jpa.JPABase;

/**
 *
 * @author Sissoko
 */
@Table(name = "AS_EVENTS_SUBSCRIPTIONS")
@Entity
@IdClass(EventSubscriptionId.class)
public class EventSubscription extends GenericModel implements Subscription {

    @Id
    Long event_id;
    @Id
    Long user_id;
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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", updatable = false, insertable = false, referencedColumnName = "id")
    public User user;
    
    @Column(name = "NOMBER_NOTIFY")
    public Integer notify;

    public EventSubscription() {
        notify = 0;
        created = new Date();
        modified = new Date();
    }

    public EventSubscription(Long event_id, Long user_id) {
        this();
        this.event_id = event_id;
        this.user_id = user_id;
    }
    
    public int incrementNotify() {
        if(notify == null) {
            notify = 0;
        }
        notify++;
        save();
        return notify;
    }

    public User getUser() {
        return user;
    }
    
    public String getType(){
        return "event";
    }

    @Override
    public <T extends JPABase> T save() {
        modified = new Date();
        return super.save();
    }
}
