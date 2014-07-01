package org.rootbeer.rbms.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import org.junit.*;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.logic.GetPostsOutOfBoundsException;
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
	public void testAccessing() throws GetPostsOutOfBoundsException{
		assertThat(getPosts(MICHIKO, 0, 2), is(nullValue()));
		long postTestDate = System.currentTimeMillis();
		Post testPost = new Post("body", MICHIKO, new Date(postTestDate), "kumar");
		Post testPost1 = new Post("body1", MICHIKO, new Date(postTestDate), "kumar");
		addPost(testPost);
		addPost(testPost1);
		assertThat(getPosts(MICHIKO, 0, 2), is(not(nullValue())));

		Post[] testPosts = getPosts(MICHIKO, 0, 2);
		assertThat(testPosts[0], is(testPost1));
		assertThat(testPosts[1], is(testPost));

		Post[] overRangePosts = getPosts(MICHIKO, 0, 3);
		assertThat(overRangePosts[0], is(testPost1));
		assertThat(overRangePosts[1], is(testPost));
		assertThat(overRangePosts.length, is(2));

		try{
			getPosts(MICHIKO, 3, 5);
		}catch(GetPostsOutOfBoundsException expected){
			assertThat(expected.getMessage(), is("範囲外の指定です。"));
		}
	}
}
