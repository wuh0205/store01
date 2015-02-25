package com.wuh.jaxbTest.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "param")
public class Param {
	@XmlAttribute(required = true)
	private String field;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	

}
