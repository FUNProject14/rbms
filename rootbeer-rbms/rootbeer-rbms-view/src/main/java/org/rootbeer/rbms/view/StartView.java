package org.rootbeer.rbms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import org.rootbeer.rbms.view.util.LoginSession;

/**
 * スタート画面
 */
public class StartView extends VerticalLayout implements View {

    public StartView() {
        addComponent(new NavigationMenuView());
        Image logo = new Image("", new ThemeResource("img/RBMS.png"));
        addComponent(logo);
        setComponentAlignment(logo, Alignment.TOP_CENTER);
        LoginView loginView = new LoginView();
        addComponent(loginView);
        loginView.setWidth("300px");
        setComponentAlignment(loginView, Alignment.TOP_CENTER);
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (LoginSession.isLoggedIn()) {
            getUI().getNavigator().navigateTo(RootBeerManagementSystemUI.MYUSER_VIEW);
        }
    }
    
}
