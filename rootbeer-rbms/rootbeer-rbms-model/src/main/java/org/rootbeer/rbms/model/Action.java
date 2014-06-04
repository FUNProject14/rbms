package org.rootbeer.rbms.model;

import java.util.Date;

/**
 * ユーザーの行動を示すクラスです。
 */
public final class Action {
	private Act act;
	private String actorUserId;
	private Date actedTime;
	
	/**
	 * 行動の種類を示す列挙型クラスです。
	 */
	enum Act {
		/** 購入 */
		BUY,
		/** 服用 */
		DRINK;
	}
	
	public Action() {}
	
	/**
	 * 行動の種類、それを行ったユーザー、それを行った時刻からインスタンスを生成します。
	 * @param act 行動の種類
	 * @param actorUserId 行動したユーザーのID
	 * @param actedTime 行動した時刻
	 */
	public Action(Act act, String actorUserId, Date actedTime) {
		this.act = act;
		this.actorUserId = actorUserId;
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
	 * 行動したユーザーのIDを返します。
	 * @return 行動したユーザーのID
	 */
	public String getActorUserId() {
		return actorUserId;
	}

	/**
	 * 行動した時刻を返します。
	 * @return 行動した時刻
	 */
	public Date getActedTime() {
		return actedTime;
	}

	public void setAct(Act act) {
		this.act = act;
	}

	public void setActorUserId(String actorUserId) {
		this.actorUserId = actorUserId;
	}

	public void setActedTime(Date actedTime) {
		this.actedTime = actedTime;
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
				&& action.actorUserId.equals(actorUserId);
	}
	
	@Override
	public int hashCode(){
		return actedTime.hashCode();
	}
}
