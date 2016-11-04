package cn.cjp.weixin.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.cjp.weixin.util.MsgUtil;

@Component
public class EventService {

	private static final Logger LOCAL = Logger.getLogger("LOCAL");

	@Value("${wx.subscribe}")
	String MSG_SUBSCRIBE;

	@Value("${wx.unsubscribe}")
	String MSG_UNSUBSCRIBE;

	public EventService() {
		LOCAL.info("Sub is ready.");
	}

	public String subscribe(Map<String, String> msgMap) {
		String from = msgMap.get(MsgUtil.KEY_FromUserName);
		String to = msgMap.get(MsgUtil.KEY_ToUserName);
		return MsgUtil.toText(to, from, MSG_SUBSCRIBE);
	}

	public String unsubscribe(Map<String, String> msgMap) {
		String from = msgMap.get(MsgUtil.KEY_FromUserName);
		String to = msgMap.get(MsgUtil.KEY_ToUserName);
		return MsgUtil.toText(to, from, MSG_UNSUBSCRIBE);
	}

	public String scan(Map<String, String> msgMap) {
		String from = msgMap.get(MsgUtil.KEY_FromUserName);
		String to = msgMap.get(MsgUtil.KEY_ToUserName);
		return MsgUtil.toText(to, from, "暂不支持二维码扫描回复。\r\nnot surpport scan.");

	}

}
