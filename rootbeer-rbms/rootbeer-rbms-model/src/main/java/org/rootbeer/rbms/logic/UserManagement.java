package org.rootbeer.rbms.logic;

import org.rootbeer.rbms.model.*;

import static org.rootbeer.rbms.util.Database.*;
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
        if(User.isValidUserId(newUserId)){
            throw new CreateAccountException.InvalidUserId();
        }
        
        User newUser = new User(newUserId, "", "");   
        addUser(newUserId, newUser);
    }
}
