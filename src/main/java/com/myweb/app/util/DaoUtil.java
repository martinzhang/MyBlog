package com.myweb.app.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.myweb.app.model.User;

public class DaoUtil {
	public static String[] getFields(Class clz) {
		Field[] flds = clz.getDeclaredFields();
		ArrayList<String> names = new ArrayList<String>();
		for (Field f :flds) {
			names.add(f.getName());
		}
		return names.toArray(new String[names.size()]);
	}
	
	public static void main(String[] args) {
		getFields(User.class);
	}
}
