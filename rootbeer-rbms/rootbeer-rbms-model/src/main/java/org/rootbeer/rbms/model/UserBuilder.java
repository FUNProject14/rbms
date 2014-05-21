package org.rootbeer.rbms.model;

import org.rootbeer.rbms.model.User.*;

public class UserBuilder {
	private Key key;
	private UserId userId;
	private Password password;
	private FullName fullName;
	private Icon icon;
	
	UserBuilder() {}
	
	public UserBuilder key(Key key) {
		this.key = new Key(key);
		return this;
	}
	
	public UserBuilder userId(UserId userId) {
		this.userId = new UserId(userId);
		return this;
	}
	
	public UserBuilder password(Password password) {
		this.password = new Password(password);
		return this;
	}
	
	public UserBuilder fullName(FullName fullName) {
		this.fullName = new FullName(fullName);
		return this;
	}
	
	public UserBuilder icon(Icon icon) {
		this.icon = new Icon(icon);
		return this;
	}
	
	public User build() {
		return new User(key, userId, password, fullName, icon);
	}
	
}
