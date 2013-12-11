package controllers;

import models.User;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import static controllers.Application.index;
import java.util.ArrayList;
import java.util.List;
import models.Dendrite;
import play.i18n.Messages;
import play.libs.OAuth2;
import play.mvc.Before;
import play.mvc.Http;

public class Security extends Secure.Security {

    static int count = 0;
    public static ArrayList<User> connecteds = new ArrayList<User>();

    @Before
    public static void setup() {
        System.out.println("Count = " + ++count);
    }
    private static OAuth2 FBOAuth = new OAuth2(
            "https://graph.facebook.com/oauth/authorize",
            "https://graph.facebook.com/oauth/access_token",
            "738389799521405",
            "2e51bfced3949b9ed0c758931acb0364");

    @SuppressWarnings("unused")
    public static void onAuth() {
        if (OAuth2.isCodeResponse()) {
            OAuth2.Response response = FBOAuth.retrieveAccessToken(onAuthURL());
            FacebookClient fbClient = new DefaultFacebookClient(response.accessToken);
            com.restfb.types.User fbUser = fbClient.fetchObject("me", com.restfb.types.User.class);
            User user = User.findByfbId(fbUser.getId());
            if (user == null) {
                user = User.findByEmail(fbUser.getEmail());
            }
            if (user == null) {
                //Find by email redirect
                user = User.findByEmailRedirect(fbUser.getEmail());
            }
            if (user == null) {
                // We don't know this user, we have to create it!
                user = new User();
                user.firstName = fbUser.getFirstName();
                user.lastName = fbUser.getLastName();
                user.email = fbUser.getEmail();
                user.facebookId = fbUser.getId();
                user.birthday = fbUser.getBirthdayAsDate();
                String ass = "signup";
                List<Dendrite> dendrites = Dendrite.findAll("name asc");
                render("Users/acelogin.html", user, ass, dendrites);
            } else {
                if (user.facebookId == null || user.facebookToken == null) {
                    user.facebookId = fbUser.getId();
                    user.save();
                }
                session.put("user", user.email);
                session.put("username", user.email);
                session.put("fbid", user.facebookId);
                flash.success(Messages.get("welcomeback.user",
                        user.fullName()));
                Http.Header header = request.headers.get("referer");
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
            Application.index();
        }
    }

    private static String onAuthURL() {
        return play.mvc.Router.getFullUrl("Security.onAuth");
    }

    public static void auth() {
        FBOAuth.retrieveVerificationCode(onAuthURL());
    }

    @SuppressWarnings("unused")
    static void onDisconnected() {
        flash.success("You have been logged out successfully.");
        session.clear();
        Application.index();
    }

    static User getSessionUser() {
        //System.out.println("session.get(username) = " +session.get("username") );
        return User.findByEmail(session.get("username"));
    }
}
