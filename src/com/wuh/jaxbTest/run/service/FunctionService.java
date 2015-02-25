package com.wuh.jaxbTest.run.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wuh.jaxbTest.bean.Function;
import com.wuh.jaxbTest.bean.Property;
import com.wuh.jaxbTest.utils.SpringServicFactory;

public class FunctionService {
	private static Log log = LogFactory.getLog(FunctionService.class);
	private Map<String,Object> returnkeyMap=new HashMap<String, Object>();
	private Map<String,Object> paramMap;
	
	public FunctionService(Map<String,Object> paramMap){
		this.paramMap=paramMap;
	}
	
	public Map<String,Object> run(List<Function> functionList){
		for(Function f:functionList){
			doFunction(f);
		}
		return returnkeyMap;
	}
	
	public void doFunction(Function function){
		Object obj=SpringServicFactory.getBean(function.getBean());
		Object[] args=doProperty(function.getProperty());
		Object returnValue=invokeMethod(obj,function.getMethod(),args);
		returnkeyMap.put(function.getReturnkey(), returnValue);
	}
	
	public Object[] doProperty(List<Property> propertyList){
		int propertySize=propertyList.size();
		Object args[]=new Object[propertySize];
		for(int i=0;i<propertySize;i++){
			String method=propertyList.get(i).getMethod();
			Object bean=paramMap.get(propertyList.get(i).getRefkey());
			if(bean==null||StringUtils.isEmpty(method)){
				throw new RuntimeException("必填业务属性为空！");
			}
			args[i]=invokeMethod(bean,method,null);
		}
		return args;
	}
	
	public Object invokeMethod(Object o,String methodName,Object[] args){
		Object obj=null;
		try {
			obj = MethodUtils.invokeMethod(o, methodName, args);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
