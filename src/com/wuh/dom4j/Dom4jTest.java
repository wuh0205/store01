package com.wuh.dom4j;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jTest {

	/**
	 * 将字符串转换成Document对象1
	 * @param str
	 * @return
	 */
	public Document strToDocument(String str){
		Document document=null;
		try {
			document = DocumentHelper.parseText(str);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return document;
	}
	
	/**
	 * 读取xml文件，获取Document对象
	 * @param url
	 * @return
	 */
	public Document getDocumentFromFile(String url){
		SAXReader reader = new SAXReader();
		Map<String, String>map=new HashMap<String, String>();  
		Document document=null;
	    try {
			document = reader.read(new File(url));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 * 读取xml文件，获取Document对象,为xpath读取，设置命名空间
	 * @param url
	 * @param map
	 * @return
	 */
	public Document getDocumentFromFile(String url,Map map){
		SAXReader reader = new SAXReader();
		if(map==null||map.size()<0){
			throw new RuntimeException("参数map不可为空");
		}
		reader.getDocumentFactory().setXPathNamespaceURIs(map);  
		Document document=null;
	    try {
			document = reader.read(new File(url));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 * 将Document对象转成字符串
	 * @param document
	 * @return
	 */
	public String DocumentToStr(Document document){
		return document.asXML();
	}
	
	
	
	
	public static void main(String[] args) {
		Dom4jTest test=new Dom4jTest();
		//输出 ： 686b367e@im.brand.cn/686b367e
		Document document= test.getDocumentFromFile("src/com/wuh/dom4j/xml/test1.xml");
		String xpath1="//*[name()='jid']";
		Element  e1= (Element ) document.selectSingleNode(xpath1);
		System.out.println("值 : "+e1.getTextTrim());
		
		//当xml中含有命名空间时，输出 ：686b367e@im.brand.cn/686b367e
		String xpath2="/p1:body/p2:iq/p3:bind/*";
		Map<String, String> map=new HashMap<String, String>();
		map.put("p1","http://jabber.org/protocol/httpbind");
		map.put("p2","jabber:client");
		map.put("p3", "urn:ietf:params:xml:ns:xmpp-bind");
		Document document2=test.getDocumentFromFile("src/com/wuh/dom4j/xml/test1.xml",map);
	    Element  e2= (Element ) document2.selectSingleNode(xpath2);
		System.out.println("值 : "+e2.getTextTrim());
		//取值  输出 ：ello
		Document document3= test.getDocumentFromFile("src/com/wuh/dom4j/xml/test2.xml");
		String xpath3="/a/b[@id='b_01']";
		System.out.println("值 : "+document3.selectSingleNode(xpath3).getText().trim());
		
		//取值  输出 ：value2
		String xpath4="/a/b[2]/c[@atr='13']";
		System.out.println("值 : "+document3.selectSingleNode(xpath4).getText().trim());
		
		//取属性  输出 ： B
		String xpath5="/a/b[2]/@name";
		System.out.println("属性: "+document3.selectSingleNode(xpath5).getText().trim());
		
		//遍历取Attribute对象
		String xpath6="/a/b/@id";
		List elist=document3.selectNodes(xpath6);
		for(Iterator i=elist.iterator();i.hasNext();){
			Attribute attr=(Attribute) i.next();
			System.out.println("属性名："+attr.getName()+"  属性值："+attr.getValue());
		}
		
		//遍历取Element对象
		String xpath7="/a/b/c";
		List elist2=document3.selectNodes(xpath7);
		for(Object o:elist2){
			Element e=(Element) o;
			System.out.println("标签名："+e.getName()+"  标签值："+e.getTextTrim()+" 属性atr的值："+e.attributeValue("atr"));
		}
		
	}
	

}
