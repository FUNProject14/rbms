package org.rootbeer.rbms.model;

public final class User {
	private final Key key;
	private final LoginId loginId;
	private final Password password;
	private final FullName fullName;
	private final ProfileImage profileImage;
	
	static public final class LoginId {
		private final String id;
		
		public LoginId(String id) {
			this.id = new String(id);
		}
		LoginId(LoginId id) {
			this.id = new String(id.id);
		}
		
		@Override
		public String toString() {
			return new String(id);
		}
	}
	
	static public final class Password {
		private final String password;
		
		Password(String password) {
			this.password = new String(password);
		}
		Password(Password password) {
			this.password = new String(password.password);
		}
		
		@Override
		public String toString() {
			return new String(this.password);
		}
	}
	
	static public final class FullName {
		private final String firstName;
		private final String middleName;
		private final String finalName;
		FullName(String firstName, String middleName, String finalName) {
			this.firstName = new String(firstName);
			this.middleName = new String(middleName);
			this.finalName = new String(finalName);
		}
		FullName(FullName fullName) {
			this.firstName = new String(fullName.firstName);
			this.middleName = new String(fullName.middleName);
			this.finalName = new String(fullName.finalName);
		}
		
		@Override
		public String toString() {
			return new String(firstName + " " + middleName + " " + finalName);
		}
	}
	
	static public final class ProfileImage {
		private final String path;
		
		ProfileImage(String path) {
			this.path = new String(path);
		}
		ProfileImage(ProfileImage profileImage) {
			this.path = new String(profileImage.path);
		}
		
		@Override
		public String toString() {
			return new String(path);
		}
	}

	public User(Key key, LoginId loginId, Password password,
			FullName fullName, ProfileImage profileImage) {
		this.key = key;
		this.loginId = loginId;
		this.password = password;
		this.fullName = fullName;
		this.profileImage = profileImage;
	}

	public Key getKey() {
		return new Key(key);
	}
	
	public LoginId getLoginId() {
		return new LoginId(loginId);
	}

	public Password getPassword() {
		return new Password(password);
	}

	public FullName getFullName() {
		return new FullName(fullName);
	}
	public ProfileImage getprofileImage() {
		return new ProfileImage(profileImage);
	}

}
