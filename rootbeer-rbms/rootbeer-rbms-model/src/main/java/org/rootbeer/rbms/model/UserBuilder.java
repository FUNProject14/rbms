package org.rootbeer.rbms.model;

import org.rootbeer.rbms.model.User.*;

public final class UserBuilder {
	private Key key;
	private LoginId loginId;
	private Password password;
	private FullName fullName;
	private ProfileImage profileImage;
	
	public UserBuilder() {}
	
	public UserBuilder key(Key key) {
		this.key = new Key(key);
		return this;
	}
	
	public UserBuilder loginId(LoginId loginId) {
		this.loginId = new LoginId(loginId);
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
	
	public UserBuilder profileImage(ProfileImage profileImage) {
		this.profileImage = new ProfileImage(profileImage);
		return this;
	}
	
	public User build() {
		return new User(key, loginId, password, fullName, profileImage);
	}
	
}
