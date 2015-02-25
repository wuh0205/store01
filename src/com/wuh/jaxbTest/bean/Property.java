package com.wuh.jaxbTest.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "property")
public class Property {
	@XmlAttribute
	private String refkey;
	@XmlAttribute
	private String method;
	@XmlAttribute
	private String type;
	@XmlAttribute
	private String value;

	public String getRefkey() {
		return refkey;
	}

	public void setRefkey(String refkey) {
		this.refkey = refkey;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
