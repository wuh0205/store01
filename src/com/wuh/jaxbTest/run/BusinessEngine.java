package com.wuh.jaxbTest.run;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wuh.jaxbTest.bean.Business;
import com.wuh.jaxbTest.bean.vo.ParameterVO;
import com.wuh.jaxbTest.bean.vo.ReturnVO;
import com.wuh.jaxbTest.run.service.BusinessService;

public class BusinessEngine {
	private static Log log = LogFactory.getLog(BusinessEngine.class);
	
	public static ReturnVO runBusinessService(ParameterVO ParameterVO){
		ReturnVO returnVO=null;
		String id=ParameterVO.getProcessId();
		Map<String,Object> paramMap=ParameterVO.getParamMap();
		if(paramMap==null||paramMap.size()==0||StringUtils.isEmpty(id)){
			throw new RuntimeException("business的id或参数为空，请检查");
		}
		Business business=BusinessPool.getInstance().getBusinessMap().get(id);
		if(business!=null){
			BusinessService businessSerive=new BusinessService(business);
			returnVO=businessSerive.run(ParameterVO);
		}else{
			log.error("没有根据business的id找到相应对象，id为："+id);
		}
		return returnVO;
	}
	
}
