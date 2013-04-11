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
		return userDao.getList();
	}
	public static List<User> getBy(String colName, Object val) {
		return userDao.getBy(colName, val);
	}
	
	public static User getOneBy(String colName, Object colVal) {
		return userDao.getOneBy(colName, colVal);
	}
	
}
