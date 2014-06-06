package org.rootbeer.rbms.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

import org.junit.Test;

public class PictureTest {
    @Test
    public void testConstructor(){
        long time = System.currentTimeMillis();
        Picture picture = new Picture("path","des","auth", new Date(time));

        assertThat(picture.getPath(), is("path"));
        assertThat(picture.getDescription(), is("des"));
        assertThat(picture.getAuthorUserId(), is("auth"));
        assertThat(picture.getUploadedTime(), is(new Date(time)));
    }

    @Test
    public void testAccessor(){
        long time = System.currentTimeMillis();
        Picture picture = new Picture();

        picture.setPath("path");
        assertThat(picture.getPath(), is("path"));
        picture.setDescription("des");
        assertThat(picture.getDescription(), is("des"));
        picture.setAuthorUserId("auth");
        assertThat(picture.getAuthorUserId(), is("auth"));
        picture.setUploadedTime(new Date(time));
        assertThat(picture.getUploadedTime(), is(new Date(time)));
    }

    @Test
    public void testEquality(){
        long time = System.currentTimeMillis();

        Picture pictureAAAA = new Picture("path","des","auth", new Date(time));
        Picture pictureAAAA2 = new Picture("path","des","auth", new Date(time));
        Picture pictureBAAA = new Picture("path2","des","auth", new Date(time));
        Picture pictureABAA = new Picture("path","des2","auth",new Date(time));
        Picture pictureAABA = new Picture("path","des","auth2", new Date(time));
        Picture pictureAAAB = new Picture("path","des","auth", new Date(time+time));
        Picture pictureBBAA = new Picture("path2","des2","auth", new Date(time));
        Picture pictureBABA = new Picture("path2","des","auth2", new Date(time));
        Picture pictureBAAB = new Picture("path2","des","auth", new Date(time+time));
        Picture pictureABBA = new Picture("path","des2","auth2", new Date(time));
        Picture pictureABAB = new Picture("path","des2","auth", new Date(time+time));
        Picture pictureAABB = new Picture("path","des","auth2", new Date(time+time));
        Picture pictureBBBA = new Picture("path2","des2","auth2", new Date(time));
        Picture pictureBBAB = new Picture("path2","des2","auth", new Date(time+time));
        Picture pictureBABB = new Picture("path2","des","auth2", new Date(time+time));
        Picture pictureABBB = new Picture("path","des2","auth2", new Date(time+time));
        Picture pictureBBBB = new Picture("path2","des2","auth2", new Date(time+time));        

        assertThat(pictureAAAA, is(pictureAAAA2));
        assertThat(pictureAAAA, is(not(pictureBAAA)));
        assertThat(pictureAAAA, is(not(pictureABAA)));
        assertThat(pictureAAAA, is(not(pictureAABA)));
        assertThat(pictureAAAA, is(not(pictureAAAB)));
        assertThat(pictureAAAA, is(not(pictureBBAA)));
        assertThat(pictureAAAA, is(not(pictureBABA)));
        assertThat(pictureAAAA, is(not(pictureBAAB)));
        assertThat(pictureAAAA, is(not(pictureABBA)));
        assertThat(pictureAAAA, is(not(pictureABAB)));
        assertThat(pictureAAAA, is(not(pictureAABB)));
        assertThat(pictureAAAA, is(not(pictureBBBA)));
        assertThat(pictureAAAA, is(not(pictureBBAB)));
        assertThat(pictureAAAA, is(not(pictureBABB)));
        assertThat(pictureAAAA, is(not(pictureABBB)));
        assertThat(pictureAAAA, is(not(pictureBBBB)));

        assertThat(pictureAAAA.hashCode(), is(pictureAAAA2.hashCode()));
        assertThat(pictureAAAA.hashCode(), is(not(pictureBAAA.hashCode())));
    }
}
