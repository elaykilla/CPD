/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.Administration.editUser;
import static controllers.Application.CV_LIMIT_YEAR;
import static controllers.Application.bcrypt;
import static controllers.Application.connected;
import static controllers.Application.index;
import static controllers.Application.logger;
import static controllers.Application.notNull;
import static controllers.Application.redirection;
import static controllers.Articles.recents;
import java.io.File;
import java.util.List;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.CivilityEnum;

import models.Dendrite;
import models.Photo;
import models.Role;
import models.SearchField;
import models.Searchable;
import models.User;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.i18n.Messages;
import play.libs.Codec;
import services.BCrypt;
import notifiers.Mails;
import play.data.validation.Validation;
import models.articles.Announce;
import models.articles.Article;
import models.events.Event;
import models.feedback.Feedback;
import play.mvc.Http;
import play.mvc.Http.Header;
import services.CompareFunction;
import services.StandardComparable;
import services.TriGenerique;

/**
 *
 * @author Sissoko
 */
public class Users extends Application {

    /**
     * display signup template.
     */
    public static void signup() {
        if (renderArgs.get("connected") != null) {
            index();
        }
        User user = new User();
        List<Dendrite> dendrites = Dendrite.findAll("named asc");
        String ass = "signup";
        render("@acelogin", dendrites, user, ass);
    }

    /**
     * create new user in database.
     *
     * @param user
     * @param verifyPassword
     * @param fromURL
     */
    public static void register(User user, String verifyPassword,
            String dendriteCode, String fromURL, String boursier, @Required Integer civilityCode) {
        validation.required(verifyPassword).message("La vérification du mot de passe est obligatoire");
        validation.equals(verifyPassword, user.password).message("Les mots de passe ne correspondent pas.");
        String ass = "signup";
        if (boursier == null) {
            user.boursier = false;
        } else {
            user.boursier = true;
        }
        User old = null;
        if (notNull(user.facebookId)) {
            old = User.findByEmail(user.email);
        } else {
            validation.valid(user);
        }
        User existent = User.findByEmailRedirect(user.email);
        if (existent != null) {
            Validation.addError("user.email", "Email déjà utilisé comme email de redirection.", "user.email");
        }
        validation.required(civilityCode).message("La civilité est obligatoire");
        validation.equals(user.boursier, user.promotion != null).message("Veillez verifier la promotion");
        Dendrite dendrite = Dendrite.findByCode(dendriteCode);
        user.dendrite = dendrite;
        if (Validation.hasErrors()) {
            flash.error(Messages.get("CorrectErrors"));
            flash.put("fromURL", fromURL);
            List<Dendrite> dendrites = Dendrite.findAll("name asc");
            render("@acelogin", user, dendrites, ass);
            return;
        }
        user.password = bcrypt.hashpw(user.password, BCrypt.gensalt());
        user.confirmToken = Codec.UUID();
        CivilityEnum civility = CivilityEnum.findByCode(civilityCode);
        user.civility = civility;
        if (old == null) {
            String email = (user.firstName + "." + user.lastName).toLowerCase().replaceAll(" ", "-") + user.promotion + "@cpd-mali.com";
            email = email.replaceAll("é", "e");
            email = email.replaceAll("è", "e");
            email = email.replaceAll("ê", "e");
            email = email.replaceAll("ï", "i");
            email = email.replaceAll("à", "a");
            email = email.replaceAll("â", "a");
            old = User.findByEmail(email);
        }
        if (old != null && (old.connexion == null || old.connexion == 0)) {
            old.emailRedirect = user.email.equals(old.email) ? old.emailRedirect : user.email;
            old.birthday = user.birthday;
            old.boursier = user.boursier;
            old.civility = user.civility;
            old.password = user.password;
            old.promotion = user.promotion;
            old.dendrite = user.dendrite;
            old.facebookId = user.facebookId;
            old.facebookToken = user.facebookToken;
            old.save();
            try {
                Mails.welcome(user);
                flash.success("Bienvenue sur CPD", user.firstName);
                session.put("user", user.email);
                session.put("username", user.email);
                index();
            } catch (Exception ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                if (!notNull(user.facebookId)) {
                    user.save();
                    Mails.confirm(user);
                    flash.success(Messages.get("confirm.user", user.firstName));
                } else {
                    user.confirmToken = null;
                    user.save();
                    Mails.welcome(user);
                    flash.success("Bienvenue sur CPD", user.firstName);
                    session.put("user", user.email);
                    session.put("username", user.email);
                }
                //String email = user.email;
                index();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed to send email: {0}", e.getMessage());
                flash.error(Messages.get("error.occured"));
            }
        }
    }

