package com.wuh.jaxbTest.bean.vo;

import java.util.Map;

public class ParameterVO {

	private String processId;

	private Map<String, Object> paramMap;

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	

}
