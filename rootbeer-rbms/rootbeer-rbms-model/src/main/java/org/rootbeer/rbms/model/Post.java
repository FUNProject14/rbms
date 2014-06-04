package org.rootbeer.rbms.model;

import java.util.Date;

/**
 * 投稿を示すクラスです。ある投稿への返信とすることも出来ます。
 */
public final class Post {
	private String body;
	private String authorUserId;
	private Date postedTime;
	private String parentPostId;
	
	public Post() {}
	
	/**
	 * 本文、著者、投稿時刻の投稿からインスタンスを生成します。
	 * @param body 本文
	 * @param authorUserId 著者のユーザID
	 * @param postedTime 投稿時刻
	 */
	public Post(String body, String authorUserId, Date postedTime) {
		this.body = body;
		this.authorUserId = authorUserId;
		this.postedTime = (Date) postedTime.clone();
		this.parentPostId = null;
	}
	
	/**
	 * 本文、著者、投稿時刻、返信先の投稿からインスタンスを生成します。
	 * @param body 本文
	 * @param author 著者
	 * @param postedTime 投稿時刻
	 * @param parent 返信先の投稿
	 */
	public Post(String body, String authorUserId, Date postedTime, String parentPostId) {
		this.body = body;
		this.authorUserId = authorUserId;
		this.postedTime = (Date) postedTime.clone();
		this.parentPostId = parentPostId;
	}
	
	/**
	 * 著者のユーザIDを返します。
	 * @return 著者のユーザID
	 */
	public String getAuthorUserId() {
		return authorUserId;
	}

	/**
	 * 本文を返します。
	 * @return 本文
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 返信先のポストIDを返します。
	 * @return 返信先のポストID
	 */
	public String getParentPostId() {
		return parentPostId;
	}

	/**
	 * 投稿時刻を返します。
	 * @return 投稿時刻
	 */
	public Date getPostedTime() {
		return (Date) postedTime.clone();
	}
	
	public void setBody(String body) {
		this.body = body;
	}

	public void setAuthorUserId(String authorUserId) {
		this.authorUserId = authorUserId;
	}

	public void setPostedTime(Date postedTime) {
		this.postedTime = postedTime;
	}

	public void setParentPostId(String parentPostId) {
		this.parentPostId = parentPostId;
	}

	@Override
 	public boolean equals(Object obj){
 		if(obj == null){
 			return false;
 		}
 		if(obj == this){
 			return true;
 		}
 		if(!(obj instanceof Post)){
 			return false;
 		}
 		Post post = (Post)obj;
 		return post.authorUserId.equals(authorUserId)
 				&& post.body.equals(body)
 				&& post.parentPostId.equals(parentPostId)
 				&& post.postedTime.equals(postedTime);
 	}
  	
 	@Override
 	public int hashCode() {
 		return authorUserId.hashCode()+postedTime.hashCode();
 	}
	
}
