/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.Application.CV_LIMIT_YEAR;
import static controllers.Application.index;
import static controllers.Application.message;
import static controllers.Application.notNull;
import static controllers.Users.editUser;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;
import services.BCrypt;
import play.Play;

import play.data.validation.*;

import java.util.*;

import models.*;
import models.cvs.Adresse;
import models.cvs.Cv;
import models.cvs.Experience;
import models.cvs.Formation;
import models.cvs.Hobbie;
import models.cvs.Language;
import models.cvs.Skill;
import play.i18n.Lang;

import play.i18n.Messages;

/**
 *
 * @author Sissoko && Elay
 */
public class Cvs extends Application {

    /**
     *
     * @param cv
     * @param act
     */
    public static void updateCv(@Valid Cv cv, String act) {
        User user = cv.user();
        if (Validation.hasErrors()) {
            flash.error(Messages.get("CorrectErrors"));
            editUser(user.id, act);
            return;
        }
        cv.save();
        flash.success("Cv mis à jour");
        String ass = "cv";
        render("Users/ajaxcv.html", cv, ass);
        //editUser(user.id, act);
        //message();
    }

    /**
     *
     * @param adresse
     * @param user_id
     */
    public static void updateAdresse(@Valid Adresse adresse, Long user_id, String act) {

        User user = User.findById(user_id);
        if (user == null) {
            flash.error(Messages.get("CorrectErrors") + ", Utilisateur non trouvé!");
            index();
            //message();
            return;
        }
        if (Validation.hasErrors()) {
            flash.error(Messages.get("CorrectErrors"));
            editUser(user.id, act);
            //message();
            return;
        }

        user.cv.adresse = adresse;
        user.save();
        flash.success("Adresse mise à jour");
        String ass = "adresse";
        render("Users/ajaxcv.html", adresse, ass, user);
//        editUser(user.id, act);
        //message();
    }

    /**
     *
     * @param id
     * @param act
     */
    public static void deleteAdresse(Long id, String act) {
        Cv cv = Cv.findById(id);
        Long aid = cv.adresse.id;
        cv.adresse = null;
        cv.save();
        Adresse adresse = Adresse.findById(aid);
        adresse.delete();
        User user = cv.user();
        flash.success("Adresse supprimée");
        editUser(user.id, act);
    }

    /**
     *
     * @param formation
     * @param cv_id
     * @param act
     */
    public static void updateFormation(@Valid Formation formation, Long cv_id, String act, String onGoing) {
        Cv cv = Cv.findById(cv_id);
        if (cv == null) {
            flash.error("Le cv est introuvable");
            index();
            //message();
            return;
        }
        User user = cv.user();
        if (Validation.hasErrors()) {
            flash.error("Les données saisies sont incorrectes");
            editUser(user.id, act);
            //message();
        }
        if (onGoing == null) {
            formation.onGoing = false;
        } else {
            formation.onGoing = true;
        }
        boolean ajax = formation.id != null;
        cv.addFormation(formation);
        flash.success("Formation ajoutée");
        String ass = "formation";
        if (ajax) {
            render("Users/ajaxcv.html", formation, ass, cv);
        } else {
            editUser(user.id, act);
//        message();
        }
    }

    /**
     *
     * @param id
     */
    public static void deleteFormation(Long id, String act) {
        Formation formation = Formation.findById(id);
        formation.delete();
        User user = formation.cv.user();
        flash.success("Formation supprimée");
        editUser(user.id, act);
    }

    /**
     *
     * @param experince
     * @param cv_id
     * @param act
     * @param onGoing
     */
    public static void updateExperience(@Valid Experience experience, Long cv_id, String act, String onGoing) {
        Cv cv = Cv.findById(cv_id);
        if (cv == null) {
            flash.error("Le cv est introuvable");
            index();
            return;
        }
        User user = cv.user();
        if (Validation.hasErrors()) {
            flash.error("Les données saisies sont incorrectes");
            editUser(user.id, act);
            //message();
        }
        if (onGoing == null) {
            experience.onGoing = false;
        } else {
            experience.onGoing = true;
        }
        boolean ajax = experience.id != null;
        cv.addExperience(experience);
        flash.success("Experience ajoutée");
        String ass = "experience";
        if (ajax) {
            List<Integer> years = yearsToNow(CV_LIMIT_YEAR);
            List<String> months = months();
            render("Users/ajaxcv.html", experience, ass, cv, years, months);
        } else {
            editUser(user.id, act);
//        message();
        }
    }

    /**
     *
     * @param id
     */
    public static void deleteExperience(Long id, String act) {
        Experience experience = Experience.findById(id);
        experience.delete();
        User user = experience.cv.user();
        flash.success("Experience supprimée");
        editUser(user.id, act);
    }

