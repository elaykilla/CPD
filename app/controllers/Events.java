/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.Application.RECENTS_MAX_SIZE;
import static controllers.Application.connected;
import static controllers.Application.index;
import static controllers.Articles.comments;
import static controllers.Articles.recents;
import static controllers.Cellule.listElection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Dendrite;
import models.RightEnum;
import models.Searchable;
import models.User;
import models.articles.Announce;
import models.articles.AnnounceSubscription;
import models.articles.ArticleComment;
import models.articles.Comment;
import models.cvs.Adresse;
import models.events.Event;
import models.events.EventComment;
import models.events.EventInvitation;
import models.events.EventInvitationId;
import models.events.EventSubscription;
import models.events.InvitationStateEnum;
import notifiers.Mails;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.db.jpa.GenericModel;
import play.i18n.Messages;
import play.mvc.Before;
import play.mvc.Http;

/**
 *
 * @author Nanakassé
 */
public class Events extends Application {

    @Before
    public static void setup() {
        User user = connected();
        if (user != null && !user.active) {
            String message = "La page des évènements";
            render("Application/desactive.html", message, user);
        }
    }

    public static void events(Long dendriteId, int page) {
        List<Event> events;
        Dendrite dendrite = null;
        if (dendriteId == null) {
            events = Event.find("order by modified desc").fetch();
        } else {
            dendrite = Dendrite.findById(dendriteId);
            events = dendrite.events;
        }
        List<Event> others = Event.find("dendrite is null").fetch();
        events.addAll(others);
        Event frontEvent = null;
        List<Event> olderEvents = new ArrayList<Event>();
        if (!events.isEmpty()) {
            frontEvent = events.get(events.size() - 1);
            olderEvents = events.subList(0, events.size() - 1);
        }
        List<Searchable> recents = recents(RECENTS_MAX_SIZE);
        String ext = "Events";
//        if (dendrite != null && dendrite.general) {
            render(frontEvent, olderEvents, dendrite, ext,recents);
//        } else {
//            render("Dendrites/events.html", frontEvent, olderEvents, dendrite);
//        }
    }

    public static void comingsoon() {
        render();
    }

    public static void event(Long id, String title, int page) {
        Event event = Event.findById(id);
        Event article = event;
        if (event == null) {
            flash.error("Event not found");
            index();
            return;
        }
        Dendrite dendrite = event.dendrite;
        long size = event.eventInvitations.size();
        int nbPagesMax = 5;
        int nbPages = (int) (size / nbPagesMax);
        if (nbPagesMax * nbPages < size) {
            nbPages = nbPages + 1;
        }
        List<EventInvitation> invites = inviteByPage(page, nbPagesMax, event.eventInvitations);
        //List<Searchable> recents = recents(RECENTS_MAX_SIZE);
        List<EventComment> comments;
        int maxSize;
        int length = 5;
        int commentPage = 1;
        String type = "event";
        GenericModel.JPAQuery query = EventComment.find("event_id = ? order by created asc", id);
        comments = query.fetch();
        maxSize = comments.size();
        length = (length <= maxSize ? length : maxSize);
        int start = maxSize - length;
        comments = query.from(start).fetch();
        int commentSize = comments.size();
        event.incermentViewed();
        render(event, size, page, invites, nbPagesMax, id, dendrite, nbPages, comments, maxSize, commentSize, type, article, commentPage);

    }

    public static List<EventInvitation> inviteByPage(int page, int length, List<EventInvitation> invites) {
        if (page < 1) {
            page = 1;
        }
        List<EventInvitation> results = new ArrayList<EventInvitation>();
        int start = (page - 1) * length;
        int end = start + length;
        for (int i = start; i < end && i < invites.size(); i++) {
            results.add(invites.get(i));
        }
        return results;
    }

