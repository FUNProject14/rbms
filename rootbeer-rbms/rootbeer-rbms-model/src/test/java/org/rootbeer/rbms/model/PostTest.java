package org.rootbeer.rbms.model;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import java.util.Date;

public class PostTest {
	@Test
	public void testConstructor() {
		long testDate = System.currentTimeMillis();
		Post testPost = new Post("TEST", "uchiyama", new Date(testDate), "parent");
		
		assertThat(testPost.getBody(), is("TEST"));
		assertThat(testPost.getAuthorUserId() , is("uchiyama"));
		assertThat(testPost.getPostedTime(), is(new Date(testDate)));
		assertThat(testPost.getParentPostId(), is("parent"));
	}
	
	@Test
	public void testAccessor(){
		long testDate = System.currentTimeMillis();	
		Post testPost = new Post();
		
		testPost.setAuthorUserId("uchiyama");
		assertThat(testPost.getAuthorUserId(), is("uchiyama"));
		testPost.setBody("TEST");
		assertThat(testPost.getBody(), is("TEST"));
		testPost.setPostedTime(new Date(testDate));
		assertThat(testPost.getPostedTime(), is(new Date(testDate)));
		testPost.setParentPostId("parent");
		assertThat(testPost.getParentPostId(), is("parent"));
	}
	
	@Test
	public void testEquality(){
		long testDateA = System.currentTimeMillis();
		long testDateB = System.currentTimeMillis()+1000;
		Post testPostAAAA = new Post("TESTA", "uchiyamaA", new Date(testDateA), "parentA");
		Post testPostAAAA2 = new Post("TESTA", "uchiyamaA", new Date(testDateA), "parentA");
		Post testPostAAAB = new Post("TESTA", "uchiyamaA", new Date(testDateA), "parentB");
		Post testPostAABA = new Post("TESTA", "uchiyamaA", new Date(testDateB), "parentA");
		Post testPostAABB = new Post("TESTA", "uchiyamaA", new Date(testDateB), "parentB");
		Post testPostABAA = new Post("TESTA", "uchiyamaB", new Date(testDateA), "parentA");
		Post testPostABAB = new Post("TESTA", "uchiyamaB", new Date(testDateA), "parentB");
		Post testPostABBA = new Post("TESTA", "uchiyamaB", new Date(testDateB), "parentA");
		Post testPostABBB = new Post("TESTA", "uchiyamaB", new Date(testDateB), "parentB");
		Post testPostBAAA = new Post("TESTB", "uchiyamaA", new Date(testDateA), "parentA");
		Post testPostBAAB = new Post("TESTB", "uchiyamaA", new Date(testDateA), "parentB");
		Post testPostBABA = new Post("TESTB", "uchiyamaA", new Date(testDateB), "parentA");
		Post testPostBABB = new Post("TESTB", "uchiyamaA", new Date(testDateB), "parentB");
		Post testPostBBAA = new Post("TESTB", "uchiyamaB", new Date(testDateA), "parentA");
		Post testPostBBAB = new Post("TESTB", "uchiyamaB", new Date(testDateA), "parentB");
		Post testPostBBBA = new Post("TESTB", "uchiyamaB", new Date(testDateB), "parentA");
		Post testPostBBBB = new Post("TESTB", "uchiyamaB", new Date(testDateB), "parentB");	
		
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