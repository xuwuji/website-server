package com.xuwuji.website.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "COMMENT")
@Entity
public class Comment {

	private int id;
	private String content;
	private String time;
	private String user;
	private int article_id;

	@GeneratedValue
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", time=" + time + ", user=" + user + ", article_id="
				+ article_id + "]";
	}

}
