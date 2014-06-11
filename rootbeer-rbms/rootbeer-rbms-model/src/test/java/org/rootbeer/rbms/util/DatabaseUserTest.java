package org.rootbeer.rbms.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import org.junit.*;

import com.couchbase.client.CouchbaseClient;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.model.*;
import org.rootbeer.rbms.util.Database.Bucket;

import com.google.gson.*;

public class DatabaseUserTest {
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
		User testUser = new User(MICHIKO, "michiko123", "BOSS");
		CouchbaseClient client = getClient(Bucket.USER);
		Gson userGson = ModelUtil.GSON;
		
		client.add(MICHIKO, userGson.toJson(testUser));
		assertThat(userGson.toJson(testUser), is((client.get(MICHIKO))));
		
		User testUserFromJson = userGson.fromJson(client.get(MICHIKO).toString(), User.class);
		assertThat(testUser, is(testUserFromJson));
	}

}
