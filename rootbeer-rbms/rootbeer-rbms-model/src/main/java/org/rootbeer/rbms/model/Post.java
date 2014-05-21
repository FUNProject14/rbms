package org.rootbeer.rbms.model;

import java.util.Date;

public final class Post {
	private final User author;
	private final String body;
	private final Post parent;
	private final Date time;
	
	public Post(User author, String body, Post parent, Date time) {
		this.author = author;
		this.body = body;
		this.parent = parent;
		this.time = (Date) time.clone();
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

	public Date getTime() {
		return (Date) time.clone();
	}
	
}
