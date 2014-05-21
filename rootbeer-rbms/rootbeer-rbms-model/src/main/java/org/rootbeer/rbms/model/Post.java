package org.rootbeer.rbms.model;

import java.util.Date;

public class Post {
	private final User author;
	private final String body;
	private final Post parent;
	private final Date time;
	
	public Post(final User author, final String body, final Post parent, final Date time) {
		this.author = author;
		this.body = new String(body);
		this.parent = parent;
		this.time = (Date) time.clone();
	}

	public User getAuthor() {
		return author;
	}

	public String getBody() {
		return new String(body);
	}

	public Post getParent() {
		return parent;
	}

	public Date getTime() {
		return (Date) time.clone();
	}
	
}
