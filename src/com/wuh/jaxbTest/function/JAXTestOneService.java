package com.wuh.jaxbTest.function;

import org.springframework.stereotype.Service;

@Service
public class JAXTestOneService {
	
	public String getType_a(String a,int b,String c){
		return "test1";
	}
	
	public String getType_b(Object obj){
		return "test2";
	}
	
	public String getType_c(int a){
		return "test3";
	}

}
