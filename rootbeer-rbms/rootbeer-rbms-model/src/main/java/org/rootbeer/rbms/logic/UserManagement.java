package org.rootbeer.rbms.logic;

import com.couchbase.client.CouchbaseClient;
import org.rootbeer.rbms.model.*;

import static org.rootbeer.rbms.util.Database.*;
import org.rootbeer.rbms.util.ModelUtil;
/**
 * ユーザ関連のクラスです。
 */
public final class UserManagement {
    private UserManagement(){
    }
    
    /**
     * 新規アカウントの作成（パスワード未設定）
     * @param newUserId 新規登録するユーザID
     * @throws org.rootbeer.rbms.logic.CreateAccountException
     */
    public static void createNewAccount(String newUserId) throws CreateAccountException{
        assert newUserId != null;
             
        // 重複するユーザIDが存在する場合、例外処理
        User existUser = getUser(newUserId);
        if(existUser != null){
            throw new ConflictedUserIdException();    
        }
        
        // ユーザIDが半角英数字で作成されていない, 空文字("")、null文字が含まれている, 1~16文字以内で書かれていない場合、例外処理   
        if(!User.isValidUserId(newUserId)){
            throw new CreateAccountException.InvalidUserId();
        }
        
        User newUser = new User(newUserId, "", "");   
        addUser(newUserId, newUser);
    }
    
    /**
     * パスワード・フルネームを設定・変更します
     * @param userID
     * @param newPassword 
     * @param newFullName 
     */
    public static void setUserInfo(String userID, String newPassword, String newFullName){
        CouchbaseClient client = getClient(Bucket.USER);
        User user = getUser(userID);
        if(newPassword != null){
            user.setPassword(newPassword);
        }
        if(newFullName != null){
            user.setFullName(newFullName);
        }
        
        client.replace(userID, ModelUtil.GSON.toJson(user));
    }
}
