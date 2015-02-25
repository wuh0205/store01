package com.wuh.test.dao.impl;

import java.util.List;

import javax.annotation.Resource;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuh.test.bean.User;
import com.wuh.test.dao.UserDAO;
import com.wuh.test.entity.UserEntity;
@Transactional
@Repository
public class UserDaoImpl implements UserDAO{
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
//	@Autowired
//	private HibernateTemplate hibernateTemplate;//hibernate4不支持hibernate3的hibernateTemple
	
	/* (保存user)
	 * @see com.wuh.test.dao.UserDAO#saveUser(com.wuh.test.entity.UserEntity)
	 */
	@Override
	public void saveUser(UserEntity user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().persist(user);
		
	}

	/* (更新user)
	 * @see com.wuh.test.dao.UserDAO#updateUser(com.wuh.test.entity.UserEntity)
	 */
	@Override
	public void updateUser(UserEntity user) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(user);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (删除user)
	 * @see com.wuh.test.dao.UserDAO#deleteUser(com.wuh.test.entity.UserEntity)
	 */
	@Override
	public void deleteUser(UserEntity user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(user);
	}

	/* (根据id查询user)
	 * @see com.wuh.test.dao.UserDAO#queryUser(java.lang.Long)
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)//查询的时候，不需要开启事务，并且指定为只读，这样可以提高查询效率
	public UserEntity queryUser(Long id) {
		// TODO Auto-generated method stub
		UserEntity user=null;
		try {
			user = (UserEntity)sessionFactory.getCurrentSession().get(UserEntity.class,id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return  user;
	}

	/* (查询user，返回list)
	 * @see com.wuh.test.dao.UserDAO#getUserList()
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@SuppressWarnings("unchecked")
	public List<UserEntity> getUserList() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from UserEntity").list();
	}

}
