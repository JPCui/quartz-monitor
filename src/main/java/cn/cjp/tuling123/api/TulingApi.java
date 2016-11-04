package cn.cjp.tuling123.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TulingApi {
	
	public static final int CODE_TEXT = 100000; 
	
	public static final int CODE_LINK = 200000; 
	
	public static final int CODE_NEWS = 302000; 
	
	public static final int CODE_MENU = 308000; 
	
	/**
	 * 儿童-儿歌
	 */
	public static final int CODE_CHILDREN_ERGE = 313000; 
	
	/**
	 * 儿歌-诗歌
	 */
	public static final int CODE_CHILDREN_POETRY = 304000; 

	@Value("${tuling.apikey}")
	String apikey;

	@Value("${tuling.secret}")
	String secret;

	@Value("${tuling.api}")
	String api;

	public String request(String info) throws IOException {
		String requestUrl = api + "?key=" + apikey + "&info=" + info;
		URL getUrl = new URL(requestUrl);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.connect();

		// 取得输入流，并使用Reader读取
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		// 断开连接
		connection.disconnect();
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		TulingApi tulingApi = new TulingApi();
		tulingApi.api = "http://www.tuling123.com/openapi/api";
		tulingApi.apikey = "96c7f65aa25b497ba5a0f236881ba657";
		tulingApi.secret = "ae16601956ef4d8c";

		String message = tulingApi.request("你好啊，图片");

		System.out.println(message);

	}

}
