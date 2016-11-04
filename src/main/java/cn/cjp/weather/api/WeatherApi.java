package cn.cjp.weather.api;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

public class WeatherApi {

	private static final Logger LOGGER = Logger.getLogger(WeatherApi.class);

	public enum URL {
		cityinfo("http://www.weather.com.cn/data/cityinfo/{code}.html",
				new String[] { "{code}" }), img("http://m.weather.com.cn/img/{img}.gif", new String[] { "{img}" });

		String url;

		String[] replaces;

		private URL(String url, String[] replaces) {
			this.url = url;
			this.replaces = replaces;
		}

		public static String url(URL url, String... replaces) {
			String apiUrl = url.url;
			for (int i = 0; i < url.replaces.length; i++) {
				apiUrl = apiUrl.replace(url.replaces[i], replaces[i]);
			}
			LOGGER.info(apiUrl);
			return apiUrl;
		}
	}

	public static String cityinfo(String code) throws IOException {
		String url = URL.url(URL.cityinfo, code);
		String jsonStr = Jsoup.connect(url).ignoreContentType(true).header("Accept", "application/json")
				.header("Accept-Encoding", "*") // EOF
				.get().text();
		return jsonStr;
	}

}
