package org.rootbeer.rbms.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

import org.junit.Test;

import org.rootbeer.rbms.model.Action.Act;

public class ActionTest {

	@Test
	public void testContstructor(){

		Action testAction = new Action(Act.BUY, "hogeA", new Date(System.currentTimeMillis()));

		assertThat(testAction.getAct(), is(Act.BUY));
		assertThat(testAction.getActorUserId(), is("hogeA"));
		assertThat(testAction.getActedTime(), is(new Date(System.currentTimeMillis())));

	}


	@Test
	public void testAccessor(){

		Action testAction = new Action();

		testAction.setAct(Act.BUY);
		assertThat(testAction.getAct(), is(Act.BUY));
		assertThat(testAction.getAct(), is(not(Act.DRINK)));

		testAction.setActorUserId("hogehoge");
		assertThat(testAction.getActorUserId(), is("hogehoge"));

		testAction.setActedTime(new Date(System.currentTimeMillis()));
		assertThat(testAction.getActedTime(), is(new Date(System.currentTimeMillis())));

	}


	@Test
	public void testEquality(){

		Action testActionAAA = new Action(Act.BUY, "hogeA", new Date(System.currentTimeMillis()));
		Action testActionAAA2 = new Action(Act.BUY, "hogeA", new Date(System.currentTimeMillis()));
		Action testActionAAB = new Action(Act.BUY, "hogeA", new Date(System.currentTimeMillis()+1));
		Action testActionABA = new Action(Act.BUY, "hogeB", new Date(System.currentTimeMillis()));
		Action testActionBAA = new Action(Act.DRINK, "hogeA", new Date(System.currentTimeMillis()));
		Action testActionABB = new Action(Act.BUY, "hogeB", new Date(System.currentTimeMillis()+1));
		Action testActionBAB = new Action(Act.DRINK, "hogeA", new Date(System.currentTimeMillis()+1));
		Action testActionBBA = new Action(Act.DRINK, "hogeB", new Date(System.currentTimeMillis()));
		Action testActionBBB = new Action(Act.DRINK, "hogeB", new Date(System.currentTimeMillis()+1));

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