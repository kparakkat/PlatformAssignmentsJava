package com.manageuser.service;

import com.manageuser.poj.Login;
import com.manageuser.poj.User;

public interface IUserService {
	public User getUser(int id);
	public int addUser(User user);
	User validateUser(Login login);
}
