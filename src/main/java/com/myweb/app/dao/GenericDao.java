package com.myweb.app.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GenericDao<T> extends BaseDao {
	private Class<T> typeClz;
	private String clzName;
	private Field[] clzFields;
	private Field idField;
	
	public GenericDao(Class<T> clz) {
		this.typeClz = clz;
		init();
	}
	
	private void init() {
		clzName = typeClz.getSimpleName();
		clzFields = typeClz.getDeclaredFields();
		try {
			Class supClz = typeClz.getSuperclass();
			if (supClz != null) {
				idField = supClz.getDeclaredField("id");
				idField.setAccessible(true);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public int add(T t) {
		String sql = buidSqlInsert(t);
		return executeUpdate(sql);
	}
	
	private String buidSqlInsert(T t) {
		String[] colNames = new String[clzFields.length];
		Object[] colVals = new Object[clzFields.length];
		for (int i = 0; i < clzFields.length; i++) {
			Field fld = clzFields[i];
			colNames[i] = fld.getName();
			fld.setAccessible(true);
			try {
				colVals[i] = "'" + fld.get(t) + "'"; 
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return String.format("insert into %s (%s) values(%s)",clzName,  join(colNames), join(colVals));
	}
	
	private String buidSqlUpdate(T t) {
		return String.format("update %s set dd = dd, dd= dd where dd = 1", clzName);
	}
	
	private static <E, F> String join(E[] keys, F[] values) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keys.length; i++) {
			sb.append(keys[i])
				.append("=")
				.append(i < values.length ? values[i] : "null")
				.append(",");
		}
		if (sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	private static <E> String join(E... es) {
		StringBuffer sb = new StringBuffer();
		for (E e : es) {
			sb.append(e).append(",");
		}
		
		if (sb.toString().endsWith(",")) {
			sb.deleteCharAt(sb.length() - 1);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] aregs) {
		System.out.println(join("aa", "bb", "cc"));
	}
	
	public int delete(int id) {
		String sql = String.format("delete from %s where id = ?", clzName);
		return executeUpdate(sql, id);
	}
	
	public List<T> getList() {
		List<T> retVal = new ArrayList<T>();
		String sql = "select * from " + clzName;
		ResultSet rs = executeQuery(sql);
		try {
			while(rs.next()) {
				try {
					T instance = typeClz.newInstance();
					if (idField != null && findColumn(rs, "id") != -1) {
						idField.set(instance, rs.getObject("id"));
					}
					
					for (Field fld : clzFields) {
						int colIdx = findColumn(rs, fld.getName());
						if (colIdx == -1) continue;
						fld.setAccessible(true);
						fld.set(instance, rs.getObject(colIdx));
					}
					retVal.add(instance);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return retVal;
	}
	
	public void createTable() {
		String sql = "drop table if exists " + clzName;
		execute(sql);
	}
	
	private String buidSqlCreate() {
		String[] colNames = new String[clzFields.length];
		for (int i = 0; i < clzFields.length; i++) {
			Field fld = clzFields[i];
			colNames[i] = fld.getName();
		}
		return String.format("create table if not exists %s " +
				"(id int primary key autoinrement not null,loginid varchar(255), )", clzName);
	}
	
	public void dropTable() {
		String sql = "drop table if exists " + clzName;
		execute(sql);
	}
}
