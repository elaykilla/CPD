/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.Application;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import play.data.validation.MaxSize;
import play.data.validation.Required;

/**
 *
 * @author Sissoko
 */
@Table(name = "T_MESSAGES")
@Entity
public class Message extends Standard {

    @Required
    @ManyToOne
    public User author;
    @Required(message = "Le contenu du message est obligatoire")
    @MaxSize(20000)
    @Lob
    @Column(name = "CONTENT")
    public String content;
    @Column(name = "SUBJECT")
    public String subject;
    @ManyToOne
    public ConversUserDendrite cud;
    @ManyToOne
    public Conversation convers;
    public Boolean ready;

    public Message() {
        ready = false;
    }

    public String resume() {
        if (content == null) {
            return "";
        }
        String resume = content;
        if (content.length() > Application.CONV_RESUME_SIZE) {
            resume = content.substring(0, Application.CONV_RESUME_SIZE) + "...";
        }
        return resume;
    }

    @Override
    public String toString() {
        return "Author: " + author + "\n"
                + "Subject: " + subject + "\n";
    }
}
