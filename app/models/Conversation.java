/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.Application;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import play.db.jpa.GenericModel;
import play.db.jpa.JPABase;
import services.Sortable;

/**
 *
 * @author Sissoko
 */
@Table(name = "AS_USERS_MESSAGES")
@Entity
@IdClass(ConversationId.class)
public class Conversation extends GenericModel implements Convers {

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
    @Id
    public long user_id1;
    @Id
    public long user_id2;
    @ManyToOne
    @JoinColumn(name = "user_id1", updatable = false, insertable = false, referencedColumnName = "id")
    public User user1;
    @ManyToOne
    @JoinColumn(name = "user_id2", updatable = false, insertable = false, referencedColumnName = "id")
    public User user2;
    @OneToMany(mappedBy = "convers", cascade = CascadeType.ALL)
    public List<Message> messages;
    @Column(name = "READY")
    public String ready = "notready";

    public Conversation() {
        messages = new ArrayList<Message>();
        created = new Date();
        modified = new Date();
    }

    /**
     *
     * @param user_id1
     * @param user_id2
     */
    public Conversation(long user_id1, long user_id2) {
        this();
        this.user_id1 = user_id1;
        this.user_id2 = user_id2;
    }

    /**
     *
     * @param user
     * @return
     */
    public long others(User user) {
        return user.id == user_id1 ? user_id2 : user_id1;
    }

    /**
     *
     * @param user
     */
    public void update(User user) {
        List<Message> unread = Message.find("byConversAndReady", this, false).fetch();
        for (Message m : unread) {
            if (!m.author.equals(user)) {
                m.ready = true;
                m.save();
            }
        }
        ready = "isread";
        save();
    }

    public boolean contains(User connected) {
        return connected.id == user_id1 || connected.id == user_id2;
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
        return null;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }
}
