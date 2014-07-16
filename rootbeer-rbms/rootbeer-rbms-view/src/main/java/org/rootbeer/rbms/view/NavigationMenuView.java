package org.rootbeer.rbms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import org.rootbeer.rbms.model.User;
import org.rootbeer.rbms.view.util.LoginSession;

/**
 * グローバルなナビゲーション・メニューを表示するビューです。
 */
public class NavigationMenuView extends VerticalLayout implements View {

    private final MenuBar menubar;
    private MenuBar.MenuItem userMenuItem;

    public NavigationMenuView() {
        menubar = new MenuBar();

        this.addComponent(menubar);
        menubar.addItem("RBMS", null);
        menubar.setWidth("100%");
        this.setSizeFull();
        userMenuItem = menubar.addItem("", null);
        userMenuItem.addItem("ログアウト", new MenuBar.Command() {

            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                LoginSession.logout();
                getUI().getPage().setLocation("");
            }
        });
        refresh();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        refresh();
    }

    private void refresh() {
        if (LoginSession.isLoggedIn()) {
            User user = LoginSession.getLoginUser();
            userMenuItem.setText(user.getUserId());
        }
    }
}
