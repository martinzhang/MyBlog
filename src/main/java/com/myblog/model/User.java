package com.myblog.model;

import java.util.Date;
import java.util.List;

import com.myblog.util.DaoUtil;

public class User extends BaseModel {
	
	private String loginId;
	private String password;
	private Date createDate;
	private List<Blog> blogs;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return String.format("User [id=%s, loginId=%s, password=%s, createdDate=%s]",getId(),
				loginId, password, DaoUtil.formatDateString(createDate));
	}
	public Date getCreateDate() {
		return createDate;
	}
	public String getCreateDateStr() {
		return DaoUtil.formatDateString(createDate);
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
}
