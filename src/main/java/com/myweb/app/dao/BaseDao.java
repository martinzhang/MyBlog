package com.myweb.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {

	private Connection connection;
	private String dbUrl = "jdbc:sqlite:blog.db";
	
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
		try {
			stt = connection.createStatement();
			return stt.execute(sql);
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
		System.out.println("Sql: " + sql);
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
}
