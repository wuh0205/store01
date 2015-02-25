package com.wuh.jaxbTest.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rediskey")
public class Rediskey {
	@XmlElements(value = { @XmlElement(name="property", required=true, type=Property.class),@XmlElement(name="method", required=true, type=Method.class) })
	private List<Object> rediskeyGroup;
	@XmlAttribute
	private String name;

	public List<Object> getRediskeyGroup() {
		return rediskeyGroup;
	}

	public void setRediskeyGroup(List<Object> rediskeyGroup) {
		this.rediskeyGroup = rediskeyGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
