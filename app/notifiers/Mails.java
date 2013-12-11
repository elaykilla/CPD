package notifiers;

import controllers.Application;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.mail.internet.InternetAddress;
import models.Dendrite;
import models.Message;
import models.Standard;

import models.User;
import models.articles.Announce;
import models.articles.ArticleSubscription;
import models.articles.Subscription;
import models.events.Event;
import models.feedback.Feedback;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import play.exceptions.MailException;
import play.exceptions.TemplateNotFoundException;
import play.exceptions.UnexpectedException;
import play.i18n.Messages;
import play.libs.Mail;
import play.mvc.Mailer;
import static play.mvc.Mailer.addRecipient;
import static play.mvc.Mailer.setFrom;
import static play.mvc.Mailer.setReplyTo;
import static play.mvc.Mailer.setSubject;
import play.templates.Template;
import play.templates.TemplateLoader;

public class Mails extends Mailer {

    /**
     *
     * @param user
     * @throws Exception
     */
    public static void welcome(User user) throws Exception {
        setFrom("CPD <cpd@cpd-mali.com>");
        setSubject(Messages.get("welcome.message.object") + "!");
        addRecipient(user.fullName() + "<" + (user.emailRedirect == null ? user.email : user.emailRedirect) + ">");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("user", user);
        mysend(args);
    }

    /**
     *
     * @param user
     * @throws Exception
     */
    public static void lostPassword(User user) throws Exception {
        setFrom("CPD <cpd@cpd-mali.com>");
        setSubject(Messages.get("password.reset"));
        addRecipient(user.email);
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("user", user);
        mysend(args);
    }

    /**
     * @param userTemp
     */
    public static void confirm(User user) {
        setFrom("CPD <cpd@cpd-mali.com>");
        setSubject(Messages.get("confirm.message.object") + "!");
        addRecipient(user.fullName() + "<" + user.externalEmail() + ">");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("user", user);
        mysend(args);
    }

    public static void confirmRedirect(User user) {
        setFrom("CPD <cpd@cpd-mali.com>");
        setSubject(Messages.get("confirm.message.object") + "!");
        addRecipient(user.fullName() + "<" + user.emailRedirect + ">");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("user", user);
        mysend(args);
    }

    /**
     *
     * @param as
     * @throws Exception
     */
    public static void notificationComment(Subscription as) throws Exception {
        User user = as.getUser();
        setFrom("CPD <cpd@cpd-mali.com>");
        setSubject("Nouveau commentaire");
        addRecipient(user.fullName() + "<" + (user.emailRedirect == null ? user.email : user.emailRedirect) + ">");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("ass", as);
        args.put("connected", Application.connected());
        args.put("type", as.getType());
        mysend(args);
    }

    /**
     *
     * @param user
     * @param stand
     */
    public static void notificationNew(User user, Standard stand) throws Exception {
        String type = "", subject = "Nouveau!";
        if (stand instanceof Event) {
            type = "event";
            subject = ((Event) stand).title;
        } else if (stand instanceof Announce) {
            type = "announce";
            subject = ((Announce) stand).title;
        }
        setFrom("CPD <cpd@cpd-mali.com>");
        setSubject(subject);
        addRecipient(user.fullName() + "<" + (user.emailRedirect == null ? user.email : user.emailRedirect) + ">");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("user", user);
        args.put("stand", stand);
        args.put("connected", Application.connected());
        args.put("type", type);
        mysend(args);
    }

