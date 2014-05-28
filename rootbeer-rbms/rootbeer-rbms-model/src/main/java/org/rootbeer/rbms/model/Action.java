package org.rootbeer.rbms.model;

import java.util.Date;

/**
 * ユーザーの行動を示すクラスです。
 * @author kumar1
 */
public final class Action {
	private final Act act;
	private final User actor;
	private final Date actedTime;
	
	/**
	 * 行動の種類を示す列挙型クラスです。
	 * @author kumar1
	 */
	enum Act {
		/** 購入 */
		BUY,
		/** 服用 */
		DRINK;
	}
	
	/**
	 * 行動の種類、それを行ったユーザー、それを行った時刻からインスタンスを生成します。
	 * @param act 行動の種類
	 * @param actor 行動したユーザー
	 * @param actedTime 行動した時刻
	 */
	public Action(Act act, User actor, Date actedTime) {
		this.act = act;
		this.actor = actor;
		this.actedTime = actedTime;
	}

	/**
	 * 行動の種類を返します。
	 * @return 行動の種類
	 */
	public Act getAct() {
		return act;
	}

	/**
	 * 行動したユーザーを返します。
	 * @return 行動したユーザー
	 */
	public User getActor() {
		return actor;
	}

	/**
	 * 行動した時刻を返します。
	 * @return 行動した時刻
	 */
	public Date getActedTime() {
		return actedTime;
	}
}
