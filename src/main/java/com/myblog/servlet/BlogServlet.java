package com.myblog.servlet;

import java.util.Date;

import com.myblog.dao.GenericDao;
import com.myblog.model.Blog;
import com.myblog.model.Comment;
import com.myblog.service.BlogService;

public class BlogServlet extends BaseServlet {
	GenericDao<Blog> blogDao = new GenericDao<Blog>(Blog.class);
	GenericDao<Comment> cmtDao = new GenericDao<Comment>(Comment.class);
	public void add() {
		if (reqMethod == "POST") {
			Blog b = getBoundInstance(Blog.class);
			b.setCreateDate(new Date());
			b.setUpdateDate(new Date());
			b.setAuthor(getLoginUser());
			b.setAuthorId(getLoginUser().getId());
			int bid = blogDao.add(b);
			redirectApp("blog/show/" + bid);
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
			Blog b = BlogService.getOneBy("id", paramArr[0]);
			if (b != null) {
				req.setAttribute("blog", b);
				dispatch("show");
			}
		} else {
			
		}
	}
	
	public void edit() {
		if (reqMethod == "POST") {
			Blog b = getBoundInstance(Blog.class);
			b.setAuthorId(getLoginUser().getId());
			b.setUpdateDate(new Date());
			logger.info("update blog {}", b);
			blogDao.update(b);
			redirectApp("blog/show/" + b.getId());
		} else {
			if (paramArr.length > 0) {
				Blog b = blogDao.getOneBy("id", paramArr[0]);
				if (b != null) {
					req.setAttribute("blog", b);
					dispatch("edit");
				}
			}
		}
	}
	
	public void del() {
		if (paramArr.length > 0) {
			blogDao.delete(Integer.parseInt(paramArr[0]));
			redirectApp("blog/list");
		}
	}
	
	public void add_cmt() {
		if (reqMethod == "POST") {
			Comment cmt = getBoundInstance(Comment.class);
			cmt.setCmtDate(new Date());
			cmt.setCmtUserId(getLoginUser().getId());
			cmtDao.add(cmt);
			redirectApp("blog/show/" + cmt.getBlogId() + "#cmt");
		}
	}
	
	public void del_cmt() {
		if (paramArr.length > 1) {
			cmtDao.delete(Integer.parseInt(paramArr[0]));
			redirectApp("blog/show/"+paramArr[1]);
		}
	}
	
	@Override
	public Class getModelClass() {
		return Blog.class;
	}

}
