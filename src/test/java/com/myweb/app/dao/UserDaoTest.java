package com.myweb.app.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.myweb.app.model.User;

public class UserDaoTest {

	UserDao userDao = new UserDao();
	@Test
	@Ignore
	public void addUser() {
//		userDao.createUserTable();
		User u = new User();
		u.setLoginId("martin");
		u.setPassword("mypass");
		int res = userDao.add(u);
		System.out.println(res);
	}
	
	@Test
//	@Ignore
	public void getUserList() {
		List<User> users = userDao.getUserList();
		
		System.out.println(users.size());
	}
	
	@Test
	@Ignore
	public void createUserTable() {
		boolean res = userDao.createUserTable();
		System.out.println(res);
	}
}
