package com.wuh.test.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.wuh.test.bean.User;
@Transactional
public class Test {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public User queryUser(Long id) {
		// TODO Auto-generated method stub
		User user=null;
		try {
			Session session=sessionFactory.getCurrentSession();
//			Session session=sessionFactory.openSession();
			
			System.out.println(session);
			user = (User)sessionFactory.getCurrentSession().get(User.class,id);
//			user = (User)hibernateTemplate.get(User.class,id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return  user;
	}

}
