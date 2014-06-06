package org.rootbeer.rbms.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

import org.junit.Test;

import org.rootbeer.rbms.model.Action.Act;

public class ActionTest {

	@Test
	public void testContstructor(){

		long testTime = System.currentTimeMillis();
		Action testAction = new Action(Act.BUY, "hogeA", new Date(testTime));

		assertThat(testAction.getAct(), is(Act.BUY));
		assertThat(testAction.getActorUserId(), is("hogeA"));
		assertThat(testAction.getActedTime(), is(new Date(testTime)));

	}


	@Test
	public void testAccessor(){

		Action testAction = new Action();

		testAction.setAct(Act.BUY);
		assertThat(testAction.getAct(), is(Act.BUY));
		assertThat(testAction.getAct(), is(not(Act.DRINK)));

		testAction.setActorUserId("hogehoge");
		assertThat(testAction.getActorUserId(), is("hogehoge"));

		long testTime = System.currentTimeMillis();
		testAction.setActedTime(new Date(testTime));
		assertThat(testAction.getActedTime(), is(new Date(testTime)));

	}


	@Test
	public void testEquality(){

		long testTimeA = System.currentTimeMillis();
		long testTimeB = System.currentTimeMillis()+1;

		Action testActionAAA = new Action(Act.BUY, "hogeA", new Date(testTimeA));
		Action testActionAAA2 = new Action(Act.BUY, "hogeA", new Date(testTimeA));
		Action testActionAAB = new Action(Act.BUY, "hogeA", new Date(testTimeB));
		Action testActionABA = new Action(Act.BUY, "hogeB", new Date(testTimeA));
		Action testActionBAA = new Action(Act.DRINK, "hogeA", new Date(testTimeA));
		Action testActionABB = new Action(Act.BUY, "hogeB", new Date(testTimeB));
		Action testActionBAB = new Action(Act.DRINK, "hogeA", new Date(testTimeB));
		Action testActionBBA = new Action(Act.DRINK, "hogeB", new Date(testTimeA));
		Action testActionBBB = new Action(Act.DRINK, "hogeB", new Date(testTimeB));

		assertThat(testActionAAA, is(testActionAAA2));
		assertThat(testActionAAA, is(not(testActionAAB)));
		assertThat(testActionAAA, is(not(testActionABA)));
		assertThat(testActionAAA, is(not(testActionBAA)));
		assertThat(testActionAAA, is(not(testActionABB)));
		assertThat(testActionAAA, is(not(testActionBAB)));
		assertThat(testActionAAA, is(not(testActionBBA)));
		assertThat(testActionAAA, is(not(testActionBBB)));

		assertThat(testActionAAA.hashCode(), is(testActionAAA2.hashCode()));
		assertThat(testActionAAA.hashCode(), is(not(testActionAAB.hashCode())));

	}

}