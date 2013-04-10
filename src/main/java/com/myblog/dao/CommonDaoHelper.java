package com.myblog.dao;

import com.myblog.model.BaseModel;
import com.myblog.model.Blog;
import com.myblog.model.Comment;
import com.myblog.model.User;

public class CommonDaoHelper {

	public static void main(String[] args) {
		new CommonDaoHelper().doMain();
	}
	
	private void doMain() {
		createDbTables();
	}

	public void createDbTables() {
		Class[] clzz = {User.class, Blog.class, Comment.class};
		GenericDao<BaseModel> dao;
		for (Class clz : clzz) {
			dao = new GenericDao<BaseModel>(clz);
			dao.setDbUrl("jdbc:sqlite:/home/martin/workspace/MyBlog/src/main/resources/db/blog.db");
			dao.dropTable();
			dao.createTable();
		}
	}

}
