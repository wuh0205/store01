package com.wuh.jaxbTest.run.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wuh.jaxbTest.bean.Business;
import com.wuh.jaxbTest.bean.Function;
import com.wuh.jaxbTest.bean.Rediskey;
import com.wuh.jaxbTest.bean.vo.ParameterVO;
import com.wuh.jaxbTest.bean.vo.ReturnVO;

public class BusinessService {
	List<Function> functionList=new ArrayList<Function>();
	List<Rediskey> rediskeyList=new ArrayList<Rediskey>();
	
	public BusinessService(Business business){
		List<Object> groupList=business.getBusinessGroup();
		for(Object obj:groupList){
			if(obj instanceof Function){
				functionList.add((Function) obj);
			}else if(obj instanceof Rediskey){
				rediskeyList.add((Rediskey) obj);
			}
		}
		
	}
	
	public ReturnVO run(ParameterVO pararmeterVO){
		Map<String,Object> paramMap=pararmeterVO.getParamMap();
		FunctionService fService=new FunctionService(paramMap);
		Map<String,Object> returnkeyMap=fService.run(functionList);
		RediskeyService rService=new RediskeyService(paramMap,returnkeyMap);
		return rService.run(rediskeyList);
	}

}
