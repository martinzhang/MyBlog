package com.myblog.servlet;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.myblog.model.User;
import com.myblog.service.UserService;

public class UserServlet extends BaseServlet {
	
	public void add() {
			if ("POST".equals(req.getMethod())) {
				User u = getBoundInstance(User.class);
				u.setCreateDate(new Date());
				UserService.add(u);
				paramArr = new String[] {u.getLoginId()};
				show();
			} else {
				dispatch("add");
			}
	}
	
	public void login() {
		
		if (reqMethod == "POST") {
			User u = getBoundInstance(User.class);
			String loginId = u.getLoginId();
			String pwd = u.getPassword();
			if (StringUtils.isBlank(loginId) || StringUtils.isBlank(pwd)) {
				req.setAttribute("user", u);
				dispatch("login", "info not complete");
			} else {
				u = UserService.getOneBy("loginid", loginId);
				if (u != null && StringUtils.equals(pwd, u.getPassword())) {
					req.getSession().setAttribute("loginUser", u);
					logger.info("user login ok {}/{}", loginId, pwd);
					redirectApp("index.jsp");
				} else {
					logger.info("user {} not login {}/{}", u, loginId, pwd);
					req.setAttribute("user", u);
					dispatch("login", "login id or pwd incorrect");
				}
			}
		} else {
			dispatch("login");
		}
	}
	
	public void show() {
		if (paramArr.length > 0) {
			User u = UserService.getOneBy("loginId", paramArr[0]);
			req.setAttribute("user", u);
			dispatch("show");
		} else {
			dispatch("show", "user not found");
		}
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
