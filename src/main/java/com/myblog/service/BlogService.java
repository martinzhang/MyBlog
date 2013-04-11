package com.myblog.service;

import java.util.List;

import com.myblog.dao.GenericDao;
import com.myblog.model.Blog;
import com.myblog.model.User;

public class BlogService {
	private static GenericDao<Blog> blogDao = new GenericDao<Blog>(Blog.class);
	public static List<Blog> getList() {
		List<Blog> blogs =  blogDao.getList();
		for (Blog b : blogs) {
			b.setAuthor(UserService.getOneBy("id", b.getAuthorId()));
		}
		return blogs;
	}
	
}
