package org.rootbeer.rbms.model;

public class User {
	private PrimaryKey key;
	private UserId userId;
	private Password password;
	private FullName fullName;
	private Icon icon;
	
	static public class UserId {
		private final String id;
		
		public UserId(String id) {
			this.id = new String(id);
		}
		UserId(UserId id) {
			this.id = new String(id.id);
		}
		
		@Override
		public String toString() {
			return new String(id);
		}
	}
	
	static public class Password {
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
	
	static public class FullName {
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
	
	static public class Icon {
		private final String path;
		
		Icon(String path) {
			this.path = new String(path);
		}
		Icon(Icon icon) {
			this.path = new String(icon.path);
		}
		
		@Override
		public String toString() {
			return new String(path);
		}
	}
	
	

	public PrimaryKey getKey() {
		return new PrimaryKey(key);
	}
	
	public UserId getUserId() {
		return new UserId(userId);
	}
	
	public void setUserId(UserId userId) {
		this.userId = new UserId(userId);
	}

	public Password getPassword() {
		return new Password(password);
	}

	public void setPassword(Password password) {
		this.password = new Password(password);
	}

	public FullName getFullName() {
		return new FullName(fullName);
	}

	public void setFullName(FullName fullName) {
		this.fullName = new FullName(fullName);
	}

	public Icon getIcon() {
		return new Icon(icon);
	}

	public void setIcon(Icon icon) {
		this.icon = new Icon(icon);
	}

}
