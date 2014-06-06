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
		long dateA = System.currentTimeMillis();
		long dateB = System.currentTimeMillis()+1;
		Date testDateA = new Date(dateA);
		Date testDateB = new Date(dateB);
		Post testPostAAAA = new Post("TESTA", "uchiyamaA", testDateA, "parentA");
		Post testPostAAAA2 = new Post("TESTA", "uchiyamaA", testDateA, "parentA");
		Post testPostAAAB = new Post("TESTA", "uchiyamaA", testDateA, "parentB");
		Post testPostAABA = new Post("TESTA", "uchiyamaA", testDateB, "parentA");
		Post testPostAABB = new Post("TESTA", "uchiyamaA", testDateB, "parentB");
		Post testPostABAA = new Post("TESTA", "uchiyamaB", testDateA, "parentA");
		Post testPostABAB = new Post("TESTA", "uchiyamaB", testDateA, "parentB");
		Post testPostABBA = new Post("TESTA", "uchiyamaB", testDateB, "parentA");
		Post testPostABBB = new Post("TESTA", "uchiyamaB", testDateB, "parentB");
		Post testPostBAAA = new Post("TESTB", "uchiyamaA", testDateA, "parentA");
		Post testPostBAAB = new Post("TESTB", "uchiyamaA", testDateA, "parentB");
		Post testPostBABA = new Post("TESTB", "uchiyamaA", testDateB, "parentA");
		Post testPostBABB = new Post("TESTB", "uchiyamaA", testDateB, "parentB");
		Post testPostBBAA = new Post("TESTB", "uchiyamaB", testDateA, "parentA");
		Post testPostBBAB = new Post("TESTB", "uchiyamaB", testDateA, "parentB");
		Post testPostBBBA = new Post("TESTB", "uchiyamaB", testDateB, "parentA");
		Post testPostBBBB = new Post("TESTB", "uchiyamaB", testDateB, "parentB");	
		
		assertThat(dateA, is(not(dateB)));
		assertThat(new Date(dateA), is(not(new Date(dateB))));
		
		assertThat(testPostAAAA, is(testPostAAAA2));
		assertThat(testPostAAAA, is(not(testPostAAAB)));
		assertThat(testPostAAAA, is(not(testPostAABA)));
		assertThat(testPostAAAA, is(not(testPostAABB)));
		assertThat(testPostAAAA, is(not(testPostABAA)));
		assertThat(testPostAAAA, is(not(testPostABAB)));
		assertThat(testPostAAAA, is(not(testPostABBA)));
		assertThat(testPostAAAA, is(not(testPostABBB)));
		assertThat(testPostAAAA, is(not(testPostBAAA)));
		assertThat(testPostAAAA, is(not(testPostBAAB)));
		assertThat(testPostAAAA, is(not(testPostBABA)));
		assertThat(testPostAAAA, is(not(testPostBABB)));
		assertThat(testPostAAAA, is(not(testPostBBAA)));
		assertThat(testPostAAAA, is(not(testPostBBAB)));
		assertThat(testPostAAAA, is(not(testPostBBBA)));
		assertThat(testPostAAAA, is(not(testPostBBBB)));
		
		assertThat(testPostAAAA.hashCode(), is(testPostAAAA2.hashCode()));
		assertThat(testPostAAAA.hashCode(), is(not(testPostBBBB.hashCode())));
	}
}