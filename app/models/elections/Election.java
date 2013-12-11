package models.elections;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import models.Dendrite;
import models.Standard;
import models.User;

import play.data.validation.Required;
import play.data.validation.Unique;

import java.util.List;
import javax.persistence.CascadeType;

/**
 *
 * @author Elay && Sissoko
 * @date
 */
@Entity
@Table(name = "T_ELECTIONS")
public class Election extends Standard {

    @Required(message = "Election code is required")
    @Column(name = "CODE", nullable = false, unique = true)
    @Unique(message = "This Elecetion code exists")
    public String code;
    /**
     * Election description
     */
    @Required
    @Column(name = "DESCRIPTION", updatable = true)
    public String description;
    /**
     * Election starting date
     */
    @Required(message = "Candidature Start date is required")
    @Column(name = "START_CANDIDATURE", nullable = false, updatable = true)
    public Date startCandidature;
    @Required(message = "Candidature End date is required")
    @Column(name = "END_CANDIDATURE", nullable = false, updatable = true)
    public Date endCandidature;
    @Required(message = "Vote Start date is required")
    @Column(name = "START_VOTE", nullable = false, updatable = true)
    public Date startVote;
    @Required(message = "Vote End date is required")
    @Column(name = "FIN_VOTE", nullable = false, updatable = true)
    public Date endVote;
//    @Required(message = "DENDRITE is required")
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Dendrite dendrite;
//    @Required(message = "ORGANISER is required")
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User organiser;

    /**
     *
     */
    public Election() {
        super();
    }

    public List<Poste> postes() {
        List<Poste> postes = this.dendrite.postes;
        return postes;
    }

    public List<Candidature> candidatures() {
        List<Candidature> candidatures = Candidature.find("select c from Candidature c where c.election=?", this).fetch();

        return candidatures;
    }

    /**
     * This function returns all the cadidates for a given poste (in the current
     * election)
     *
     * @param p
     * @return
     */
    public List<Candidature> candidaturesByPoste(long posteId) {
        Poste p = Poste.findById(posteId);
//        List<Candidature> candidatures = Candidature.find("select c from Candidature c where c.election=? and c.poste=?", this, p).fetch();

        List<Candidature> candidatures = Candidature.find("byElectionAndPoste", this, p).fetch();
        
        return candidatures;
    }

    /**
     * This function returns the current leader for a given poste when the
     * voting is over, currentWinner represents the final winner
     *
     * @param p
     * @return
     */
    public Candidature currentLeaderByPoste(long posteId) {

        Candidature currentLeader = null;
        long max = 0;
        for (Candidature c : candidaturesByPoste(posteId)) {
            if (c.numberOfVotes() > max) {
                currentLeader = c;
                max = c.numberOfVotes();
            }
        }

        return currentLeader;
    }

    /**
     * This function returns the current leaders for all the posts in the
     * election
     *
     * @return A HashMap of Postes and current leader for the post
     */
    public HashMap<Poste, Candidature> currentLeaders() {
        HashMap<Poste, Candidature> leaders = new HashMap<Poste, Candidature>();

        for (Poste p : postes()) {
            leaders.put(p, currentLeaderByPoste(p.id));
        }



        return leaders;
    }

    /**
     * This method returns the status of the election:
     *
     * @return preElection: if candidature hasn't started yet candidature: if
     * candidature has started but hasn't ended yet voting: if the voting period
     * has started and not ended yet postElection: if the voting phase is
     * finished
     */
    public String status() {
        //String status;
        Date today = new Date();

        //If candidatures haven't started yet
        if (today.compareTo(this.startCandidature) < 0) {
            return ("preElection");
        } //If candidatures haven't ended yet
        else if (today.compareTo(this.endCandidature) < 0) {
            return ("candidature");
        } //If voting hasn't ended yet
        else if (today.compareTo(this.endVote) < 0) {
            return ("voting");
        } else {
            return ("postElection");
        }

    }

    /**
     * For a given election, this function returns the candidature of user for a
     * poste
     *
     * @param candidate
     * @param poste
     * @return
     */
    public Candidature candidatureByPosteForUser(User candidate, Poste poste) {
        Candidature candidature = Candidature.find("select distinct c from Candidature c where c.candidate=? and c.poste=? and c.election=?", candidate, poste, this).first();

        //System.out.println(candidature.presentation);
        return candidature;
    }

    @Override
    public Election delete() {
        for (Candidature c : candidatures()) {
            c.delete();
        }
        return super.delete();
    }
}
