package com.wuh.test.bean;

import java.text.DecimalFormat;

public class Customer implements Comparable<Customer>{
	
	private String name;
	
	private double total;
	
	private double integral;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getIntegral() {
		return integral;
	}

	public void setIntegral(double integral) {
		this.integral = integral;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return name + "," + df.format(total) + ","+ integral;
	}

	/* 
	 * 先根据积分排序（从大到小），如果积分相等根据合计排序（从大到小），如果合计相等按照名字排序（字母先后顺序）
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Customer c) {
		// TODO Auto-generated method stub
		if(this.getIntegral()==c.getIntegral()){
			if(this.getTotal()==c.getTotal()){
				return this.getName().compareTo(c.getName());
			}else{
				if(this.getTotal()>c.getTotal()){//降序
					return -1;
				}else{
					return 1;
				}
			}
		}else{
			if(this.getIntegral()>c.getIntegral()){//降序
				return -1;
			}else{
				return 1;
			}
		}
	}
}
