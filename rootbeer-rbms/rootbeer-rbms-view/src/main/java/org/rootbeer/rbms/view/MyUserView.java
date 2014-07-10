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
public class MyUserView extends GridLayout implements View {

    private final Panel remainRootBeerPanel;
    private final String userId;
    private final Label haveRootBeer;
    private final Button buyButton;
    private final Button drinkButton;
    private final TabSheet userTab;
    private final VerticalLayout logTab;
    private final VerticalLayout graphTab;
    private final VerticalLayout albumTab;
    Label remainRootBeerLabel = new Label();

    public MyUserView() {

        setRows(2);
        setColumns(3);

        userId = LoginSession.getLoginUserId();
        remainRootBeerPanel = new Panel();
        refreshRemainRootBeer(userId);

        haveRootBeer = new Label("残り" + remainRootBeerLabel + "本");
        remainRootBeerPanel.setContent(haveRootBeer);

        buyButton = new Button("買う");
        buyButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                org.rootbeer.rbms.model.Action action = new org.rootbeer.rbms.model.Action(
                        org.rootbeer.rbms.model.Action.Act.BUY, userId, new Date()
                );
                try {
                    ActionManagement.addActionWithChecking(action);
                    refreshRemainRootBeer("michiko2");
                } catch (AddActionIllegalStateException e) {
                    buyButton.setComponentError(new UserError(e.getMessage()));
                }
            }
        });
        remainRootBeerPanel.setContent(buyButton);
        drinkButton = new Button("飲む");
        drinkButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                org.rootbeer.rbms.model.Action action = new org.rootbeer.rbms.model.Action(
                        org.rootbeer.rbms.model.Action.Act.DRINK, userId, new Date());
                try {
                    ActionManagement.addActionWithChecking(action);
                    refreshRemainRootBeer(userId);
                } catch (AddActionIllegalStateException e) {
                    drinkButton.setComponentError(new UserError(e.getMessage()));
                }
            }
        });
        remainRootBeerPanel.setContent(drinkButton);

        addComponent(remainRootBeerPanel, 0, 1);

        userTab = new TabSheet();
        userTab.setSizeFull();
        addComponent(userTab, 1, 1);

        //log表示
        logTab = new VerticalLayout();
        ActionLogView UserAction = new ActionLogView();
        UserAction.setUserId(userId);
        logTab.addComponent(UserAction);
        logTab.setSizeFull();
        userTab.addTab(logTab, "Log");

        //Graph表示
        graphTab = new VerticalLayout();
        userTab.addTab(graphTab, "Graph");

        //Album表示
        albumTab = new VerticalLayout();
        userTab.addTab(albumTab, "Album");

    }

    private void refreshRemainRootBeer(String userId) {
        remainRootBeerLabel.setValue(String.valueOf(ActionManagement.countStock(userId)));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
