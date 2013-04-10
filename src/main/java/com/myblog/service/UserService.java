package com.myblog.service;

import java.util.List;

import com.myblog.dao.GenericDao;
import com.myblog.model.User;

public class UserService {
	private static GenericDao<User> userDao = new GenericDao<User>(User.class);
	public static boolean add(User u) {
		return userDao.add(u) != -1;
	}
	public static List<User> getList() {
		// TODO Auto-generated method stub
		return userDao.getList();
	}
}
