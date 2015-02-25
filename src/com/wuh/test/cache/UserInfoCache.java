package com.wuh.test.cache;

import com.wuh.test.utils.SpringServicFactory;


public class UserInfoCache extends AbstractCache{
	
	private static UserInfoCache instance=null;
	
	private UserInfoCache (){
		this.ehcache=SpringServicFactory.getBean("UserInfoCache");
	};
	
	public static UserInfoCache getInstance(){
		if(instance==null){
			synchronized(UserInfoCache.class){
				if(instance==null){
					instance=new UserInfoCache();
				}
			}
		}
		return instance;
	}

}
