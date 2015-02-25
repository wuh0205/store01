package com.wuh.jaxbTest.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "business", propOrder = { "businessGroup" })
public class Business {
	@XmlElements(value = { @XmlElement(name="function",required=false,type=Function.class),@XmlElement(name="rediskey",required=true,type=Rediskey.class) })
	private List<Object> businessGroup;
	@XmlAttribute(required = true)
	private String id;
	@XmlAttribute(required = true)
	private String cache;
	@XmlAttribute
	private String desc;

	public List<Object> getBusinessGroup() {
		return businessGroup;
	}

	public void setBusinessGroup(List<Object> businessGroup) {
		this.businessGroup = businessGroup;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCache() {
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
