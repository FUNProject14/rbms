package org.rootbeer.rbms.view.util;

/**
 * ログイン時に発生する例外を表しているクラスです。
 */
public class LoginException extends Exception {

    public LoginException() {
        super("ログインに失敗しました");
    }
    
}
