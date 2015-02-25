package com.wuh.jaxbTest.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "function", propOrder = { "property" })
public class Function {
	private List<Property> property;
	@XmlAttribute(required = true)
	private String bean;
	@XmlAttribute(required = true)
	private String method;
	@XmlAttribute(required = true)
	private String returnkey;

	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getReturnkey() {
		return returnkey;
	}

	public void setReturnkey(String returnkey) {
		this.returnkey = returnkey;
	}

	public List<Property> getProperty() {
		return property;
	}

	public void setProperty(List<Property> property) {
		this.property = property;
	}

}
