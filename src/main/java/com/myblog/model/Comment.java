package com.myblog.model;

import java.util.Date;

import com.myblog.util.DaoUtil;

public class Comment extends BaseModel {
	private int blogId;
	private String content;
	private Date cmtDate;
	private int cmtUserId;
	private User cmtUser;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCmtDate() {
		return cmtDate;
	}
	public String getCmtDateStr() {
		return DaoUtil.formatDateString(cmtDate);
	}
	public void setCmtDate(Date cmtDate) {
		this.cmtDate = cmtDate;
	}
	public int getCmtUserId() {
		return cmtUserId;
	}
	public void setCmtUserId(int cmtUserId) {
		this.cmtUserId = cmtUserId;
	}
	public User getCmtUser() {
		return cmtUser;
	}
	public void setCmtUser(User cmtUser) {
		this.cmtUser = cmtUser;
	}
	@Override
	public String toString() {
		return String.format("Comment [content=%s, cmtDate=%s, cmtUser=%s]",
				content, cmtDate, cmtUser);
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	
	
}
