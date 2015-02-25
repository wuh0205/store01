package com.wuh.jaxbTest.jTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.wuh.jaxbTest.bean.Business;
import com.wuh.jaxbTest.bean.Function;
import com.wuh.jaxbTest.bean.Method;
import com.wuh.jaxbTest.bean.Property;
import com.wuh.jaxbTest.bean.Rediskey;
import com.wuh.jaxbTest.bean.vo.ParameterVO;
import com.wuh.jaxbTest.bean.vo.RediskeyVO;
import com.wuh.jaxbTest.bean.vo.ReturnVO;
import com.wuh.jaxbTest.bean.vo.XmlTestVO;
import com.wuh.jaxbTest.run.BusinessEngine;
import com.wuh.jaxbTest.run.BusinessPool;

public class BusinessTest extends BaseTest{
	private XmlTestVO xmlTestVO;
	@Before
	public void initVO(){
		xmlTestVO=new XmlTestVO();
		xmlTestVO.setName("wuh");
		xmlTestVO.setAge(26);
		xmlTestVO.setAddress("地球");
	}
	
	@Test
	public void checkBusinessById查看是否可以根据business的id查询到相应的对象(){
		List<Function> functionList=new ArrayList<Function>();
		List<Rediskey> rediskeyList=new ArrayList<Rediskey>();
		
		Map<String,Business> map=BusinessPool.getInstance().getBusinessMap();
		assertTrue(map.size()>0);
		Business business=map.get("engine.transaction.saveSpotTDTransaction.Q4001.key.demo");
		List<Object> businessGroup=business.getBusinessGroup();
		for(Object o:businessGroup){
			if(o instanceof Function){
				functionList.add((Function)o);
			}else if(o instanceof Rediskey){
				rediskeyList.add((Rediskey)o);
			}
		}
		assertTrue(functionList.size()==1);
		assertTrue(rediskeyList.size()==1);
		assertEquals(functionList.get(0).getBean(), "JAXTestOneService");
		assertEquals(rediskeyList.get(0).getName(), "HMEMBER_DECLARATION");
		List<Object> rediskeyGroup=rediskeyList.get(0).getRediskeyGroup();
		for(Object r:rediskeyGroup){
			if(r instanceof Method){
				assertNotNull(((Method) r).getName());
			}
		}
		List<Property> propertyList=functionList.get(0).getProperty();
		assertNotNull(propertyList);
        assertTrue(propertyList.size()>0);
		for(Property pro:propertyList){
			assertNotNull(pro);
		}
	}
	
	@Test
	public void testRunService测试XML解析后业务逻辑的运行(){
		ParameterVO parameterVO=new ParameterVO();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("xmlTestVO", xmlTestVO);
		parameterVO.setProcessId("engine.transaction.saveSpotTDTransaction.Q4001.key.demo");
		parameterVO.setParamMap(map);
		ReturnVO returnVO=BusinessEngine.runBusinessService(parameterVO);
		assertNotNull(returnVO);
		List<RediskeyVO>rediskeyList=returnVO.getRediskeyVOList();
		String key=rediskeyList.get(0).getKey();
		assertNotNull(key);
		System.out.println(key);
		assertEquals(key, "HMEMBER_DECLARATION:test1:wuh:YEA");
	}
	
	@Test
	public void testMoreBusinessService测试多个xml中多个business中多个function运行情况(){
		ParameterVO parameterVO_1=new ParameterVO();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("xmlTestVO", xmlTestVO);
		
		parameterVO_1.setProcessId("engine.jaxbTest.business.demo1");
		parameterVO_1.setParamMap(map);
		ReturnVO returnVO_1=BusinessEngine.runBusinessService(parameterVO_1);
		List<RediskeyVO> rediskeyListList_1=returnVO_1.getRediskeyVOList();
		assertNotNull(rediskeyListList_1.get(0).getKey());
		assertNotNull(rediskeyListList_1.get(1).getKey());
		assertEquals(rediskeyListList_1.get(0).getKey(), "ZCUSTOMER_INOUT_WAREHOUSE:test2:aaa:HA");
		assertEquals(rediskeyListList_1.get(1).getKey(), "HMEMBER_INOUT_WAREHOUSE:26:test2:YEA");
		
		ParameterVO parameterVO_2=new ParameterVO();
		parameterVO_2.setProcessId("engine.jaxbTest.business.demo2");
		parameterVO_2.setParamMap(map);
		ReturnVO returnVO_2=BusinessEngine.runBusinessService(parameterVO_2);
		List<RediskeyVO> rediskeyListList_2=returnVO_2.getRediskeyVOList();
		assertNotNull(rediskeyListList_2.get(0).getKey());
		System.out.println(rediskeyListList_2.get(0).getKey());
		assertEquals(rediskeyListList_2.get(0).getKey(), "HMEMBER_DECLARATION:test3:wuh:YA");
		String feild=(String) rediskeyListList_2.get(0).getMethodList().get(0).getParamList().get(0).getField();
		assertNotNull(feild);
		assertEquals(feild, "地球");
	}

}
