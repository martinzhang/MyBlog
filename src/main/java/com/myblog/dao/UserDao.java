package com.myblog.dao;

import com.myblog.model.User;

public class UserDao extends BaseDao {
	static GenericDao<User> userDao = new GenericDao<User>(User.class);

	public static void main(String[] args) {
		
	}
}
