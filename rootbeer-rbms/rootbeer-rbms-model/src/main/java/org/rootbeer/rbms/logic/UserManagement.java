package org.rootbeer.rbms.logic;

import com.couchbase.client.CouchbaseClient;
import com.google.gson.Gson;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.model.*;
import org.rootbeer.rbms.util.Database.Bucket;
import org.rootbeer.rbms.util.ModelUtil;

/**
 * ユーザ関連のクラスです。
 */
public final class UserManagement {
    private UserManagement(){
    }
    
    /**
     * 新規アカウントの作成をします。
     * @param newUserId 新規登録するユーザID
     * @param newPassword 新規登録するパスワード
     * @param newFullName 新規登録するフルネーム
     */
    public static void CreateNewAccount(String newUserId, String newPassword, String newFullName){
        getClient(Bucket.USER);
        User newUser = new User(newUserId, newPassword, newFullName);
        CouchbaseClient client = getClient(Bucket.USER);
        Gson userGson = ModelUtil.GSON;
        
        client.add(newUserId, userGson.toJson(newUser));
    }
}
