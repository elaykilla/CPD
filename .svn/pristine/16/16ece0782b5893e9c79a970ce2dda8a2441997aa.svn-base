/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.articles;

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
@Table(name = "AS_ANNOUNCES_SUBSCRIPTIONS")
@Entity
@IdClass(AnnounceSubscriptionId.class)
public class AnnounceSubscription extends GenericModel implements Subscription {

    @Id
    Long announce_id;
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
    @JoinColumn(name = "announce_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Announce announce;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", updatable = false, insertable = false, referencedColumnName = "id")
    public User user;
    
    @Column(name = "NOMBER_NOTIFY")
    public Integer notify;

    public AnnounceSubscription() {
        notify = 0;
        created = new Date();
        modified = new Date();
    }

    public AnnounceSubscription(Long announce_id, Long user_id) {
        this();
        this.announce_id = announce_id;
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
        return "announce";
    }

    @Override
    public <T extends JPABase> T save() {
        modified = new Date();
        return super.save();
    }
}
