package org.rootbeer.rbms.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import org.junit.*;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.model.*;

public class DatabasePostTest {
	private static final String MICHIKO = "michiko_oba"; 
	@Before
	public void setUp() {
		getClient(Bucket.ACTION).delete(MICHIKO);
		getClient(Bucket.PICTURE).delete(MICHIKO);
		getClient(Bucket.POST).delete(MICHIKO);
		getClient(Bucket.USER).delete(MICHIKO);
	}
	
	@Test
	public void testAccessing(){
		assertThat(getPosts(MICHIKO), is(nullValue()));
		long postTestDate = System.currentTimeMillis();
		Post testPost = new Post("body", MICHIKO, new Date(postTestDate), "kumar");
		Post testPost1 = new Post("body1", MICHIKO, new Date(postTestDate), "kumar");
		addPost(testPost);
		addPost(testPost1);

		Post[] testPosts = getPosts(MICHIKO);
		assertThat(testPosts[0], is(not(nullValue())));
		assertThat(testPosts[0], is(testPost));
		assertThat(testPosts[1], is(testPost1));
	}
}
