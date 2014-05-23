package org.rootbeer.rbms.model;

import java.util.Date;

public final class Action {
	private final Act act;
	private final User actor;
	private final Date actedTime;
	
	enum Act {
		BUY, DRINK;
	}
	
	public Action(Act act, User actor, Date actedTime) {
		this.act = act;
		this.actor = actor;
		this.actedTime = actedTime;
	}

	public Act getAct() {
		return act;
	}

	public User getActor() {
		return actor;
	}

	public Date getActedTime() {
		return actedTime;
	}
}
