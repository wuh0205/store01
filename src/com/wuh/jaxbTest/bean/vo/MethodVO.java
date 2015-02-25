package com.wuh.jaxbTest.bean.vo;

import java.util.List;

public class MethodVO {
	
	private String name;
	
	private String type;
	
	private List<ParamVO> paramList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ParamVO> getParamList() {
		return paramList;
	}

	public void setParamList(List<ParamVO> paramList) {
		this.paramList = paramList;
	}

	
	
}
