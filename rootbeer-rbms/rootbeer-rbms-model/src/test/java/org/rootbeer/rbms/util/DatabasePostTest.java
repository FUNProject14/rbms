package org.rootbeer.rbms.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import org.junit.*;

import com.couchbase.client.CouchbaseClient;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.model.*;

import com.google.gson.*;

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
	public void testAccessingAction() {
	}
	
	@Test
	public void testAccessingPost(){
		long postTestDate = System.currentTimeMillis();
		Post testPost = new Post("body", MICHIKO, new Date(postTestDate), "kumar");
		CouchbaseClient client = getClient(Bucket.POST);
		Gson postGson = new GsonBuilder()
		.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
		.create();
		
		client.add(MICHIKO, postGson.toJson(testPost));
		assertThat(postGson.toJson(testPost), is((client.get(MICHIKO))));
		
		Post testPostFromJson = postGson.fromJson(client.get(MICHIKO).toString(), Post.class); 
		assertThat(testPost, is(testPostFromJson));
	}
}