    /**
     *
     * @param id
     */
    public static void editUser(Long id, String act) {
        User connected = connected();
        User user = User.findById(id);
        if (connected == null || !connected.id.equals(id)) {
            flash.error("Vous n'êtes pas autorisé à modifier ce profil.");
            if (connected != null) {
                if (user != null) {
                    user(id, user.fullName());
                } else {
                    user(connected.id, connected.fullName());
                }
            } else {
                login();
            }
        } else {
            if (user == null) {
                flash.error("Utilisateur inexistant");
                index();
                return;
            }
            List<Dendrite> dendrites = Dendrite.findAll("named asc");
            List<Integer> years = Cvs.yearsToNow(CV_LIMIT_YEAR);
            List<String> months = Cvs.months();
            List<String> languages = Cvs.languages();
            List<String> levels = Cvs.levels();
            render(user, dendrites, years, months, languages, levels, act);
        }

    }

    /**
     * Ajax caller
     *
     * @param id
     * @param act
     */
    public static void userajax(Long id, String act) {
        User connected = connected();
            User user = User.findById(id);
        if (connected == null || !connected.id.equals(id)) {
            flash.error("Vous n'êtes pas autorisé à modifier ce profil.");
            if (connected != null) {
               if (user != null) {
                    user(id, user.fullName());
                } else {
                    user(connected.id, connected.fullName());
                }
            } else {
                login();
            }
        } else {
            if (user == null) {
                flash.error("Utilisateur inexistant");
                index();
                return;
            }
            List<Dendrite> dendrites = Dendrite.findAll("named asc");
            List<Integer> years = Cvs.yearsToNow(CV_LIMIT_YEAR);
            List<String> months = Cvs.months();
            List<String> languages = Cvs.languages();
            List<String> levels = Cvs.levels();
            render(user, dendrites, years, months, languages, levels, act);
        }
    }

