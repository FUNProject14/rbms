package org.rootbeer.rbms.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;

import com.couchbase.client.CouchbaseClient;
import static org.rootbeer.rbms.util.Database.*;

public class DatabaseTest {
	private static final String MICHIKO = "michiko_oba"; 
	@Before
	public void setUp() {
		getClient(Bucket.ACTION).delete(MICHIKO);
		getClient(Bucket.PICTURE).delete(MICHIKO);
		getClient(Bucket.POST).delete(MICHIKO);
		getClient(Bucket.USER).delete(MICHIKO);
	}
	
	@Test
	public void testAccessorAction() {
		
	}
}
