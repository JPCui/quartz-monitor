package cn.cjp.weixin.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cjp.tuling123.api.TulingApi;
import cn.cjp.weather.service.WeatherService;
import cn.cjp.weixin.util.MsgUtil;

/**
 * 普通消息业务
 * 
 * @author Jinpeng Cui
 *
 */
@Component
public class MsgService {

	private static final Logger LOCAL = Logger.getLogger("LOCAL");

	@Autowired
	TulingApi tulingApi;

	@Autowired
	WeatherService weatherService;

	public String text(Map<String, String> msgMap) throws Exception {
		String from = msgMap.get(MsgUtil.KEY_FromUserName);
		String to = msgMap.get(MsgUtil.KEY_ToUserName);
		String content = msgMap.get(MsgUtil.KEY_Content);
		String rec = ":Cmd NotImpl.";
		if (content.startsWith(":") || content.startsWith("：")) {
			String city = content.substring(1);
			try {
				// 获取天气
				Object weatherRec = weatherService.index(city);
				if (weatherRec != null) {
					if (weatherRec instanceof Collection) {
						rec = "考虑以下城市：\r\n";
						@SuppressWarnings("unchecked")
						Iterator<String> it = ((Collection<String>) weatherRec).iterator();
						int times = 0;
						do {
							if (times++ != 0) {
								rec += ", ";
							}
							rec += it.next();
						} while (it.hasNext());
					} else if (weatherRec instanceof String) {
						JSONObject json = new JSONObject(weatherRec.toString());
						JSONObject weatherinfo = json.getJSONObject("weatherinfo");
						String temp1 = weatherinfo.getString("temp1");
						String temp2 = weatherinfo.getString("temp2");
						String weather = weatherinfo.getString("weather");
						String ptime = weatherinfo.getString("ptime");
						rec = temp1 + "/" + temp2 + " " + weather + " " + ptime;
					} else {
						rec = "没有找到该城市.";
					}
				}
			} catch (Exception e) {
				LOCAL.info(e.getMessage(), e);
			}
			return MsgUtil.toText(to, from, rec);
		} else {
			// 交给机器人
			try {
				String info = tulingApi.request(content);
				return MsgUtil.handlerTulingText(to, from, info);
			} catch (IOException e) {
				LOCAL.info(e.getMessage(), e);
			}
		}
		LOCAL.warn("Some Question !");
		LOCAL.warn(msgMap);
		return null;
	}

}
