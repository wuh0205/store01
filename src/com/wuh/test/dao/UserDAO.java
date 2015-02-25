package com.wuh.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wuh.test.bean.User;
import com.wuh.test.entity.UserEntity;
@Transactional
@Repository
public interface UserDAO {
	public void saveUser(UserEntity user) ;
	
	public void updateUser(UserEntity user);
	
	public void deleteUser(UserEntity user);
	
	public UserEntity queryUser(Long id);
	
	public List<UserEntity> getUserList();

}
