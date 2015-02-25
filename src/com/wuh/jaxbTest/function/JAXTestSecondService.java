package com.wuh.jaxbTest.function;

import org.springframework.stereotype.Service;

@Service
public class JAXTestSecondService {
	
	public String getTestResult_1(String a,int b,String c){
		return "aaa";
	}
	
	public String getTestResult_2(Object obj){
		return "bbb";
	}
	
	public String getTestResult_3(Object obj){
		return "ccc";
	}

}
