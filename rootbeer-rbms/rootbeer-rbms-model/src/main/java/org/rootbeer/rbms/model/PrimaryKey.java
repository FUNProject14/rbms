package org.rootbeer.rbms.model;

public class PrimaryKey {
	private final Long key;
	
	public PrimaryKey(Long key) {
		this.key = new Long(key);
	}
	public PrimaryKey(PrimaryKey key) {
		this.key = new Long(key.key);
	}
	
	@Override
	public String toString() {
		return key.toString();
	}
}
