package org.rootbeer.rbms.model;

import java.util.Date;

/**
 * 投稿を示すクラスです。ある投稿への返信とすることも出来ます。
 */
public final class Post {
	private final String body;
	private final User author;
	private final Date postedTime;
	private final Post parent;
	
	/**
	 * 本文、著者、投稿時刻の投稿からインスタンスを生成します。
	 * @param body 本文
	 * @param author 著者
	 * @param postedTime 投稿時刻
	 */
	public Post(String body, User author, Date postedTime) {
		this.body = body;
		this.author = author;
		this.postedTime = (Date) postedTime.clone();
		this.parent = null;
	}
	
	/**
	 * 本文、著者、投稿時刻、返信先の投稿からインスタンスを生成します。
	 * @param body 本文
	 * @param author 著者
	 * @param postedTime 投稿時刻
	 * @param parent 返信先の投稿
	 */
	public Post(String body, User author, Date postedTime, Post parent) {
		this.body = body;
		this.author = author;
		this.postedTime = (Date) postedTime.clone();
		this.parent = parent;
	}
	
	/**
	 * 著者を返します。
	 * @return 著者
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * 本文を返します。
	 * @return 本文
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 返信先を返します。
	 * @return 返信先
	 */
	public Post getParent() {
		return parent;
	}

	/**
	 * 投稿時刻を返します。
	 * @return 投稿時刻
	 */
	public Date getPostedTime() {
		return (Date) postedTime.clone();
	}
	
}
