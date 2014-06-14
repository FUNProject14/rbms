package org.rootbeer.rbms.logic;

public class CreateAccountException extends Exception{
    public CreateAccountException(String str){    
    super(str);
    }
}

/**
 * ユーザIDが半角英数字で作成されていない
 * ユーザIDに空文字、null文字が含まれている
 * ユーザIDが17文字以上で書かれている
 * ときに例外処理
 */
class InvaliedUserIdException extends CreateAccountException{
    public InvaliedUserIdException() {
        super("ユーザIDが無効です。使用できるのは1文字以上16文字以下の半角英数字のみです。");
    }
}

/**
 * 重複するユーザIDが存在するとき例外処理
 */
class ConflictedUserIdException extends CreateAccountException{
    public ConflictedUserIdException() {
        super("既に存在するユーザIDです。");
    }   
}