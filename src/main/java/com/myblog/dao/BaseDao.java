package com.myblog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {

	private Connection connection;
	private String dbUrl = "jdbc:sqlite:/home/martin/workspace/MyBlog/src/main/resources/db/blog.db";
	
	public BaseDao() {
		try {
			init();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void init() throws Exception {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection(dbUrl);
	}
	
	protected int findColumn(ResultSet rs, String colName) {
		try {
			return rs.findColumn(colName);
		} catch (SQLException e) {
			return -1;
		}
	}
	
	public boolean execute(String sql) {
		Statement stt = null;
		log(sql);
		try {
			stt = connection.createStatement();
			stt.execute(sql);
			return stt.getUpdateCount() != -1;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (stt != null) {
					stt.close();
				}
			} catch (SQLException e) {			}
		}
	}
	
	public ResultSet executeQuery(String sql, Object... params) {
		PreparedStatement stt = null;
		log(sql);
		try {
			stt = connection.prepareStatement(sql);
			if (params != null && params.length != 0) {
				for (int i = 0; i < params.length; i++) {
					stt.setObject(i + 1, params[0]);
				}
			}
			
			return stt.executeQuery();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int executeUpdate(String sql, Object... params) {
		PreparedStatement stt = null;
		log(sql);
		try {
			stt = connection.prepareStatement(sql);
			if (params != null && params.length != 0) {
				for (int i = 0; i < params.length; i++) {
					stt.setObject(i + 1, params[0]);
				}
			}
			
			return stt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (stt != null) {
					stt.close();
				}
			} catch (SQLException e) {			}
		}
	}
	
	public void setDbUrl(String url) {
		dbUrl = url;
		try {
			init();
		} catch (Exception e) {

		}
	}
	
	private void log(String sql) {
		System.out.println("sql -> " + sql);
	}
}
