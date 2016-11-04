package cn.cjp.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

public class XmlUtil {

	public static Element getElement(String xml) throws DocumentException {
		StringReader sr = new StringReader(xml);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(sr);
		Element elem = doc.getRootElement();
		return elem;
	}

	@Deprecated
	public static String toXml(Map<String, String> xmlMap) throws IOException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("xml");
		for (String key : xmlMap.keySet()) {
			Element child = root.addElement(key);
			child.setText(xmlMap.get(key));
		}
		StringWriter writer = new StringWriter();
		XMLWriter output = new XMLWriter(writer);
		output.write(doc);
		output.close();
		return writer.toString();
	}

	public static void main(String[] args) throws SAXException, IOException, DocumentException {

		Element elem = getElement("<xml>" + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName>" + "<CreateTime>1348831860</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>" + "<Content><![CDATA[this is a test]]></Content>"
				+ "<MsgId>1234567890123456</MsgId>" + "</xml>\n");

		for (@SuppressWarnings("unchecked")
		Iterator<Element> it = elem.elementIterator(); it.hasNext();) {
			Element child = it.next();
			String name = child.getName();
			String text = child.getText();
			System.out.println(name + " " + text);
		}
		
	}

}
