/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.Application;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import play.db.jpa.GenericModel;
import play.db.jpa.JPABase;
import services.Sortable;

/**
 *
 * @author Sissoko
 */
@Table(name = "AS_DENDRITES_MESSAGES")
@Entity
@IdClass(ConversUserDendriteId.class)
public class ConversUserDendrite extends GenericModel implements Convers {

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    public Date created;
    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED")
    public Date modified;
    /**
     *
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User modifier;
    @Id
    public long dendrite_id;
    @Id
    public long user_id;
    @ManyToOne
    @JoinColumn(name = "dendrite_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Dendrite dendrite;
    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, insertable = false, referencedColumnName = "id")
    public User user1;
    @OneToMany(mappedBy = "cud")
    public List<Message> messages;
    @Column(name = "READY")
    public String ready = "isread";

    public ConversUserDendrite() {
        messages = new ArrayList<Message>();
        created = new Date();
        modified = new Date();
    }

    public ConversUserDendrite(long user_id, long dendrite_id) {
        this();
        this.dendrite_id = dendrite_id;
        this.user_id = user_id;
    }

    public Message getLast() {
        if (messages.isEmpty()) {
            return null;
        }
        return messages.get(messages.size() - 1);
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

    public int compareTo(Sortable o) {
        if (o instanceof Conversation) {
            if (modified != null) {
                return -modified.compareTo(((Conversation) o).modified);
            }
            return -created.compareTo(((Conversation) o).created);
        }
        if (modified != null) {
            return -modified.compareTo(((ConversUserDendrite) o).modified);
        }
        return -created.compareTo(((ConversUserDendrite) o).created);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Dendrite getDendrite() {
        return dendrite;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return null;
    }
}
