package org.rootbeer.rbms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import org.rootbeer.rbms.view.util.LoginException;
import org.rootbeer.rbms.view.util.LoginSession;

/**
 * ログインフォームを表示するビューです。
 */
public class LoginView extends GridLayout implements View {
    
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
        setSizeFull();

        Panel panel = new Panel("ログイン");
        addComponent(panel);

        // magic goes in 2 lines below
        panel.setWidth("300px");
        panel.setHeight("200px");

        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        
        FormLayout form = new FormLayout();
        form.addComponent(userIdField);
        form.addComponent(passwordField);
        form.addComponent(loginButton);
        panel.setContent(form);
        
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        userIdField.focus();
    }
    
}
