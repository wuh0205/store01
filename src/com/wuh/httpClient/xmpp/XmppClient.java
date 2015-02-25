package com.wuh.httpClient.xmpp;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.wuh.test.utils.createMessage.MessageTemplet;

public class XmppClient {
	private static Log log = LogFactory.getLog(XmppClient.class);
	private static final String URL = "http://10.1.123.210:7070/http-bind/";
	private Properties pros;
	private int rid = 10000;
	private String sid;
	private String jid;

	public void initRequestXmlTemplet() {
		log.info("==开始初始化报文模板");
		pros = new Properties();
		InputStream in = MessageTemplet.class
				.getResourceAsStream("/requestXml.properties");
		try {
			pros.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String post(String requestXml) {
		log.info("requestXml: " + requestXml);
		HttpClient httpclient = new DefaultHttpClient();
		// 目标地址
		HttpPost httppost = new HttpPost(URL);
		// 构造最简单的字符串数据
		String body = "";
		try {
			StringEntity reqEntity = new StringEntity(requestXml);
			// 设置类型
			reqEntity.setContentType("application/xml");
			// 设置请求的数据
			httppost.setEntity(reqEntity);
			// 执行
			HttpResponse httpresponse = httpclient.execute(httppost);
			log.info("StatusCode: "
					+ httpresponse.getStatusLine().getStatusCode());
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity);
			log.info("Body: " + body);
			rid += 1;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return body;
	}

	public Document strToXMLDocument(String xmlStr) {
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}

	public void step1() {
		log.info("====== step1 =======");
		String requestXml = (String) pros.get("step1_xml");
		requestXml = requestXml.replaceAll("#rid#", String.valueOf(rid));
		step2(post(requestXml));
	}

	public void step2(String responseXml) {
		log.info("====== step2 =======");
		Document document = strToXMLDocument(responseXml);
		Element root = document.getRootElement();
		sid = root.attributeValue("sid");
		String mechanism = "ANONYMOUS";

		String requestXml = (String) pros.get("step2_xml");
		requestXml = requestXml.replaceAll("#rid#", String.valueOf(rid))
				.replaceAll("#sid#", sid).replaceAll("#mechanism#", mechanism);
		step3(post(requestXml));
	}

	public void step3(String responseXml) {
		log.info("====== step3 =======");
		Document document = strToXMLDocument(responseXml);
		Element root = document.getRootElement();
		Element success = root.element("success");
		if (success == null) {
			return;
		}

		String requestXml = (String) pros.get("step3_xml");
		requestXml = requestXml.replaceAll("#rid#", String.valueOf(rid))
				.replaceAll("#sid#", sid);
		step4(post(requestXml));
	}

	public void step4(String responseXml) {
		log.info("====== step4 =======");
		Document document = strToXMLDocument(responseXml);
		Element root = document.getRootElement();
		List list = root.elements("stream:features");
		if (list == null || list.size() < 0) {
			return;
		}

		String requestXml = (String) pros.get("step4_xml");
		requestXml = requestXml.replaceAll("#rid#", String.valueOf(rid))
				.replaceAll("#sid#", sid);
		step5(post(requestXml));
	}

	public void step5(String responseXml) {
		log.info("====== step5 =======");
		Document document = strToXMLDocument(responseXml);
		Element root = document.getRootElement();
		// Element element=(Element) root.selectSingleNode("//iq/bind/jid");
		Element iq = root.element("iq");
		if (iq == null) {
			return;
		}
		Element bind = iq.element("bind");
		if (bind == null) {
			return;
		}
		jid = bind.element("jid").getTextTrim();

		String requestXml = (String) pros.get("step4_xml");
		requestXml = requestXml.replaceAll("#rid#", String.valueOf(rid))
				.replaceAll("#sid#", sid);
		step6(post(requestXml));
	}

	public void step6(String responseXml) {
		log.info("====== step6 =======");
		Document document = strToXMLDocument(responseXml);
		Element root = document.getRootElement();
		Element iq = root.element("iq");
		String requestXml = "";
		String xmlTempletName = "";
		if (iq != null) {
			xmlTempletName = "step5_xml";
		} else {
			xmlTempletName = "step6_xml";
		}
		requestXml = (String) pros.get(xmlTempletName);
		requestXml = requestXml.replaceAll("#rid#", String.valueOf(rid))
				.replaceAll("#sid#", sid);
//		step6(post(requestXml));
	}

	@Override
	public String toString() {
		return "所需值: [rid=" + rid + ", sid=" + sid + ", jid=" + jid + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XmppClient xmppClient = new XmppClient();
		xmppClient.initRequestXmlTemplet();
		xmppClient.step1();
		System.out.println(xmppClient.toString());
	}

}
