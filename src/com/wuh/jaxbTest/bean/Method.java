package com.wuh.jaxbTest.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "method", propOrder = { "param" })
public class Method {
	private List<Param> param;
	@XmlAttribute(required = true)
	private String name;
	@XmlAttribute(required = true)
	private String type;



	public List<Param> getParam() {
		return param;
	}

	public void setParam(List<Param> param) {
		this.param = param;
	}

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

}
