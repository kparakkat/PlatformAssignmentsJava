package com.manageuser.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import com.manageuser.exception.GlobalExceptionHandler;
import com.manageuser.poj.Login;
import com.manageuser.poj.User;

public class UserDao implements IUserDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	
	@Transactional
	public User getUser(int id) {
		User user = (User) jdbcTemplate.queryForObject("select * from user where id = ?", new Object[] { id }, new UserRowMapper());
		return user;
	}
	
	@Transactional
	public int addUser(User user) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");
		Map<String, Object> parameters = new HashMap<String, Object>(4);
		parameters.put("name", user.getName());
		parameters.put("email", user.getEmail());
		parameters.put("username", user.getUsername());
		parameters.put("password", user.getPassword());
		Number insertedId = simpleJdbcInsert.executeAndReturnKey(parameters);
		return insertedId.intValue();
		
	}
	
	@Transactional
	public int updateUser(User user) {
		String sql = "update user set name = ?, email = ?, username = ?, password = ? where id = ?";
		int resp = jdbcTemplate.update(sql, new Object[] { user.getName(), user.getEmail(),
				user.getUsername(), user.getPassword(), user.getId() });
		return resp;
	}
	
	@Transactional
	public User validateUser(Login login) {
		User user = null;
		try {
		user = (User) jdbcTemplate.queryForObject("select * from user where username = ? and password = ?", new Object[] { login.getUsername(), login.getPassword() }, new UserRowMapper());
		}
		catch(Exception ex)
		{
			logger.error("Invalid login Attmpt: " + ex.getMessage());
			user = null;
		}
		return user;
	}
	
	
	
}
