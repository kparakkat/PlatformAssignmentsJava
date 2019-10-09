package com.manageuser.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.manageuser.poj.User;

public class UserRowMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int row) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setName(rs.getString("email"));
		user.setName(rs.getString("username"));
		user.setName(rs.getString("password"));
		return user;
	}
}
