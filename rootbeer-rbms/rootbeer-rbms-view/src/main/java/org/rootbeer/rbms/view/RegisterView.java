package org.rootbeer.rbms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rootbeer.rbms.logic.CreateAccountException;
import org.rootbeer.rbms.logic.UserManagement;
import org.rootbeer.rbms.model.User;
import org.rootbeer.rbms.view.util.LoginException;
import org.rootbeer.rbms.view.util.LoginSession;

/**
 * アカウント新規登録ビューです。
 */
public class RegisterView extends VerticalLayout implements View {

    private final TextField userIdField;
    private final PasswordField passwordField;
    private final TextField fullNameField;
    
    public RegisterView() {
        // 入力フォーム
        userIdField = new TextField("ユーザーID");
        userIdField.setRequired(true);
        passwordField = new PasswordField("パスワード");
        passwordField.setRequired(true);
        fullNameField = new TextField("フルネーム");
        fullNameField.setRequired(true);
        // 登録ボタン
        final Button registerButton = new Button("登録");
        registerButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    register(new User(userIdField.getValue(),
                            passwordField.getValue(),
                            fullNameField.getValue()));
                    try {
                        LoginSession.login(userIdField.getValue(), passwordField.getValue());
                    } catch (LoginException ex) {
                        Logger.getLogger(RegisterView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    getUI().getNavigator().navigateTo(RootBeerManagementSystemUI.MYUSER_VIEW);
                } catch (CreateAccountException ex) {
                    registerButton.setComponentError(new UserError(ex.getMessage()));
                }
            }
        });
        
        FormLayout form = new FormLayout();
        form.setCaption("新規登録");
        form.addComponent(userIdField);
        form.addComponent(passwordField);
        form.addComponent(fullNameField);
        form.addComponent(registerButton);
        
        this.addComponent(form);
        
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        userIdField.focus();
    }
    
    /**
     * ユーザーを登録する
     * @param user 
     */
    private void register(final User user) throws CreateAccountException {
        UserManagement.createNewAccount(user.getUserId());
        UserManagement.setUserInfo(user.getUserId(), user.getPassword(), user.getFullName());
    }
}
