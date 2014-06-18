package org.rootbeer.rbms.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import org.junit.*;

import com.couchbase.client.CouchbaseClient;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.model.*;

import com.google.gson.*;

public class DatabasePictureTest {
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
		long pictureTestDate = System.currentTimeMillis();
		Picture testPicture = new Picture("path", MICHIKO, "authorUserId", new Date(pictureTestDate));
		CouchbaseClient client = getClient(Bucket.PICTURE);
		Gson gson = ModelUtil.GSON;
		
		client.add(MICHIKO, gson.toJson(testPicture));
		assertThat(gson.toJson(testPicture), is((client.get(MICHIKO))));
		
		Picture testPictureFromJson = gson.fromJson(client.get(MICHIKO).toString(), Picture.class); 
		assertThat(testPicture, is(testPictureFromJson));
	}
}