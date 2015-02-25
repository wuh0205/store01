package com.wuh.test.jTest;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.wuh.test.dao.impl.UserDaoImpl;
import com.wuh.test.entity.UserEntity;
import static org.junit.Assert.*;

public class UserHibernateTest extends BaseTest{
	@Resource
	private UserDaoImpl userDaoImpl;
	
	@Test
	public void testQueryUser(){
		Long id=(long) 1;
		UserEntity user=userDaoImpl.queryUser(id);
		assertEquals(user.getName(), "张三丰");
		assertEquals(user.getAge(),100);
		assertEquals(user.getPassword(),"123123");
		System.out.println(user.toString());
	}
	
	@Test
	public void testUpdateUser(){
		Long id=(long) 1;
		UserEntity user=new UserEntity();
		user.setU_Id((long) id);
		user.setPassword("44444");
		userDaoImpl.updateUser(user);
		UserEntity userNew=userDaoImpl.queryUser(id);
		assertEquals(userNew.getPassword(), "44444");
		assertEquals(user.getPassword(), userNew.getU_Id());
	}
	
	@Test
	public void testQueryUserList(){
		List<UserEntity> userList=userDaoImpl.getUserList();
		assertTrue(userList.size()>0);
	}
	
	@Test
	public void testSaveUser(){
		UserEntity user=new UserEntity();
		String name="张三";
		String age="10";
		user.setName(name);
		user.setAge("10");
		user.setAddress("China");
		user.setPassword("123123");
		
		userDaoImpl.saveUser(user);
		Map<String,UserEntity> map=new HashMap<String,UserEntity>();
		List<UserEntity> userList=userDaoImpl.getUserList();
		for(UserEntity ue:userList){
			map.put(ue.getName(), ue);
		}
		
		UserEntity userNew=map.get(name);
		assertNotNull(userNew);
		assertEquals(userNew.getName(), name);
		assertEquals(userNew.getAge(), age);
	}

}
