package org.rootbeer.rbms.logic;

import com.couchbase.client.CouchbaseClient;
import com.google.gson.Gson;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.rootbeer.rbms.model.*;

import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.util.Database.Bucket;
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
     * @param newFullName 新規登録するフルネーム
     * @throws org.rootbeer.rbms.logic.CreateAccountException
     */
    public static void CreateNewAccount(String newUserId, String newFullName) throws CreateAccountException{
        User newUser = new User(newUserId, "", newFullName);
        User existUser = getUser(newUserId); 
        Pattern p = Pattern.compile("^\\w{1,16}$");
        
        // 重複するユーザIDが存在する場合、例外処理
        if(newUser.equals(existUser)){
            throw new ConflictedUserIdException();    
        }
        
        // ユーザIDが半角英数字で作成されていない, 空文字("")、null文字が含まれている, 1~16文字以内で書かれていない場合、例外処理
        try{
            Matcher m = p.matcher(newUser.getUserId());
            if(!m.find()){
                throw new InvaliedUserIdException();
            }
        }catch(NullPointerException e){
            throw new InvaliedUserIdException();
        } 
        
        addUser(newUserId, newUser);
    }
}
