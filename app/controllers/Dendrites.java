/**
 *
 */
package controllers;

import static controllers.Application.RECENTS_MAX_SIZE;
import static controllers.Application.connected;
import static controllers.Application.index;
import static controllers.Application.notconnected;
import static controllers.Articles.recents;
import static controllers.Cellule.dendriteId;
import static controllers.Cellule.listElection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Dendrite;
import models.RightEnum;
import models.Searchable;
import models.Standard;
import models.User;
import models.articles.Announce;
import models.articles.AnnounceComment;
import models.elections.Candidature;
import models.elections.Election;
import models.elections.Mandate;
import models.elections.Poste;
import models.events.Event;
import models.events.EventComment;
import models.events.EventInvitation;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Before;
import play.mvc.Http;

/**
 * @author Sissoko && Elay
 * @date 2 mai 2013 12:45:48
 */
public class Dendrites extends Application {

    @Before
    public static void setup() {
        User user = connected();
        if (user == null) {
            flash.error("Vous devez vous connecter pour voir la page des dentrites");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (!user.active) {
            String message = "La page des dendrites";
            render("Application/desactive.html", message, user);
        }
    }

    /**
     *
     */
    public static void dendrites() {
        List<Dendrite> dendrites = Dendrite.findDendrites("name asc");
        render(dendrites);
    }

    public static void announce(Long dendriteId, Long id, String title) {
        Announce announce = Announce.findById(id);
        if (dendriteId == null) {
            dendriteId = Cellule.dendriteId();
        }
        if (announce != null) {
            Dendrite dendrite = Dendrite.findById(dendriteId);
            if (dendrite == null) {
                dendrite = announce.dendrite;
            }
            if (dendrite != null && dendrite.general) {
                Announces.announce(id, title);
            } else {
                List<AnnounceComment> comments;
                Announce article = announce;
                int maxSize;
                int length = 5;
                int commentPage = 1;
                comments = AnnounceComment.find("announce_id = ? order by created desc", id).fetch();
                maxSize = comments.size();
                comments = comments.subList(0, length <= maxSize ? length : maxSize);
                int commentSize = comments.size();
                String type = "announce";
                announce.incermentViewed();
                render(announce, dendrite, comments, maxSize, commentSize, article, type, commentPage);

            }
        } else {
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        }
    }

    /**
     * This function displays
     *
     * @param id
     */
    public static void dendrite(@Required Long id) {
        validation.valid(id);
        if (Validation.hasErrors()) {
            flash.error("L'id de la dendrite est obligatoire.");
            index();
        }
        List<Dendrite> dendrites = Dendrite.findDendrites("name asc");
        Dendrite dendrite = Dendrite.findById(id);
        List<Searchable> recents = recents(6);
        render(dendrite, dendrites, recents);
    }

    /**
     *
     * @param id
     */
    public static void announces(@Required Long id) {
        Dendrite dendrite = Dendrite.findById(id);
        List<Announce> announces = dendrite.announces();
        String ext = "Announces";
        //List<Article> recents = Article.recents(5);
        render(announces, dendrite, ext);
    }

    /**
     *
     * @param id
     */
    public static void events(@Required Long id) {
        if (id == null) {
            id = Cellule.dendriteId();
        }
        Dendrite dendrite = Dendrite.findById(id);
        List<Event> events = dendrite.events;
        Event frontEvent = null;
        List<Event> olderEvents = new ArrayList<Event>();
        if (null != events && !events.isEmpty()) {
            frontEvent = events.get(events.size() - 1);
            olderEvents = events.subList(0, events.size() - 1);
        }
        String ext = "Events";
        //List<Article> recents = Article.recents(5);
        if (dendrite.general) {
            render("Events/events.html", frontEvent, olderEvents, dendrite, ext);
        } else {
            render(frontEvent, olderEvents, dendrite, ext);
        }
    }

    /**
     *
     * @param id
     */
    public static void event(Long dendriteId, @Required Long id, String title, int page) {
        if (dendriteId == null) {
            dendriteId = Cellule.dendriteId();
        }
        Event event = Event.findById(id);
        if (event != null) {
            Dendrite dendrite = Dendrite.findById(dendriteId);
            if (dendrite == null) {
                dendrite = event.dendrite;
            }
            if (dendrite != null && dendrite.general) {
                Events.event(id, title, 1);
            } else {
                long size = event.eventInvitations.size();
                int nbPagesMax = 5;
                int nbPages = (int) (size / nbPagesMax);
                if (nbPagesMax * nbPages < size) {
                    nbPages = nbPages + 1;
                }
                List<EventInvitation> invites = Events.inviteByPage(page, nbPagesMax, event.eventInvitations);
                //List<Event> recents = Event.recents(5);
                //render(event, size, page, invites, nbPagesMax, id, dendrite, nbPages);
                List<EventComment> comments;
                Event article = event;
                int maxSize;
                int length = 5;
                int commentPage = 1;
                String type = "event";
                comments = EventComment.find("event_id = ? order by created desc", id).fetch();
                maxSize = comments.size();
                comments = comments.subList(0, length <= maxSize ? length : maxSize);
                int commentSize = comments.size();
                event.incermentViewed();
                render(event, size, page, invites, nbPagesMax, id, dendrite, nbPages, comments, maxSize, commentSize, type, article, commentPage);
            }
        } else {
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        }
    }

    /**
     * This function displays all the elections for the given dendrite
     */
    public static void elections(@Required Long id) {
        List<Dendrite> dendrites = Dendrite.findDendrites("name asc");

        if (notconnected()) {
            flash.error("Connectez vous pour continuer");
            Users.login();
            return;
        }

        Dendrite dendrite = Dendrite.findById(id);
        if (dendrite == null) {
            flash.error("la dendrite en question n'existe plus!! Veuillez contacter un Administrateur");
            dendrites();
            return;
        }

        List<Election> elections = dendrite.elections();

        Election last = null;
        if (!elections.isEmpty()) {
            last = elections.get(0);
            elections.remove(0);
        }
        User connected = connected();
        long dendriteId = dendrite.id;
        String ext = "Elections";
        //List<Article> recents = Article.recents(5);
        render(connected, last, elections, dendrite, dendriteId, dendrites, ext);

    }

    /**
     *
     * @param dendriteId
     * @param electionId
     */
    public static void editElection(Long dendriteId, Long electionId) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.login();
            return;
        }
        if (dendriteId == null) {
            dendriteId = Cellule.dendriteId();
        }
        Dendrite dendrite = Dendrite.findById(dendriteId);
        if (dendrite == null) {
            flash.error("Impossible de créer une élection sans dendrite");
            dendrites();
            return;
        }
        Election election = new Election();
        if (electionId != null) {
            election = Election.findById(electionId);
        }
        if (election == null) {
            election = new Election();
            election.dendrite = dendrite;
        }
        if (!connected.contains(RightEnum.EDIT_ELECTION.name())) {
            if (connected.dendrite == null) {
                flash.error("Vous ne pouvez pas continuer cette action car vous n'êtes pas affecté à une dendrite.");
                return;
            }
            if (election.dendrite.id == connected.dendrite.id && !connected.contains(RightEnum.EDIT_MY_ELECTION.name())) {
                flash.error("Désolé %s, Vous n'avez pas le droit de modifier ou de créer une élection veuillez contacter un administrateur", connected.fullName());
                listElection();
                return;
            }
        }
        String ext = "edition d'elections";
        render(dendriteId, election, dendrite, ext);
    }

    public static void showElection(Long dendriteId, Long electionId) {
        List<Dendrite> dendrites = Dendrite.findDendrites("named asc");
        Election election = Election.findById(electionId);
        if (dendriteId == null) {
            dendriteId = Cellule.dendriteId();
        }
        Dendrite dendrite = Dendrite.findById(dendriteId);
        if (election == null) {
            flash.error("Désolé l'Election en question n'existe plus. Veuillez informer l'Administrateur de la Dendrite ");
        } else {
            List<Poste> postes = election.postes();
            render(election, postes, dendrites, dendrite, dendriteId);
        }

    }

    /**
     *
     * @param id
     */
    public static void contact(Long id) {
        List<Dendrite> dendrites = Dendrite.findDendrites("name asc");
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.login();
            return;
        }
        render(dendrites);
    }

    public static void resultsElection(long electionId) {
        List<Dendrite> dendrites = Dendrite.findDendrites("named asc");

        Election election = Election.findById(electionId);
        if (election == null) {
            flash.error("Désolé l'Election en question n'existe plus. Veuillez informer l'Administrateur de la Dendrite " + election.dendrite.code);
        } else {
            List<Poste> postes = election.postes();
            HashMap<Poste, Candidature> leaders = election.currentLeaders();
            render(election, postes, leaders, dendrites);
        }
    }

    /**
     *
     * @param id
     */
    public static void users(Long id, int page) {
        if (id == null) {
            id = Cellule.dendriteId();
        }
        Dendrite dendrite = Dendrite.findById(id);
        if (dendrite == null) {
            flash.error("Dendrite non trouvée!");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        List<User> all = dendrite.users();
        long size = all.size();
        int nbPagesMax = 7;
        int nbPages = (int) (size / nbPagesMax);
        if (nbPagesMax * nbPages < size) {
            nbPages = nbPages + 1;
        }

        if (page == 0) {
            page = 1;
        }
        List<User> users = (List<User>) listByPage(page, nbPagesMax, all);
        List<Searchable> recents = Articles.recents(6);
        render(users, size, page, nbPagesMax, nbPages, dendrite, id, recents);
    }

    /**
     * This displays the current members of the executive office
     *
     * @param dendriteId
     */
    public static void leBureau(long dendriteId) {
        Dendrite dendrite = Dendrite.findById(dendriteId);
        if (dendrite == null) {
            flash.error("Dendrite non trouvée!");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        String ext = "Le Bureau";
        List<Searchable> recents = Articles.recents(6);
        List<Mandate> currentMandates = Mandate.dendriteCurrentMandates(dendriteId);
        render(dendrite, ext, recents, currentMandates);
    }
}
