package com.wuh.reflectTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Test {
	
	public void demo_1() throws Exception{
		String className="com.wuh.reflectTest.bean.Person";
		Class<?> testClass=Class.forName(className);
		Object o=testClass.newInstance();
		
		Method sayHelloMethod=testClass.getMethod("sayHello", String[].class);
		sayHelloMethod.invoke(o, new Object[]{new String[]{"wuh1","wuh2","wuh3"}});
		
		Method smileMethod=testClass.getMethod("smile", new Class<?>[]{String.class,int.class});
		System.out.println(smileMethod.invoke(o, new Object[]{"haha~",5}));
	}
	
	public void demo_2()  throws Exception{
		 Class<?> demo=null;
	        try{
	            demo=Class.forName("com.wuh.reflectTest.bean.Person");
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        Method method[]=demo.getMethods();
	        for(int i=0;i<method.length;++i){
	            Class<?> returnType=method[i].getReturnType();
	            Class<?> para[]=method[i].getParameterTypes();
	            int temp=method[i].getModifiers();
	            System.out.print(Modifier.toString(temp)+" ");
	            System.out.print(returnType.getName()+"  ");
	            System.out.print(method[i].getName()+" ");
	            System.out.print("(");
	            for(int j=0;j<para.length;++j){
	                System.out.print(para[j].getName()+" "+"arg"+j);
	                if(j<para.length-1){
	                    System.out.print(",");
	                }
	            }
	            Class<?> exce[]=method[i].getExceptionTypes();
	            if(exce.length>0){
	                System.out.print(") throws ");
	                for(int k=0;k<exce.length;++k){
	                    System.out.print(exce[k].getName()+" ");
	                    if(k<exce.length-1){
	                        System.out.print(",");
	                    }
	                }
	            }else{
	                System.out.print(")");
	            }
	            System.out.println();
	        }
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
		Test test=new Test();
		try {
			test.demo_1();
			test.demo_2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		

	}

}
