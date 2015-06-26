package com.wuh.reflectTest.bean;

import org.apache.commons.lang.StringUtils;

public class Person {
	
	private String name;
	
	private String age;
	
	public void sayHi(){
		System.out.println("Hi~");
	}
	
	public void sayHello(String... names){
		System.out.println("Hello "+StringUtils.join(names, ","));
	}
	
	public String smile(String voice,int num){
		String returnStr="";
		for(int i=0;i<num;i++){
			returnStr+=voice;
		}
		return returnStr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	

}
