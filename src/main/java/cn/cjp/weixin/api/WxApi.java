package cn.cjp.weixin.api;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class WxApi {

	private static final Logger LOGGER = Logger.getLogger(WxApi.class);

	public enum URL {

		TOKEN("https://api.weixin.qq.com/cgi-bin/token", "GET", new String[] { "grant_type", "appid", "secret" }),

		get_current_autoreply_info("https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info", "GET",
				new String[] { "access_token" });
		
		

		String url;

		String method;

		String[] params;

		private URL(String url, String method, String[] params) {
			this.url = url;
			this.method = method;
			this.params = params;
		}
	}
	
	public static JSONObject get_current_autoreply_info(String accessToken) throws IOException, JSONException {
		String json = request(URL.get_current_autoreply_info, new String[]{accessToken});
		try {
			return new JSONObject(json);
		} catch (JSONException e) {
			LOGGER.error("json", e);
			throw e;
		}
	}

	public static String token(String appid, String secret) throws IOException, JSONException {
		String json = request(URL.TOKEN, new String[] { "client_credential", appid, secret });
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(json);
			return jsonObj.getString("access_token");
		} catch (JSONException e) {
			LOGGER.error("json", e);
			throw e;
		}
	}

	private static String request(URL url, String[] params) throws IOException {
		LOGGER.info(url);
		LOGGER.info(params);
		Connection conn = Jsoup.connect(url.url).ignoreContentType(true);
		for (int i = 0; i < url.params.length; i++) {
			conn.data(url.params[i], params[i]);
		}
		return (url.method.equalsIgnoreCase("GET") ? conn.get() : conn.post()).text();
	}

}
