/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.events;

import controllers.Application;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import models.CivilityEnum;
import models.Dendrite;
import models.RoleRightId;
import models.Standard;
import models.User;
import models.articles.Comment;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import static play.db.jpa.GenericModel.find;
import play.db.jpa.JPABase;

/**
 *
 * @author Nanakassé
 */
@Entity
@Table(name = "AS_EVENT_INVITATIONS")
@IdClass(EventInvitationId.class)
public class EventInvitation  extends GenericModel {

    
    @Id
    public long guest_id;
    @Id
    public long event_id;
    
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
    @JoinColumn(name = "guest_id", updatable = false, insertable = false, referencedColumnName = "id")
    public User guest;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Event event;
    @Column(name = "INVITATION_STATE")
    @Enumerated(EnumType.STRING)
    public InvitationStateEnum state;
    @Column(name = "PAYMENT_STATE")
    @Enumerated(EnumType.STRING)
    public InvitationPaymentStateEnum paymentState;

    public EventInvitation() {
        created = new Date();
    }

    public EventInvitation(Long guest_id, Long event_id) {
        this.guest_id = guest_id;
        this.event_id = event_id;
        this.state = InvitationStateEnum.WAITING;
        this.paymentState = InvitationPaymentStateEnum.NOT_PAID;
        created = new Date();
    }
    public static EventInvitation findByUserAndEvent(User user, Event event){
        EventInvitation eventInvitation = find("byGuest and byEvent", user, event).first();
        return eventInvitation;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see play.db.jpa.GenericModel#save()
     */
    @Override
    public <T extends JPABase> T save() {
        modified = new Date();
        modifier = Application.connected(); 
        return super.save();
    }
}
