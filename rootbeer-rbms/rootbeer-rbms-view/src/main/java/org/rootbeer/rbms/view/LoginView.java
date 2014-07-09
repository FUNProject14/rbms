package org.rootbeer.rbms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import org.rootbeer.rbms.view.util.LoginException;
import org.rootbeer.rbms.view.util.LoginSession;

/**
 * ログインフォームを表示するビューです。
 */
public class LoginView extends FormLayout implements View {
    
    private final TextField userIdField;
    private final PasswordField passwordField;
    private final Button loginButton;
    
    public LoginView() {
        // ユーザーID入力領域を設定
        userIdField = new TextField("ユーザーID:");
        userIdField.setRequired(true);
        
        // パスワード入力領域を設定
        passwordField = new PasswordField("パスワード:");
        passwordField.setRequired(true);
        
        // ログインボタンを作る
        loginButton = new Button("ログイン");
        loginButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    LoginSession.login(userIdField.getValue(), passwordField.getValue());
                } catch (LoginException ex) {
                    loginButton.setComponentError(new UserError(ex.getMessage()));
                }
            }
        });
        
        // レイアウトを設定
        setCaption("アプリケーションの機能にアクセスするために、ログインして下さい。");
        addComponent(userIdField);
        addComponent(passwordField);
        addComponent(loginButton);
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        userIdField.focus();
    }
    
}
