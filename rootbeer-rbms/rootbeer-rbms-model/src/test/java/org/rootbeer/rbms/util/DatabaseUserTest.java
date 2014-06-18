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
		CouchbaseClient client = getClient(Bucket.USER);
		Gson gson = ModelUtil.GSON;

		User boss = new User(MICHIKO, "michiko123", "BOSS");
		addUser(MICHIKO, boss);
		assertThat(client.get(MICHIKO).toString(), is(gson.toJson(boss)));
		
		User boss2 = getUser(MICHIKO);
		assertThat(boss2, is(boss));
	}

}
