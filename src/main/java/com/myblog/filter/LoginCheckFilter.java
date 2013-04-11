package com.myblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginCheckFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	String[] loginUrls = {
			"blog/add",
			"user/show"
	};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String path =  req.getRequestURI();

		boolean needLogin = false;
		if (req.getSession().getAttribute("loginUser") == null && path != null) {
			for (String url : loginUrls) {
				System.out.println("Check url " + path);
				if (path.startsWith("/MyBlog/" + url)) {
					needLogin = true;
					break;
				}
			}
		}
		if (needLogin) {
			request.setAttribute("message", "login first");
			req.getRequestDispatcher("/user/login").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

}
