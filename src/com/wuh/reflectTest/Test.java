package com.wuh.reflectTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.wuh.reflectTest.bean.Person;

public class Test {
	

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
		Method method1=testClass.getMethod("sayHi");
		Method method2=o.getClass().getMethod("Hello");
		method1.invoke(o);
		method2.invoke(o, "wuh ","wuh2");
//		method.invoke(o, "1",2);
		

	}

}
