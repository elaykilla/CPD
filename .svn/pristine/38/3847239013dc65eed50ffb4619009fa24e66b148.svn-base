package controllers;

import static controllers.Application.RECENTS_MAX_SIZE;
import static controllers.Application.connected;
import static controllers.Application.index;
import static controllers.Application.listByPage;
import static controllers.Application.redirection;
import static controllers.Application.sort;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;
import services.BCrypt;
import play.Play;

import play.data.validation.*;

import java.util.*;

import models.*;
import models.articles.Announce;
import models.articles.Article;
import models.events.Event;
import play.db.jpa.GenericModel.JPAQuery;

import play.i18n.Messages;
import play.mvc.Http;

/**
 * @author Sissoko && Elay This class is used to handle the different Html
 * pages.
 */
///j
public class Annuaires extends Application {

    @Before
    public static void setup() {
        User user = connected();
        if (user == null) {
            flash.error("Connectez vous pour voir cette page");
            Http.Header referer = request.headers.get("referer");
            if (referer != null) {
                redirect(referer.value());
            } else {
                index();
            }
            return;
        }
        if (!user.active) {
            String message = "La page des dendrites";
            render("Application/desactive.html", message, user);
        }
    }

    public static void annuaireWelcome() {
//        if(page == 0)
//            page = 1;
//        long size = User.count();
//        int nbPagesMax = 10;
//        int nbPages = (int) (size / nbPagesMax);
//        if (nbPagesMax * nbPages < size) {
//            nbPages = nbPages + 1;
//        }
//        List<User> users = User.users(page, "lastName asc");
        render();
    }

    /**
     *
     * @param search
     */
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
     * @param q
     */
    public static void instantSearch(String q, String type, int page) {
        if (q == null || q.isEmpty() || q.matches("[ ]+")) {
            renderJSON(new String[]{"error", "Champs vide"});
            return;
        }
        System.out.println(q);
        q = q.replaceAll("'", "''");
        System.out.println(q);
        String[] req = q.split(" ");
        SearchField sf = new SearchField();
        sf.request = req;
        sf.type = "user";
        if (page <= 0) {
            page = 1;
        }
        JPAQuery query = User.find(sf.createRequest());
        int searchMaxSize = query.fetch().size();
        List<User> users = query.fetch(page * 5);
        int searchSize = users.size();
        System.out.println("searchMaxSize: " + searchMaxSize);
        System.out.println("searchSize: " + searchSize);
        render(users, type, searchMaxSize, searchSize, page);
    }

    public static void fullAnnuaire() {
    }
}
