package com.xuwuji.website.model;

import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table
@Entity
public class Article {
	@Id
	@GeneratedValue
	private int id;
	private String title;
	private String content;
	private String time;
	private String category;
	// each tag is appended by a white space
	private String tags;
	// 0-> deleted 1->ok
	private int flag;

	@OneToMany(mappedBy = "article_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Comment> comments;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Article() {
		super();
	}

	public Article(int id, String title, String content, String time, String category) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.category = category;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void addComment(Comment comment) {
		if (!comments.contains(comment)) {
			comments.add(comment);
		}
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", time=" + time + ", category="
				+ category + ", tags=" + tags + "]";
	}

}
