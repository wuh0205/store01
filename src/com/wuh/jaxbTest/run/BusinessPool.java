package com.wuh.jaxbTest.run;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wuh.jaxbTest.bean.Business;

public class BusinessPool {
	private static Log log = LogFactory.getLog(BusinessPool.class);

	private static BusinessPool instance = new BusinessPool();

	private Map<String, Business> businessMap = new HashMap<String, Business>();

	public static BusinessPool getInstance() {
		return instance;
	}

	public void registerBusiness(List<Business> businessList) {
		for (Business bss : businessList) {
			String key = bss.getId();
			if (businessMap.containsKey(key)) {
				log.error(key + "该business的id已注册，无法重复注册");
			} else {
				businessMap.put(bss.getId(), bss);
			}
		}
	}

	public Map<String, Business> getBusinessMap() {
		return businessMap;
	}

}
