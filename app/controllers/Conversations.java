/**
 *
 */
package controllers;

import static controllers.Application.connected;
import static controllers.Application.index;
import static controllers.Application.notNull;
import java.util.Date;

import notifiers.Mails;

import models.Conversation;
import models.Message;
import models.User;
import play.i18n.Messages;
import play.mvc.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Convers;
import models.ConversUserDendrite;
import models.ConversationId;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Http;
import services.CompareFunction;
import services.StandardComparable;
import services.TriGenerique;

/**
 * @author sissoko
 * @date 30 d�c. 2012 19:44:13
 */
public class Conversations extends Application {

    @Before
    public static void checkUser() {
        if (connected() == null) {
            flash.error("Connectez vous pour continuer");
            String ass = "login";
            render("Users/acelogin.html", ass);
        }
    }

    public static void messages(Long id, String type, int page, String chat) {
        if (page == 0) {
            page = 1;
        }
        User connected = connected();
        if (connected == null) {
            flash.error(Messages.get("accesdenied"));
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (id == null) {
            conversations(1);
        }
        if (type == null) {
            ConversationId cid = new ConversationId(connected.id, id);
            Conversation conversation = Conversation.findById(cid);
            if (conversation == null) {
                conversation = new Conversation(connected.id, id);
                conversation.save();
            }
            conversation.update(connected);
            int maxSize = Message.find("convers.user1 = ? and "
                    + "convers.user2 = ? order by created desc", conversation.user1,
                    conversation.user2).fetch().size();
            List<Message> messages = Message.find("convers.user1 = ? and "
                    + "convers.user2 = ? order by created desc", conversation.user1,
                    conversation.user2).fetch(5 * page);
            int size = messages.size();
            List<Message> conMessages = new ArrayList<Message>();
            for (int i = messages.size(); i > 0; i--) {
                conMessages.add(messages.get(i - 1));
            }
            render(conversation, conMessages, chat, page, size, maxSize);
        } else if (type.equals("dendrite")) {
            //ConversUserDendriteId cid = new ConversUserDendriteId(connected.id, id);
            ConversUserDendrite conversation = ConversUserDendrite.find("dendrite = ?", connected.dendrite).first();
            if (conversation == null) {
                Http.Header referer = request.headers.get("referer");
                if (referer != null) {
                    redirect(referer.value());
                } else {
                    index();
                }
                return;
            }
            int maxSize = Message.find("cud.dendrite = ?"
                    + " order by created desc", conversation.dendrite).fetch().size();
            List<Message> conMessages = Message.find("cud.dendrite = ?"
                    + " order by created desc", conversation.dendrite).fetch(5 * page);
            int size = conMessages.size();
            render(conversation, conMessages, chat, page, size, maxSize);
        }
    }

    public static void conversations(int page) {
        if (page < 1) {
            page = 1;
        }
        User connected = connected();
        int maxSize = Conversation.find("user1 = ? order by modified desc", connected).fetch().size();
        maxSize += ConversUserDendrite.find("dendrite = ? order by modified desc", connected.dendrite).fetch().size();
        List<Conversation> cs = Conversation.find("user1 = ? order by modified desc", connected).fetch(15 * page);
        List<ConversUserDendrite> cuds = ConversUserDendrite.find("dendrite = ? order by modified desc", connected.dendrite).fetch(5 * page);
        List<Convers> convers = new ArrayList<Convers>();
        convers.addAll(cs);
        convers.addAll(cuds);
        int size = convers.size();
        TriGenerique<Convers> tri = new TriGenerique<Convers>();
        CompareFunction sd = new StandardComparable();
        convers = tri.mergeSort(convers, sd);
        render(convers, page, size, maxSize);
    }

    /**
     *
     * @param id
     * @param subject
     * @param content
     * @param chat
     * @param ajax
     */
    public static void sendMessage(@Required Long id, String subject, @Required String content, String chat) {
        sendAjaxMessage(id, subject, content, chat, false);

    }

    /**
     *
     * @param id
     * @param subject
     * @param content
     * @param chat
     */
    public static void sendAjaxMessage(@Required Long id, String subject, @Required String content, String chat, boolean ajax) {
        User connected = connected();
        if (!notNull(subject)) {
            subject = "Aucun object";
        }
        validation.required(id);
        validation.required(content);
        if (Validation.hasErrors()) {
            if (!ajax) {
                flash.error(Messages.get("CorrectErrors"));
                Http.Header referer = request.headers.get("referer");
                if (referer != null) {
                    redirect(referer.value());
                } else {
                    index();
                }
            } else {
                renderJSON(new String[]{"error", "Veuillez vérifier vos champs."});
            }
            return;
        }
        User receiver = User.findById(id);
        if (receiver == null) {
            if (!ajax) {
                flash.error("Utilisation un trouvable");
                Http.Header referer = request.headers.get("referer");
                if (referer != null) {
                    redirect(referer.value());
                } else {
                    index();
                }
            } else {
                renderJSON(new String[]{"error", "Utilisation un trouvable."});
            }
            return;
        }
        ConversationId cid = new ConversationId(connected.id, id);
        Conversation senderConvers = Conversation.findById(cid);
        if (senderConvers == null) {
            senderConvers = new Conversation(connected.id, id);
            senderConvers.save();
        }
        ConversationId cidr = new ConversationId(id, connected.id);
        Conversation receiverConvers = Conversation.findById(cidr);
        if (receiverConvers == null) {
            receiverConvers = new Conversation(id, connected.id);
            receiverConvers.save();
        }
        Message mail = new Message();
        mail.author = connected;
        mail.convers = senderConvers;
        mail.content = content;
        mail.subject = subject;
        mail.ready = true;
        Message rmail = new Message();
        rmail.author = connected;
        rmail.convers = receiverConvers;
        rmail.content = content;
        rmail.subject = subject;
        rmail.ready = false;
        try {
            Mails.sendMessage(receiver, mail, chat == null);
            mail.save();
            senderConvers.save();
            if (!connected.id.equals(id)) {
                rmail.save();
                receiverConvers.ready = "notready";
                receiverConvers.save();
            }
        } catch (Exception ex) {
            Logger.getLogger(Conversations.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!ajax) {
            messages(id, null, 1, chat);
        } else {
            int page = 1;
            Conversation conversation = Conversation.findById(cid);
            if (conversation == null) {
                conversation = new Conversation(connected.id, id);
                conversation.save();
            }
            conversation.update(connected);
            int maxSize = Message.find("convers.user1 = ? and "
                    + "convers.user2 = ? order by created desc", conversation.user1,
                    conversation.user2).fetch().size();
            List<Message> messages = Message.find("convers.user1 = ? and "
                    + "convers.user2 = ? order by created desc", conversation.user1,
                    conversation.user2).fetch(5 * page);
            int size = messages.size();
            List<Message> conMessages = new ArrayList<Message>();
            for (int i = messages.size(); i > 0; i--) {
                conMessages.add(messages.get(i - 1));
            }
            render(conversation, conMessages, chat, page, size, maxSize);
        }
    }

    /**
     * 
     * @param id
     * @param ajax
     * @param userId 
     */
    public static void deleteMessage(Long id, boolean ajax, Long userId) {
        Message m = Message.findById(id);
        Http.Header referer = request.headers.get("referer");
        if (m == null) {
            if (!ajax) {
                flash.error("Message introuvable");
                if (referer != null) {
                    redirect(referer.value());
                } else {
                    index();
                }
            } else {
                renderJSON(new String[]{"error", "Message introuvable."});
            }
            return;
        }
        User connected = connected();
        if (m.convers == null || connected.id != m.convers.user_id1) {
            if (!ajax) {
                flash.error("Vous ne pouvez pas supprimer ce message");
                if (referer != null) {
                    redirect(referer.value());
                } else {
                    index();
                }
            } else {
                renderJSON(new String[]{"error", "Vous ne pouvez pas supprimer ce message."});
            }
        }
        m.delete();
        if (!ajax) {
            flash.success(Messages.get("message.messageDeleted"));
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        } else {
            int page = 1;
            ConversationId cid = new ConversationId(connected.id, userId);
            Conversation conversation = Conversation.findById(cid);
            if (conversation == null) {
                conversation = new Conversation(connected.id, id);
                conversation.save();
            }
            conversation.update(connected);
            int maxSize = Message.find("convers.user1 = ? and "
                    + "convers.user2 = ? order by created desc", conversation.user1,
                    conversation.user2).fetch().size();
            List<Message> messages = Message.find("convers.user1 = ? and "
                    + "convers.user2 = ? order by created desc", conversation.user1,
                    conversation.user2).fetch(5 * page);
            int size = messages.size();
            List<Message> conMessages = new ArrayList<Message>();
            for (int i = messages.size(); i > 0; i--) {
                conMessages.add(messages.get(i - 1));
            }
            render("@sendAjaxMessage", conversation, conMessages, page, maxSize, size);
        }
    }
}
