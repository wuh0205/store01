package com.wuh.test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.*;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;


public class TestMain {

	private static String getKey(String... args) {
		return StringUtils.join(args, "-");
	}

	private static String getKey1(List... args) {
		return StringUtils.join(args, "#");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		/*
		 * int [] tset1 = new int []{2,3,4,5,6,7,8} ;
		 * 
		 * System.out.println(Arrays.toString(tset1));
		 * 
		 * int [] test2 = new int [tset1.length] ;
		 * 
		 * System.arraycopy(tset1, 1, test2, 0, 3) ;
		 * 
		 * System.out.println(Arrays.toString(test2));
		 */

//		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(5);
//		for (int i = 0; i < 6; i++) {
//			try {
//				if (i == 5) {
//					String qu = queue.take();
//					System.out.println("take:" + qu);
//				}
//				System.out.println(i);
//				queue.put(String.valueOf(i));
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		/*System.out.println(getKey("a", "b", "c"));
		System.out.println(getKey());
		String[] ags={"o","l","o"};
		System.out.println(getKey(ags));
		List<String> list1 = new ArrayList<String>();
		list1.add("a");
		list1.add("b");
		list1.add("c");
		List<String> list2 = new ArrayList<String>();
		list2.add("1");
		list2.add("2");
		list2.add("3");
		System.out.println(getKey1(list1, list2));*/
		
		 /*BigDecimal divide1 = new BigDecimal("3655.36");
         BigDecimal divide2 = new BigDecimal("6589.23888");
         double num = divide1.divide(divide2, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
         System.out.println(num);*/
         
         /*Properties pros = new Properties();
         InputStream in = TestMain.class.getResourceAsStream("/socket.properties");
         try {
			pros.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         Set<Object> keySet = pros.keySet();
         System.out.println(keySet.size());
         for(Object key:keySet){
        	 String str=(String) pros.get(key);
        	 System.out.println(str);
        	 str.replaceAll("##", "@234453@@");
        	 System.out.println(str);
         }
         
         String json = "{\"name1\":\"value1\",\"na\"me2\":\"va\"l\"u\"e2\"}";   
         System.out.println(json);  
         Pattern p = Pattern.compile("(?<=(\\{|,|:)\").*?(?=\"(:|,|\\}))");  
         Matcher m = p.matcher(json);  
         
         String srcString = "{\"name1\":\"#value1#\"}";
         System.out.println(srcString);
         srcString = srcString.replaceAll("#value1#","8888");
 		System.out.println(srcString);
 		
 		String str1="{\"head\":\"#headsequenceNo#\"}";
 		str1=str1.replaceAll("#headsequenceNo#", "8888");
 		System.out.println(str1);*/
		
//		String[] arr={"a","b","c","d"}; 
//		String[] arr1=arr;
//		arr1[3]="345";
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i]);
//		}
//		System.out.println();
//		for (int i = 0; i < arr1.length; i++) {
//			System.out.print(arr1[i]);
//		}
		
		String body="{\"sgwId\":1, \"isMasterSgw\":1}";
		System.out.println(body.length());
	}

}
