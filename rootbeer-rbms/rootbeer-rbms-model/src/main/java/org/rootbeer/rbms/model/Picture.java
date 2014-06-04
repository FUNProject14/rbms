package org.rootbeer.rbms.model;

import java.util.Date;

/**
 * パスで示される位置に投稿された画像を表すクラスです。
 */
public final class Picture {
	private String path;
	private String description;
	private String authorUserId;
	private Date uploadedTime;
	
	/**
	 * 画像がある位置を示すパス、説明文、撮影者、投稿された時刻からインスタンスを生成します。
	 * @param path 画像のある位置を示すパス
	 * @param description 説明文
	 * @param authorUserId 撮影者のユーザID
	 * @param uploadedTime 投稿された時刻
	 */
	public Picture(String path, String description, String authorUserId, Date uploadedTime) {
		this.path = path;
		this.description = description;
		this.authorUserId = authorUserId;
		this.uploadedTime = new Date(uploadedTime.getTime());
	}

	/**
	 * 画像のある位置を示すパスを返します。
	 * @return 画像のある位置を示すパス
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 画像の説明文を返します。
	 * @return 画像の説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 画像の撮影者のユーザIDを返します。
	 * @return 画像の撮影者のユーザID
	 */
	public String getAuthorUserId() {
		return authorUserId;
	}

	/**
	 * 画像が投稿された時刻を返します。
	 * @return 画像の投稿された時刻
	 */
	public Date getUploadedTime() {
		return new Date(uploadedTime.getTime());
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAuthorUserId(String authorUserId) {
		this.authorUserId = authorUserId;
	}

	public void setUploadedTime(Date uploadedTime) {
		this.uploadedTime = uploadedTime;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		if(obj == this){
			return true;
		}
		if(!(obj instanceof Picture)){
			return false;
		}
		Picture pic = (Picture)obj;
		return pic.path.equals(path)
				&& pic.description.equals(description)
				&& pic.authorUserId.equals(authorUserId)
				&& pic.uploadedTime.equals(uploadedTime);
	}
	
	@Override
	public int hashCode(){
		return path.hashCode();
	}
	
}
