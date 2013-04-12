package com.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myblog.model.User;

public abstract class BaseServlet extends HttpServlet {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected String[] pathInfoArr = new String[]{};
	protected String[] paramArr = new String[]{};
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	protected String reqMethod = "GET";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pathInfo = req.getPathInfo();

		this.req = req;
		this.resp = resp;
		reqMethod = req.getMethod();
		
		if (pathInfo != null) {
			pathInfo = pathInfo.substring(1);
			pathInfoArr = pathInfo.split("/");
			if (pathInfoArr.length != 0) {
				String cmd = pathInfoArr[0];
				if (pathInfoArr.length > 1) {
					paramArr = new String[pathInfoArr.length - 1];
					System.arraycopy(pathInfoArr, 1, paramArr, 0, paramArr.length);
				}
				invokeAction(cmd);
			}
		}
		
	}

	private void invokeAction(String cmd) {
		Class clz = getClass();
		try {
			Method mtd = clz.getDeclaredMethod(cmd);
			mtd.setAccessible(true);
			mtd.invoke(this);
		} catch (Exception e) {
			logger.error("", e);
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			try {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, sw.toString());
			} catch (IOException e1) {
				loge(e1.getMessage());
			}
		}
	}
	
	

	protected <T> T getBoundInstance(Class<T> clz) {
		Field[] flds = clz.getDeclaredFields();
		T t = null;
		String valToSet = null;
		try {
			t = clz.newInstance();
			for (Field fld : flds) {
				fld.setAccessible(true);
				valToSet = req.getParameter(fld.getName());
				logger.debug("parameter name/value {}/{}", fld.getName(), valToSet);
				if (valToSet == null) continue;
				if (fld.getType() == String.class) {
					fld.set(t, valToSet);
				} else if (fld.getType() == int.class) {
					fld.set(t, Integer.parseInt(valToSet));
				}
			}
			String id = req.getParameter("id");
			if (id != null) {
				Field idFld = clz.getSuperclass().getDeclaredField("id");
				idFld.setAccessible(true);
				idFld.set(t, Integer.parseInt(id));
			}
		} catch (NumberFormatException e) {
			logger.error(valToSet, e);
		} catch (Exception e) {
			logger.error("", e);
		}
		return t;
	}
	
	protected void redirect(String page) {
		try {
			resp.sendRedirect(String.format("/MyBlog/view/%s/%s.jsp", 
					getModelClass().getSimpleName().toLowerCase(), page));
		} catch (IOException e) {
			loge(e.getMessage());
		}
	}
	
	protected void redirectApp(String page) {
		try {
			resp.sendRedirect(String.format("/MyBlog/%s", page));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void dispatch(String page) {
		try {
			req.getRequestDispatcher(String.format("/view/%s/%s.jsp", 
					getModelClass().getSimpleName().toLowerCase(), page))
						.forward(req, resp);
		} catch (Exception e) {
			loge(e.getMessage());
		}
	}
	

	protected void dispatch(String page, String message) {
		req.setAttribute("message", message);
		dispatch(page);
	}

	protected User getLoginUser() {
		return (User) req.getSession().getAttribute("loginUser");
	}
	
	public abstract Class getModelClass();
	
	public void loge(Object o) {
		System.err.println(o);
	}
	
}
