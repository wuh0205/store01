package com.wuh.reflectTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Test {
	
	public void demo_1(){
		
	}
	

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		String className="com.wuh.reflectTest.bean.Person";
		Class<?> testClass=Class.forName(className);
		
		Object o=testClass.newInstance();
		Method sayHelloMethod=testClass.getMethod("sayHello", String[].class);
		sayHelloMethod.invoke(o, new Object[]{new String[]{"wuh1","wuh2","wuh3"}});
		Method smileMethod=testClass.getMethod("smile", new Class<?>[]{String.class,int.class});
		System.out.println(smileMethod.invoke(o, new Object[]{"haha~",5}));
		
		

		

	}

}
