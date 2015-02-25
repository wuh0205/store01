package com.wuh.test.bean;

public class User extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String password;

	private String age;

	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getU_Id() {
		return id;
	}

	public void setU_Id(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [uId=" + id + ", name=" + name + ", password=" + password
				+ ", age=" + age + ", adderss=" + address + "]";
	}

}
