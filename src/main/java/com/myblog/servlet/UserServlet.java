package com.myblog.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import com.myblog.model.User;
import com.myblog.service.UserService;

public class UserServlet extends BaseServlet {
	
	public void add() {
		try {
			if ("GET".equals(req.getMethod())) {
				dispatch("add");
			} else if ("POST".equals(req.getMethod())) {
				User u = getBoundInstance(User.class);
				u.setCreateDate(new Date());
				UserService.add(u);
				resp.getWriter().print(u.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void show() {
		
	}
	
	public void list() {
		List<User> users = UserService.getList();
		req.setAttribute("users", users);
		dispatch("list");
	}

	@Override
	public Class getModelClass() {
		return User.class;
	}
	
}
