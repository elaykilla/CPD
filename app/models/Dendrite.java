/**
 *
 */
package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import models.articles.Announce;


import models.elections.Election;
import models.elections.Poste;
import models.events.Event;
import org.hibernate.annotations.Index;
import play.data.validation.Email;
import play.data.validation.Required;
import play.data.validation.Unique;

/**
 * @author Sissoko && Elay
 * @date 1 mai 2013 02:41:06
 */
@Entity
@Table(name = "T_DENDRITES")
public class Dendrite extends Standard {

    /**
     * code can be a name of dendrite.
     */
    @Required(message = "Dendrite code is required")
    @Unique(message = "This dendrite code exists")
    @Column(name = "CODE", nullable = false, unique = true, updatable = true)
    public String code;
    /**
     *
     */
    @Required(message = "Dendrite name is required")
    @Column(name = "NAME", nullable = false, updatable = true)
    public String named;
    @Column(name = "DESCRIPTION", updatable = true)
    public String description;
    @JoinTable(name = "AS_DENDRITES_POSTES", joinColumns = {
        @JoinColumn(name = "DENDRITE_ID", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "POSTE_ID", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.PERSIST)
    public List<Poste> postes;
    @OneToMany(mappedBy = "dendrite", cascade = CascadeType.PERSIST)
    public List<Event> events;
    @OneToMany(mappedBy = "dendrite", cascade = CascadeType.PERSIST)
    public List<Announce> announces;
    @OneToMany(mappedBy = "dendrite", cascade = CascadeType.PERSIST)
    public List<User> users;
    /**
     * This variable states whether or not the dendrite in question in the
     * Cellule
     */
    @Column(name = "GENERAL")
    public Boolean general;
    @OneToOne(cascade = CascadeType.ALL)
    public Photo photo;
    @Email(message = "Email is note valid")
    @Index(name = "DENDRITE_EMAIL")
    @Column(name = "EMAIL")
    public String email;

    /**
     *
     */
    public Dendrite() {
        super();
        this.postes = new ArrayList<Poste>();
        this.events = new ArrayList<Event>();
        this.announces = new ArrayList<Announce>();
        this.users = new ArrayList<User>();
        this.general = false;
    }

    /**
     * @param named
     */
    public Dendrite(String code, String named) {
        this();
        this.code = code;
        this.named = named;
    }

    /**
     * Gets the dendrite by his code. If it is not exist returns null.
     *
     * @param code
     * @return
     */
    public static Dendrite findByCode(String code) {
        Dendrite d = find("byCode", code).first();
        return d;
    }

    /**
     * The users by dendrite
     *
     * @param dendriteCode
     * @return all members from the dendrite dendriteCode
     */
    public List<User> users() {
        List<User> us = User.find("dendrite = ? order by lastName asc",
                this).fetch();
        return us;
    }

    /**
     * Find all dendrites ordered by order
     *
     * @param order
     * @return
     */
    public static List<Dendrite> findAll(String order) {
        List<Dendrite> dendrites = Dendrite.find(
                "select distinct d from Dendrite d order by " + order).fetch();
        return dendrites;
    }

    /*
     * Returns all the dendrites except the bureau
     */
    public static List<Dendrite> findDendrites(String order) {
        List<Dendrite> dendrites = Dendrite.find(
                "general=false or general is null order by " + order).fetch();

        return dendrites;
    }

    /**
     * get
     *
     * @param dendriteCode
     * @param roleCode
     * @return
     */
    public static User findUserFromDendriteByRole(String dendriteCode,
            String roleCode) {
        Dendrite d = findByCode(dendriteCode);
        if (d == null) {
            return null;
        }
        // "select distinct i from Item i join i.tags as t where i.onSale = true and t.name = ?"
        User user = User
                .find("select distinct u from User u join u.roles as r where u.dendrite.code=? and r.code=?",
                dendriteCode, roleCode).first();
        return user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see play.db.jpa.JPABase#toString()
     */
    @Override
    public String toString() {
        return named;
    }

    public List<Election> elections() {
        List<Election> elections = Election.find("dendrite = ? order by startCandidature desc", this).fetch();
        return elections;
    }

    /**
     *
     * @param codeDendrite
     * @return all the Elections for the current Dendrite
     */
    public static List<Election> electionsDendrite(long dendriteId) {
        List<Election> elections = Election.find("dendrite.id=? order by startCandidature desc", dendriteId).fetch();
        return elections;
    }

    /**
     * This function returns the Elections for the bureau
     *
     * @return
     */
    public static List<Election> electionsCellule() {
        List<Election> elections = Election.find("dendrite.general = true order by startCandidature desc").fetch();
        return elections;
    }

    /**
     *
     * @return
     */
    public List<User> usersNotIn() {
        List<User> users = User.find(
                "select distinct u from User u where u.dendrite = null")
                .fetch();
        return users;
    }

    /**
     *
     * @return
     */
    public List<Announce> announces() {
        return Announce.find("dendrite = ? order by modified desc", this).fetch();
    }

    public List<Event> events() {
        return Event.find("dendrite = ? order by modified desc", this).fetch();
    }

    /**
     *
     * @return
     */
    public List<Event> events(String order, int page, int length) {
        return Event.find("dendrite=? order by " + order, this).fetch(page, length);
    }

    public List<Poste> getPostes() {
        return postes;
    }
    
    

    @Override
    public Dendrite delete() {
        for (Election e : elections()) {
            e.delete();
        }
        for (Event e : events()) {
            e.delete();
        }
        for (Announce a : announces()) {
            a.delete();
        }
        return super.delete();
    }
}