    public static void postComment(Long id, @Required String content) {
        User connected = connected();
        if (connected == null) {
            renderJSON(new String[]{"error", "Veuillez vous connecter pour continuer"});
            return;
        }
        Event event = Event.findById(id);
        if (Validation.hasErrors()) {
            renderJSON(new String[]{"error", "Le commentaire est obligatoire."});
            return;
        }
        Comment comment = new Comment();
        comment.author = connected;
        comment.content = content.replaceAll("\n", "<br/>");
        event.addComment(comment);
        for (EventSubscription as : event.subscriptions) {
            try {
                if (!connected.equals(as.user)) {
                    Mails.notificationComment(as);
                    as.incrementNotify();
                }
            } catch (Exception ex) {
                Logger.getLogger(Announces.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        String ass = "event";
//        render("Application/comment.html", event, ass, comment);
        Articles.comments(id, "event", 1);
    }

    public static void editEvent(Long dendriteId, Long eventId) {
        //  List<Dendrite> dendrites = Dendrite.findAll("code desc");
        //===============Begin rightifing==================\\
        if (dendriteId == null) {
            dendriteId = Cellule.dendriteId();
        }
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.login();
            return;
        }
        Event event = new Event();
        Dendrite dendrite = Dendrite.findById(dendriteId);
        event.dendrite = dendrite;
        event.author = connected;
        if (eventId != null) {
            event = Event.findById(eventId);
        }

        if (dendrite == null) {
            flash.error("Pas de dendrite envoyé!!!");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (!connected.canEdit(event)) {
            flash.error("Désolé %s, Vous n'avez pas le droit de modifier ou de créer un évènement dans cette dendrite contacter un administrateur", connected.fullName());
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        //===============End rightifing==================\\
        //List<Dendrite> dendrites = Dendrite.findAll("named asc");
        render(dendrite, event);
    }

    public static void updateEvent(Event event, Long dendriteId) {
        //===============Begin rightifing==================\\
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.login();
            return;
        }
        if (dendriteId == null) {
            dendriteId = Cellule.dendriteId();
        }
        if (event == null) {
            flash.error("evenement introuvable");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        Dendrite dendrite = Dendrite.findById(dendriteId);

        if (dendrite == null) {
            flash.error("Aucune dendrite trouvée");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (!connected.canEdit(event)) {
            flash.error("Désolé %s, Vous n'avez pas le droit de modifier ou de créer un évènement dans cette dendrite contacter un administrateur", connected.fullName());
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        //===============End rightifing==================\\

        event.dendrite = dendrite;
        if (event.id == null) {
            event.author = connected;
        }

        if (Validation.hasErrors()) {
            flash.error("Veuillez corriger les erreurs");
            render("@editEvent", dendrite, event);
            return;
        }
        event.save();
        flash.success("Evenement bien Crée");
        event(event.id, event.title, 1);
    }

    public static void postEvent(@Valid Event event, Long dendriteId, @Valid Adresse adresse, @Required String beginning) {
        //===============Begin rightifing==================\\
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.acelogin();
            return;
        }
        Dendrite dendrite = Dendrite.findById(dendriteId);

        if (dendrite == null) {
            flash.error("Pas de dendrite envoyé!!!");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (event.id == null) {
            event.author = connected();
            event.dendrite = dendrite;
        }

        if (!connected.canEdit(event)) {
            flash.error("Désolé %s, Vous n'avez pas le droit de modifier ou de créer un évènement dans cette dendrite contacter un administrateur", connected.fullName());
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        //===============End rightifing==================\\
        if (beginning != null) {
            String[] tab = beginning.split(" ");
            String[] td = tab[0].split("-");
            String[] th = tab[1].split(":");
            Date begin = new Date(Integer.parseInt(td[0]) - 1900, Integer.parseInt(td[1]) - 1,
                    Integer.parseInt(td[2]), Integer.parseInt(th[0]),
                    Integer.parseInt(th[1]), Integer.parseInt(th[2]));
            event.beginning = begin;
        }
        if (Validation.hasErrors()) {
            flash.error("Veuillez corriger les erreurs pour continuer");
            event.adresse = adresse;
            render("@editEvent", event, dendrite);
            return;
        }
        adresse.save();
        event.adresse = adresse;
        event.save();
        event.subscribe(connected);
        for (User user : event.dendrite.users) {
            try {
                Mails.notificationNew(user, event);
            } catch (Exception ex) {
                Logger.getLogger(Announces.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        flash.success("Thanks for posting %s", event.author.fullName());
        event(event.id, event.title, 1);
    }

    public static void AddGuest(long dendriteId, long eventId) {
        Dendrite dendrite = Dendrite.findById(dendriteId);
        Event event = Event.findById(eventId);
        if (Validation.hasErrors()) {
            render("Events/event.html", event);
            return;
        }
        User u = connected();
        if (u == null) {
            Users.login();
            return;
        }
        flash.success("Thanks for adding guest %s", u.fullName());
    }

    public static void participer(Long id) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.acelogin();
            return;
        }
        EventInvitationId eiid = new EventInvitationId(connected.id, id);
        EventInvitation ei = EventInvitation.findById(eiid);
        if (ei == null) {
            ei = new EventInvitation(connected.id, id);
        }
        ei.state = InvitationStateEnum.GOING;
        ei.save();
    }

    public static void departiciper(Long id) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.acelogin();
            return;
        }
        EventInvitationId eiid = new EventInvitationId(connected.id, id);
        EventInvitation ei = EventInvitation.findById(eiid);
        if (ei != null) {
            ei.delete();
        }
    }

    /**
     *
     * @param id
     */
    public static void deleteEvent(Long id) {
//===============Begin rightifing==================\\
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.login();
            return;
        }
        Event event = Event.findById(id);
        if (event == null) {
            flash.error("Event introuvable");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (!connected.contains(RightEnum.DELETE_EVENT.name())) {
            if (event.dendrite.id == connected.dendrite.id && !connected.contains(RightEnum.DELETE_MY_EVENT.name())) {
                flash.error("Désolé %s, Vous n'avez pas le droit de supprimer un évènement veuillez contacter un administrateur", connected.fullName());
                Http.Header referer = request.headers.get("referer");
                if (referer != null) {
                    redirect(referer.value());
                } else {
                    index();
                }
                return;
            }
        }
        //===============End rightifing==================\\

        event.delete();
        Long dendriteId = event.dendrite == null ? Cellule.dendriteId() : event.dendrite.id;
        flash.success("Evènement supprimé avec succès");
        events(dendriteId, 1);
    }

    /**
     *
     * @param id
     * @param event_id
     */
    public static void deleteComment(Long commentId, Long id) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.login();
            return;
        }

        Event event = Event.findById(id);
        if (event == null) {
            flash.error("Event not found");
            if (connected.dendrite != null) {
                events(connected.dendrite.id, 1);
            } else {
                index();
            }
            return;
        }
        Comment c = Comment.findById(commentId);
        if (c == null) {
            flash.error("Comment not found");
            if (connected.dendrite != null) {
                events(connected.dendrite.id, 1);
            } else {
                index();
            }
            return;
        }
        if (!connected.isMine(c)) {
            flash.error("Vous ne pouvez pas supprimer ce commentaire.");
            events(event.dendrite.id, 1);
            return;
        }
        event.removeComment(c);
        comments(id, "event", 1);
    }

    /**
     *
     */
    public static void eventsByUser(Long id) {
        render();
    }

    /**
     *
     * @param id
     */
    public static void subscribe(Long id) {
        User connected = connected();
        if (connected == null) {
            renderJSON(new String[]{"Error", "Il faut se connecter pour s'abonner"});
            return;
        }
        Event event = Event.findById(id);
        if (event == null) {
            renderJSON(new String[]{"Error", "L'évènement est introuvable"});
            return;
        }
        event.subscribe(connected);
        renderJSON(new String[]{"Success", "Vous êtes abonné(e)"});
    }

    /**
     *
     * @param id
     */
    public static void unsubscribe(Long id) {
        User connected = connected();
        if (connected == null) {
            renderJSON(new String[]{"Error", "Il faut se connecter pour s'abonner"});
            return;
        }
        Event event = Event.findById(id);
        if (event == null) {
            renderJSON(new String[]{"Error", "L'annonce est introuvable"});
            return;
        }
        event.unsubscribe(connected);
        renderJSON(new String[]{"Success", "Vous êtes désabonné(e)"});
    }
}
