package controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import models.Dendrite;
import models.SearchField;
import models.Searchable;
import models.User;
import models.articles.Announce;
import models.articles.Article;
import models.events.Event;
import notifiers.Mails;
import play.Play;
import play.data.validation.Validation;
import play.i18n.Messages;
import play.libs.WS;
import play.libs.ws.WSUrlFetch;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http.Header;
import services.BCrypt;
import services.CompareFunction;
import services.FBHelper;
import services.StandardComparable;
import services.TriGenerique;

/**
 * @author Sissoko && Elay
 * @date 2 mai 2013 12:45:48
 */
public class Application extends Controller {

    public static final java.util.logging.Logger logger = java.util.logging.Logger
            .getLogger(Application.class.getName());
    public static final BCrypt bcrypt = new BCrypt();
    public static int CV_LIMIT_YEAR = Integer.parseInt(Play.configuration.getProperty("cv.limit.year"));
    public static int PHOTO_LIMIT_SIZE = Integer.parseInt(Play.configuration.getProperty("photo.limit.size"));
    public static int PHOTO_HOME_WIDTH_MAX = Integer.parseInt(Play.configuration.getProperty("photo.home.width.max"));
    public static int PHOTO_HOME_HEIGHT_MAX = Integer.parseInt(Play.configuration.getProperty("photo.home.height.max"));
    public static int PHOTO_WIDTH_SMALL = Integer.parseInt(Play.configuration.getProperty("photo.width.small"));
    public static int PHOTO_HEIGHT_SMALL = Integer.parseInt(Play.configuration.getProperty("photo.height.small"));
    public static int PHOTO_WIDTH_PROFILE = Integer.parseInt(Play.configuration.getProperty("photo.width.profile"));
    public static int PHOTO_HEIGHT_PROFILE = Integer.parseInt(Play.configuration.getProperty("photo.height.profile"));
    public static int CONV_RESUME_SIZE = Integer.parseInt(Play.configuration.getProperty("conv.resume.size"));
    public static int RECENTS_MAX_SIZE = 6;
    static String EMAIL_REGEX = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*"
            + "[\\w])?\\.)+[a-zA-Z0-9](?:[\\w-]*[\\w])?";
    /**
     * This is called when loading the page.
     */
    @Before
    public static void global() {
        renderArgs.put("fbUrl", FBHelper.getLoginUrl("Application.FBcallback"));
        User user = connected();
        if (user != null) {
            renderArgs.put("connected", user);
            Cvs.countries();
            Cvs.languages();
            Cvs.levels();
            Cvs.months();
        }
        List<Dendrite> dendrites = Dendrite.findAll("named asc");
        renderArgs.put("dendrites", dendrites);
    }

