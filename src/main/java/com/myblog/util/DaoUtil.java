package com.myblog.util;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myblog.model.User;

public class DaoUtil {
	public static String[] getFields(Class clz) {
		Field[] flds = clz.getDeclaredFields();
		ArrayList<String> names = new ArrayList<String>();
		for (Field f :flds) {
			names.add(f.getName());
		}
		return names.toArray(new String[names.size()]);
	}
	
	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
	private final static Logger logger = LoggerFactory.getLogger(DaoUtil.class);
	
	public static String formatDateString(Date date) {
		return dateFormat.format(date);
	}
	
	public static Date parseDateString(String dateStr) {
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			logger.debug("", e);
		}
		return null;
	}
	public static void main(String[] args) {
		getFields(User.class);
	}
}
