package org.rootbeer.rbms.model;

public final class User {
	private final String loginId;
	private final String password;
	private final String fullName;
	private final ProfileImage profileImage;

	public static final class ProfileImage {
		private final String path;

		ProfileImage(String path) {
			this.path = path;
		}

		@Override
		public String toString() {
			return path;
		}
	}

	public User(String loginId, String password, String fullName,
			ProfileImage profileImage) {
		this.loginId = loginId;
		this.password = password;
		this.fullName = fullName;
		this.profileImage = profileImage;
	}

	public static final class Builder {
		private String loginId;
		private String password;
		private String fullName;
		private ProfileImage profileImage;
		
		public Builder() {}

		public Builder(User user) {
			this.loginId(user.loginId).password(user.password)
					.fullName(user.fullName).profileImage(user.profileImage);
		}

		public Builder loginId(String loginId) {
			this.loginId = loginId;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder fullName(String fullName) {
			this.fullName = fullName;
			return this;
		}

		public Builder profileImage(ProfileImage profileImage) {
			this.profileImage = profileImage;
			return this;
		}

		public User build() {
			return new User(loginId, password, fullName, profileImage);
		}
	}

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public ProfileImage getprofileImage() {
		return profileImage;
	}

}
