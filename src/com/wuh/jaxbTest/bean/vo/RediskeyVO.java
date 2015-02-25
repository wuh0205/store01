package com.wuh.jaxbTest.bean.vo;

import java.util.List;

public class RediskeyVO {
	
	private String key;
	
	private List<MethodVO> methodList;

	public List<MethodVO> getMethodList() {
		return methodList;
	}

	public void setMethodList(List<MethodVO> methodList) {
		this.methodList = methodList;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	

}
