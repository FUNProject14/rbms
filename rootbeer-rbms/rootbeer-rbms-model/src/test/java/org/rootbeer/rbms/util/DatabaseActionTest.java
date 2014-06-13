package org.rootbeer.rbms.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.*;

import com.couchbase.client.CouchbaseClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.model.*;

public class DatabaseActionTest {
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
		long currentDate = System.currentTimeMillis();
		Action action1 = new Action(Action.Act.BUY, MICHIKO, new Date(currentDate));
		Action action2 = new Action(Action.Act.DRINK, MICHIKO, new Date(currentDate + 1));
		Action[] actions = new Action[] {
				action1,
				action2
				};
		
		CouchbaseClient client = getClient(Bucket.ACTION);
		Gson gson = ModelUtil.GSON;
		client.add(MICHIKO, gson.toJson(actions));
		
		String gotString = (String) client.get(MICHIKO);
		Action[] gotActions = gson.fromJson(gotString, Action[].class);
		assertThat(Arrays.asList(gotActions), is(Arrays.asList(actions)));
	}
}
