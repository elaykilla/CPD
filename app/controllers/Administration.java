/**
 *
 */
package controllers;

import static controllers.Application.bcrypt;
import static controllers.Application.connected;
import static controllers.Application.index;
import static controllers.Application.notNull;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.RoleRight;
import models.CivilityEnum;
import models.ConversUserDendrite;
import services.CompareFunction;

import models.Dendrite;
import models.Message;
import models.Photo;
import models.Right;
import models.Role;
import models.User;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.i18n.Messages;
import play.mvc.Before;
import services.BCrypt;
import models.RightEnum;
import models.SearchField;
import services.StandardComparable;
import services.TriGenerique;
import models.elections.Candidature;
import models.elections.Election;
import models.elections.Mandate;
import models.elections.MandateId;
import models.elections.Poste;
import models.feedback.Evolution;
import models.feedback.Feedback;
import notifiers.Mails;
import org.codehaus.jackson.node.ObjectNode;
import play.data.validation.Validation;
import play.mvc.Http;

/**
 * *
 * @author Sissoko && Elay
 * @date 11 mai 2013 17:49:29
 */
public class Administration extends Application {

    @Before
    public static void setup() {
        User connected = connected();
        if (connected == null) {
            flash.error("Connect to continue.");
            Users.login();
            return;
        }
        if (!connected.contains(RightEnum.ACCESS_ADMIN.name())) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        }
    }

    public static void admin() {
        render();
    }

    /**
     * Display dendrite form to create new dendrite.
     *
     * @param args
     */
    public static void editDendrite(@Required Long id, int page) {
        User connected = connected();
        Dendrite dendrite = Dendrite.findById(id);
        if (dendrite == null) {
            flash.error("Dendrite doesn't exist");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (!connected.canEdit(dendrite)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        long size = User.count();
        int nbPagesMax = 10;
        int nbPages = (int) (size / nbPagesMax);
        if (nbPagesMax * nbPages < size) {
            nbPages = nbPages + 1;
        }
        List<User> users = Users.users(page, "lastName asc");
        List<Poste> postes = Poste.findPostes(dendrite.general);
        render(dendrite, users, postes, size, page, nbPagesMax, nbPages);
    }

    /**
     *
     */
    public static void dendrites() {
        List<Dendrite> dendrites = Dendrite.all().fetch();
        render(dendrites);
    }

    /**
     *
     * @param id
     */
    public static void updateDendrite(@Valid Dendrite dendrite, int page) {
        User connected = connected();
        if (Validation.hasErrors()) {
            flash.error("Vérifiez les informations");
            editDendrite(dendrite.id, page);
            return;
        }

        if (!connected.canEdit(dendrite)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        dendrite.save();
        flash.success("Dendrite mise à jour avec success");
        editDendrite(dendrite.id, page);
    }

    /**
     *
     * @param logo
     * @param description
     */
    public static void uploadPhoto(File logo, Long dendriteId, String description, int page) {
        notFoundIfNull(logo);
        validation.required(logo);
        if (Validation.hasErrors()) {
            Validation.addError("logo", Messages.get("validation.required"));
            flash.error("Vous devez charger un fichier");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        }
        User connected = connected();
        Dendrite dendrite = Dendrite.findById(dendriteId);
        if (dendrite == null) {
            flash.error("Dendrite not found");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (!connected.canEdit(dendrite)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (Validation.hasErrors()) {
            flash.error("Vérifiez les informations");
            editDendrite(dendrite.id, page);
            return;
        }
        Photo oldPhoto = dendrite.photo;
        Photo photo = new Photo();
        photo.description = description;
        photo.saveFile(logo);
        Photo.saveProfileFile(logo, photo.title, "profile");
        dendrite.photo = photo;
        dendrite.save();
        if (oldPhoto != null) {
            oldPhoto.delete();
        }
        flash.success("Dendrite a bien été mise à jour %s",
                dendrite.named);
        editDendrite(dendrite.id, page);

    }

    /**
     * create new dendrite in database.
     *
     * @param dendrite
     */
    public static void saveDendrite(@Valid Dendrite dendrite) {
        User connected = connected();
        if (!(connected.contains(RightEnum.CREATE_DENDRITE.name()))) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (Validation.hasErrors() || dendrite == null) {
            flash.error(Messages.get("CorrectErrors"));
            render("@editDendrite", dendrite);
            return;
        }
        Dendrite d = Dendrite.findByCode(dendrite.code);
        if (d == null) {
            dendrite.save();
            flash.success("Dendrite create with success");
            index();
        }
    }

    /**
     *
     * @param id
     */
    public static void deleteDendrite(@Required Long id) {
        User connected = connected();
        if (!connected.contains(RightEnum.DELETE_DENDRITE.name())) {
            flash.error("Please contact a super user because you have not right to do this action.");
            dendrites();
            return;
        }
        Dendrite dendrite = Dendrite.findById(id);
        List<User> users = dendrite.users();
        if (users == null || users.isEmpty()) {
            dendrite.delete();
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        } else {
            flash.error(
                    "Cette dendrite contient %d utilisateur(s). Vous devez dissocier tous les utilisateurs.",
                    users.size());
            dendrites();
        }
    }

    /**
     * Display Election form to create new dendrite.
     *
     * @param args
     */
    public static void editElection(@Required Long id) {
        User connected = connected();
        Election election = Election.findById(id);
        if (election == null) {
            flash.error("Election doesn't exist");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (!connected.canEdit(election)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        List<User> users = Users.findAll("firstName asc");
        List<Poste> postes = election.postes();
        render(election, postes, users);
    }

    /**
     *
     */
    public static void elections() {
        List<Election> elections = Election.all().fetch();
        render(elections);
    }

    /**
     *
     * @param id
     */
    public static void updateElection(@Valid Election election, Long dendriteId) {
        User connected = connected();
        if (!connected.canEdit(election)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (dendriteId == null) {
            dendriteId = Cellule.dendriteId();
        }
        Dendrite dendrite = Dendrite.findById(dendriteId);
        if (Validation.hasErrors()) {
            flash.error("Vérifiez les informations");
            editElection(election.id);
            return;
        }
        election.dendrite = dendrite;
        election.save();
        flash.success("Election mise à jour avec success");
        editElection(election.id);
    }

    /**
     *
     * @param id
     */
    public static void deleteElection(@Required Long id) {
        User connected = connected();
        if (!connected.contains(RightEnum.DELETE_ELECTION.name())) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        Election election = Election.findById(id);
        if (election != null) {
            election.delete();
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        } else {
            flash.error("L'élection introuvable.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        }
    }

    /**
     *
     * @param id
     */
    public static void updateCandidature(String presentation, Long candidateId, Long posteId, Long electionId) {
        User connected = connected();
        User candidate = User.findById(candidateId);
        Poste poste = Poste.findById(posteId);
        Election election = Election.findById(electionId);
        if (candidate == null || poste == null || election == null) {
            flash.error("Vérifiez les informations");
            editElection(electionId);
            return;
        }
        Candidature candidature = new Candidature();
        candidature.candidate = candidate;
        candidature.poste = poste;
        candidature.election = election;
        candidature = candidature.findOrCreate();
        candidature.presentation = presentation;
        if (!connected.canEdit(candidature)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            editElection(electionId);
            return;
        }
        candidature.save();
        flash.success("Candidature de %s mise à jour avec success", candidature.candidate.fullName());
        editElection(electionId);
    }

    /**
     *
     * @param id
     */
    public static void deleteCandidature(@Required Long id) {
        User connected = connected();
        if (!connected.contains(RightEnum.DELETE_CANDIDATURE.name())) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        Candidature candidature = Candidature.findById(id);
        if (candidature != null) {
            candidature.delete();
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        } else {
            flash.error("L'élection introuvable.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        }
    }

    /**
     *
     */
    public static void users(String status, int page) {
        if (status != null) {
            if (status.equals("active")) {
                long size = User.count();
                List<User> users = Users.findActiveUsers(page, "lastName asc");
                render(users, size, page);
                return;
            }
            if (status.equals("disable")) {
                long size = User.count();
                List<User> users = Users.findDisableUsers(page, "lastName asc");
                render(users, size, page);
                return;
            }
        }
        long size = User.count();
        int nbPagesMax = 10;
        int nbPages = (int) (size / nbPagesMax);
        if (nbPagesMax * nbPages < size) {
            nbPages = nbPages + 1;
        }
        List<User> users = Users.users(page, "lastName asc");
        render(users, size, page, nbPagesMax, nbPages);
    }

    public static void search(SearchField search, int page) {
        if (search.promo_from == null) {
            search.promo_from = 0;
        }
        if (search.promo_to == null) {
            search.promo_to = new Date().getYear() + 1900;
        }
        if (page == 0) {
            page = 1;
        }
        List<User> all = User.find("select u from User u where "
                + "u.firstName like ? and u.lastName like ? "
                + "and (u.promotion >= ? and u.promotion <= ? or u.promotion is null) "
                + "order by lastName asc",
                "%" + search.firstName + "%", "%" + search.lastName + "%", search.promo_from, search.promo_to).fetch();
        long size = all.size();
        int nbPagesMax = 10;
        int nbPages = (int) (size / nbPagesMax);
        if (nbPagesMax * nbPages < size) {
            nbPages = nbPages + 1;
        }
        List<User> users = new ArrayList<User>();
        for (int i = 10 * (page - 1); i < all.size() && i < page * 10; i++) {
            users.add(all.get(i));
        }
        render(users, size, page, nbPagesMax, nbPages, search);
    }

    /**
     *
     * @param id
     */
    public static void editUser(@Required Long id) {
        User connected = connected();
        if (!connected.contains(RightEnum.EDIT_USER.name())) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        validation.required(id);
        if (Validation.hasErrors()) {
            flash.error("Please select a user");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        User user = User.findById(id);
        if (user == null) {
            flash.error("User doesn't exist");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        List<Role> roles = Role.findAll("code asc");
        List<Dendrite> dendrites = Dendrite.all().fetch();
        List<Poste> postes = Poste.findAll("title asc");
        render(dendrites, user, roles, postes);
    }

    /**
     *
     * @param user_id
     * @param roleCode
     */
    public static void updateUserRoles(@Required Long user_id, String[] roleCode) {
        User connected = connected();
        if (!connected.contains(RightEnum.ADD_AND_REMOVE_ROLE_USER.name())) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        validation.required(user_id);
        validation.required(roleCode);
        if (Validation.hasErrors()) {
            flash.error("Please select a user");
            users(null, 1);
            return;
        }
        User user = User.findById(user_id);
        if (user == null) {
            flash.error("User don't find");
            users(null, 1);
            return;
        }

        user.roles.clear();

        for (String rc : roleCode) {
            user.attributeRole(rc);
        }
        flash.success("Mise à jour effectuée");
        editUser(user.id);
    }

    /**
     *
     * @param dendriteId
     * @param userId
     * @param page
     */
    public static void updateDendriteUsers(Long dendriteId, Long[] userId, int page) {
        User connected = connected();
        if (!connected.contains(RightEnum.ADD_AND_REMOVE_USER_TO_DENDRITE.name())) {
            flash.error("Please contact a super user because you do not have right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (page == 0) {
            page = 1;
        }
        if (userId == null) {
            userId = new Long[]{};
        }
        Dendrite dendrite = Dendrite.findById(dendriteId);
        if (dendrite == null) {
            flash.error("Dendrite introuvable.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        System.out.println("page: " + page);
        List<User> fromPage = Users.users(page, "lastName asc");
        System.out.println("top: " + fromPage);
        List<User> myUsers = dendrite.users();
        List<User> newers = new ArrayList<User>();
        int add = 0;
        for (Long id : userId) {
            User user = User.findById(id);
            if (!myUsers.contains(user)) {
                add++;
                user.dendrite = dendrite;
                user.save();
                myUsers.add(user);
            }
            newers.add(user);
        }
        fromPage.retainAll(myUsers);
        System.out.println("bottom: " + fromPage);
        int remove = 0;
        for (User user : fromPage) {
            if (!newers.contains(user)) {
                user.dendrite = null;
                user.save();
                remove++;
            }
        }
        flash.success("Dendrite %s mise à jour avec succès : %d membre(s) ajouté(s) et %d enlévé(s)", dendrite.named, add, remove);
        editDendrite(dendriteId, page);
    }

    /**
     *
     * @param dendriteId
     * @param userId
     * @param page
     */
    public static void updateDendritePostes(Long dendriteId, Long[] posteId, int page) {
        User connected = connected();
        if (!connected.contains(RightEnum.ADD_AND_REMOVE_POSTE_TO_DENDRITE.name())) {
            flash.error("Please contact a super user because you do not have right to do this action.");
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
            flash.error("Dendrite introuvable.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        List<Poste> myPostes = dendrite.postes;
        List<Poste> newers = new ArrayList<Poste>();
        int add = 0;
        for (Long id : posteId) {
            Poste poste = Poste.findById(id);
            if (!myPostes.contains(poste)) {
                dendrite.postes.add(poste);
                add++;
            }
            newers.add(poste);
        }
        int remove = 0;
        for (int i = 0; i < dendrite.postes.size(); i++) {
            Poste poste = dendrite.postes.get(i);
            if (!newers.contains(poste)) {
                dendrite.postes.remove(i);
                remove++;
            }
        }
        dendrite.save();
        flash.success("Dendrite %s mise à jour avec succès : %d poste(s) ajouté(s) et %d enlévé(s)", dendrite.named, add, remove);
        editDendrite(dendriteId, page);
    }

    /**
     *
     * @param user
     * @param verifyPassword
     * @param dendriteCode
     * @param fromURL
     */
    public static void updateUser(@Valid User user, String password, String verifyPassword,
            String dendriteCode, String fromURL, String boursier, String active, @Required Integer civilityCode, String emailRedirect) {
        User connected = connected();
        Dendrite dendrite = Dendrite.findByCode(dendriteCode);
        user.dendrite = dendrite;
        if (!connected.canEdit(user)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (boursier == null) {
            user.boursier = false;
        } else {
            user.boursier = true;
        }

        if (active == null) {
            user.active = false;
        } else {
            user.active = true;
        }
        if (!user.password.equals(password)) {
            validation.required(verifyPassword).message("La vérification du mot de passe est obligatoire");
            validation.equals(verifyPassword, password).message("Les mots de passe ne correspondent pas.");
        }

        validation.equals(user.boursier, user.promotion != null).key("user.boursier").message("La promotion est obligatoire");
        validation.required(civilityCode).message("Civilité est obligatoire");
        if (Validation.hasErrors()) {
            flash.error("Les informations sont incorrectes");
            flash.put("fromURL", fromURL);
            List<Dendrite> dendrites = Dendrite.all().fetch();
            render("@editUser", user, user.id, dendrites);
            //message();
        }
        if (notNull(password) && password.equals(verifyPassword)) {
            user.password = bcrypt.hashpw(password, BCrypt.gensalt());
        }
        CivilityEnum civility = CivilityEnum.findByCode(civilityCode);
        user.civility = civility;
        User exist = null;
        if (user.emailRedirect != null && !user.emailRedirect.equals(emailRedirect)) {
            if (emailRedirect != null) {
                exist = User.findByEmail(emailRedirect);
            }
            if (exist == null) {
                exist = User.findByEmailRedirect(emailRedirect);
            }
        }
        if (exist == null) {
            user.emailRedirect = emailRedirect;
            user.save();
            flash.success("Mise à jour effectuée pour " + user.firstName);
            editUser(user.id);
        } else {
            Validation.addError("emailRedirect", "Utilisateur inexistant.", "emailRedirect");
            flash.error("Renseigner un autre email de redirection...");
            List<Dendrite> dendrites = Dendrite.all().fetch();
            List<Role> roles = Role.findAll("code asc");
            render("@editUser", dendrites, user, roles, emailRedirect);
        }
    }

    /**
     * remove the user from database
     *
     * @param id
     */
    public static void deleteUser(@Required Long id) {
        User connected = connected();
        if (!connected.contains(RightEnum.DELETE_USER.name())) {
            flash.error("Please contact a super user because you do not have right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
        }
        User user = User.findById(id);
        if (user == null) {
            flash.error("Utilisateur non trouvé");
            users(null, 1);
            return;
        }
        user.delete();
        flash.success("Utilisateur supprimé avec succès");
        Http.Header referer = request.headers.get("referer");
        if (referer != null) {
            redirect(referer.value());
        } else {
            index();
        }
    }

    /**
     *
     */
    public static void roles() {
        List<Role> roles = Role.all().fetch();
        render(roles);
    }

    /**
     *
     * @param id
     */
    public static void editRole(@Required Long id) {
        User connected = connected();
        if (!connected.contains(RightEnum.EDIT_ROLE.name())) {
            flash.error("Please contact a super user because you do not have right to do this action.");
            roles();
            return;
        }
        Role role = null;
        if (id != null) {
            role = Role.findById(id);
        }
        if (role == null) {
            role = new Role();
        }
        List<Right> rights = Right.findAll("code asc");
        render(role, rights);
    }

    /**
     *
     * @param role
     */
    public static void updateRole(@Valid Role role) {
        User connected = connected();
        if (!connected.contains(RightEnum.EDIT_ROLE.name())) {
            flash.error("Please contact a super user because you do not have right to do this action.");
            roles();
            return;
        }
        if (Validation.hasErrors()) {
            flash.error("Vérifiez les informations");
            render("@editRole", role, role.id);
            return;
        }
        role.save();
        flash.success("Mise à jour de " + role.code);
        editRole(role.id);
    }

    /**
     *
     * @param role
     * @param rightCode
     */
    public static void saveRole(@Valid Role role, Long[] rightCode) {
        User connected = connected();
        if (!connected.contains(RightEnum.EDIT_ROLE.name())) {
            flash.error("Please contact a super user because you do not have right to do this action.");
            roles();
            return;
        }
        if (Validation.hasErrors()) {
            flash.error("Vérifiez les informations");
            render("@editRole", role, role.id);
            return;
        }
        role.save();
        flash.success("Nouveau role créé " + role.code);
        if (rightCode != null && rightCode.length != 0) {
            updateRoleRights(role.id, rightCode);
        } else {
            editRole(role.id);
        }
    }

    /**
     *
     * @param id
     */
    public static void editPoste(Long id) {
        User connected = connected();
        if (!connected.contains(RightEnum.EDIT_POSTE.name())) {
            flash.error("Please contact a super user because you do not have right to do this action.");
            postes();
            return;
        }
        Poste poste;
        if (id != null) {
            poste = Poste.findById(id);
            if (poste == null) {
                flash.error("Poste inexistant.");
                postes();
                return;
            }
        } else {
            poste = new Poste();
        }
        List<Role> roles = Role.findAll("code asc");
        render(roles, poste);
    }

    /**
     *
     * @param poste
     */
    public static void updatePoste(@Valid Poste poste, String general) {
        User connected = connected();
        if (!connected.contains(RightEnum.EDIT_POSTE.name())) {
            flash.error("Please contact a super user because you do not have right to do this action.");
            postes();
            return;
        }
        if (Validation.hasErrors()) {
            flash.error("Vérifiez les informations");
            render("@editPoste", poste, poste.id);
            return;
        }
        poste.general = general != null;
        poste.code = poste.title;
        poste.save();
        flash.success("Mise à jour de " + poste.title + " effectuée");
        editPoste(poste.id);
    }

    /**
     *
     * @param poste_id
     * @param roleCode
     */
    public static void updatePosteRoles(@Required Long poste_id, String[] roleCode) {
        User connected = connected();
        if (!connected.contains(RightEnum.ADD_AND_REMOVE_ROLE_TO_POSTE.name())) {
            flash.error("Please contact a super user because you have not right to do this action.");
            index();
            return;
        }
        validation.required(poste_id);
        validation.required(roleCode);
        if (Validation.hasErrors()) {
            flash.error("Please select a user");
            users(null, 1);
            return;
        }
        Poste poste = Poste.findById(poste_id);
        if (poste == null) {
            flash.error("Poste don't find");
            postes();
            return;
        }

        poste.roles.clear();

        for (String rc : roleCode) {
            Role role = Role.findByCode(rc);
            poste.roles.add(role);
        }
        poste.save();
        flash.success("Mise à jour effectuée poste %s", poste.title);
        editPoste(poste.id);
    }

    /**
     *
     * @param role_id
     * @param rightCode
     */
    public static void updateRoleRights(@Required Long role_id, Long[] rightCode) {
        User connected = connected();
        if (!connected.contains(RightEnum.ADD_AND_REMOVE_RIGHT_ROLE.name())) {
            flash.error("Please contact a super user because you have not right to do this action.");
            index();
            return;
        }
        validation.required(role_id);
        validation.required(rightCode);
        if (Validation.hasErrors()) {
            flash.error("Please select a role");
            roles();
            return;
        }
        Role role = Role.findById(role_id);
        if (role == null) {
            flash.error("Role don't find");
            roles();
            return;
        }
        System.out.println(role.asRoleRights.size());
        List<RoleRight> l2 = new ArrayList<RoleRight>();
        for (Long rc : rightCode) {
            l2.add(new RoleRight(role_id, rc));
        }
        Set<RoleRight> toDel = new HashSet<RoleRight>();
        toDel.addAll(role.asRoleRights);
        Set<RoleRight> toAdd = new HashSet<RoleRight>();
        toAdd.addAll(l2);
        toDel.removeAll(l2);
        toAdd.removeAll(role.asRoleRights);
        for (RoleRight arr : toAdd) {
            arr.save();
        }
        for (RoleRight arr : toDel) {
            arr.delete();
        }
        flash.success("Mise à jour effectuée, %d ajouté(s) et %d supprimé(s)", toAdd.size(), toDel.size());
        editRole(role.id);
    }

    /**
     *
     * @param id
     */
    public static void deleteRole(@Required Long id) {
        User connected = connected();
        if (!connected.contains(RightEnum.DELETE_ROLE.name())) {
            flash.error("Please contact a super user because you do not have right to do this action.");
            roles();
            return;
        }
        Role role = Role.findById(id);
        role.delete();
        flash.success("Role supprimé avec succès");
        Http.Header referer = request.headers.get("referer");
        if (referer != null) {
            redirect(referer.value());
        } else {
            index();
        }
    }

    /**
     *
     * @param id
     */
    public static void deletePoste(@Required Long id) {
        User connected = connected();
        if (!connected.contains(RightEnum.DELETE_POSTE.name())) {
            flash.error("Please contact a super user because you do not have right to do this action.");
            roles();
            return;
        }
        Poste poste = Poste.findById(id);
        poste.delete();
        flash.success("Poste supprimé avec succès");
        //postes();
        Http.Header referer = request.headers.get("referer");
        if (referer != null) {
            redirect(referer.value());
        } else {
            index();
        }
    }

    /**
     *
     */
    public static void postes() {
        List<Poste> postes = Poste.findAll("title asc");
        render(postes);
    }

    public static void feedbacks(int page) {
        if (page == 0) {
            page = 1;
        }
        List<Feedback> feedbacks = Feedback.find("order by progress asc, created desc").fetch(5 * page);
        TriGenerique<Feedback> tri = new TriGenerique<Feedback>();
        CompareFunction sd = new StandardComparable();
        feedbacks = tri.mergeSort(feedbacks, sd);
        long maxSize = Feedback.count();
        int size = feedbacks.size();
        render(feedbacks, page, maxSize, size);
    }

    public static void updateProgress(Long feedback_id, Integer progress) {
        if (feedback_id == null) {
            renderJSON(new String[]{"Le feeback est introuvable."});
            return;
        }
        Feedback feed = Feedback.findById(feedback_id);
        User connected = connected();
        if (!connected.canEdit(feed)) {
            renderJSON(new String[]{"Please contact a super user because you have not right to do this action."});
            return;
        }
        if (progress != feed.progress) {
            Evolution evolution = new Evolution();
            evolution.feedback = feed;
            evolution.description = "Progression de " + feed.progress + " à " + progress;
            evolution.priority = feed.priority;
            evolution.progress = progress;
            evolution.save();
            feed.progress = progress;
            feed.save();
            renderJSON(new String[]{"La progression a été enregistrée"});
        }
    }

    public static void feedback(Long id) {
        if (id == null) {
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        Feedback feedback = Feedback.findById(id);
        User connected = connected();
        if (!connected.canEdit(feedback)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        List<Evolution> evolutions = feedback.evolutios;
        List<User> devs = findDevs();
        render(feedback, evolutions, devs);
    }

    public static void deleteFeedback(Long id) {
        if (id == null) {
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        Feedback feedback = Feedback.findById(id);
        User connected = connected();
        if (!connected.hasRight(RightEnum.DELETE_FEEDBACK)) {
            if (!connected.hasRight(RightEnum.DELETE_MY_FEEDBACK)) {
                flash.error("Please contact a super user because you have not right to do this action.");
                Http.Header referer = request.headers.get("referer");
                if (referer != null) {
                    redirect(referer.value());
                } else {
                    index();
                }
                return;
            }
        }
        if (feedback == null) {
            flash.error("Feedback non trouvé");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        feedback.delete();
        Http.Header referer = request.headers.get("referer");
        if (referer != null) {
            redirect(referer.value());
        } else {
            index();
        }
    }

    /**
     *
     * @param out
     */
    public static void sendMessage() {
        User connected = connected();
        if (!connected.canSendMessage(connected.dendrite)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        boolean m = true;
        render(m);
    }

    /**
     *
     * @param content
     * @param subject
     * @param dendriteId
     * @param out
     */
    public static void sendMail(@Required String content, String subject, @Required Long dendriteId, String m) {
        User connected = connected();
        Dendrite dendrite = Dendrite.findById(dendriteId);
        if (!connected.canSendMessage(dendrite)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (!notNull(subject)) {
            subject = "Aucun object";
        }
        validation.required(dendriteId);
        validation.required(content);
        validation.equals(dendriteId == null, false);
        if (Validation.hasErrors()) {
            flash.error(Messages.get("CorrectErrors"));
            render("@sendMessage", content, subject, dendriteId, m);
            return;
        }
        //ConversUserDendriteId cudid = new ConversUserDendriteId(connected.id, dendriteId);
        ConversUserDendrite cud = ConversUserDendrite.find("dendrite = ?", dendrite).first();
        if (cud == null) {
            cud = new ConversUserDendrite(connected.id, dendriteId);
            cud.save();
        }
        Message mail = new Message();
        mail.author = connected;
        mail.content = content;
        mail.subject = subject;
        mail.cud = cud;

        try {
            Mails.sendMessage(cud.dendrite, mail, m != null);
            cud.save();
            mail.save();
        } catch (Exception ex) {
            Logger.getLogger(Conversations.class.getName()).log(Level.SEVERE, null, ex);
        }
        messages();
    }

    public static void messages() {
        User connected = connected();
        List<Message> conMessages = Message.find("author = ? and cud.dendrite != null order by created desc", connected).fetch();
        render(conMessages);
    }

    private static List<User> findDevs() {
        List<User> devs = User.find("select distinct u from User u join u.roles r where r.code = ? order by u.lastName asc", "DEV").fetch();
        return devs;
    }

    /**
     *
     * @param feedback
     * @param responsableId
     */
    public static void updateFeedback(@Valid Feedback feedback, Long responsableId, Integer oldPriority, Integer oldProgress) {
        User connected = connected();
        if (!connected.canEdit(feedback)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        User responsable = null;
        if (oldPriority == null) {
            oldPriority = 0;
        }
        if (oldProgress == null) {
            oldProgress = 0;
        }
        if (Validation.hasErrors()) {
            flash.error("Veuillez corriger les erreurs s'il vous plait.");
            List<Evolution> evolutions = feedback.evolutios;
            List<User> devs = findDevs();
            render("@feedback", feedback, responsableId, evolutions, devs);
        }
        if (responsableId != null) {
            responsable = User.findById(responsableId);
        }
        Evolution evolution = new Evolution();
        evolution.feedback = feedback;
        evolution.description = "";
        boolean changed = false;
        if (oldProgress != feedback.progress) {
            changed = true;
            evolution.description = "Progression de " + oldProgress + " à " + feedback.progress;
        }
        if (oldPriority != feedback.priority) {
            if (changed) {
                evolution.description += "\nChangement de priorité de " + oldPriority + " à " + feedback.priority;
            } else {
                evolution.description += "Changement de priorité de " + oldPriority + " à " + feedback.priority;
            }
            changed = true;
        }
        feedback.responsable = responsable;
        feedback.save();
        if (changed) {
            evolution.save();
        }
        feedback(feedback.id);
    }

    /**
     * Updating all rights on RightEnum witch are not in table T_RIGHTS.
     */
    public static void updateRights() {
        User connected = connected();
        if (!connected.hasRight(RightEnum.CREATE_RIGHT)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        List<Right> rights = Right.findAll();
        RightEnum[] res = RightEnum.values();
        List<Right> fromEnum = new ArrayList<Right>();
        for (RightEnum re : res) {
            fromEnum.add(new Right(re.name()));
        }
        fromEnum.removeAll(rights);
        int count = 0;
        for (Right r : fromEnum) {
            r.save();
            System.out.println(r.code);
            count++;
        }
        flash.success("Nouveau(x) droit(s): " + count);
        Http.Header referer = request.headers.get("referer");
        if (referer != null) {
            redirect(referer.value());
        } else {
            index();
        }
    }

    /**
     *
     * @param userId
     * @param posteId
     * @param description
     */
    public static void addPosteToUser(@Required Long userId, @Required Long posteId, @Required Long electionId, Date beginDate, Date endDate, String description) {
        User connected = connected();
        if (!connected.hasRight(RightEnum.ADD_AND_REMOVE_POSTE_TO_USER)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        User user = User.findById(userId);
        if (user == null) {
            flash.error("L'utilisateur est obligatoire!");
            List<Dendrite> dendrites = Dendrite.all().fetch();
            List<Role> roles = Role.findAll("code asc");
            List<Poste> postes = Poste.findAll("title asc");
            render("@editUser", userId, posteId, electionId, description, dendrites, user, roles, postes);
            return;
        }
        user.mandates.clear();
        user.save();
        int count = 0;
        String titles = "";
        Poste poste = Poste.findById(posteId);
        Election election = Election.findById(electionId);
        if (poste != null) {
            user.addPoste(poste, election, description);
            titles += poste.title;
            count++;
        }
        flash.success("Poste ajouté" + titles + " total: " + count);
        Http.Header referer = request.headers.get("referer");
        if (referer != null) {
            redirect(referer.value());
        } else {
            index();
        }
    }

    /**
     *
     */
    public static void mandates(Long electionId) {
        String electionTitle = "Tout", electionRequest = "";
        if (electionId != null) {
            Election election = Election.findById(electionId);
            if (election != null) {
                electionTitle = election.code;
                electionRequest = election.id + "";
            }
        }
        if (electionId != null) {
            List<Mandate> mandates = Mandate.find("select m from Mandate m where m.election_id = ?", electionId).fetch();
            render(mandates, electionTitle);
        } else {
            List<Mandate> mandates = Mandate.findAll();
            render(mandates, electionTitle);
        }
    }

    /**
     *
     * @param mandate
     */
    public static void createMandate(@Valid Mandate mandate) {
        User connected = connected();
        if (!connected.hasRight(RightEnum.ADD_AND_REMOVE_POSTE_TO_USER)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (Validation.hasErrors()) {
            flash.error("Veuillez corriger les erreurs s'il vous plait.");
            List<Election> elections = Election.findAll();
            List<Poste> postes = mandate.dendrite.getPostes();
            mandate.user = User.findById(mandate.user_id);
            mandate.poste = Poste.findById(mandate.poste.id);
            mandate.election = Election.findById(mandate.election_id);
            mandate.dendrite = Dendrite.findById(mandate.dendrite_id);
            render("@editMandate", mandate, elections, postes);
            return;
        }
        List<Mandate> oldMandate = Mandate.find("poste.id = ? and election.id = ? "
                + "and dendrite.id = ?", mandate.poste.id, mandate.election_id, mandate.dendrite_id).fetch();
        if (oldMandate.isEmpty() || oldMandate.get(0).poste.equals(mandate.poste)) {
            mandate.save();
            flash.success("Le mandat est mis à jour!");
            mandates(mandate.election_id);
        } else {
            List<Election> elections = Election.findAll();

            flash.error("Ce poste est déjà occupé.");
            mandate.user = User.findById(mandate.user_id);
            mandate.poste = Poste.findById(mandate.poste.id);
            mandate.election = Election.findById(mandate.election_id);
            mandate.dendrite = Dendrite.findById(mandate.dendrite_id);
            List<Poste> postes = mandate.dendrite.getPostes();
            Validation.addError("mandate.poste.id", "Ce poste est déjà occupé");
            render("@editMandate", mandate, elections, postes);
        }
    }

    /**
     *
     * @param userId
     * @param posteId
     * @param electionId
     */
    public static void editMandate(Long userId, Long posteId, Long dendriteId, Long electionId) {
        User connected = connected();
        if (!connected.hasRight(RightEnum.ADD_AND_REMOVE_POSTE_TO_USER)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
//        if (userId == null) {
//            flash.error("L'utilisateur est requis pour la création d'un mandat.");
//            Http.Header referer = request.headers.get("referer");
//            if (referer != null) {
//                redirect(referer.value());
//            } else {
//                index();
//            }
//            return;
//        }
        List<Poste> postes = new ArrayList<Poste>();
        Mandate mandate = null;
        if (posteId != null && dendriteId != null && electionId != null && userId != null) {
            MandateId mid = new MandateId(userId, /*posteId,*/ dendriteId, electionId);
            mandate = Mandate.findById(mid);
            postes = mandate.dendrite.getPostes();
        }
        if (mandate == null) {
            mandate = new Mandate(userId, /*posteId,*/ dendriteId, electionId);
            if (userId != null) {
                User user = User.findById(userId);
                mandate.user = user;
            }
            if (posteId != null) {
                Poste poste = Poste.findById(posteId);
                mandate.poste = poste;
            }
            List<Dendrite> dendrites = Dendrite.findDendrites("named asc");
            List<Election> elections = Election.findAll();
            render(mandate, elections, postes, dendrites);
        }
        List<Election> elections = Election.findAll();
        render(mandate, elections, postes);
    }

    /**
     *
     * @param userId
     * @param posteId
     * @param dendriteId
     * @param electionId
     */
    public static void deleteMandate(Long userId, Long posteId, Long dendriteId, Long electionId) {
        User connected = connected();
        if (!connected.hasRight(RightEnum.ADD_AND_REMOVE_POSTE_TO_USER)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (userId != null && posteId != null && dendriteId != null && electionId != null) {
            MandateId mid = new MandateId(userId, /*posteId,*/ dendriteId, electionId);
            Mandate mandate = Mandate.findById(mid);
            if (mandate != null) {
                mandate.delete();
                flash.success("Mandat supprimé avec succès!");
            }
        } else {
            flash.error("Mandat non trouvé!");
        }
        Http.Header referer = request.headers.get("referer");
        if (referer != null) {
            redirect(referer.value());
        } else {
            index();
        }
    }

    /**
     * ajax! Pour changer la liste des postes.
     *
     * @param id
     */
    public static void dendritePosteList(Long id) {
        if (id == null) {
            renderJSON(new String[]{});
            return;
        }
        Dendrite dendrite = Dendrite.findById(id);
        if (dendrite == null) {
            renderJSON(new String[]{});
            return;
        }
        List<Poste> postes = dendrite.getPostes();
        String postesString = "{" + "\"general\": \"" + (dendrite.general ? "Cellule" : "Dendrite") + "\"" + (postes.isEmpty() ? "" : ", ");
        for (int i = 0; i < postes.size(); i++) {
            Poste p = postes.get(i);
            postesString += "\"" + p.id + "\": \"" + p.title + "\"" + (i != postes.size() - 1 ? ", " : "");
        }
        postesString += "}";
        renderJSON(postesString);
    }
}
