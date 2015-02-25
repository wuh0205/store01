package com.wuh.httpClient;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;


public class httpClientTest {
	
//	public String post(String url,String xmlFileName){    
//		//关闭   
//        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");     
//        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");     
//        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");
//        
//      //创建HttpClientBuilder  
//        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
//        //HttpClient  
//        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
//          
//        //创建httpclient工具对象   
////        HttpClient client = new HttpClient();   
//        CloseableHttpClient httpclient = HttpClients.createDefault();  
//        //创建post请求方法   
//        PostMethod myPost = new PostMethod(url);    
//        //设置请求超时时间   
//        client.setConnectionTimeout(20*1000);  
//        String responseString = null;    
//        String xmlString="<body rid='3847621530' xmlns='http://jabber.org/protocol/httpbind' to='zhengyan01' xml:lang='en' wait='60' hold='1' content='text/xml; charset=utf-8' ver='1.6' xmpp:version='1.0' xmlns:xmpp='urn:xmpp:xbosh'/>";
//        try{    
//            //设置请求头部类型   
//            myPost.setRequestHeader("Content-Type","text/xml");  
//            myPost.setRequestHeader("charset","utf-8");  
//              
//            //设置请求体，即xml文本内容，注：这里写了两种方式，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式   
//          myPost.setRequestBody(xmlString);   
//                
////            InputStream body=this.getClass().getResourceAsStream("/"+xmlFileName);  
////            myPost.setRequestBody(body);  
////            myPost.setRequestEntity(new StringRequestEntity(xmlString,"text/xml","utf-8"));     
//            int statusCode = client.executeMethod(myPost);    
//            
//            System.out.println("statusCode:  "+statusCode);
//            if(statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_MOVED_TEMPORARILY){    
//                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());    
//                byte[] bytes = new byte[1024];    
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();    
//                int count = 0;    
//                while((count = bis.read(bytes))!= -1){    
//                    bos.write(bytes, 0, count);    
//                }    
//                byte[] strByte = bos.toByteArray();    
//                responseString = new String(strByte,0,strByte.length,"utf-8");    
//                bos.close();    
//                bis.close();    
//            }    
//        }catch (Exception e) {    
//            e.printStackTrace();    
//        }    
//        myPost.releaseConnection();    
//        
//        System.out.println("==="+responseString);
//        return responseString;    
//    }
	
	@SuppressWarnings({ "deprecation", "resource" })
	public void post(String url) throws Exception{
		 HttpClient  httpclient = new DefaultHttpClient();    
         // 目标地址    
          HttpPost httppost = new HttpPost(url);    
         // 构造最简单的字符串数据    
//          StringEntity reqEntity = new StringEntity("<body rid=\"371110853\" xmlns=\"http://jabber.org/protocol/httpbind\" to=\"zhengyan01\" xml:lang=\"en\" wait=\"60\" hold=\"1\" content=\"text/xml; charset=utf-8\" ver=\"1.6\" xmpp:version=\"1.0\" xmlns:xmpp=\"urn:xmpp:xbosh\"/>");    
          StringEntity reqEntity = new StringEntity("<body rid='100' xmlns='http://jabber.org/protocol/httpbind' to='zhengyan01' xml:lang='en' wait='60' hold='1' content='text/xml; charset=utf-8' ver='1.6' xmpp:version='1.0' xmlns:xmpp='urn:xmpp:xbosh'/>");    
         // 设置类型    
          reqEntity.setContentType("application/xml");    
//          httppost.setHeader("Content-Type", "text/plain; charset=UTF-8");
//          httppost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
         // 设置请求的数据    
          httppost.setEntity(reqEntity);    
         // 执行    
         HttpResponse httpresponse = httpclient.execute(httppost);  
         System.out.println("StatusCode: "+httpresponse.getStatusLine().getStatusCode());
         HttpEntity entity = httpresponse.getEntity();  
         String body = EntityUtils.toString(entity); 
         System.out.println("Body: "+body); 
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="http://10.1.123.210:7070/http-bind/";
		httpClientTest hct=new httpClientTest();
		try {
			hct.post(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
