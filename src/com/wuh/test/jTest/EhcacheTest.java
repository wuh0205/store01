package com.wuh.test.jTest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.wuh.test.bean.User;
import com.wuh.test.cache.UserInfoCache;
import com.wuh.test.dao.impl.UserDaoImpl;
import com.wuh.test.entity.UserEntity;

public class EhcacheTest extends BaseTest{
	@Resource
	private UserDaoImpl userDaoImpl;
	
	private int userSize;
	
	@Test
	public void saveUserInfo测试将用户信息放入缓存(){
		List<UserEntity> userList=userDaoImpl.getUserList();
		userSize=userList.size();
		for(UserEntity en:userList){
			User user=new User();
			BeanUtils.copyProperties(en, user);
			boolean boo=UserInfoCache.getInstance().setEhCacheObject(getKey(String.valueOf(user.getU_Id()),user.getName()), user);
			assertTrue(boo);
		}
	}
	@Test
	public void getUserInfo测试将缓存的客户信息取出(){
		List<String> list=UserInfoCache.getInstance().getEhCacheKeys();
		assertTrue(list.size()==userSize);
		for(String key:list){
			User user=(User) UserInfoCache.getInstance().getEhCacheObject(key);
			assertNotNull(user);
			System.out.println(user.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void saveAndGetCache测试对User对象进行缓存存取(){
		UserEntity userEntity=userDaoImpl.queryUser((long)1);
		User user=new User();//缓存对象，对象需要序列化
		BeanUtils.copyProperties(userEntity, user);
		Map<String,User> map=new HashMap<String,User>();
		map.put(user.getName(), user);
		boolean boo=UserInfoCache.getInstance().setEhCacheObject(String.valueOf(user.getU_Id()),map);
		assertTrue(boo);
		Map<String,User> mapCache=(Map<String, User>) UserInfoCache.getInstance().getEhCacheObject(String.valueOf(user.getU_Id()));
		assertNotNull(mapCache);
		assertEquals(mapCache.get(user.getName()).getName(), user.getName());
	}
	
	@Test
	public void simpleTest测试对字符串进行缓存存取(){
		String key="wuh";
		String value="123";
		UserInfoCache.getInstance().setEhCacheObject(key,value);
		String str=(String) UserInfoCache.getInstance().getEhCacheObject(key);
		assertEquals(value, str);
	}
	
	private String getKey(String...str){
		return StringUtils.join(str,":");
	}


}
