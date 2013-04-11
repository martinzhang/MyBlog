package com.myblog.dao;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.myblog.model.User;

public class UserDaoTest {

	GenericDao<User> userDao = new GenericDao<User>(User.class);
	@Before
	public void setup() {
		BaseDao.setDbUrl("jdbc:sqlite:/home/martin/tuohe/workspace/MyBlog/src/main/resources/db/blog.db");
	}
	@Test
	@Ignore
	public void addUser() {
//		userDao.createUserTable();
		
		User u = new User();
		u.setLoginId("martin");
		u.setPassword("mypass");
		u.setCreateDate(new Date(System.currentTimeMillis()));
		int res = userDao.add(u);
		System.out.println(res);
	}
	
	@Test
	@Ignore
	public void getUserList() {
		List<User> users = userDao.getList();
		
		System.out.println(users);
	}
	
	@Test
//	@Ignore
	public void getBy() {
		List<User> users = userDao.getBy("id", 99);
		
		System.out.println(users);
	}
	
	
	@Test
	@Ignore
	public void createUserTable() {
//		boolean res = userDao.createUserTable();
//		System.out.println(res);
	}
}
