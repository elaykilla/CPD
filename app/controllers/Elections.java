/**
 *
 */
package controllers;

import static controllers.Application.connected;
import static controllers.Application.index;
import static controllers.Cellule.listElection;
import java.util.List;
import models.Dendrite;
import models.RightEnum;
import models.User;

import models.elections.Candidature;
import models.elections.Election;
import models.elections.Poste;
import models.elections.Vote;

import play.data.validation.*;
import play.i18n.Messages;
import play.mvc.Before;
import play.mvc.Http;

/**
 *
 * @author Sissoko && Elay
 * @date 18 mai 2013 14:59:00
 */
public class Elections extends Application {

    @Before
    public static void setup() {
        User user = connected();
        if (user == null) {
            redirection();
            return;
        }
        if (!user.active) {
            String message = "La page de la cellule";
            render("Application/desactive.html", message, user);
            return;
        }
        if (!user.boursier) {
            //flash.error("Désolé %s, cette page est réservée qu'aux boursiers veuillez contacter un administrateur cpd@cpd-mali.com", user.fullName());
            flash.error("Dans peu de temps cette page ne sera accessible que par des boursiers. Si vous êtes, envoyez un mail à cpd@cpd-mali.com");
            //redirection();
        }

    }

    public static void showElection(long electionId) {
        Election election = Election.findById(electionId);

        if (election == null) {
            flash.error(Messages.get("CorrectErrors"));
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        } else {

            if (election.dendrite.general) {
                Cellule.showElection(election.id);
            } else {
            }
            List<Poste> postes = election.postes();
            List<Dendrite> dendrites = Dendrite.findDendrites("name asc");
            User connected = connected();
            //Candidature candidature = election.candidatureByPosteForUser(connected, null)
            //Cellule.listElection(1);
            render("Dendrites/showElection.html", connected, election, postes, dendrites);
        }

        //Election election = Election.findById(electionId);

        //TO BE MODIFIED DEPENDING ON HOW WE SEPARATE BUREAU FROM THE REST
//        if(election.dendrite.general){
//            render("Cellule/showElection.html", election);
//        }
//        
//        else{
//            
//        }    

    }

    public static void editElection(Long electionId, Long dendriteId) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.login();
            return;
        }
        Election election = Election.findById(electionId);
        List<Dendrite> dendrites = Dendrite.findDendrites("named asc");
        if (election == null) {
            flash.error("Election introuvable");
            render("Dendrites/editElection.html", election, dendriteId, dendrites);
            return;
        }

        if (!connected.canEdit(election)) {
            flash.error("Désolé %s, Vous n'avez pas le droit de modifier ou de créer une élection veuillez contacter un administrateur", connected.fullName());
            listElection();
            return;
        }
        if (dendriteId == null) {
            dendriteId = election.dendrite.id;
        }
//        long dendriteId = election.dendrite.id;

//        if (dendrite.general) {
//            render("Cellule/editElection.html", election, dendriteId, dendrites,dendrite);
//        } else {
        render("Dendrites/editElection.html", election, dendriteId, dendrites);
//        }

    }

    /**
     *
     * @param election
     * @param dendriteId
     */
    public static void createElection(@Valid Election election, @Required Long dendriteId) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veuillez vous connecter pour continuer");
            Users.login();
            return;
        }
        if (election == null) {
            flash.error("Election introuvable");
            Dendrites.dendrites();
            return;
        }
        validation.required(dendriteId).message("La dendrite est réquise");
        Dendrite dendrite = election.dendrite;
        if (dendrite == null) {
            dendrite = Dendrite.findById(dendriteId);
            if (dendrite == null) {
                flash.error("Pas de dendrite envoyé!!!");
                render("Dendrites/editElection.html", election, dendriteId);
                return;
            }
            election.dendrite = dendrite;
        }
        election.organiser = connected;
        if (Validation.hasErrors()) {

            flash.error(Messages.get("CorrectErrors"));
            render("Dendrites/editElection.html", election, dendriteId);
            return;
        }
        if (!connected.canEdit(election)) {
            flash.error("Désolé %s, Vous n'avez pas le droit de modifier ou de créer une élection veuillez contacter un administrateur", connected.fullName());
            listElection();
            return;
        }
        election.save();
        flash.success("Election bien Crée");
        List<Poste> postes = election.postes();
        election.save();

        if (dendrite.general) {
            render("cellule/showElection.html", election, connected, postes, dendrite);
        } else {
            render("dendrites/showElection.html", election, connected, postes, dendrite);
        }
    }

