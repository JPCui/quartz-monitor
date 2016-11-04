package cn.cjp.weixin.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.cjp.util.Password;
import cn.cjp.util.XmlUtil;
import cn.cjp.weixin.api.WxApi;
import cn.cjp.weixin.util.MsgUtil;

@Component
public class WxService {

	private static final Logger LOGGER = Logger.getLogger(WxService.class);

	private static final Logger LOCAL = Logger.getLogger("LOCAL");

	private static String LOCAL_ACCESS_TOKEN_FILE = "/wx/access_token.txt";

	private static long ACCESS_TOKEN_TIMEOUT = 7000;

	@Value("${wx.AppId}")
	String appId;

	@Value("${wx.AppSecret}")
	String appSecret;

	@Value("${wx.Token}")
	String token;

	@Autowired
	EventService eventService;

	@Autowired
	MsgService msgService;

	public WxService() {
		LOGGER.info("Wx is ready.");
	}

	/**
	 * 解析XML
	 * 
	 * @param body
	 * @return 消息体Map
	 * @throws DocumentException
	 */
	private Map<String, String> parseMsg(String body) throws DocumentException {
		Element elem = XmlUtil.getElement(body);
		Map<String, String> map = new HashMap<>();
		for (@SuppressWarnings("unchecked")
		Iterator<Element> it = elem.elementIterator(); it.hasNext();) {
			Element child = it.next();
			String name = child.getName();
			String text = child.getText();
			LOCAL.info(name + "\t : \t" + text);
			map.put(name, text);
		}
		return map;
	}

	public String msg(String body) throws Exception {
		Map<String, String> msgMap = this.parseMsg(body);
		if (msgMap.get("MsgType").equalsIgnoreCase("text")) {
			return msgService.text(msgMap);
		} else if (msgMap.get("MsgType").equalsIgnoreCase("image")) {
			return MsgUtil.toText(msgMap.get("ToUserName"), msgMap.get("FromUserName"),
					"暂不支持图片。\r\nnot surpport image.");
		} else if (msgMap.get("MsgType").equalsIgnoreCase("voice")) {
			return MsgUtil.toText(msgMap.get("ToUserName"), msgMap.get("FromUserName"),
					"暂不支持语音。\r\nnot surpport voice.");
		} else if (msgMap.get("MsgType").equalsIgnoreCase("video")) {
			return MsgUtil.toText(msgMap.get("ToUserName"), msgMap.get("FromUserName"),
					"暂不支持视频。\r\nnot surpport video.");
		} else if (msgMap.get("MsgType").equalsIgnoreCase("shortvideo|")) {
			return MsgUtil.toText(msgMap.get("ToUserName"), msgMap.get("FromUserName"),
					"暂不支持小视频。\r\nnot surpport shortvideo.");
		} else if (msgMap.get("MsgType").equalsIgnoreCase("location")) {
			return MsgUtil.toText(msgMap.get("ToUserName"), msgMap.get("FromUserName"),
					"暂不支持地理位置消息。\r\nnot surpport location.");
		} else if (msgMap.get("MsgType").equalsIgnoreCase("event")) {
			if ("subscribe".equalsIgnoreCase(msgMap.get("Event"))) {
				return eventService.subscribe(msgMap);
			} else if ("subscribe".equalsIgnoreCase(msgMap.get("Event"))) {
				return eventService.unsubscribe(msgMap);
			} else if ("SCAN".equalsIgnoreCase(msgMap.get("Event"))) {
				// 二维码扫描
				return eventService.scan(msgMap);
			}
		}
		return MsgUtil.toText(msgMap.get("ToUserName"), msgMap.get("FromUserName"), "不支持的事件类型，消息体为：\r\n" + body);
	}

	public String checkSignature(String signature, String timestamp, String nonce, String echostr) {
		String[] tmpStr = sort(token, timestamp, nonce);
		String pwd = Password.SHA1(combine(tmpStr));
		LOCAL.info("SHA1: " + pwd);
		if (signature.equals(pwd)) {
			return echostr;
		}
		return "ERROR";
	}

	private String combine(String[] strs) {
		StringBuilder sbuilder = new StringBuilder();
		for (String str : strs) {
			sbuilder.append(str);
		}
		return sbuilder.toString();
	}

	private String[] sort(String... strs) {
		strs = StringUtils.sortStringArray(strs);
		LOCAL.info("after sort: " + StringUtils.arrayToDelimitedString(strs, ", "));
		return strs;
	}

	protected String getAccessToken() throws IOException, JSONException {
		String accessToken = getLocalAccessToken();
		if (accessToken == null) {
			accessToken = WxApi.token(appId, appSecret);
			setLocalAccessToken(accessToken);
		}
		return accessToken;
	}

	private synchronized void setLocalAccessToken(String accessToken) throws IOException {
		File file = new File(LOCAL_ACCESS_TOKEN_FILE);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileUtils.write(file, accessToken, "UTF-8", false);
		FileUtils.write(file, "\n" + System.currentTimeMillis(), "UTF-8", true);
	}

	private String getLocalAccessToken() throws IOException {
		File file = new File(LOCAL_ACCESS_TOKEN_FILE);
		if (!file.exists()) {
			file.createNewFile();
		}
		String accessTokens = FileUtils.readFileToString(file, "UTF-8");
		String accessToken = accessTokens.substring(0, accessTokens.indexOf('\n'));
		String createTimestampStr = accessTokens.substring(accessTokens.indexOf('\n') + 1);
		long createTimestamp = Long.parseLong(createTimestampStr);
		if (System.currentTimeMillis() - createTimestamp > ACCESS_TOKEN_TIMEOUT * 1000) {
			// timeout
			LOCAL.info("Weixin AccessToken Timeout");
			return null;
		}
		return accessToken;
	}

	public static void main(String[] args) throws IOException, JSONException {
		WxService wxService = new WxService();
		wxService.appId = "wx6cfe5927a6bfcab5";
		wxService.appSecret = "600ecbecf7255671886426f6e52bcacd";
		wxService.token = "wxitsucrecom";

		String signature = "3a0c098eabf0ebd1e51547ce2329251a1b904fd0";
		String timestamp = "1478159079";
		String nonce = "260508759";
		String echostr = "AAAAA";

		String accessToken = wxService.checkSignature(signature, timestamp, nonce, echostr);
		System.out.println(accessToken);
	}

}
