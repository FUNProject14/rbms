package org.rootbeer.rbms.model;

/**
 * ユーザーを表すクラスです。
 * @author kumar1
 */
public final class User {
	private final String loginId;
	private final String password;
	private final String fullName;
	private final ProfileImage profileImage;

	/**
	 * URIで示される位置に置かれたプロフィール画像を表すクラスです。
	 * @author kumar1
	 */
	public static final class ProfileImage {
		private final String path;

		/**
		 * プロフィール画像が置かれた位置を示すURIから、インスタンスを生成します。
		 * @param path プロフィール画像が置かれた位置を示すURI
		 */
		ProfileImage(String path) {
			this.path = path;
		}

		@Override
		public String toString() {
			return path.toString();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (obj == this)
				return true;
			if (!(obj instanceof ProfileImage))
				return false;
			ProfileImage pi = (ProfileImage)obj;
			return pi.path.equals(path);
		}
		
		@Override
		public int hashCode() {
			return path.hashCode();
		}
	}

	/**
	 * ログインID、パスワード、フルネーム、プロフィール画像から、インスタンスを生成します。
	 * @param loginId ログインID
	 * @param password パスワード（必ずハッシュであること）
	 * @param fullName フルネーム
	 * @param profileImage プロフィール画像
	 */
	public User(String loginId, String password, String fullName,
			ProfileImage profileImage) {
		this.loginId = loginId;
		this.password = password;
		this.fullName = fullName;
		this.profileImage = profileImage;
	}

	/**
	 * <code>User</code>クラスを生成するビルダーです。
	 * 各メソッドで構成を行ったのち、<code>build</code>メソッドを呼び出すことで、
	 * <code>User</code>クラスのインスタンスを得ることが出来ます。
	 * @author kumar1
	 */
	public static final class Builder {
		private String loginId;
		private String password;
		private String fullName;
		private ProfileImage profileImage;
		
		/**
		 * デフォルトコンストラクタです。
		 */
		public Builder() {}

		/**
		 * <code>User</code>クラスのインスタンスより、インスタンスを生成します。
		 * @param user <code>User</code>クラスのインスタンス
		 */
		public Builder(User user) {
			this.loginId(user.loginId).password(user.password)
					.fullName(user.fullName).profileImage(user.profileImage);
		}

		/**
		 * ログインIDを設定します。
		 * @param loginId ログインID
		 * @return this
		 */
		public Builder loginId(String loginId) {
			this.loginId = loginId;
			return this;
		}

		/**
		 * パスワード（必ずハッシュであること）を設定します。
		 * @param password パスワード（必ずハッシュであること）
		 * @return this
		 */
		public Builder password(String password) {
			this.password = password;
			return this;
		}

		/**
		 * フルネームを設定します。
		 * @param fullName フルネーム
		 * @return this
		 */
		public Builder fullName(String fullName) {
			this.fullName = fullName;
			return this;
		}

		/**
		 * プロフィール画像を設定します。
		 * @param profileImage プロフィール画像
		 * @return this
		 */
		public Builder profileImage(ProfileImage profileImage) {
			this.profileImage = profileImage;
			return this;
		}

		/**
		 * 設定された構成から、<code>User</code>クラスのインスタンスを生成します。
		 * @return <code>User</code>クラスのインスタンス
		 */
		public User build() {
			return new User(loginId, password, fullName, profileImage);
		}
	}

	/**
	 * ログインIDを返します。
	 * @return ログインID
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * パスワード（ハッシュ）を返します。
	 * @return パスワード（ハッシュ）
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * フルネームを返します。
	 * @return フルネーム
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * プロフィール画像を返します。
	 * @return プロフィール画像
	 */
	public ProfileImage getProfileImage() {
		return profileImage;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof User))
			return false;
		User user = (User)obj;
		return user.loginId.equals(loginId)
				&& user.password.equals(password)
				&& user.fullName.equals(fullName)
				&& user.profileImage.equals(profileImage);
	}
	
	@Override
	public int hashCode() {
		return loginId.hashCode();
	}
}
