package org.rootbeer.rbms.model;

import java.net.URI;

public final class Picture {
	private final URI path;
	
	public Picture(URI path) {
		this.path = path;
	}

	public URI getPath() {
		return path;
	}
}
