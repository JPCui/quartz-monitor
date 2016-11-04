package cn.cjp.weixin.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.cjp.tuling123.api.TulingApi;

public class MsgUtil {

	private static final Logger LOCAL = Logger.getLogger("LOCAL");

	public static final String KEY_FromUserName = "FromUserName";
	public static final String KEY_ToUserName = "ToUserName";
	public static final String KEY_CreateTime = "CreateTime";
	public static final String KEY_MsgType = "MsgType";
	public static final String KEY_Event = "Event";
	public static final String KEY_Content = "Content";
	public static final String KEY_MsgId = "MsgId";
	public static final String KEY_ArticleCount = "ArticleCount";
	public static final String KEY_Articles = "Articles";
	public static final String KEY_item = "item";
	public static final String KEY_Title = "Title";
	public static final String KEY_Description = "Description";
	public static final String KEY_PicUrl = "PicUrl";
	public static final String KEY_Url = "Url";

	public enum MsgType {
		text("text"), news("news");

		String value;

		private MsgType(String value) {
			this.value = value;
		}
	}

	public static String handlerTulingText(String fromUserName, String toUserName, String text) throws Exception {
		JSONObject json = new JSONObject(text);
		int code = json.getInt("code");

		if (code == TulingApi.CODE_TEXT) {
			return toText(fromUserName, toUserName, json.getString("text"));
		} else if (code == TulingApi.CODE_LINK) {
			return toText(fromUserName, toUserName, json.getString("text") + "\r\n" + json.getString("url"));
		} else if (code == TulingApi.CODE_NEWS) {
			JSONArray newsList = json.getJSONArray("list");
			List<Map<String, String>> newsMapList = new ArrayList<>();
			for (int i = 0; i < newsList.length() && i < 10; i++) {
				JSONObject newsJson = newsList.getJSONObject(i);
				String article = newsJson.getString("article");
				String source = newsJson.getString("source");
				String icon = newsJson.getString("icon");
				String detailurl = newsJson.optString("detailurl");

				Map<String, String> newsMap = new HashMap<>();
				newsMap.put(KEY_Title, article);
				newsMap.put(KEY_Description, source);
				newsMap.put(KEY_PicUrl, icon);
				newsMap.put(KEY_Url, detailurl);
				newsMapList.add(newsMap);
			}
			return toNews(fromUserName, toUserName, newsMapList);
		} else if (code == TulingApi.CODE_MENU) {
			JSONArray newsList = json.getJSONArray("list");
			List<Map<String, String>> newsMapList = new ArrayList<>();
			for (int i = 0; i < newsList.length() && i < 10; i++) {
				JSONObject newsJson = newsList.getJSONObject(i);
				String name = newsJson.getString("name");
				String info = newsJson.getString("info");
				String icon = newsJson.getString("icon");
				String detailurl = newsJson.getString("detailurl");

				Map<String, String> newsMap = new HashMap<>();
				newsMap.put(KEY_Title, name);
				newsMap.put(KEY_Description, info);
				newsMap.put(KEY_PicUrl, icon);
				newsMap.put(KEY_Url, detailurl);
				newsMapList.add(newsMap);
			}
			return toNews(fromUserName, toUserName, newsMapList);
		} else if (code == TulingApi.CODE_CHILDREN_ERGE) {
			JSONObject func = json.getJSONObject("function");
			return toText(fromUserName, toUserName, "给你推荐：" + func.optString("singer") + "-" + func.getString("song"));
		} else if (code == TulingApi.CODE_CHILDREN_POETRY) {
			JSONObject func = json.getJSONObject("function");
			return toText(fromUserName, toUserName, "来一首：" + func.optString("name") + "-" + func.getString("author"));
		} else {
			Exception e = new Exception("unknown tuling code.");
			LOCAL.error(json, e);
			throw e;
		}
	}

	public static String toNews(String fromUserName, String toUserName, List<Map<String, String>> newsList) {
		Map<String, Object> map = new HashMap<>();
		map.put(KEY_FromUserName, fromUserName);
		map.put(KEY_ToUserName, toUserName);
		map.put(KEY_CreateTime, ("" + System.currentTimeMillis()).substring(0, 10)); // 消息创建时间
																						// （整型）
		map.put(KEY_MsgType, MsgType.news.value);
		map.put(KEY_ArticleCount, newsList.size() + "");

		Map<String, Object> articlesMap = new HashMap<>();
		map.put(KEY_MsgType, articlesMap);
		for (Map<String, String> news : newsList) {
			Map<String, Object> itemMap = new HashMap<>();
			articlesMap.put(KEY_item, itemMap);
			for (String key : news.keySet()) {
				itemMap.put(key, news.get(key));
			}
		}

		String xml = MsgUtil.toXml(map); // XmlUtil.toXml(map);
		LOCAL.info(xml);
		return xml;
	}

	@SuppressWarnings("unchecked")
	public static String toXml(Map<String, Object> msgMap) {
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>");
		for (String key : msgMap.keySet()) {
			Object value = msgMap.get(key);
			if (value instanceof String) {
				xml.append('<' + key + '>');
				xml.append(msgMap.get(key));
				xml.append("</" + key + '>');
			} else if (value instanceof Map) {
				xml.append('<' + key + '>');
				xml.append(toXml((Map<String, Object>) value));
				xml.append("</" + key + '>');
			}
		}
		xml.append("</xml>");
		LOCAL.debug(xml);
		return xml.toString();
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put("asd", "啊啊啊");
		System.out.println(toXml(map));
	}

	/**
	 * 回复消息，转换成微信text类型的XML
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static String toText(String fromUserName, String toUserName, String content) {
		Map<String, Object> map = new HashMap<>();
		map.put("FromUserName", fromUserName);
		map.put("ToUserName", toUserName);
		map.put("CreateTime", ("" + System.currentTimeMillis()).substring(0, 10)); // 消息创建时间
																					// （整型）
		map.put("MsgType", MsgType.text.value);
		map.put("Content", content);

		String xml = MsgUtil.toXml(map); // XmlUtil.toXml(map);
		LOCAL.info(xml);
		return xml;
	}

}
