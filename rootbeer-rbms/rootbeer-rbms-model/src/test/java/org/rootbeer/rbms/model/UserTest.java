package org.rootbeer.rbms.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.rootbeer.rbms.model.User;

public class UserTest {
	@Test
	public void testConstuctor() {
		User user = new User("kumar8600", "unkoderu", "Yuya Kumagai");
		assertThat(user.getUserId(), is("kumar8600"));
		assertThat(user.getPassword(), is("unkoderu"));
		assertThat(user.getFullName(), is("Yuya Kumagai"));
	}
	
	@Test
	public void testAccesssor() {
		User user = new User();
		user.setUserId("kumar8600");
		
		assertThat(user.getUserId(), is("kumar8600"));
		user.setPassword("unkoderu");
		assertThat(user.getPassword(), is("unkoderu"));
		user.setFullName("Yuya Kumagai");
		assertThat(user.getFullName(), is("Yuya Kumagai"));
	}
	
	@Test
	public void testEquality() {
		User userA = new User("kumar8600", "unkoderu", "Yuya Kumagai");
		User userB = new User("kumar8600", "unkoderu", "Yuya Kumagai");
		User userC = new User("kumar17200", "unkoderu", "Yuya Kumagai");
		
		assertThat(userA, is(userB));
		assertThat(userA, is(not(userC)));
		
		assertThat(userA.hashCode(), is(userB.hashCode()));
	}
}