//    public static void createCandidature(@Valid Candidature candidature){
//        
//    }
    public static void createCandidature(String presentation, long candidateId, long electionId, long posteId) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veillez vous connecter s'il vous plait.");
            Users.login();
            return;
        }
        if (connected.dendrite == null) {
            flash.error("Vous n'êtes pas affecté à une dendrite vous ne pouvez donc pas candidater");
            index();
            return;
        }
        Election election = Election.findById(electionId);
        if (election == null) {
            flash.error("L'élection est introuvable.");
            index();
            return;
        }
        if (!election.dendrite.general && !connected.dendrite.equals(election.dendrite)) {
            flash.error("Vous n'avez pas le droit de candidater dans cette dendrite");
            Http.Header referer = request.headers.get("referer");
            if (referer == null) {
                index();
            } else {
                redirect(referer.value());
            }
            return;
        }

        Candidature candidature = new Candidature();

        Poste poste = Poste.findById(posteId);
        User candidate = User.findById(candidateId);


        candidature.election = election;
        candidature.poste = poste;
        candidature.candidate = candidate;


        candidature = candidature.findOrCreate();

        candidature.presentation = presentation;
        candidature.save();


        if (election.dendrite.general) {
            flash.success("Merci %s de votre participation votre candidature a bien été prise en compte", candidate.fullName());
            Cellule.showElection(electionId);
        } else {
            flash.success("Merci %s de votre participation votre candidature a bien été prise en compte", candidate.fullName());
            showElection(electionId);
        }
    }

    /**
     * This function creates a vote for a given User and a given Candidature
     *
     * @param electorId
     * @param candidatureId
     */
    public static void createVote(long electorId, long candidatureId) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veillez vous connecter s'il vous plait.");
            Users.login();
            return;
        }
        User elector = User.findById(electorId);
        if (elector == null) {
            flash.error(Messages.get("CorrectErrors"));
            return;
        }

        Candidature candidature = Candidature.findById(candidatureId);

        if (candidature == null) {
            flash.error(Messages.get("CorrectErrors"));
            return;
        }

        Election election = candidature.election;
        Poste poste = candidature.poste;

        //On n'a pas le droit de voter dans une autre dendrite que la nôtre
        if (!election.dendrite.general && !election.dendrite.equals(connected.dendrite)) {
            flash.error("Vous ne pouvez pas réaliser de vote dans cette dendrite car vous n'y êtes pas affecté.");
            showElection(election.id);
            return;
        }

        //Quand l'élection n'a pas commentcé ou bien si elle est passée.
        if (!election.status().equals("voting")) {
            flash.error("Vous ne pouvez pas réaliser de vote en ce moment.");
            showElection(election.id);
            return;
        }

        //On ne peut voter qu'une seule fois pour un poste et pour une élection
        if (connected.voted(election, poste)) {
            flash.error("Vous avez déjà voté pour ce poste.");
            showElection(election.id);
            return;
        }

        Vote vote = new Vote();
        vote.candidature = candidature;
        vote.elector = elector;

        vote.save();

        List<Poste> postes = election.postes();

        //When it's the Cellule
        flash.success("Merci %s de votre participation votre vote a bien été pris en compte", elector.fullName());
        if (election.dendrite.general) {
            render("cellule/showElection.html", election, postes, connected);
        } //For all other dendrites
        else {
            render("dendrites/showElection.html", election, postes, connected);
        }


    }

    /**
     * This displays the results for a given election
     *
     * @param electionId
     */
    public static void resultsElection(long electionId) {

        Election election = Election.findById(electionId);

        if (election == null) {
            flash.error(Messages.get("CorrectErrors"));
        } else {
            if (election.dendrite.general) {
                Cellule.resultsElection(electionId);
            } else {
                Dendrites.resultsElection(electionId);
            }

        }
    }

    /**
     *
     *
     * @param electionId
     */
    public static void removeElection(long electionId) {
        User connected = connected();
        if (connected == null) {
            flash.error("Veillez vous connecter s'il vous plait.");
            Users.login();
            return;
        }
        Election election = Election.findById(electionId);
        if (!connected.contains(RightEnum.DELETE_ELECTION.name())) {
            if (!connected.contains(RightEnum.DELETE_MY_ELECTION.name())) {
                flash.error("Vous n'avez pas le droit de supprimer cette élection");
                index();
                return;
            } else if (!connected.equals(election.organiser)) {
                flash.error("Vous n'avez pas le droit de supprimer cette élection");
                index();
                return;
            }
        }
        if (election == null) {
        } else {
            long dendriteId = election.dendrite.id;
            election.delete();
            if (dendriteId == 1) {
                render("Cellule/bureauElection.html");
            }
        }
    }

    /**
     * failed
     *
     * @param candidatureId
     */
    public static void removeCandidature(long candidatureId) {
        System.out.println("Deleted candidature!!");
        Candidature candidature = Candidature.findById(candidatureId);

        if (candidature == null) {
            System.out.println("Ooopsss no candidature");
            flash.error(Messages.get("CorrectErrors"));
            index();
        } else {
            Election election = candidature.election;
            List<Poste> postes = election.postes();
            User connected = connected();
            candidature.delete();

            if (election.dendrite.general) {
                render("Cellule/showElection.html", election, postes, connected);
            } else {
                render("dendrites/showElection.html", election, postes, connected);
            }

        }
    }

    public static void userElections() {
        User connected = connected();
        List<Election> elections = connected.elections();

        Election last = null;
        if (!elections.isEmpty()) {
            last = elections.get(0);
            elections.remove(0);
        }

        render("Dendrites/userElections.html", last, elections);
    }
}
