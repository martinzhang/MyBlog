package com.myblog.servlet;

import com.myblog.dao.GenericDao;
import com.myblog.model.Blog;
import com.myblog.service.BlogService;

public class BlogServlet extends BaseServlet {
	GenericDao<Blog> blogDao = new GenericDao<Blog>(Blog.class);
	public void add() {
		if (reqMethod == "POST") {
			Blog b = getBoundInstance(Blog.class);
			b.setAuthor(getLoginUser());
			b.setAuthorId(getLoginUser().getId());
			blogDao.add(b);
			req.setAttribute("blog", b);
			dispatch("show");
		} else {
			dispatch("add");
		}
	}
	
	public void list() {
		req.setAttribute("blogs",BlogService.getList() );
		dispatch("list");
	}
	
	public void show() {
		if (paramArr.length > 0) {
			Blog b = blogDao.getOneBy("id", paramArr[0]);
			if (b != null) {
				req.setAttribute("blog", b);
				dispatch("show");
			}
		} else {
			
		}
	}
	
	@Override
	public Class getModelClass() {
		return Blog.class;
	}

}
