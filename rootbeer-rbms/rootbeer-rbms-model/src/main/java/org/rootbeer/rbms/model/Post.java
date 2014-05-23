package org.rootbeer.rbms.model;

import java.util.Date;

public final class Post {
	private final String body;
	private final User author;
	private final Date postedTime;
	private final Post parent;
	
	public Post(String body, User author, Date time, Post parent) {
		this.body = body;
		this.author = author;
		this.postedTime = (Date) time.clone();
		this.parent = parent;
	}
	
	public User getAuthor() {
		return author;
	}

	public String getBody() {
		return body;
	}

	public Post getParent() {
		return parent;
	}

	public Date getPostedTime() {
		return (Date) postedTime.clone();
	}
	
}
