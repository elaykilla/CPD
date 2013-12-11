package controllers;

import static controllers.Application.connected;
import static controllers.Application.redirection;
import java.util.HashMap;
import java.util.List;
import models.Dendrite;
import models.RightEnum;
import models.User;
import models.elections.Election;
import models.elections.Poste;

import models.elections.Candidature;
import models.elections.Mandate;
import play.mvc.Before;

/**
 * 
 * @author Elay && Sissoko
 * @date
 */
public class Cellule extends Application {

	@Before
	public static void setup() {
		User user = connected();
		String req = request.path;
		if (user == null && !req.contains("payment") && !req.contains("bureauWelcome")) {
			redirection();
			return;
		}
		if (user != null && !user.active) {
			String message = "La page de la cellule";
			render("Application/desactive.html", message, user);
			return;
		}
		if (user != null && !user.boursier) {
			flash.error(
					"%s, cette page sera réservée qu'aux boursiers dans le futur si vous êtes un boursier"
							+ " veuillez contacter un administrateur mailto:admin@cpd-mali.com",
					user.fullName());
		}

	}

	/**
     *
     */
	public static void bureauWelcome() {
		// List<Dendrite> dendrites = Dendrite.findAll("named desc");
		Dendrite laCellule = laCellule();
		render("Cellule/bureauWelcome.html", laCellule);
	}

	public static void bureauElection() {
		List<Dendrite> dendrites = Dendrite.findDendrites("named asc");
		Dendrite laCellule = laCellule();
		render(dendrites, laCellule);
	}

	public static void listElection() {
		List<Dendrite> dendrites = Dendrite.findDendrites("named asc");
		Dendrite laCellule = laCellule();
		List<Election> elections = Dendrite.electionsCellule();

		Election last = null;
		if (!elections.isEmpty()) {
			last = elections.get(0);
			elections.remove(0);
		}

		render("Cellule/bureauElection.html", last, elections, dendrites,
				laCellule);
	}

	public static void editElection(long electionId) {
		Dendrite laCellule = laCellule();
		User connected = connected();
		if (connected == null) {
			flash.error("Veuillez vous connecter pour continuer");
			Users.login();
			return;
		}
		// if (!connected.contains(RightEnum.EDIT_ELECTION.name())) {
		// flash.error("Désolé %s, Vous n'avez pas le droit de modifier ou de créer une élection veuillez contacter un administrateur",
		// connected.fullName());
		// listElection();
		// return;
		// }
		List<Dendrite> dendrites = Dendrite.findDendrites("named asc");

		long dendriteId = dendriteId();
		Election election = Election.findById(electionId);
		render(election, dendriteId, dendrites, laCellule);
	}

	public static void showElection(long electionId) {
		List<Dendrite> dendrites = Dendrite.findDendrites("named asc");
		Dendrite laCellule = laCellule();
		Election election = Election.findById(electionId);
		// Candidature candidature = Candidature.findById(candidatureId);
		if (election == null) {
			flash.error("Désolé l'Election en question n'existe plus. Veuillez informer l'Administrateur de la Dendrite ");
		} else {
			List<Poste> postes = election.postes();

			render(election, postes, dendrites, laCellule);
		}

	}

	public static Dendrite laCellule() {
		Dendrite laCellule = Dendrite.find("general", true).first();
		return laCellule;
	}

	public static Long dendriteId() {
		Dendrite bureau = Dendrite.find("general", true).first();
		return bureau.id;
	}

	public static void resultsElection(long electionId) {
		List<Dendrite> dendrites = Dendrite.findDendrites("named asc");
		Dendrite laCellule = laCellule();
		Election election = Election.findById(electionId);
		if (election == null) {
			flash.error("Désolé l'Election en question n'existe plus. Veuillez informer l'Administrateur de la Dendrite "
					+ election.dendrite.code);
		} else {
			List<Poste> postes = election.postes();
			HashMap<Poste, Candidature> leaders = election.currentLeaders();
			render(election, postes, leaders, dendrites, laCellule);
		}
	}

	public static void payment() {
		List<Dendrite> dendrites = Dendrite.findDendrites("named asc");
		Dendrite laCellule = laCellule();
		User connected = connected();
		String ext = "Cotisation";
		render(connected, dendrites, laCellule, ext);

	}

	public static void contact() {
		List<Dendrite> dendrites = Dendrite.findDendrites("named asc");
		Dendrite laCellule = laCellule();
		User connected = connected();
		render(connected, dendrites, laCellule);
	}

	public static void countdown() {
		render();
	}
        
    /**
     * Renders the members of the Bureau
     */
    public static void leBureau() {
        Dendrite laCellule = laCellule();
        String ext = "Le Bureau";
        List<Mandate> currentMandates = Mandate.dendriteCurrentMandates(dendriteId());
        render(laCellule,currentMandates, ext);
    }
}
