package org.rootbeer.rbms.model;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import java.util.Date;

public class PostTest {
	@Test
	public void testConstructor() {
		Date testDate = new Date();
		Post testPost = new Post("TEST", "uchiyama", testDate, "parent");
		
		assertThat(testPost.getBody(), is("TEST"));
		assertThat(testPost.getAuthorUserId() , is("uchiyama"));
		assertThat(testPost.getPostedTime(), is(testDate));
		assertThat(testPost.getParentPostId(), is("parent"));
	}
	
	@Test
	public void testAccessor(){
		Date testDate = new Date();		
		Post testPost = new Post();
		
		testPost.setAuthorUserId("uchiyama");
		assertThat(testPost.getAuthorUserId(), is("uchiyama"));
		testPost.setBody("TEST");
		assertThat(testPost.getBody(), is("TEST"));
		testPost.setPostedTime(testDate);
		assertThat(testPost.getPostedTime(), is(testDate));
		testPost.setParentPostId("parent");
		assertThat(testPost.getParentPostId(), is("parent"));
	}
	
	@Test
	public void testEquality(){
		Date testDate = new Date();
		Post testPostA = new Post("TEST1", "uchiyama", testDate, "parent");
		Post testPostB = new Post("TEST1", "uchiyama", testDate, "parent");
		Post testPostC = new Post("TEST", "uchiyama1", testDate, "parent");
		
		assertThat(testPostA, is(testPostB));
		assertThat(testPostA, is(not(testPostC)));
		
		assertThat(testPostA.hashCode(), is(testPostB.hashCode()));
		assertThat(testPostA.hashCode(), is(not(testPostC.hashCode())));
	}
}