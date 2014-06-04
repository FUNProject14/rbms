package org.rootbeer.rbms.model;

/**
 * ユーザーを表すクラスです。
 */
public final class User {
	private String userId;
	private String password;
	private String fullName;

	/**
	 * ユーザーID、パスワード、フルネーム、プロフィール画像から、インスタンスを生成します。
	 * @param userId ユーザーID
	 * @param password パスワード（必ずハッシュであること）
	 * @param fullName フルネーム
	 */
	public User(String userId, String password, String fullName) {
		this.userId = userId;
		this.password = password;
		this.fullName = fullName;
	}

	/**
	 * ユーザーIDを返します。
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
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
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
		return user.userId.equals(userId)
				&& user.password.equals(password)
				&& user.fullName.equals(fullName);
	}
	
	@Override
	public int hashCode() {
		return userId.hashCode();
	}
}
