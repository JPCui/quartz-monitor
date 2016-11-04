package cn.cjp.util;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;

import cn.cjp.weather.util.CityCodes;

public class Collections {

	public static List<String> searchSub(Collection<String> colls, String regex) {
		return colls.parallelStream().filter(coll -> coll.matches(regex)).collect(Collectors.toList());
	}

	public static void main(String[] args) throws IOException {
		System.out.println(searchSub(CityCodes.map.keySet(), "北.*"));
		System.out.println(Jsoup.connect("http://www.weather.com.cn/data/cityinfo/101010100.html")
				.ignoreContentType(true).ignoreHttpErrors(true)
				.header("Accept", "application/json")
				.header("Accept-Encoding", "*") // EOF
				.get());
		Object obj = Jsoup.parse(new URL("http://www.weather.com.cn/data/cityinfo/101010100.html").openStream(), "UTF-8", "").text();
		System.out.println(obj);
	}
}
