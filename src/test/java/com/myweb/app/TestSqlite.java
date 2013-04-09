package com.myweb.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSqlite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestSqlite().doMain();
		System.out.println("DONE");
	}

	private void doMain() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery("select * from sqlite_master");
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
//			boolean res = sta.execute("create table user (name varchar(200), age int)");
//			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
