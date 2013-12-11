package services;

import play.exceptions.ConfigurationException;
import play.libs.WS;
import play.libs.WS.WSRequest;
import play.mvc.Http;
import play.mvc.Router;
import play.Play;

public class FBHelper {
	public static final String FB_API_KEY = "fb.api.key";
	public static final String FB_API_SECRET = "fb.api.secret";
	public static final String FB_API_SCOPE = "fb.api.scope";
	public static String apiKey;
	public static String secret;
	public static String scope;

	public static void init() {
		if (!Play.configuration.containsKey(FB_API_KEY)) {
			throw new ConfigurationException(
					"Bad configuration for FB: no access key");
		} else if (!Play.configuration.containsKey(FB_API_SECRET)) {
			throw new ConfigurationException(
					"Bad configuration for FB: no secret key");
		} else if (!Play.configuration.containsKey(FB_API_SCOPE)) {
			throw new ConfigurationException(
					"Bad configuration for FB: no FB Scope");
		}
		apiKey = Play.configuration.getProperty(FB_API_KEY);
		secret = Play.configuration.getProperty(FB_API_SECRET);
		scope = Play.configuration.getProperty(FB_API_SCOPE);
	}

	public static String getLoginUrl(String callBackURL) {
		init();
		String url = String
				.format("https://www.facebook.com/dialog/oauth?client_id=%s&display=%s&redirect_uri=%s",
						WS.encode(apiKey), WS.encode("page"),
						WS.encode(Router.getFullUrl(callBackURL)));
		if (scope != null) {
			url += "&scope=" + WS.encode(scope);
		}
		return url;
	}

	public static String getAuthUrl(String callBackURL, String authToken) {
		return String
				.format("https://graph.facebook.com/oauth/access_token?client_id=%s&redirect_uri=%s&client_secret=%s&code=%s",
						WS.encode(apiKey),
						WS.encode(Router.getFullUrl(callBackURL)),
						WS.encode(secret), WS.encode(authToken));
	}

}
