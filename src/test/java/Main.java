import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws IOException {

		Document doc = Jsoup.connect("http://m.weather.com.cn/data/101010100.html")
				.header("Host", "m.weather.com.cn")
				.header("Referer", "http://m.weather.com.cn/data/101010100.html")
				.ignoreContentType(true)
				.ignoreHttpErrors(true)
				.get();

		System.out.println(doc.text());

	}

}
