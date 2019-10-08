package com.manageuser.dao;

import com.manageuser.poj.Login;
import com.manageuser.poj.User;

public interface IUserDao {
	
	public User getUser(int id);
	public int addUser(User user);
	public int updateUser(User user);
	User validateUser(Login login);
}
