package org.rootbeer.rbms.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.junit.*;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.logic.GetPostsOutOfBoundsException;
import org.rootbeer.rbms.model.*;
import org.rootbeer.rbms.model.Action.Act;
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
	@Test
	public void testDeleteTestUserData() throws GetPostsOutOfBoundsException{
		long date = System.currentTimeMillis();
		User user = new User("@testUser", "", "");
		Post post = new Post("body", "@testUser", new Date(date));
		Action action = new Action(Act.BUY, "@testUser", new Date(date));
		Picture picture = new Picture("path", "test", "@testUser", new Date(date));
//		
		addUser("@testUser",user);
		addPost(post);
		addAction(action);
		addPicture(picture);

		User testUserA = getUser("@testUser");
		Post[] testPostsA = getPosts("@testUser", 0, 100);
		Action[] testActionsA = getActions("@testUser");
		Picture[] testPicturesA = getPictures("@testUser");
		assertThat(testUserA.getUserId(), is("@testUser"));
		assertThat(testPostsA[0].getAuthorUserId(), is("@testUser"));
		assertThat(testActionsA[0].getActorUserId(), is("@testUser"));
		assertThat(testPicturesA[0].getAuthorUserId(), is("@testUser"));
		
		deleteTestUserData();
		//TODO:削除されるまで時間があって待つ
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		assertThat(getUser("@testUser"), is(nullValue()));
		assertThat(getPosts("@testUser", 0, 100), is(nullValue()));
		assertThat(getActions("@testUser"), is(nullValue()));
		assertThat(getPictures("@testUser"), is(nullValue()));
	}

}
