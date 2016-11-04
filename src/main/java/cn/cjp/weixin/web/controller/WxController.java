package cn.cjp.weixin.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cjp.weixin.service.WxService;

@Controller
@RequestMapping("/wx")
public class WxController {

	private static final Logger LOGGER = Logger.getLogger(WxController.class);

	@Autowired
	WxService wxService;

	@RequestMapping(value = { "", "/checkSignature" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkSignature(String signature, String timestamp, String nonce, String echostr) {
		return wxService.checkSignature(signature, timestamp, nonce, echostr);
	}

	@RequestMapping(value = { "", "/checkSignature" }, method = { RequestMethod.POST })
	@ResponseBody
	public String msg(String body, HttpServletRequest request) throws Exception {
		if (StringUtils.isEmpty(body)) {
			body = parseXml(request);
		}
		LOGGER.info(request.getParameterNames());
		LOGGER.info(body);

		try {
			return wxService.msg(body);
		} catch (DocumentException | IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return "";
	}

	public static String parseXml(HttpServletRequest request) throws Exception {
		// 从request中取得输入流
		InputStream is = request.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup
				.connect(
						"http://local:8080/quartz-monitor/wx/checkSignature.json?signature=68602785577d5dd8bd03ac07d8e34d7a663f0420&echostr=5127621535267287039&timestamp=1478158811&nonce=197912320")
				.ignoreHttpErrors(true).ignoreContentType(true)
				.data("body",
						"<xml><ToUserName><![CDATA[gh_1c238cc5e066]]></ToUserName><FromUserName><![CDATA[ooQxlwt36V9VsuOyv1bILQTLgDBI]]></FromUserName><CreateTime>1478240440</CreateTime>"
						+ "<MsgType><![CDATA[text]]></MsgType>"
						+ "<Content><![CDATA[来个新闻]]></Content>"
						+ "<Event><![CDATA[subscribe]]></Event>"
						+ "<EventKey><![CDATA[]]></EventKey></xml>")
				// .header("Content-Type", "text/xml")
				.post();

		System.out.println(doc);

	}

}
