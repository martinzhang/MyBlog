package com.myblog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CommTest().doMain();
	}

	private void doMain() {
		int dd = 33;
		System.out.println();
	}
	
	class Parent<T> {
		protected Class model;
		
		protected void okok() {
			try {
				Class clz = getClass();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	class Chield<T> extends Parent {
		public void mm() {
			
		}
	}

}
