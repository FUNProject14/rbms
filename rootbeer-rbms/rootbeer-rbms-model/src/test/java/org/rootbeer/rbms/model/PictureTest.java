package org.rootbeer.rbms.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

import org.junit.Test;

public class PictureTest {
    @Test
    public void PictureConstruct(){
        Date date = new Date();
        Picture picture = new Picture("path","des","auth", date);
        
        assertThat(picture.getPath(), is("path"));
        assertThat(picture.getDescription(), is("des"));
        assertThat(picture.getAuthorUserId(), is("auth"));
        assertThat(picture.getUploadedTime(), is(date));
    }
    
    @Test
    public void PictureAccess(){
        Date date = new Date();
        Picture picture = new Picture();
        
        picture.setPath("path");
        assertThat(picture.getPath(), is("path"));
        picture.setDescription("des");
        assertThat(picture.getDescription(), is("des"));
        picture.setAuthorUserId("auth");
        assertThat(picture.getAuthorUserId(), is("auth"));
        picture.setUploadedTime(date);
        assertThat(picture.getUploadedTime(), is(date));
    }
    
    @Test
    public void PictureEqual(){
        Date date = new Date();
        Picture picture1 = new Picture("path","des","auth", date);
        Picture picture2 = new Picture("path","des","auth", date);
        Picture picture3 = new Picture("notpath","des","auth", date);
        
        assertThat(picture1, is(picture2));
        assertThat(picture1, is(not(picture3)));
        assertThat(picture1.hashCode(), is(picture2.hashCode()));
        assertThat(picture1.hashCode(), is(not(picture3.hashCode())));
    }
}
