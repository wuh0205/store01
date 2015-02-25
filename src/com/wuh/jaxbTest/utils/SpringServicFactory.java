package com.wuh.jaxbTest.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("SpringServiceFactory")
@Scope("singleton")
public class SpringServicFactory implements ApplicationContextAware {

	private static ApplicationContext applicationContext; // Spring应用上下文

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		SpringServicFactory.applicationContext = arg0;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}

}
