package org.rootbeer.rbms.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.rootbeer.rbms.model.User;

public class UserTest {

    @Test
    public void testConstuctor() {
        User user = new User("kumar8600", "unkoderu", "Yuya Kumagai");
        assertThat(user.getUserId(), is("kumar8600"));
        assertThat(user.getPassword(), is("unkoderu"));
        assertThat(user.getFullName(), is("Yuya Kumagai"));
    }

    @Test
    public void testAccesssor() {
        User user = new User();
        user.setUserId("kumar8600");

        assertThat(user.getUserId(), is("kumar8600"));
        user.setPassword("unkoderu");
        assertThat(user.getPassword(), is("unkoderu"));
        user.setFullName("Yuya Kumagai");
        assertThat(user.getFullName(), is("Yuya Kumagai"));
    }

    @Test
    public void testEquality() {
        User userAAA = new User("kumar8600", "unkoderu", "Yuya Kumagai");
        User userAAA2 = new User("kumar8600", "unkoderu", "Yuya Kumagai");
        User userBAA = new User("kumar17200", "unkoderu", "Yuya Kumagai");
        User userABA = new User("kumar8600", "unkodenai", "Yuya Kumagai");
        User userAAB = new User("kumar8600", "unkoderu", "Hirobumi Ito");
        User userABB = new User("kumar8600", "unkodenai", "Hirobumi Ito");
        User userBBB = new User("kumar17200", "unkodenai", "Hirobumi Ito");
        User userBBA = new User("kumar17200", "unkodenai", "Yuya Kumagai");

        assertThat(userAAA, is(userAAA2));
        assertThat(userAAA, is(not(userBAA)));
        assertThat(userAAA, is(not(userABA)));
        assertThat(userAAA, is(not(userAAB)));
        assertThat(userAAA, is(not(userABB)));
        assertThat(userAAA, is(not(userBBB)));
        assertThat(userAAA, is(not(userBBA)));

        assertThat(userAAA.hashCode(), is(userAAA2.hashCode()));
    }

    @Test
    public void testValidity() {
        String[] trueData = {"testname", "testName", "a", "1", "abcdefghijklmnop"
                            , "1234567890123456", "abcdefghij123456", "test_name"
                            };
        String[] falseData = {"Ｔestname", "testname１", "テストネーム", "abcdefghijklmnopq", "12345678901234567"
                            , "良さ👏", "~", "test1&test2", " testname", "test name"
                            , "testname ", "", "test-name"
                             };
        
        for(String trueUserId: trueData){
            assertThat(User.isValidUserId(trueUserId), is(true));
        }
        for(String falseUserId: falseData){
        	// System.err.println(falseUserId);
            assertThat(User.isValidUserId(falseUserId), is(false));
        }
    }
}
