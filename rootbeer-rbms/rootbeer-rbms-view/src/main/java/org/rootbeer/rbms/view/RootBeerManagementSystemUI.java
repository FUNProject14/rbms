package org.rootbeer.rbms.view;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import javax.servlet.annotation.WebServlet;

/**
 * ルートビア管理システムのUIを表すクラス
 */
public class RootBeerManagementSystemUI extends UI {
    
    private Navigator navigator;
    protected static final String LOGIN_VIEW = "login";
    protected static final String ACTION_VIEW = "action";
    protected static final String USER_VIEW = "userview";

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = RootBeerManagementSystemUI.class)
    public static class Servlet extends VaadinServlet {
    }
    
    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("ルートビア管理システム");
        
        // ビューを操作するナビゲーターを作成
        navigator = new Navigator(this, this);
        
        // ビューを作成し登録
        navigator.addView("", new StartView());
        navigator.addView(LOGIN_VIEW, new LoginView());
        navigator.addView(USER_VIEW,  new UserView());
        navigator.addView(ACTION_VIEW, new ActionView());
    }
    
}
