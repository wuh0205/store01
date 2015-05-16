package com.wuh.reflectTest.bean;

public class Person {
	
	private String name;
	
	private String age;
	
	public void sayHi(){
		System.out.println("Hi~");
	}
	
	public void sayHello(String... name){
		System.out.println("Hello "+name);
	}
	
	public String smile(String voice,int num){
		for(int i=0;i<num;i++){
			voice+=voice;
		}
		return voice;
	}

}
