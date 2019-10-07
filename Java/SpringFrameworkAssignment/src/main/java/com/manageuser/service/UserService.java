package com.manageuser.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.manageuser.dao.IUserDao;
import com.manageuser.poj.Login;
import com.manageuser.poj.User;

public class UserService implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	@Override
	public User getUser(int id) {
		return userDao.getUser(id);
	}
	
	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}
	
	@Override
	public User validateUser(Login login) {
		return userDao.validateUser(login);
	}

}
