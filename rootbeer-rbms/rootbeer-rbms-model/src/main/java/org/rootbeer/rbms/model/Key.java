package org.rootbeer.rbms.model;

public final class Key {
	private final Long key;
	
	public Key(Long key) {
		this.key = new Long(key);
	}
	public Key(Key key) {
		this.key = new Long(key.key);
	}
	
	@Override
	public String toString() {
		return key.toString();
	}
}
