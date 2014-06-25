package org.rootbeer.rbms.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import org.junit.*;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.model.*;

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
	public void testAccessing() {
		assertThat(getPictures(MICHIKO), is(nullValue()));
		
		long TestDate = System.currentTimeMillis();
		Picture picture = new Picture("choco", "michiko", MICHIKO, new Date(TestDate));
		Picture picture2 = new Picture("choco2", "michiko2", MICHIKO, new Date(TestDate));
		
		
		addPicture(picture);
		addPicture(picture2);
		Picture[] pictures = getPictures(MICHIKO);
		
		assertThat(pictures[0], is(not(nullValue())));
		assertThat(pictures[0], is(picture));
		assertThat(pictures[1], is(not(nullValue())));
		assertThat(pictures[1], is(picture2));
	}
}
