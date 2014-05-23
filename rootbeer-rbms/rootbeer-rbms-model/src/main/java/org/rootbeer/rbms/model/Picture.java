package org.rootbeer.rbms.model;

import java.net.URI;
import java.util.Date;

public final class Picture {
	private final URI path;
	private final String description;
	private final User author;
	private final Date uploadedTime;
	
	public Picture(URI path, String description, User author, Date uploadedTime) {
		this.path = path;
		this.description = description;
		this.author = author;
		this.uploadedTime = new Date(uploadedTime.getTime());
	}

	public URI getPath() {
		return path;
	}

	public String getDescription() {
		return description;
	}

	public User getAuthor() {
		return author;
	}

	public Date getUploadedTime() {
		return new Date(uploadedTime.getTime());
	}
}
