package models.elections;

import java.util.List;
import javax.persistence.CascadeType;
import models.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import models.Standard;
import play.data.validation.Required;

/**
 *
 * @author Elay && Sissoko
 * @date
 */
/**
 * This class represents a post to an Election
 *
 */
@Entity
@Table(name = "T_POSTES")
public class Poste extends Standard {

    /**
     *
     */
    @Column(name = "CODE", nullable = false)
    public String code;
    
    /**
     * 
     */
    @Required(message = "Le titre est obligatoire et unique")
    @Column(name="TITLE")
    public String title;
    /**
     *
     */
    @Column(name = "DESCRIPTION", updatable = true)
    public String description;
    /**
     * Boolean to tell if the Poste is of the Cellule
     */
    @Column(name = "GENERAL", updatable = true)
    public Boolean general;
    /**
     * The list of role of the Poste. A Poste can have many roles.
     */
    @JoinTable(name = "AS_POSTES_ROLES", joinColumns = {
        @JoinColumn(name = "POSTE_ID", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "ROLE_ID", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.PERSIST)
    public List<Role> roles;
    /**
     * The list of mandates occupying the poste in either a Dendrite or in the
     * Cellule
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poste")
    public List<Mandate> mandates;

    public Poste() {
        super();
    }

    public Poste(String code, Boolean general) {
        this();
        this.code = code;
        this.general = general;

    }
    
    /**
     * 
     * @param code
     * @return 
     */
    public boolean contains(Right right) {
        for (Role r : roles) {
            if (r.contains(right)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @param code
     * @return 
     */
    public boolean contains(Role role) {
        return roles.contains(role);
    }

    /**
     * This function gives current leader if the election isn't over yet, and
     * the winner when the election is Over!
     *
     * @param election
     * @return
     */
    public User winner(Election election) {
        List<Candidature> candidatures = election.candidaturesByPoste(this.id);

        if(candidatures.isEmpty()) {
            return null;
        } else {
            Candidature winningCandidature = candidatures.get(0);

            for (Candidature c : candidatures.subList(1, candidatures.size())) {
                if (c.numberOfVotes()> winningCandidature.numberOfVotes()) {
                    winningCandidature = c;
                }
            }
            
            return winningCandidature.candidate;
        }

    }
    
    /**
	 * Find all roles ordered by order
	 * 
	 * @param order
	 * @return
	 */
	public static List<Poste> findAll(String order) {
		List<Poste> postes = Poste.find(
				"select distinct u from Poste u order by " + order).fetch();
		return postes;
	}
    
    /**
     * 
     * @param general
     * @return 
     */
    public static List<Poste> findPostes(Boolean general) {
        List<Poste> postes = Poste.find("general", true).fetch();
        return postes;
    }
}
