package org.rootbeer.rbms.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.*;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.model.*;
import org.rootbeer.rbms.util.Database.Bucket;


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
	public void testAccessing() {
                assertThat(getUser(MICHIKO), is(nullValue()));
                
		User boss = new User(MICHIKO, "michiko123", "BOSS");
		addUser(MICHIKO, boss);
		User boss2 = getUser(MICHIKO);
                
                assertThat(boss2, is(not(nullValue())));
		assertThat(boss2, is(boss));
	}

	@Test
	public void testGetUserIDs() {
		for (int i = 1; i <= 10; ++i) {
			String uid = MICHIKO + String.valueOf(i);
			getClient(Bucket.USER).delete(MICHIKO);
		}
		
		for (int i = 1; i <= 10; ++i) {
			String uid = MICHIKO + String.valueOf(i);
			User boss = new User(uid, "michiko123", "BOSS");
			addUser(uid, boss);
		}
		
		String[] userIDs = getUserIDs();
		HashSet<String> userIDSet = new HashSet<String>();
		userIDSet.addAll(Arrays.asList(userIDs));
		for (int i = 1; i <= 10; ++i) {
			String uid = MICHIKO + String.valueOf(i);
			assertThat(userIDSet.contains(uid), is(true));
		}
		
		for (int i = 1; i <= 10; ++i) {
			String uid = MICHIKO + String.valueOf(i);
			getClient(Bucket.USER).delete(MICHIKO);
		}
	}

}
