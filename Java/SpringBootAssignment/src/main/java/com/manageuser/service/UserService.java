package com.manageuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageuser.dao.UserDao;
import com.manageuser.poj.Login;
import com.manageuser.poj.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	

	public User getUser(int id) {
		return userDao.getById(id);
	}
	
	public void addUser(User user) {
		userDao.save(user);
		return;
	}
	
	public void updateUser(User user) {
		userDao.update(user);
		return;
	}
	
	public User validateUser(Login login) {
		return userDao.validateUser(login);
	}
}
