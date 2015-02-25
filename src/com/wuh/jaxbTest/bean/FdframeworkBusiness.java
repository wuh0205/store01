package com.wuh.jaxbTest.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "business" })
@XmlRootElement(name = "fdframework-rediskey")
public class FdframeworkBusiness {
	private List<Business> business;

	public List<Business> getBusiness() {
		if(business==null){
			business=new ArrayList<Business>();
		}
		return business;
	}

	public void setBusiness(List<Business> business) {
		this.business = business;
	}
	
	
}
