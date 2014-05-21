package org.rootbeer.rbms.model;

import java.util.Date;

public class Post {
	private final Key key;
	private final User author;
	private final String body;
	private final Post parent;
	private final Date time;
	
	public Post(final Key key, final User author, final String body, final Post parent, final Date time) {
		this.key = new Key(key);
		this.author = author;
		this.body = new String(body);
		this.parent = parent;
		this.time = (Date) time.clone();
	}
	
	public Key getKey() {
		return new Key(key);
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
