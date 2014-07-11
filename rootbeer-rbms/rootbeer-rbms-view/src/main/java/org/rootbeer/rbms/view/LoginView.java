package org.rootbeer.rbms.view;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import org.rootbeer.rbms.view.util.LoginException;
import org.rootbeer.rbms.view.util.LoginSession;

/**
 * ログインフォームを表示するビューです。
 */
public final class LoginView extends GridLayout implements View {

    private TextField userIdField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;

    public LoginView() {
        if (LoginSession.isLoggedIn()) {
            final Label label = new Label(LoginSession.getLoginUserId() + " としてログイン中");
            final Button logoutButton = new Button("ログアウト", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    LoginSession.logout();
                    getUI().getPage().setLocation("");
                }
            });
            addComponent(label);
            addComponent(logoutButton);
            return;
        }
        // ユーザーID入力領域を設定
        userIdField = new TextField("ユーザーID:");
        userIdField.setRequired(true);

        // パスワード入力領域を設定
        passwordField = new PasswordField("パスワード:");
        passwordField.setRequired(true);

        // ログインボタンを作る
        loginButton = new Button("ログイン");
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginButton.addClickListener(new Button.ClickListener() {
            
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    LoginSession.login(userIdField.getValue(), passwordField.getValue());
                } catch (LoginException ex) {
                    loginButton.setComponentError(new UserError(ex.getMessage()));
                }
                if (LoginSession.isLoggedIn()) {
                    getUI().getNavigator().navigateTo(RootBeerManagementSystemUI.MYUSER_VIEW);
                }
            }
        });
        
        // 新規登録ボタンを作る
        registerButton = new Button("新規登録");
        registerButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().navigateTo(RootBeerManagementSystemUI.REGISTER_VIEW);
            }
        });

        // レイアウトを設定
        setSizeFull();

        FormLayout form = new FormLayout();
        form.addComponent(userIdField);
        form.addComponent(passwordField);
        form.addComponent(loginButton);
        
        GridLayout grid = new GridLayout(2, 2);
        grid.addComponent(registerButton, 1, 0);
        grid.addComponent(form, 0, 1, 1, 1);
        
        Panel panel = new Panel("ログイン");
        panel.setContent(grid);

        addComponent(panel);
        
        panel.setWidth("300px");
        panel.setHeight("200px");

        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        userIdField.focus();
    }

}
