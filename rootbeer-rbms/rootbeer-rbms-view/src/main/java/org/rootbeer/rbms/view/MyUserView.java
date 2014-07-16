package org.rootbeer.rbms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.BaseTheme;
import java.util.Date;
import org.rootbeer.rbms.logic.ActionManagement;
import org.rootbeer.rbms.logic.AddActionIllegalStateException;
import org.rootbeer.rbms.view.util.LoginSession;

/**
 *
 * @author prices_over
 */
public final class MyUserView extends VerticalLayout implements View {

    private final GridLayout remainRootBeerLayout;
    private String userId;
    private ActionLogView actionLogView;
    private final Button buyButton;
    private final Button drinkButton;
    private final TabSheet userTab;
    private final VerticalLayout logTab;
    private final VerticalLayout graphTab;
    private final VerticalLayout albumTab;
    Label remainRootBeerLabel = new Label("残り本数", ContentMode.HTML);

    public MyUserView() {

        setSizeFull();

        GridLayout mainGrid = new GridLayout(2, 1);

        addComponent(mainGrid);

        mainGrid.setSizeFull();

        remainRootBeerLayout = new GridLayout(2, 3);
        remainRootBeerLayout.addComponent(remainRootBeerLabel, 0, 0, 1, 0);

        buyButton = new Button("");
        buyButton.setStyleName(BaseTheme.BUTTON_LINK);
        buyButton.setIcon(new ThemeResource("img/ichiren_kau.svg"));
        buyButton.setWidth("240px");
        buyButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                buyButtonClick();
            }
        });
        remainRootBeerLayout.addComponent(buyButton, 1, 1);

        drinkButton = new Button("");
        drinkButton.setStyleName(BaseTheme.BUTTON_LINK);
        drinkButton.setIcon(new ThemeResource("img/ichiren_nomu.svg"));
        drinkButton.setWidth("240px");
        drinkButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                drinkButtonClick();
            }
        });
        remainRootBeerLayout.addComponent(drinkButton,
                1, 2);

        mainGrid.addComponent(remainRootBeerLayout,
                0, 0);
        remainRootBeerLayout.setSizeFull();

        userTab = new TabSheet();

        userTab.setSizeFull();

        mainGrid.addComponent(userTab,
                1, 0);

        logTab = new VerticalLayout();
        graphTab = new VerticalLayout();
        albumTab = new VerticalLayout();

        userTab.addTab(logTab,
                "Log");
        userTab.addTab(graphTab,
                "Graph");
        userTab.addTab(albumTab,
                "Album");

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (!LoginSession.isLoggedIn()) {
            getUI().getNavigator().navigateTo("");
            Notification.show("ログインしてください", Notification.Type.WARNING_MESSAGE);
            return;
        }

        userId = LoginSession.getLoginUserId();
        refreshRemainRootBeer(userId);
        //Log表示部分
        actionLogView = new ActionLogView(userId);
        logTab.addComponent(actionLogView);
        logTab.setSizeFull();

    }

    private void refreshRemainRootBeer(String userId) {
        remainRootBeerLabel.setValue("<h1>残り<strong> " + String.valueOf(ActionManagement.countStock(userId)) + " </strong>本</h1>");
    }

    void buyButtonClick() {
        org.rootbeer.rbms.model.Action action = new org.rootbeer.rbms.model.Action(
                org.rootbeer.rbms.model.Action.Act.BUY, userId, new Date()
        );
        try {
            ActionManagement.addActionWithChecking(action);
            refreshRemainRootBeer(userId);
            actionLogView.refreshActionLogTable(userId);
        } catch (AddActionIllegalStateException e) {
            buyButton.setComponentError(new UserError(e.getMessage()));
        }
    }

    void drinkButtonClick() {
        org.rootbeer.rbms.model.Action action = new org.rootbeer.rbms.model.Action(
                org.rootbeer.rbms.model.Action.Act.DRINK, userId, new Date());
        try {
            ActionManagement.addActionWithChecking(action);
            refreshRemainRootBeer(userId);
            actionLogView.refreshActionLogTable(userId);
        } catch (AddActionIllegalStateException e) {
            drinkButton.setComponentError(new UserError(e.getMessage()));
        }
    }
}
