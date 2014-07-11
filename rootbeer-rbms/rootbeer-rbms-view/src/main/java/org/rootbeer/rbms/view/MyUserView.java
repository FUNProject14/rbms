package org.rootbeer.rbms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import java.util.Date;
import org.rootbeer.rbms.logic.ActionManagement;
import org.rootbeer.rbms.logic.AddActionIllegalStateException;
import org.rootbeer.rbms.view.util.LoginSession;

/**
 *
 * @author prices_over
 */
public final class MyUserView extends GridLayout implements View {

    private GridLayout remainRootBeerLayout;
    private String userId;
    private ActionLogView actionLogView;
    private Button buyButton;
    private Button drinkButton;
    private TabSheet userTab;
    private final VerticalLayout logTab;
    private VerticalLayout graphTab;
    private VerticalLayout albumTab;
    Label remainRootBeerLabel = new Label();

    public MyUserView() {
        if (!LoginSession.isLoggedIn()) {
            RootBeerManagementSystemUI.getCurrent().getNavigator().navigateTo("");
        }

        setRows(1);
        setColumns(2);

        setSizeFull();

        remainRootBeerLayout = new GridLayout(2, 3);
        remainRootBeerLayout.addComponent(remainRootBeerLabel, 0, 0, 1, 0);

        buyButton = new Button("買う");
        buyButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                buyButtonClick();
            }
        });
        remainRootBeerLayout.addComponent(buyButton, 1, 1);

        drinkButton = new Button("飲む");
        drinkButton.addClickListener(new Button.ClickListener() {
            
            @Override
            public void buttonClick(Button.ClickEvent event) {
                drinkButtonClick();
            }
        });
        remainRootBeerLayout.addComponent(drinkButton,
                1, 2);

        addComponent(remainRootBeerLayout,
                0, 0);
        remainRootBeerLayout.setSizeFull();

        userTab = new TabSheet();

        userTab.setSizeFull();

        addComponent(userTab,
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

        userId = LoginSession.getLoginUserId();
        refreshRemainRootBeer(userId);
        //Log表示部分
        actionLogView = new ActionLogView(userId);
        logTab.addComponent(actionLogView);
        logTab.setSizeFull();

    }

    private void refreshRemainRootBeer(String userId) {
        remainRootBeerLabel.setValue("残り" + String.valueOf(ActionManagement.countStock(userId)) + "本");
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
