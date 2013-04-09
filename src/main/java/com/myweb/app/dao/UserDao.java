package com.myweb.app.dao;

import java.util.List;

import com.myweb.app.model.User;

public class UserDao extends BaseDao {
	static GenericDao<User> userDao = new GenericDao<User>(User.class);

	public static void main(String[] args) {
		User u = new User();
		u.setId(33);
		u.setLoginId("admin");
		u.setPassword("padmin");
		int res = userDao.add(u);
		System.out.println(res);
	}
	
	
	public List<User> getUserList() {
		return userDao.getList();
	}
	
	public <T> List<T> getList() {
		System.out.println();
		return null;
	}
	
	
	public int add(User user) {
		String sql = "insert into user (loginid, password) values(?, ?)";
		return executeUpdate(sql, user.getLoginId(), user.getPassword());
	}
	
	public boolean createUserTable() {
//		dropUserTable();
		String sql = "create table user (id integer primary key autoincrement not null , " +
				"loginid varcahr(255), password varchar(255))";
		return execute(sql);
	}
	
	public boolean dropUserTable() {
		String sql = "drop table user;";
		return execute(sql);
	}
}
