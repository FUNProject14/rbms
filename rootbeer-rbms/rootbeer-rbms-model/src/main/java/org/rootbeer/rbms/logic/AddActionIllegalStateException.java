package org.rootbeer.rbms.logic;

public class AddActionIllegalStateException extends Exception {
    public AddActionIllegalStateException() {
        super("不正な状態でのActionの追加です");
    }
    
    public AddActionIllegalStateException(String message) {
        super(message);
    }
}
