/**
 *
 */
package models;

import controllers.Application;
import models.articles.Article;
import models.articles.Announce;
import models.articles.Comment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import models.articles.AnnounceSubscription;
import models.articles.ArticleSubscription;
import models.cvs.Cv;
import models.elections.Candidature;
import models.elections.Election;
import models.elections.Poste;
import models.elections.Mandate;
import models.elections.MandateId;
import models.elections.Vote;
import models.events.Event;
import models.events.EventInvitation;
import models.events.EventInvitationId;
import models.events.EventSubscription;
import models.feedback.Feedback;

import org.hibernate.annotations.Index;

import play.data.validation.Email;
import play.data.validation.Min;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.GenericModel;
import static play.db.jpa.GenericModel.find;
import play.db.jpa.JPABase;
import services.Sortable;

/**
 * @author Sissoko && Elay
 * @date 1 mai 2013 03:05:50
 */
@Entity
@Table(name = "T_USERS")
public class User extends Standard implements Searchable {

    /**
     * author name email
     */
    @Required(message = "The email is required")
    @Email(message = "Email is note valid")
    @Unique(message = "UserExists")
    @Index(name = "USER_EMAIL")
    @Column(name = "EMAIL", unique = true, nullable = false, updatable = true)
    public String email;
    @Email(message = "Email is note valid")
    @Unique(message = "UserExists")
    @Column(name = "EMAIL_REDIRECT", unique = true, updatable = true)
    public String emailRedirect;
    /**
     * password
     */
    @Required(message = "The password is required")
    @MinSize(6)
    @Column(name = "PASSWORD")
    public String password;
    /**
     * passwordToken is generate when a author lost his password or if he wants
     * to change it. this token is deleted when the author update his password
     */
    @Column(name = "PWDT")
    public String passwordToken;
    /**
     *
     */
    @Column(name = "CONFIRM")
    public String confirmToken;
    /**
     *
     */
    @Column(name = "CONFIRM_REDIRECT")
    public String confirmRedirect;
    /**
     *
     */
    @Required(message = "The first name is required")
    @Column(name = "FIRST_NAME", nullable = false)
    public String firstName;
    /**
     *
     */
    @Required(message = "The last name is required")
    @Column(name = "LAST_NAME", nullable = false)
    public String lastName;
    /**
     *
     */
    @Required(message = "The birthday is required")
    @Column(name = "BIRTHDAY")
    public Date birthday;
    /**
     *
     */
    @Column(name = "CIVILITY")
    @Enumerated(EnumType.STRING)
    public CivilityEnum civility;
    /**
     *
     */
    @Column(name = "PROMOTION")
    @Min(value = 2000, message = "La promotion commence à 2000")
    public Integer promotion;
    /**
     *
     */
    @Column(name = "BOURSIER")
    public Boolean boursier;
    @OneToOne(cascade = CascadeType.ALL)
    public Cv cv;
    /**
     * Dendrite which author is affected.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Dendrite dendrite;
    @OneToOne(cascade = CascadeType.ALL)
    public Photo photo;
    /**
     * The user is active when he signup. but an administrator can disable a
     * user.
     */
    @Column(name = "ACTIVE")
    public Boolean active;
    /**
     * Count the number off user connexion.
     */
    @Column(name = "CONNEXION")
    public Integer connexion;
    /**
     * Save the last timestamp connexion.
     */
    @Column(name = "LAST_CONNEXION")
    public Date lastConnexion;
    @Column(name = "FACEBOOK_ID")
    public String facebookId;
    @Column(name = "FACEBOOK_TOKEN")
    public String facebookToken;
    /**
     * The list of role of the author. A author can have many roles.
     */
    @JoinTable(name = "AS_USERS_ROLES", joinColumns = {
        @JoinColumn(name = "USER_ID", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "ROLE_ID", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.PERSIST)
    public List<Role> roles;
    /**
     * La liste des mandates occupés par l'utilisateur.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<Mandate> mandates;
    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
    public List<Conversation> convers;
    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
    public List<Conversation> convers2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guest")
    public List<EventInvitation> eventInvitations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    public List<Article> articles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    public List<Announce> announces;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    public List<Event> events;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<AnnounceSubscription> annouceSubscriptions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<EventSubscription> eventSubscriptions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<ArticleSubscription> articleSubscriptions;

    /**
     *
     */
    public User() {
        super();
        active = true;
        cv = new Cv();
    }

    /**
     * @param email
     * @param password
     */
    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }

    public String fullName() {
        return firstName + " " + lastName;
    }

    /**
     * Check if author has the right
     *
     * @param right
     * @return
     */
    public boolean contains(String code) {
        Right right = Right.findByCode(code);
        if (right == null) {
            return false;
        }
        for (Role r : roles) {
            if (r.contains(right)) {
                return true;
            }
        }
        for (Mandate p : mandates) {
            if (p.poste.contains(right)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if author has the right
     *
     * @param right
     * @return
     */
    public boolean hasRight(String code) {
        return contains(code);
    }

    /**
     * Check if author has the right
     *
     * @param right
     * @return
     */
    public boolean hasRight(RightEnum right) {
        return contains(right.name());
    }

    /**
     *
     * @param role
     * @return
     */
    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    /**
     * if the current author dendriteCode is not equal to dendriteCode we check
     * if the is applicable to all dendrites.
     *
     * @param dendriteCode
     * @param ruleCode
     * @return
     */
    public boolean canActe(String dendriteCode, String ruleCode) {
        if (dendrite.code.equals(dendriteCode)) {
            return contains(ruleCode);
        }
        Right right = Right.findByCode(ruleCode);
        if (right == null) {
            return false;
        }
        for (Role r : roles) {
            if (r.contains(right)) {
                return right.visibility.code.equals(Visibility.NOT_RESTRICTED);
            }
        }
        return false;
    }

    /**
     *
     * @param model
     * @return
     */
    public boolean canEdit(Standard model) {
        if (model == null) {
            return false;
        }
        if (model instanceof Dendrite) {
            Dendrite dend = (Dendrite) model;
            if (hasRight(RightEnum.EDIT_DENDRITE.name())) {
                return true;
            }
            if (hasRight(RightEnum.EDIT_MY_DENDRITE.name())) {
                return dend.equals(this.dendrite);
            }
            return false;
        }
        if (model instanceof User) {
            User user = (User) model;
            if (dendrite == null) {
                return false;
            }
            if (hasRight(RightEnum.EDIT_DENDRITE.name())) {
                return true;
            }
            if (hasRight(RightEnum.EDIT_MY_USER.name())) {
                if (dendrite.equals(user.dendrite)) {
                    return true;
                }
                return false;
            }
            if (id == user.id) {
                return true;
            }
            return false;
        }
        if (model instanceof Poste) {
            Poste poste = (Poste) model;
            if (dendrite == null) {
                return false;
            }
            if (hasRight(RightEnum.EDIT_POSTE.name())) {
                return true;
            }
            if (hasRight(RightEnum.EDIT_MY_POSTE.name())) {
                if (dendrite.postes.contains(poste)) {
                    return true;
                }
                return false;
            }
            return false;
        }
        if (model instanceof Election) {
            Election election = (Election) model;
            if (hasRight(RightEnum.EDIT_ELECTION.name())) {
                return true;
            }
            if (hasRight(RightEnum.EDIT_MY_ELECTION.name())) {
                if (dendrite.elections().contains(election)) {
                    return true;
                }
                if (this.equals(election.organiser)) {
                    return true;
                }
                return false;
            }
            return false;
        }
        if (model instanceof Candidature) {
            Candidature candidature = (Candidature) model;
            if (dendrite == null) {
                return false;
            }
            if (hasRight(RightEnum.EDIT_CANDIDATURE.name())) {
                return true;
            }
            if (hasRight(RightEnum.EDIT_MY_CANDIDATURE.name())) {
                if (dendrite.equals(candidature.election.dendrite)) {
                    return true;
                }
            }
            if (dendrite.equals(candidature.election.dendrite) || candidature.election.dendrite.general) {
                if (this.equals(candidature.candidate)) {
                    return true;
                }
            }
            return false;
        }
        if (model instanceof Event) {
            Event event = (Event) model;
            if (dendrite == null) {
                return false;
            }
            if (hasRight(RightEnum.EDIT_EVENT.name())) {
                return true;
            }
            if (event.dendrite != null && !dendrite.equals(event.dendrite) && !event.dendrite.general) {
                return false;
            }
            if (hasRight(RightEnum.EDIT_MY_EVENT.name())) {
                if (dendrite.equals(event.dendrite)) {
                    return true;
                }
            }
            if (isMine(event)) {
                return true;
            }

            return false;
        }
        if (model instanceof Announce) {
            Announce announce = (Announce) model;
            if (dendrite == null) {
                return false;
            }
            if (hasRight(RightEnum.EDIT_ANNOUNCE.name())) {
                return true;
            }
            if (hasRight(RightEnum.EDIT_MY_ANNOUNCE.name())) {
                if (dendrite.equals(announce.dendrite)) {
                    return true;
                }
            }
            if (announce.isMine(this)) {
                return true;
            }
            return false;
        }
        if (model instanceof Feedback) {
            Feedback feedback = (Feedback) model;
            if (hasRight(RightEnum.EDIT_FEEDBACK)) {
                return true;
            }
            if (hasRight(RightEnum.EDIT_MY_FEEDBACK)) {
                return this.equals(feedback.responsable);
            }
            return false;
        }
        return false;
    }

    /**
     *
     * @param model
     * @return
     */
    public boolean canDelete(Standard model) {
        return false;
    }

    /**
     * attribute a new role to the author. Check if he already has this role we
     * do anything.
     *
     * @param code
     */
    public void attributeRole(String code) {
        if (code == null) {
            return;
        }
        Role role = new Role(code);
        if (roles.contains(role)) {
            return;
        }
        role = Role.findByCode(code);
        if (role == null) {
            return;
        }
        roles.add(role);
        save();
    }

    /**
     * removes a role to the author.
     *
     * @param code
     */
    public void removeRole(String code) {
        if (code == null) {
            return;
        }
        Role role = new Role(code);
        roles.remove(role);
        save();
    }

    /**
     * gets author from database where email is $email return null if author
     * does not exist.
     *
     * @param email
     * @return author
     */
    public static User findByEmail(String email) {
        User user = User.find("byEmail", email).first();
        return user;
    }

    /**
     *
     * @param emailRedirect
     * @return
     */
    public static User findByEmailRedirect(String emailRedirect) {
        User user = User.find("byEmailRedirect", emailRedirect).first();
        return user;
    }

    /**
     *
     * @param emailRedirect
     * @return
     */
    public static User findByConfirmRedirect(String confirmRedirect) {
        User user = User.find("byConfirmRedirect", confirmRedirect).first();
        return user;
    }

    /**
     * Finds user by his facebook accessToken
     *
     * @param accessToken
     * @return
     */
    public static User findByfbId(String accessToken) {
        User user = User.find("byFacebookId", accessToken).first();
        return user;
    }

    /**
     * this method is used when a author asks to change or he has lost his
     * password gets author from database where passwordToken is $passwordToken
     * return null if author does not exist.
     *
     * @param passwordToken
     * @return author
     */
    public static User findByPasswordToken(String passwordToken) {
        User user = find("byPasswordToken", passwordToken).first();
        return user;
    }

    /**
     * Change author's dendrite. This method can be used if a author change town
     * for example after 2 years study at Valence the author can change town for
     * Grenoble so his new Dendrite is dendrite_grenoble.
     *
     * @param newDendriteCode
     */
    public void changeDendrite(String newDendriteCode) {
        if (newDendriteCode == null) {
            return;
        }
        if (newDendriteCode.equals(dendrite.code)) {
            return;
        }
        Dendrite d = Dendrite.findByCode(newDendriteCode);
        if (d != null) {
            this.dendrite = d;
            save();
        }
    }
    
    public String username(){
        String username = firstName.toLowerCase().replaceAll("[ ]+", "-")+"-"+lastName.toLowerCase().replaceAll("[ ]+", "-");
        return username;
    }

    /**
     * Get all articles.
     *
     * @param page
     * @return
     */
    public List<Article> articles() {
        return Article.find("author = ? order by modified desc", this).fetch();
    }

    /**
     * Get articles by page and 10/page.
     *
     * @param page
     * @return
     */
    public List<Article> articles(int page) {
        return Article.find("author = ? order by modified desc", this).fetch(page, 10);
    }

    /**
     * Get all announces.
     *
     * @param page
     * @return
     */
    public List<Announce> announces() {
        return Announce.find("author = ? order by modified desc", this).fetch();
    }

    /**
     * Get announces by page and 10/page.
     *
     * @param page
     * @return
     */
    public List<Announce> announces(int page) {
        return Announce.find("author = ? order by modified desc", this)
                .fetch(page, 10);
    }

    public static List<User> userByPromotion(int promotion) {
        List<User> users = User.find("promotion", promotion).fetch();
        return users;
    }

    /**
     *
     * @return
     */
    public List<Poste> postes() {
        List<Poste> postes = Poste.find("select distinct p from Poste p join p.users as u where u = ?", this).fetch();
        return postes;
    }

    /**
     *
     * @param model
     * @return
     */
    public boolean isMine(Standard model) {
        if (model == null) {
            return false;
        }

        if (model instanceof Article) {
            return equals(((Article) model).author);
        }

        if (model instanceof Announce) {
            return equals(((Announce) model).author);
        }

        if (model instanceof Comment) {
            return equals(((Comment) model).author);
        }

        if (model instanceof Event) {
            return equals(((Event) model).author);
        }
        return false;
    }

    /**
     *
     * @return
     */
    public List<Role> complementRole() {
        List<Role> rls = Role.findAll("code asc");
        rls.removeAll(this.roles);
        return rls;
    }

    /**
     *
     * @param role
     * @return
     */
    public static List<User> usersAsRole(Role role) {
        List<User> users;
        users = User.find(
                "select distinct u from User u join u.roles as r where r = ?",
                role).fetch();
        return users;
    }

    /**
     * This function returns all the elections that were created by the user
     *
     * @return
     */
    public List<Election> elections() {
        List<Election> elections = Election.find("Select e from Election e where e.organiser=?", this).fetch();
        return elections;

    }

    public boolean hasVoted(long posteId, long electionId) {
        Poste poste = Poste.findById(posteId);
        Election election = Election.findById(electionId);
        if (poste == null) {
            return false;
        }

        if (election == null) {
            return false;
        }

        Vote vote = Vote.find("select v from Vote v where v.candidature.poste=? and v.candidature.election=? and v.elector=?", poste, election, this).first();
        if (vote != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean participe(Long id) {
        EventInvitation ei = EventInvitation.findById(new EventInvitationId(this.id, id));
        return ei != null;
    }

    /**
     * This function returns all the User's votes for this election
     *
     * @param electionId
     * @return
     */
    public List<Vote> votesForElection(long electionId) {
        Election election = Election.findById(electionId);

        if (election == null) {
            return null;
        }
        List<Vote> votes = Vote.find("select distinct v from Vote v where v.candidature.election=? and v.elector=?", election, this).fetch();

        return votes;
    }

    @Override
    public String toString() {
        return fullName();
    }

    public boolean canVote(long electionId) {
        Election election = Election.findById(electionId);

        if (election == null) {
            return false;
        }

        if (election.dendrite.general) {
            return true;
        }

        if (election.dendrite.id == this.dendrite.id) {
            return true;
        } else {
            return false;
        }
    }

    public boolean voted(Election election, Poste poste) {
        Vote vote = Vote.find("select distinct v from Vote v where "
                + "v.candidature.election = ? and v.candidature.poste = ? and v.elector = ?", election, poste, this).first();
        return vote != null;
    }

    public List<Event> events() {
        return Event.find("author = ? order by modified desc", this).fetch();
    }

    public boolean redirectConfirmed() {
        return emailRedirect != null && confirmRedirect == null;
    }

    /**
     *
     * @return
     */
    public String externalEmail() {
        if (!redirectConfirmed() || !Application.isEmail(emailRedirect)) {
            return email;
        }
        return emailRedirect;
    }

    /**
     *
     * @param o
     * @return
     */
    public int compareTo(Sortable o) {
        if (o instanceof User) {
            return firstName.compareTo(((User) o).firstName);
        }
        if (o instanceof Article) {
            return firstName.compareTo(((Article) o).title);
        }
        if (o instanceof Event) {
            return firstName.compareTo(((Event) o).title);
        }
        if (o instanceof Announce) {
            return firstName.compareTo(((Announce) o).title);
        }
        return -1;
    }

    public String myType() {
        return "user";
    }

    public int increConnexion() {
        if (connexion == null) {
            connexion = 0;
        }
        connexion++;
        lastConnexion = new Date();
        save();
        return connexion;
    }

    /**
     *
     * @param dendrite
     * @return
     */
    public boolean canSendMessage(Dendrite dendrite) {
        if (this.dendrite == null) {
            return false;
        }
        if (hasRight(RightEnum.SEND_MESSAGE)) {
            return true;
        }
        if (hasRight(RightEnum.SEND_MY_MESSAGE)) {
            return this.dendrite.equals(dendrite);
        }
        return false;
    }

    /**
     * On vérifie si l'utilisateur à un facebook id on retourne le lien vers sa
     * photo de profil Sinon on renvoie l'url vers sa photo chargée
     *
     * @return
     */
    public String profileUrl() {
        if (facebookId != null && !facebookId.equals("")
                && !facebookId.isEmpty()) {
            return "https://graph.facebook.com/" + facebookId
                    + "/picture?type=large";
        }
        if (photo == null) {
            return null;
        }
        return photo.profileUrl();
    }

    /**
     * On vérifie si l'utilisateur à un facebook id on retourne le lien vers sa
     * photo de profil Sinon on renvoie l'url vers sa photo chargée
     *
     * @return
     */
    public String smallUrl() {
        if (facebookId != null && !facebookId.equals("")
                && !facebookId.isEmpty()) {
            return "https://graph.facebook.com/" + facebookId
                    + "/picture?type=large";
        }
        if (photo == null) {
            return null;
        }
        return photo.smallUrl();
    }

    public int unread() {
        List<Message> unreads = Message.find("select m from Message m where m.convers.user1 = ? and m.ready is ?", this, false).fetch();
        return unreads.size();
    }

    /**
     *
     * @return
     */
    public boolean isDev() {
        return hasRole(new Role("DEV"));
    }

    /**
     *
     * @param poste
     * @param description
     * @return
     */
    public boolean addPoste(Poste poste, Election election, String description) {
        if (poste == null || election == null || dendrite == null) {
            return false;
        }
        Mandate up = Mandate.findById(new MandateId(id, /*poste.id,*/ dendrite.id, election.id));
        if (up != null) {
            if (description != null) {
                up.description = description;
                up.save();
                mandates.add(up);
//                save();
                return true;
            }
            return true;
        }
        up = new Mandate(id, /*poste.id,*/ dendrite.id, election.id);
        if (description != null) {
            up.description = description;
        } else {
            up.description = poste.description;
        }
        up.save();
        mandates.add(up);
//        save();
        return true;
    }

    /**
     *
     * @param poste_id
     * @return
     */
    public boolean removePoste(Long poste_id, Long election_id) {
        if (dendrite == null) {
            return false;
        }
        Mandate up = Mandate.findById(new MandateId(id/*, poste_id*/, dendrite.id, election_id));
        if (up == null) {
            return false;
        }
        System.out.println(up);
        up.delete();
        mandates.remove(up);
        //save();
        return true;
    }

    /**
     *
     * @param mandate
     * @return
     */
    public boolean addMandate(Mandate mandate) {
        if (mandate == null) {
            return false;
        }
        if (dendrite == null) {
            return false;
        }
        if (mandate.dendrite_id == null) {
            mandate.dendrite_id = dendrite.id;
        }
        mandate.user_id = this.id;
        Mandate old = Mandate.findById(new MandateId(id, /*mandate.poste_id,*/ mandate.dendrite_id, mandate.election_id));
        if (old != null) {
            return true;
        }
        mandate.save();
        return true;
    }

    public boolean removeMandate(MandateId mid) {
        Mandate mandate = Mandate.findById(mid);
        if (mandate == null) {
            return false;
        }
        boolean del = mandate.delete() != null;
        save();
        return del;
    }

    public List<Mandate> getMandates() {
        if (mandates == null) {
            mandates = new ArrayList<Mandate>();
        }
        return mandates;
    }

    public List<Role> getRoles() {
        if (roles == null) {
            this.roles = new ArrayList<Role>();
        }
        return roles;
    }

    public List<Conversation> getConvers() {
        if (convers == null) {
            convers = new ArrayList<Conversation>();
        }
        return convers;
    }

    public List<Conversation> getConvers2() {
        return convers2;
    }

    public List<Announce> getAnnounces() {
        if (announces == null) {
            announces = new ArrayList<Announce>();
        }
        return announces;
    }

    public List<Event> getEvents() {
        if (events == null) {
            events = new ArrayList<Event>();
        }
        return events;
    }

    /**
     * To delete a author we must delete all his posts for example an article or
     * announce will be deleted with their eventual c comments
     *
     * @see play.db.jpa.GenericModel#delete()
     */
    @Override
    public <T extends JPABase> T delete() {
        return super.delete();
    }
}