    /**
     *
     * @param user
     * @param verifyPassword
     * @param dendriteCode
     * @param fromURL
     */
    public static void updateUser(@Valid User user, String verifyPassword, @Required String password,
            String dendriteCode, String fromURL, String boursier, String act, @Required Integer civilityCode, String emailRedirect) {
        User connected = connected();
        if (!connected.canEdit(user)) {
            flash.error("Please contact a super user because you have not right to do this action.");
            Header referer = request.headers.get("referer");
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

        if (!connected.password.equals(password)) {
            validation.required(verifyPassword).message("La vérification du mot de passe est obligatoire");
            validation.equals(verifyPassword, password).message("Les mots de passe ne correspondent pas.");
        }

        validation.required(password).message("Le mot de passe est obligatoire");
        validation.equals(user.boursier, user.promotion != null).message("La promotion est obligatoire");
        validation.required(civilityCode);
        if (Validation.hasErrors()) {
            flash.error("Les informations sont incorrectes");
            flash.put("fromURL", fromURL);
            List<Dendrite> dendrites = Dendrite.all().fetch();
            render("@editUser", user, user.id, dendrites, act, civilityCode);
        }
        if (notNull(password) && password.equals(verifyPassword)) {
            user.password = bcrypt.hashpw(password, BCrypt.gensalt());
        }
        CivilityEnum civility = CivilityEnum.findByCode(civilityCode);
        user.civility = civility;
        if (emailRedirect != null && !emailRedirect.equals(connected.emailRedirect)) {
            User exist = null;
            if (!emailRedirect.equals(user.emailRedirect)) {
                exist = User.findByEmail(emailRedirect);
                if (exist == null) {
                    exist = User.findByEmailRedirect(emailRedirect);
                }
            }
            if (exist == null) {
                user.emailRedirect = emailRedirect;
                user.confirmRedirect = Codec.UUID();
                user.save();
                Mails.confirmRedirect(user);
                flash.success("Mise à jour effectuée %s, un email de validation de "
                        + "votre email de redirection vous a été envoyé", user.firstName);
                editUser(user.id, act);
            } else {
                Validation.addError("emailRedirect", "Utilisateur inexistant.", "emailRedirect");
                flash.error("Renseigner un autre email de redirection...");
                List<Dendrite> dendrites = Dendrite.findAll("named asc");
                List<Integer> years = Cvs.yearsToNow(CV_LIMIT_YEAR);
                List<String> months = Cvs.months();
                List<String> languages = Cvs.languages();
                List<String> levels = Cvs.levels();
                render("@editUser", user, dendrites, years, months, languages, levels, emailRedirect, act);
            }
        } else {
            user.save();
            flash.success("Mise à jour effectuée %s", user.firstName);
            editUser(user.id, act);
        }
    }

    /**
     *
     * @param confirmToken
     */
    public static void confirm(String confirmToken, String confirmRedirect) {
        User user = null;
        if (notNull(confirmToken)) {
            user = User.find("confirmToken", confirmToken).first();
        } else if (notNull(confirmRedirect)) {
            user = User.find("confirmRedirect", confirmRedirect).first();
        }
        if (user == null) {
            flash.error("Utilisateur non trouvé!");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (notNull(confirmToken)) {
            user.confirmToken = null;
        } else if (notNull(confirmRedirect)) {
            user.confirmRedirect = null;
        }
        user.save();
        try {
            if (notNull(confirmToken)) {
                Mails.welcome(user);
                flash.success("%s votre email a été vérifié avec succès vous pouvez vous connecter maintenant.", user.fullName());
                String ass = "login";
                render("@acelogin", user.email, ass);
            } else {
                flash.success("%s votre email a été vérifié avec succès vous pouvez vous connecter maintenant.", user.fullName());
                index();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to send email:{0}", e.getMessage());
            flash.error(Messages.get("error.occured"));
        }
    }

    /**
     *
     * @param id
     */
    public static void resendConfirm(Long id) {
        User user = User.findById(id);
        if (user == null) {
            flash.error("Utilisateur non trouvé");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (notNull(user.confirmToken)) {
            try {
                Mails.confirm(user);
                flash.success(Messages.get("confirm.user", user.firstName));
                //String email = user.email;
                index();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed to send email: {0}", e.getMessage());
                flash.error(Messages.get("error.occured"));
            }
        }
    }

    /**
     * display login template with a redirect link..
     *
     * @param fromURL
     */
    public static void login(String fromURL) {
        if (renderArgs.get("connected") != null) {
            index();
        }
        flash.put("fromURL", fromURL);
        render();
    }

    /**
     * display login template.
     */
    public static void login() {
        if (renderArgs.get("connected") != null) {
            Header header = request.headers.get("referer");
            if (header == null) {
                index();
                return;
            }
            String refer = header.value();
            if (refer != null && !refer.contains("login") && !refer.contains("confirm")) {
                redirect(refer);
            } else {
                index();
            }
        }
        String ass = "login";
        render("@acelogin", ass);
    }

    public static void acelogin() {
        User user = connected();
        if (user != null) {
            index();
        }
        String ass = "login";
        render(ass);
    }

    /**
     * logged in a user.
     *
     * @param email
     * @param password
     * @param fromURL
     */
    public static void loggedin(@Required String email,
            @Required String password, String fromURL) {
        validation.required(email).message("L'email est obligatoire.");
        validation.required(password).message("Le mot de passe est obligatoire.");
        User user = User.findByEmail(email);
        String ass = "login";
        if (Validation.hasErrors()) {
            flash.put("email", email);
            flash.error("Login ou mot de passe incorrect");
            flash.put("fromURL", fromURL);
            render("@acelogin", email, fromURL, ass);
        }
        if (user == null) {
            user = User.findByEmailRedirect(email);
            System.out.println("user: " + user);
            if (user == null) {
                Validation.addError("email", "Utilisateur inexistant.", "email");
            }
        }
        if (user != null && bcrypt.checkpw(password, user.password)) {
            if (notNull(user.confirmToken)) {
                flash.success("%s votre email n'a pas été vérifié. Veuillez vous "
                        + "connecter à votre adresse %s pour confirmer",
                        user.fullName(), user.emailRedirect == null ? user.email : user.emailRedirect);
                flash.put("email", email);
                render("@acelogin", email, fromURL, ass, user);
                return;
            }
            session.put("user", user.email);
            session.put("username", user.email);
            user.increConnexion();
            flash.success("Welcome back " + user.fullName());
            if (notNull(fromURL)) {
                redirect(fromURL);
                return;
            }
            Header header = request.headers.get("referer");
            if (header == null) {
                index();
                return;
            }
            String refer = header.value();
            if (refer != null && !refer.contains("login") && !refer.contains("confirm")) {
                redirect(refer);
            } else {
                index();
            }
        } else if (user != null) {
            flash.put("email", email);
            flash.put("fromURL", fromURL);
            flash.error(Messages.get("Mot de passe incorrect"));
            Validation.addError("password", "Mot de passe incorrect.");
            render("@acelogin", email, fromURL, ass);
        }
        // Oops
        flash.put("email", email);
        flash.put("fromURL", fromURL);
        flash.error(Messages.get("Login Failed"));
        Validation.addError("password", "Mot de passe incorrect.");
        render("@acelogin", email, fromURL, ass);
    }

    /**
     *
     * @param id
     */
    public static void user(@Required Long id, String username) {
        User connected = connected();
        if (connected == null) {
            flash.error("Vous devez être connecté pour accéder à cette page");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        User user = User.findById(id);
        render(user);
    }

    /**
     *
     */
    public static void profilePhoto() {
        render();
    }

    /**
     *
     * @param logo
     */
    public static void uploadPhoto(File logo, String description) {
        notFoundIfNull(logo);
        validation.required(logo);
        if (Validation.hasErrors()) {
            Validation.addError("logo", Messages.get("validation.required"));
            flash.error(Messages.get("CorrectErrors"));
        }

        if (logo.length() > PHOTO_LIMIT_SIZE) {
            flash.error("La taille limite autorisée est depassée");
            render("@profilePhoto", description);
        }
        User user = connected();
        Photo oldPhoto = user.photo;
        Photo photo = new Photo();
        photo.description = description;
        photo.saveFile(logo);
        Photo.saveProfileFile(logo, photo.title, "profile");
        Photo.saveProfileFile(logo, photo.title, "small");
        user.photo = photo;
        user.save();
        if (oldPhoto != null) {
            oldPhoto.delete();
        }
        flash.success("Votre profil a bien été mis à jour %s",
                user.fullName());
        user(user.id, user.fullName());
    }

    /**
     * Lorsqu'on clique sur mot de passe oublié cette méthode va chercher
     * l'utilisateur avec l'email fourni. Et lui envoyé un lien de mis à jour.
     *
     * @param email
     */
    public static void retrievePassword(String email) {
        validation.required(email);
        String ass = "forgot";
        if (Validation.hasErrors()) {
            flash.error(Messages.get("CorrectErrors"));
            render("@acelogin", email, ass);
        }
        User user = User.findByEmail(email);
        if (user == null) {
            // Oops
            flash.put("email", email);
            flash.error(Messages.get("password.dontexist"));
            password();
        } else {
            try {
                user.passwordToken = Codec.UUID();
                user = user.save();
                Mails.lostPassword(user);
                flash.success(Messages.get("password.emailsent"));
                index();

            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed to send email to {0} Message :{1}", new Object[]{user.email, e.getMessage()});
                flash.error(Messages.get("error.occured"));
                index();
            }
        }

    }

    /**
     * Juste l'affichage du champ de saisi de l'email
     */
    public static void password() {
        String ass = "forgot";
        render("@acelogin", ass);
    }

    /**
     * Il se charge juste de récupérer l'utilisateur et l'envoyer à la vue pour
     * pouvoir mettre à jour.
     *
     * @param passwordToken
     */
    public static void updatePassword(String passwordToken) {
        User user = User.findByPasswordToken(passwordToken);
        if (user == null) {
            flash.error("Votre lien de mise à jour a expiré ou incorrect.");
            index();
        }
        render(user);
    }

    /**
     * Met à jour le mot de passe de l'utilisateur.
     *
     * @param user
     * @param verifyPassword
     */
    public static void passwordUpdated(@Valid User user, String verifyPassword) {
        validation.required(verifyPassword);
        validation.equals(verifyPassword, user.password).message(
                Messages.get("password.match"));
        if (Validation.hasErrors()) {
            flash.error(Messages.get("CorrectErrors"));
            updatePassword(user.passwordToken);
            return;
        }
        user.password = bcrypt.hashpw(user.password, BCrypt.gensalt());
        user.passwordToken = null;
        user.save();
        flash.success(Messages.get("password.reset.success", user.firstName));
        Http.Header referer = request.headers.get("referer");
        if (referer != null) {
            redirect(referer.value());
        } else {
            index();
        }
    }

    public static void userInfo(Long id) {
        try {
            User user = User.findById(id);
            Integer promotion = user.promotion;
            String dendrite = "";
            if (user.dendrite != null) {
                dendrite = user.dendrite.named;
            }
            String[] reponse = {dendrite, promotion + ""};
            System.out.println(Arrays.toString(reponse));
            renderJSON(reponse);
        } catch (Exception e) {
            renderJSON(new String[]{"Dendrite non renseignée", "Promotion"});
        }
    }

    public static void articles(Long id) {
        User connected = connected();
        if (connected == null) {
            flash.error("Vous devez être connecté pour accéder à cette page");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        List<Article> articles;
        if (id == null) {
            flash.error("L'id ne doit pas être null");
            Header header = request.headers.get("referer");
            if (header == null) {
                index();
                return;
            }
            String refer = header.value();
            if (refer != null) {
                redirect(refer);
            } else {
                index();
            }
            return;
        }
        User user = User.findById(id);
        if (user == null) {
            flash.error("L'utilisateur introuvable");
            return;
        }
        articles = user.articles();
        List<Searchable> recents = recents(RECENTS_MAX_SIZE);
        render(articles, recents, user);
    }

    public static void announces(Long id) {
        User connected = connected();
        if (connected == null) {
            flash.error("Vous devez être connecté pour accéder à cette page");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (id == null) {
            flash.error("L'id ne doit pas être null");
            Header header = request.headers.get("referer");
            if (header == null) {
                index();
                return;
            }
            String refer = header.value();
            if (refer != null) {
                redirect(refer);
            } else {
                index();
            }
            return;
        }
        User user = User.findById(id);
        if (user == null) {
            flash.error("L'utilisateur introuvable");
            Header header = request.headers.get("referer");
            if (header == null) {
                index();
                return;
            }
            String refer = header.value();
            if (refer != null) {
                redirect(refer);
            } else {
                index();
            }
            return;
        }
        List<Announce> announces = user.announces();
        List<Searchable> recents = recents(RECENTS_MAX_SIZE);
        Dendrite dendrite = user.dendrite;
        render(announces, recents, user, dendrite);
    }

    public static void events(Long id) {
        User connected = connected();
        if (connected == null) {
            flash.error("Vous devez être connecté pour accéder à cette page");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (id == null) {
            flash.error("L'id ne doit pas être null");
            Header header = request.headers.get("referer");
            if (header == null) {
                index();
                return;
            }
            String refer = header.value();
            if (refer != null) {
                redirect(refer);
            } else {
                index();
            }
            return;
        }
        User user = User.findById(id);
        if (user == null) {
            flash.error("L'utilisateur introuvable");
            Header header = request.headers.get("referer");
            if (header == null) {
                index();
                return;
            }
            String refer = header.value();
            if (refer != null) {
                redirect(refer);
            } else {
                index();
            }
            return;
        }
        List<Event> events = user.events();
        List<Searchable> recents = recents(RECENTS_MAX_SIZE);
        Dendrite dendrite = user.dendrite;
        render(events, recents, user, dendrite);
    }

    public static void membres() {
        render();
    }

    public static void feedbacks(int page) {
        User user = connected();
        if (user == null) {
            flash.error("Connectez vous pour voir les feedbacks");
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
        List<Feedback> feedbacks = Feedback.find("order by progress asc, created desc").fetch(5 * page);
        TriGenerique<Feedback> tri = new TriGenerique<Feedback>();
        CompareFunction sd = new StandardComparable();
        feedbacks = tri.mergeSort(feedbacks, sd);
        long maxSize = Feedback.count();
        int size = feedbacks.size();
        render(feedbacks, page, maxSize, size);
    }

    /**
     * Find all mandates ordered by order
     *
     * @param order
     * @return
     */
    public static List<User> findAll(String order) {
        List<User> users = User.find("order by " + order).fetch();
        return users;
    }

    /**
     *
     * @param page
     * @param order
     * @return
     */
    public static List<User> users(int page, String order) {
        List<User> users = User.find("order by " + order).fetch(page, 10);
        return users;
    }

    /**
     *
     * @param order
     * @return
     */
    public static List<User> findActiveUsers(String order) {
        List<User> users = User.find("active = ? order by " + order, true).fetch();
        return users;
    }

    /**
     *
     * @param order
     * @return
     */
    public static List<User> findDisableUsers(String order) {
        List<User> users = User.find("active = ? order by " + order, false).fetch();
        return users;
    }

    /**
     *
     * @param page
     * @param order
     * @return
     */
    public static List<User> findActiveUsers(int page, String order) {
        List<User> users = User.find("active = ? order by " + order, true).fetch(page, 10);
        return users;
    }

    /**
     *
     * @param page
     * @param order
     * @return
     */
    public static List<User> findDisableUsers(int page, String order) {
        List<User> users = User.find("active = ? order by " + order, false).fetch(page, 10);
        return users;
    }

    /**
     *
     * @param search
     * @return
     */
    public static List<User> find(SearchField search) {
        if (search.promo_from == null) {
            search.promo_from = 0;
        }
        if (search.promo_to == null) {
            search.promo_to = new Date().getYear() + 1900;
        }
        List<User> all = User.find("select distinct u from User u where "
                + "u.firstName like ? and u.lastName like ? "
                + "and (u.promotion >= ? and u.promotion <= ? or u.promotion is null) "
                + "order by lastName asc",
                "%" + search.firstName + "%", "%" + search.lastName + "%", search.promo_from, search.promo_to).fetch();
        return all;
    }
}
