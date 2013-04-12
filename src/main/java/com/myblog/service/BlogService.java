package com.myblog.service;

import java.util.List;

import com.myblog.dao.GenericDao;
import com.myblog.model.Blog;
import com.myblog.model.Comment;
import com.myblog.model.User;

public class BlogService {
	private static GenericDao<Blog> blogDao = new GenericDao<Blog>(Blog.class);
	private static GenericDao<Comment> cmtDao = new GenericDao<Comment>(Comment.class);
	public static List<Blog> getList() {
		List<Blog> blogs =  blogDao.getList();
		for (Blog b : blogs) {
			b.setAuthor(UserService.getOneBy("id", b.getAuthorId()));
		}
		return blogs;
	}
	public static Blog getOneBy(String colName, String colVal) {
		// TODO Auto-generated method stub
		Blog b = blogDao.getOneBy(colName, colVal);
		b.setAuthor(UserService.getOneBy("id", b.getAuthorId()));
		b.setComments(getCmtBy("blogId", b.getId() + ""));
		return b;
	}
	
	public static List<Comment> getCmtBy(String colName, String colVal) {
		List<Comment> cmts = cmtDao.getBy(colName, colVal);
		for (Comment cmt : cmts) {
			cmt.setCmtUser(UserService.getOneBy("id", cmt.getCmtUserId()));
		}
		return cmts;
	}
}
