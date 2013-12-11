
/**
 *
 * @author Elay && Sissoko
 * @date
 */
import java.util.Date;
import models.Dendrite;
import models.User;
import models.elections.*;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import play.test.UnitTest;

public class BasicElectionTest extends UnitTest {

    @Before
    public void setup() {
        //Fixtures.deleteDatabase();
        
        //Fixtures.loadModels("data.yml");
    }
    


    /**
     * Test for Poste
     */
    @Test
    public void TestPoste() {
        //Poste 
        Poste prez = new Poste();
        prez.code = "President";
        prez.description = "Poste de president";
        prez.save();

        assertNotNull(prez);
    }

    /**
     * This function tests the creation of an election
     */
    @Test
    public void TestElection() {
        //Dendrite creation
        Dendrite d = new Dendrite();
        d.code = "Dendrite_valence";
        d.named = "Valence";
        d.save();


        //The candidate
        User organiser = new User();
        organiser.firstName = "Laylay";
        organiser.lastName = "Killa";
        organiser.email = "laylay@laylay.com";
        organiser.password = "secret";
        organiser.dendrite = d;
        organiser.save();

        //Election
        Election election = new Election();
        election.code = "Election President";
        election.startCandidature = new Date();
        election.endCandidature = new Date();
        election.startVote = new Date();
        election.endVote = new Date();
        election.dendrite = d;
        election.organiser= organiser;
        election.save();

        assertNotNull(election);
    }

    /**
     * This function tests the creation of a Candidature
     */
    @Test
    public void TestCandidature() {
        
        //Dendrite creation
        Dendrite d = new Dendrite();
        d.code = "Dendrite_valence";
        d.named = "Valence";
        d.save();
        
        //The Organisateur
        User organiser = new User();
        organiser.firstName = "Laylay";
        organiser.lastName = "Killa";
        organiser.email = "laylay@laylay.com";
        organiser.password = "secret";
        organiser.dendrite = d;
        organiser.save();
        
        //The candidate
        User candidate = new User();
        candidate.firstName = "Laylay";
        candidate.lastName = "Killa";
        candidate.email = "laylay@laylay.com";
        candidate.password = "secret";
        candidate.dendrite = d;
        candidate.save();
        
        //Poste 
        Poste prez = new Poste();
        prez.code = "President";
        prez.description = "Poste de president";
        prez.save();
        
        //Election
        Election election = new Election();
        election.code = "Election President";
        election.startCandidature = new Date();
        election.endCandidature = new Date();
        election.startVote = new Date();
        election.endVote = new Date();
        election.dendrite = d;
        election.save();
        
        //Candidature
        Candidature candidature = new Candidature();
        candidature.poste = prez;
        candidature.candidate = candidate;
        candidature.election = election;
        candidature.save();
        
        assertNotNull(candidature);             
    }
    
    
    @Test
    public void TestVote(){
        //Dendrite creation
        Dendrite d = new Dendrite();
        d.code = "Dendrite_valence";
        d.named = "Valence";
        d.save();
        
        //Poste 
        Poste prez = new Poste();
        prez.code = "President";
        prez.description = "Poste de president";
        prez.save();
        
        //Election
        Election election = new Election();
        election.code = "Election President";
        election.startCandidature = new Date();
        election.endCandidature = new Date();
        election.startVote = new Date();
        election.endVote = new Date();
        election.dendrite = d;
        election.save();
              
        //Electeur
        User elector = new User();
        elector.firstName = "Laylay";
        elector.lastName = "Killa";
        elector.email = "laylay@laylay.com";
        elector.password = "secret";
        elector.dendrite = d;
        elector.save();
        
        //The candidate
        User candidate = new User();
        candidate.firstName = "Laylay";
        candidate.lastName = "Killa";
        candidate.email = "laylay@laylay.com";
        candidate.password = "secret";
        candidate.dendrite = d;
        candidate.save();
        
        //Candidature
        Candidature candidature = new Candidature();
        candidature.poste = prez;
        candidature.candidate = candidate;
        candidature.election = election;
        candidature.save();
        
        //Vote 
        Vote vote = new Vote();
        vote.candidature = candidature;
        vote.elector = elector;
        
        assertNotNull(vote);
        
        
    }
}