    /**
     * Rewrite the send method cause html mails don't work on some SMTP servers
     *
     * @param args
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Future<Boolean> mysend(Map<String, Object> args) {
        try {
            final HashMap<String, Object> map = infos.get();
            if (map == null) {
                throw new UnexpectedException("Mailer not instrumented ?");
            }

            // Body character set
            final String charset = (String) infos.get().get("charset");

            // Headers
            final Map<String, String> headers = (Map<String, String>) infos
                    .get().get("headers");

            // Subject
            final String subject = (String) infos.get().get("subject");

            String templateName = (String) infos.get().get("method");
            if (templateName.startsWith("notifiers.")) {
                templateName = templateName.substring("notifiers.".length());
            }
            if (templateName.startsWith("controllers.")) {
                templateName = templateName.substring("controllers.".length());
            }
            templateName = templateName.substring(0, templateName.indexOf("("));
            templateName = templateName.replace(".", "/");

            // overrides Template name
            // if (args.length > 0 && args[0] instanceof String &&
            // LocalVariablesNamesTracer.getAllLocalVariableNames(args[0]).isEmpty())
            // {
            // templateName = args[0].toString();
            // }

            final Map<String, Object> templateHtmlBinding = new HashMap<String, Object>();
            final Map<String, Object> templateTextBinding = new HashMap<String, Object>();
            for (String key : args.keySet()) {
                templateHtmlBinding.put(key, args.get(key));
                templateTextBinding.put(key, args.get(key));
            }

            // The rule is as follow: If we ask for text/plain, we don't care
            // about the HTML
            // If we ask for HTML and there is a text/plain we add it as an
            // alternative.
            // If contentType is not specified look at the template available:
            // - .txt only -> text/plain
            // else
            // - -> text/html
            String contentType = (String) infos.get().get("contentType");
            String bodyHtml = null;
            String bodyText = "";
            try {
                Template templateHtml = TemplateLoader.load(templateName
                        + ".html");
                bodyHtml = templateHtml.render(templateHtmlBinding);
            } catch (TemplateNotFoundException e) {
                if (contentType != null
                        && !contentType.startsWith("text/plain")) {
                    throw e;
                }
            }

            try {
                Template templateText = TemplateLoader.load(templateName
                        + ".txt");
                bodyText = templateText.render(templateTextBinding);
            } catch (TemplateNotFoundException e) {
                if (bodyHtml == null
                        && (contentType == null || contentType
                        .startsWith("text/plain"))) {
                    throw e;
                }
            }

            // Content type

            if (contentType == null) {
                if (bodyHtml != null) {
                    contentType = "text/html";
                } else {
                    contentType = "text/plain";
                }
            }

            // Recipients
            final List<Object> recipientList = (List<Object>) infos.get().get(
                    "recipients");
            // From
            final Object from = infos.get().get("from");
            final Object replyTo = infos.get().get("replyTo");

            Email email = null;
            if (infos.get().get("attachments") == null) {
                if (StringUtils.isEmpty(bodyHtml)) {
                    email = new SimpleEmail();
                    email.setMsg(bodyText);
                } else {
                    HtmlEmail htmlEmail = new HtmlEmail();
                    htmlEmail.setHtmlMsg(bodyHtml);
                    htmlEmail.setContent(bodyHtml, "text/html"); // to force the
                    // content
                    // for dumb
                    // smtp
                    // servers
                    if (!StringUtils.isEmpty(bodyText)) {
                        htmlEmail.setTextMsg(bodyText);
                    }
                    email = htmlEmail;
                }

            } else {
                if (StringUtils.isEmpty(bodyHtml)) {
                    email = new MultiPartEmail();
                    email.setMsg(bodyText);
                } else {
                    HtmlEmail htmlEmail = new HtmlEmail();
                    htmlEmail.setHtmlMsg(bodyHtml);
                    htmlEmail.setContent(bodyHtml, "text/html"); // to force the
                    // content
                    // for dumb
                    // smtp
                    // servers
                    if (!StringUtils.isEmpty(bodyText)) {
                        htmlEmail.setTextMsg(bodyText);
                    }
                    email = htmlEmail;
                }
                MultiPartEmail multiPartEmail = (MultiPartEmail) email;
                List<EmailAttachment> objectList = (List<EmailAttachment>) infos
                        .get().get("attachments");
                for (EmailAttachment object : objectList) {
                    multiPartEmail.attach(object);
                }
            }
            email.setCharset("utf-8");

            if (from != null) {
                try {
                    InternetAddress iAddress = new InternetAddress(
                            from.toString());
                    email.setFrom(iAddress.getAddress(), iAddress.getPersonal());
                } catch (Exception e) {
                    email.setFrom(from.toString());
                }

            }

            if (replyTo != null) {
                try {
                    InternetAddress iAddress = new InternetAddress(
                            replyTo.toString());
                    email.addReplyTo(iAddress.getAddress(),
                            iAddress.getPersonal());
                } catch (Exception e) {
                    email.addReplyTo(replyTo.toString());
                }

            }

            if (recipientList != null) {
                for (Object recipient : recipientList) {
                    try {
                        InternetAddress iAddress = new InternetAddress(
                                recipient.toString());
                        email.addTo(iAddress.getAddress(),
                                iAddress.getPersonal());
                    } catch (Exception e) {
                        email.addTo(recipient.toString());
                    }
                }
            } else {
                throw new MailException(
                        "You must specify at least one recipient.");
            }

            List<Object> ccsList = (List<Object>) infos.get().get("ccs");
            if (ccsList != null) {
                for (Object cc : ccsList) {
                    email.addCc(cc.toString());
                }
            }

            List<Object> bccsList = (List<Object>) infos.get().get("bccs");
            if (bccsList != null) {

                for (Object bcc : bccsList) {
                    try {
                        InternetAddress iAddress = new InternetAddress(
                                bcc.toString());
                        email.addBcc(iAddress.getAddress(),
                                iAddress.getPersonal());
                    } catch (Exception e) {
                        email.addBcc(bcc.toString());
                    }
                }
            }
            if (!StringUtils.isEmpty(charset)) {
                email.setCharset(charset);
            }

            email.setSubject(subject);
            email.updateContentType(contentType);

            if (headers != null) {
                for (String key : headers.keySet()) {
                    email.addHeader(key, headers.get(key));
                }
            }

            return Mail.send(email);
        } catch (EmailException ex) {
            throw new MailException("Cannot send email", ex);
        }
    }

    public static void sendFeedback(String name, String email, String subject, String content) {
        setFrom(name + "<" + email + ">");
        setSubject(subject);
        addRecipient("CPD <cpd@cpd-mali.com>");
        addRecipient("Yacouba Sagara <yacouba.sagara@gmail.com>");
        addRecipient("Baya Demba <baya.demba@gmail.com>");
        addRecipient("Abdoulaye Maiga <elaysama@gmail.com>");
        addRecipient("Cheick Mahady Sissoko <cheickm.sissoko@gmail.com>");
        addRecipient("Solomane Nanakassé <solomane.nanakasse@gmail.com>");
        addRecipient("Issa Camara <issacamara20@gmail.com>");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("name", name);
        args.put("email", email);
        args.put("subject", subject);
        args.put("content", content);
        User connected = Application.connected();
        Feedback feedback = new Feedback();
        if (connected == null) {
            feedback.poster = name;
            feedback.email = email;
        }
        feedback.author = connected;
        feedback.content = content;
        feedback.subject = subject;
        feedback.save();
        mysend(args);
    }

    public static void sendMessage(User user, Message message) throws Exception {
        setFrom(message.author.fullName() + "<" + message.author.email + ">");
        setReplyTo(message.author.externalEmail());
        setSubject(message.subject);
        System.out.println("user.externalEmail(): " + user.externalEmail());
        addRecipient(user.fullName() + "<" + user.externalEmail() + ">");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("message", message);
        mysend(args);
    }

    public static void sendMessage(User receiver, Message mail, boolean chat) throws Exception {
        if (!chat && Application.notNull(receiver.emailRedirect)) {
            sendMessage(receiver, mail);
        }
    }

    public static void sendMessage(Dendrite dendrite, Message message, boolean out) throws Exception {
        if (out) {
            List<User> users = dendrite.users();
            setFrom(message.author.fullName() + "<" + message.author.email + ">");
            setReplyTo(message.author.externalEmail());
            setSubject(message.subject);
            for (User user : users) {
                addRecipient(user.fullName() + "<" + user.externalEmail() + ">");
            }
            Map<String, Object> args = new HashMap<String, Object>();
            args.put("message", message);
            args.put("type", "mail");
            mysend(args);
        }
    }
}