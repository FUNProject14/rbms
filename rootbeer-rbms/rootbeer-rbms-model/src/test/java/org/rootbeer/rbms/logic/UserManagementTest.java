package org.rootbeer.rbms.logic;

import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.rootbeer.rbms.logic.UserManagement.*;
import org.rootbeer.rbms.model.*;
import org.rootbeer.rbms.util.*;
import static org.rootbeer.rbms.util.Database.*;

public class UserManagementTest {
    private static final String MICHIKO = "michiko_oba"; 
    @Before
    public void setUp() {
        getClient(Database.Bucket.ACTION).delete(MICHIKO);
        getClient(Database.Bucket.PICTURE).delete(MICHIKO);
        getClient(Database.Bucket.POST).delete(MICHIKO);
        getClient(Database.Bucket.USER).delete(MICHIKO);
    }
    
    @Test
    public void testSetting() {
        User boss = new User(MICHIKO, "", "");
        String[] password = {"", "michiko456"};
        String[] fullName = {"", "†BOSS†"};
        
        addUser(MICHIKO, boss);
        
        for(int i=0; i<=3; i++) {
           setUserInfo(MICHIKO, password[i/2], fullName[i%2]);
            assertThat(getUser(MICHIKO).getPassword(), is(password[i/2]));
            assertThat(getUser(MICHIKO).getFullName(), is(fullName[i%2])); 
        }
    }
    
    @Test
    public void testCreating() throws Exception{
        String[] exceptionUserID = {"michiko_oba","みちこ"};
        String[] exceptionMessage = {"既に存在するユーザIDです。", 
                                     "ユーザIDが無効です。使用できるのは1文字以上16文字以下の半角英数字のみです。"
                                    };
        
        createNewAccount(MICHIKO);
        for(int i=0; i<=1; i++) {
            try {
                createNewAccount(exceptionUserID[i]);
                fail("例外処理に引っかかりませんでした。[ユーザID]"+exceptionUserID[i]);
            }catch(CreateAccountException e){
                assertEquals(e.getMessage(), exceptionMessage[i]);
            }
        } 
    }
}
