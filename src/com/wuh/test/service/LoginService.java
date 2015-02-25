package com.wuh.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuh.test.bean.User;
import com.wuh.test.dao.JdbcTemplateTestDAO;
import com.wuh.test.dao.impl.UserDaoImpl;
import com.wuh.test.entity.UserEntity;

@Service
public class LoginService {
	@Autowired
	JdbcTemplateTestDAO jdbcTemplateTest;
	@Autowired
	private UserDaoImpl userDaoImpl;

	public String login(User u) {
		String message = "";
		Long userId = u.getU_Id();
		UserEntity user1=userDaoImpl.queryUser(userId);
		if (!jdbcTemplateTest.isExistsUser(userId)) {
			return message = "error.notuser";
		}
		User user = jdbcTemplateTest.queryUserById(userId);
		if (u.getPassword().equals(user.getPassword())) {
			message = "success";
		} else {
			message = "error.password";
		}

		return message;
	}

}