    public static void home() {
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
            if (refer != null && !refer.contains("login")) {
                redirect(refer);
            } else {
                index();
            }
        }
        String ass = "login";
        render(ass);
    }

    /**
     * The
     *
     * @Before states that this function is called before the application is
     * launched. This function sets the default values to display
     */
    @Before
    static void addDefaults() {
        renderArgs.put("HomeTitle",
                Play.configuration.getProperty("Home.title"));
        renderArgs.put("HomeBaseline",
                Play.configuration.getProperty("Home.baseline"));
    }

    /**
     * The index Method when called opens the index.html file
     */
    public static void index() {
        render();
    }

    /**
     * Verify is value is not null.
     *
     * @param value
     * @return
     */
    public static boolean notNull(String value) {
        return value != null && !value.equals("") && !value.isEmpty()
                && !"null".equals(value);
    }

    /**
     * check and find the connected user.
     *
     * @return
     */
    public static User connected() {
        if (renderArgs.get("user") != null) {
            return renderArgs.get("user", User.class);
        }
        return Security.getSessionUser();
    }

    public static List<String> toHomeFormat(String text, int length) {
        if (text == null) {
            return new ArrayList<String>();
        }
        List<String> res = new ArrayList<String>();
        String tab[] = text.split(" ");
        String line = "";
        for (int i = 0; i < tab.length; i++) {
            line += " " + tab[i];
            if (line.length() >= length) {
                res.add(line);
                line = "";
            }
        }
        if (!"".equals(line)) {
            res.add(line);
        }
        return res;
    }

    /**
     *
     * @return
     */
    public static boolean notconnected() {
        return connected() == null;
    }

    public static void sendFeedback(String name, String email, String subject, String content) {
        validation.required(name);
        validation.required(email);
        validation.required(subject);
        validation.required(content);
        if (Validation.hasErrors()) {
            flash.error("Les champs doivent être rempli.");
            redirection();
            return;
        }
        try {
            Mails.sendFeedback(name, email, subject, content);
            flash.success("Message envoyé!");
            redirection();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to send email:{0}", e.getMessage());
            flash.error("Il y a eu problème lors de l'envoi du message.");
            redirection();
        }
    }

    /**
     * disconnection
     */
    public static void logout() {
        User connected = connected();
        if (connected != null) {
            connected.lastConnexion = new Date();
            connected.save();
        }
        session.clear();
        index();
//        Http.Header referer = request.headers.get("referer");
//        if (referer == null) {
//            index();
//        } else {
//            redirect(referer.value());
//        }
    }

    public static void search(String q, int page) {
        if (q == null || q.isEmpty() || q.matches("[ ]+")) {
            redirection();
            return;
        }
        String[] req = q.split(" ");
        SearchField sf = new SearchField();
        sf.request = req;
        sf.type = "user";
        List<User> users = User.find(sf.createRequest()).fetch();
        sf.type = "article";
        List<Article> articles = Article.find(sf.createRequest()).fetch();
        sf.type = "event";
        List<Event> events = Event.find(sf.createRequest()).fetch();
        sf.type = "announce";
        List<Announce> announces = Announce.find(sf.createRequest()).fetch();
        List<Searchable> all = new ArrayList<Searchable>();
        all.addAll(articles);
        all.addAll(users);
        all.addAll(events);
        all.addAll(announces);
        all = sort(all);
        long size = all.size();
        int nbPagesMax = 10;
        int nbPages = (int) (size / nbPagesMax);
        if (nbPagesMax * nbPages < size) {
            nbPages = nbPages + 1;
        }
        users.clear();
        articles.clear();
        announces.clear();
        events.clear();
        if (page == 0) {
            page = 1;
        }
        List<Searchable> searchs = (List<Searchable>) listByPage(page, nbPagesMax, all);
        for (int i = 0; i < searchs.size() && i < nbPagesMax; i++) {
            Searchable s = searchs.get(i);
            if (s instanceof User) {
                users.add((User) s);
            } else if (s instanceof Article) {
                articles.add((Article) s);
            } else if (s instanceof Announce) {
                announces.add((Announce) s);
            } else if (s instanceof Event) {
                events.add((Event) s);
            }
        }
        List<Searchable> recents = Articles.recents(RECENTS_MAX_SIZE);
        render(users, articles, events, announces, recents, size, page, nbPagesMax, nbPages);
    }

    public static List<Searchable> sort(List<Searchable> list) {
        TriGenerique<Searchable> tri = new TriGenerique<Searchable>();
        CompareFunction sd = new StandardComparable();
        return tri.mergeSort(list, sd);
    }

    public static void message() {
        render();
    }

    public static void redirection() {
        Header referer = request.headers.get("referer");
        if (referer != null) {
            redirect(referer.value());
        } else {
            index();
        }
    }

    public static void contact() {
        render();
    }

    // Facebook Callback
    public static void FBcallback() {

        String code = params.get("code");
        String error = params.get("error");
        if (error != null) {
            flash.error(Messages.get("FBLoginFailed") + " " + "error_reason="
                    + params.get("error_reason"));
            logger.log(Level.SEVERE, "FB Login failed: "
                    + "error_reason={0}error_description={1}error={2}",
                    new Object[]{params.get("error_reason"), params.get("error_description"), params.get("error")});
            index();
        }
        
            System.out.println("code: "+code);
        if (code != null) {
            String authUrl = FBHelper
                    .getAuthUrl("Application.FBcallback", code);
            WSUrlFetch ws = new WSUrlFetch();
            String resp = ws.newRequest(authUrl, "UTF-8").get().getString();
            if (resp == null) {
                Header referer = request.headers.get("referer");
                if (referer != null) {
                    redirect(referer.value());
                } else {
                    index();
                }
                return;
            }
            String accessToken = null;
            Integer expires = null;
            String[] pairs = resp.split("&");
            for (String pair : pairs) {
                String[] kv = pair.split("=");
                if (kv.length != 2) {
                    flash.error(Messages.get("FBLoginFailed")
                            + "Facebook didn't tell why :( ");

                    logger.severe("Module fbconnect got an unexpected auth response from facebook");
                } else {
                    if (kv[0].equals("access_token")) {
                        accessToken = kv[1];
                    }
                    if (kv[0].equals("expires")) {
                        expires = Integer.valueOf(kv[1]);
                    }
                }
            }
            if (accessToken != null) {
                String uri = "https://graph.facebook.com/me?access_token="
                        + WS.encode(accessToken);
                JsonObject jsonData = ws.newRequest(uri, "UTF-8").get()
                        .getJson().getAsJsonObject();
                jsonData.add("accessToken", new JsonPrimitive(accessToken));
                if (expires != null) {
                    jsonData.add("expires", new JsonPrimitive(expires));
                }
                String email = jsonData.get("email").getAsString();
                String firstName = jsonData.get("first_name")
                        .getAsString();
                String lastName = jsonData.get("last_name").getAsString();
                String facebookId = jsonData.get("id").getAsString();
                String facebookToken = jsonData.get("accessToken")
                        .getAsString();

                User user = User.findByfbId(facebookId);
                if (user == null) {
                    // If email has been changed find by Token
                    user = User.findByEmail(email);
                }
                if (user == null) {
                    //Find by email redirect
                    user = User.findByEmailRedirect(email);
                }
                if (user == null) {
                    // We don't know this user, we have to create it!
                    user = new User();
                    user.firstName = firstName;
                    user.lastName = lastName;
                    user.email = email;
                    user.facebookId = facebookId;
                    user.facebookToken = facebookToken;
                    String ass = "signup";
                    List<Dendrite> dendrites = Dendrite.findAll("name asc");
                    render("Users/acelogin.html", user, ass, dendrites);
                } else {
                    if (user.facebookId == null || user.facebookToken == null) {
                        user.facebookId = facebookId;
                        user.facebookToken = facebookToken;
                        user.save();
                    }
                    session.put("user", user.email);
                    session.put("username", user.email);
                    flash.success(Messages.get("welcomeback.user",
                            user.fullName()));
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

            }

        }
        index();
    }

    public static String fbUrl() {
        return FBHelper.getLoginUrl("Application.FBcallback");
    }

    public static List<? extends Searchable> listByPage(int page, int length, List<? extends Searchable> list) {
        if (page < 1) {
            page = 1;
        }
        List results = new ArrayList();
        int start = (page - 1) * length;
        int end = start + length;
        for (int i = start; i < end && i < list.size(); i++) {
            results.add(list.get(i));
        }
        return results;
    }

    /**
     *
     * @param page
     * @return
     */
    public static String createPaginationLink(int page) {
        String link = request.path;
        String querystring = request.querystring;
        if (notNull(querystring)) {
            if (querystring.contains("page")) {
                querystring = querystring.replaceAll("page=([0-9]+)", "page=" + page);

            } else {
                querystring += "&page=" + page;
            }
        } else {
            querystring = "page=" + page;
        }
        link = link + "?" + querystring;
        return link;
    }

    public static boolean isEmail(String email) {
        if (!Application.notNull(email)) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }
}