package com.wuh.test.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
	
    private Long id;
	
	private String name;
	
	private String password;
	
	private String age;
	
	private String address;
	
	@Id  
	public Long getU_Id() {
		return id;
	}

	public void setU_Id(Long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", password="
				+ password + ", age=" + age + ", adderss=" + address + "]";
	}
	
	
	

}
