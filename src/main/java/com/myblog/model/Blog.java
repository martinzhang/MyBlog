package com.myblog.model;

import java.util.Date;
import java.util.List;

import com.myblog.util.DaoUtil;

public class Blog extends BaseModel {
	
	private String title;
	private String content;
	private Date createDate;
	private Date updateDate;
	private User author;
	private int authorId;
	private List<Comment> comments;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public String getUpdateDateStr() {
		return DaoUtil.formatDateString(updateDate);
	}
	public String getCreateDateStr() {
		return DaoUtil.formatDateString(createDate);
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	@Override
	public String toString() {
		return String.format(
				"Blog [id=%s, title=%s, content=%s, createDate=%s, updateDate=%s]",getId(),
				title, content, createDate, updateDate);
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
}
