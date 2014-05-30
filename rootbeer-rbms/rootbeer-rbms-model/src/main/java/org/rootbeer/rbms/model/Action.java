package org.rootbeer.rbms.model;

import java.util.Date;

/**
 * ユーザーの行動を示すクラスです。
 */
public final class Action {
	private final Act act;
	private final User actor;
	private final Date actedTime;
	
	/**
	 * 行動の種類を示す列挙型クラスです。
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
	
	@Override
	public boolean equals(Object obj){
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Action))
			return false;
		Action action = (Action)obj;
		return action.act.equals(act)
				&& action.actedTime.equals(actedTime)
				&& action.actor.equals(actor);
	}
	
	@Override
	public int hashCode(){
		return actedTime.hashCode();
	}
}
