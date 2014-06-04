package org.rootbeer.rbms.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

import org.junit.Test;

import org.rootbeer.rbms.model.Action.Act;

public class ActionTest {
	
	@Test
	public void testContstructor(){
		Date testDate = new Date(System.currentTimeMillis());
		Action testAction = new Action(Act.BUY, "mahara123", testDate);
		
		assertThat(testAction.getAct(), is(Act.BUY));
		assertThat(testAction.getActorUserId(), is("mahara123"));
		assertThat(testAction.getActedTime(), is(testDate));
		
	}
	
	@Test
	public void testAccessor(){
		Action testAction = new Action();
		
		testAction.setAct(Act.BUY);
		assertThat(testAction.getAct(), is(Act.BUY));
		assertThat(testAction.getAct(), is(not(Act.DRINK)));
		
		testAction.setActorUserId("hogehoge");
		assertThat(testAction.getActorUserId(), is("hogehoge"));
		
		Date testDate = new Date(System.currentTimeMillis());
		testAction.setActedTime(testDate);
		assertThat(testAction.getActedTime(), is(testDate));
		
	}
	
	@Test
	public void testEquality(){
		
		Date testDate = new Date(System.currentTimeMillis());
		
		Action actionA = new Action(Act.BUY, "hoge1", testDate);
		Action actionB = new Action(Act.BUY, "hoge1", testDate);
		Action actionC = new Action(Act.BUY,"hoge2", testDate);
		
		assertThat(actionA,  is(actionB));
		assertThat(actionA, is(not(actionC)));
		assertThat(actionA.hashCode(), is(actionB.hashCode()));
		
	}
	
}