    /**
     *
     * @param skill
     * @param cv_id
     * @param act
     */
    public static void updateSkill(@Valid Skill skill, Long cv_id, String act) {
        Cv cv = Cv.findById(cv_id);
        if (cv == null) {
            flash.error("Le cv est introuvable");
            index();
            //message();
            return;
        }
        User user = cv.user();
        if (Validation.hasErrors()) {
            flash.error("Les données saisies sont incorrectes");
            editUser(user.id, act);
            //message();
            return;
        }
        boolean ajax = skill.id != null;
        cv.addSkill(skill);
        flash.success("Compétence ajoutée");
        String ass = "skill";
        if (ajax) {
            List<Integer> years = yearsToNow(CV_LIMIT_YEAR);
            List<String> months = months();
            render("Users/ajaxcv.html", skill, ass, cv, months, years);
        } else {
            editUser(user.id, act);
//        message();
        }
    }

    /**
     *
     * @param id
     * @param act
     */
    public static void deleteSkill(Long id, String act) {
        Skill skill = Skill.findById(id);
        skill.delete();
        User user = skill.cv.user();
        flash.success("Compétence supprimée");
        editUser(user.id, act);
    }

    /**
     *
     * @param hobbie
     * @param cv_id
     * @param act
     */
    public static void updateHobbie(@Valid Hobbie hobbie, Long cv_id, String act) {
        Cv cv = Cv.findById(cv_id);
        if (cv == null) {
            flash.error("Le cv est introuvable");
            index();
            //message();
            return;
        }
        User user = cv.user();
        if (Validation.hasErrors()) {
            flash.error("Les données saisies sont incorrectes");
            editUser(user.id, act);
            //message();
            return;
        }
        boolean ajax = hobbie.id != null;
        cv.addHobbie(hobbie);
        flash.success("Centre d'intérêt ajouté");
        String ass = "hobbie";
        if (ajax) {
            render("Users/ajaxcv.html", hobbie, ass, cv);
        } else {
            editUser(user.id, act);
//        message();
        }
    }

    /**
     *
     * @param id
     * @param act
     */
    public static void deleteHobbie(Long id, String act) {
        Hobbie hobbie = Hobbie.findById(id);
        hobbie.delete();
        User user = hobbie.cv.user();
        flash.success("Centre d'intérêt supprimé");
        editUser(user.id, act);
    }

    /**
     *
     * @param formation
     * @param cv_id
     * @param act
     */
    public static void updateLanguage(@Valid Language language, Long cv_id, String act) {
        Cv cv = Cv.findById(cv_id);
        if (cv == null) {
            flash.error("Le cv est introuvable");
            index();
            return;
        }
        User user = cv.user();
        if (Validation.hasErrors()) {
            flash.error("Les données saisies sont incorrectes");
            editUser(user.id, act);
            return;
        }
        boolean ajax = language.id != null;
        cv.addLanguage(language);
        flash.success("Langue ajoutée");
        String ass = "language";
        List<String> languages = languages();
        List<String> levels = levels();
        if (ajax) {
            render("Users/ajaxcv.html", language, ass, cv, levels, languages);
        } else {
            editUser(user.id, act);
//        message();
        }
    }

    /**
     *
     * @param id
     */
    public static void deleteLanguage(Long id, Long cv_id, String act) {
        Language language = Language.findById(id);
        Cv cv = Cv.findById(cv_id);
        cv.removeLanguage(language);
        User user = cv.user();
        flash.success("Langue supprimée");
        editUser(user.id, act);
    }

    /**
     *
     * @param from
     * @param n
     * @return
     */
    public static List<Integer> years(Integer from, int n) {
        List<Integer> years = new ArrayList<Integer>();
        for (int y = from + n; y >= from; y--) {
            years.add(y);
        }
        return years;
    }

    /**
     *
     * @param from
     * @param n
     * @return
     */
    @SuppressWarnings("deprecation")
    public static List<Integer> yearsFromNow(int n) {
        Integer year = new Date().getYear() + 1900;
        return years(year, n);
    }

    /**
     *
     * @param from
     * @param n
     * @return
     */
    @SuppressWarnings("deprecation")
    public static List<Integer> yearsToNow(int n) {
        Integer year = new Date().getYear() + 1900;
        int from = year - n;
        return years(from, n);
    }

    /**
     * The months list.
     *
     * @return list
     */
    public static List<String> months() {
        String[] mths = Messages.get("months").split(",");
        List<String> mthss = Arrays.asList(mths);
        session.put("months", mthss);
        return mthss;
    }

    /**
     *
     * @return all countries perhaps in wold :)
     */
    public static List<String> countries() {
        String[] countryCodes = Locale.getISOCountries();
        List<String> countries = new ArrayList<String>();
        for (String cc : countryCodes) {
            countries.add(new Locale(Lang.get(), cc).getDisplayCountry());
        }
        session.put("countries", countries);
        return countries;
    }

    /**
     *
     * @return available levels in language
     */
    public static List<String> levels() {
        String[] levels = Messages.get("levels").split(",");
        List<String> lvls = Arrays.asList(levels);
        renderArgs.put("levels", lvls);
        return lvls;
    }

    /**
     *
     * @return
     */
    public static List<String> languages() {
        String[] languages = Messages.get("languages").split(",");
        List<String> lgs = Arrays.asList(languages);
        renderArgs.put("languages", lgs);
        return lgs;
    }
}
