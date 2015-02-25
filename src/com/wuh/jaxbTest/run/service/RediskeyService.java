package com.wuh.jaxbTest.run.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wuh.jaxbTest.bean.Method;
import com.wuh.jaxbTest.bean.Param;
import com.wuh.jaxbTest.bean.Property;
import com.wuh.jaxbTest.bean.Rediskey;
import com.wuh.jaxbTest.bean.vo.MethodVO;
import com.wuh.jaxbTest.bean.vo.ParamVO;
import com.wuh.jaxbTest.bean.vo.RediskeyVO;
import com.wuh.jaxbTest.bean.vo.ReturnVO;

public class RediskeyService {
	private static Log log = LogFactory.getLog(RediskeyService.class);
	private Map<String,Object> paramMap; 
	private Map<String,Object> returnkeyMap; 
	
	public RediskeyService(Map<String,Object> paramMap,Map<String,Object> returnkeyMap){
		this.paramMap=paramMap;
		this.returnkeyMap=returnkeyMap;
	}
	
	public ReturnVO run(List<Rediskey> rediskeyList){
		List<RediskeyVO> rediskeyVOList=new ArrayList<RediskeyVO>();
		for(Rediskey r:rediskeyList){
			rediskeyVOList.add(doRediskey(r));
		}
		//封装ReturnVO对象
		ReturnVO returnVO=new ReturnVO();
		returnVO.setRediskeyVOList(rediskeyVOList);
		return returnVO;
	}
	
	public RediskeyVO doRediskey(Rediskey rediskey){
		String name=rediskey.getName();
		List<String> strList=new ArrayList<String>();
		strList.add(name);
		List<MethodVO> methodVOList=new ArrayList<MethodVO>();
	    List<Object> rediskeyGroup=rediskey.getRediskeyGroup();
	    for(Object obj:rediskeyGroup){
	    	if(obj instanceof Property){
	    		strList.add(doProperty((Property) obj));
	    	}else if(obj instanceof Method){
	    		methodVOList.add(doMethod((Method) obj));
	    	}
	    }
	    //封装RediskeyVO对象
	    RediskeyVO redisKeyVO=new RediskeyVO();
	    redisKeyVO.setKey(getKey(strList));
	    redisKeyVO.setMethodList(methodVOList);
		return redisKeyVO;
	}
	
	public String doProperty(Property property){
		String subStr="";
		String value=property.getValue();
		String refKey=property.getRefkey();
		if(StringUtils.isNotEmpty(value)){
			String[] arr=value.split("[.]");
			if(arr.length>1){
				subStr=String.valueOf(invokeMethod(getObjectFromParamMap(arr[0],"Property"),arr[1],null));
			}else{
				subStr=value;
			}
		}else if(StringUtils.isNotEmpty(refKey)){
			if(returnkeyMap.containsKey(refKey)){
				subStr = (String) returnkeyMap.get(refKey);
			}else{
				throw new RuntimeException("拼接rediskey时，未在所有Function返回的参数中找到对应的refkey！");
			}
		}
		return subStr;
	}
	
	public MethodVO doMethod(Method method){
		String name=method.getName();
		String type=method.getType();
		List<Param> paramList=method.getParam();
		List<ParamVO> paramVOList=new ArrayList<ParamVO>();
		if(paramList!=null&&paramList.size()>0){
			for(Param p:paramList){
				paramVOList.add(doParam(p));
			}
		}else{
			throw new RuntimeException("xml中method必须含有param属性!");
		}
		//封装MethodVO对象
		MethodVO methodVO =new MethodVO();
		methodVO.setName(name);
		methodVO.setType(type);
		methodVO.setParamList(paramVOList);
		return methodVO;
	}
	
	public ParamVO doParam(Param param){
		ParamVO paramVO=new ParamVO();
		String field=param.getField();
		if(StringUtils.isNotEmpty(field)){
			String[] arr=field.split("[.]");
			if(arr.length>1){
				String bean=arr[0];
				String methodName=arr[1];
				Object obj=invokeMethod(getObjectFromParamMap(bean,"Param"),methodName,null);
				paramVO.setField(obj);
			}else{
				paramVO.setField(field);
			}
		}else{
			throw new RuntimeException("xml中param属性不能为空，请检查配置文件!");
		}
		return paramVO;
		
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
	
	
	
	private Object getObjectFromParamMap(String key,String type) {
		Object obj=null;
		if(paramMap.containsKey(key)){
			obj=paramMap.get(key);
		}else{
			throw new RuntimeException("处理"+type+"属性时，未在ParameterVO中找到对应参数！");
		}
		return obj;
	}
	
	public  String getKey(List<String> list){
		return StringUtils.join(list, ":");
	} 

	public static void main(String[] args) {
		String str="sisiek";
		String str1="bean.method";
		System.out.println(str.split("[.]")[0]+"------------");
		System.out.println(str1.split("[.]")[0]+"------------"+str1.split("[.]")[1]);
		
	}

}
