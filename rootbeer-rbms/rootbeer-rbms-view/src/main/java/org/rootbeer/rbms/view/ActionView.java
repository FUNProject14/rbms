package org.rootbeer.rbms.view;

import com.vaadin.annotations.*;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;

import java.util.Date;
import org.rootbeer.rbms.logic.ActionManagement;
import org.rootbeer.rbms.logic.AddActionIllegalStateException;
import org.rootbeer.rbms.util.Database;

// import org.rootbeer.rbms.model.*;


/**
 * 飲む、買うと言ったActionを表示するビューです。
 */
@Title("RBMS - Action log")
public class ActionView extends VerticalLayout implements View {
    
    LoginView loginView = new LoginView();
    Table actionLogTable = new Table("ACTION LOG");
    Label remainRootBeerLabel = new Label();

    public ActionView() {
        // Initialize Table
        actionLogTable.addContainerProperty("UserID", String.class, null);
        actionLogTable.addContainerProperty("Act", String.class, null);
        actionLogTable.addContainerProperty("Date", String.class, null);
        
        // refresh table
        refreshActionLogTable("michiko2");
        refreshRemainRootBeer("michiko2");
        
        // Drink rootbeer!
        final Button drinkButton = new Button("Drink");
        drinkButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                org.rootbeer.rbms.model.Action action = new org.rootbeer.rbms.model.Action(
                        org.rootbeer.rbms.model.Action.Act.DRINK, "michiko2", new Date());
                try {
                    ActionManagement.addActionWithChecking(action);
                    refreshActionLogTable("michiko2");
                    refreshRemainRootBeer("michiko2");
                } catch (AddActionIllegalStateException e) {
                    drinkButton.setComponentError(new UserError(e.getMessage()));
                }
            }
        });
        
        // Buy rootbeer!
        final Button buyButton = new Button("Buy");
        buyButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                org.rootbeer.rbms.model.Action action = new org.rootbeer.rbms.model.Action(
                        org.rootbeer.rbms.model.Action.Act.BUY, "michiko2", new Date());
                try {
                    ActionManagement.addActionWithChecking(action);
                    refreshActionLogTable("michiko2");
                    refreshRemainRootBeer("michiko2");
                } catch (AddActionIllegalStateException e) {
                    buyButton.setComponentError(new UserError(e.getMessage()));
                }
            }
        });
        
        // Display Components
        addComponent(remainRootBeerLabel);
        addComponent(buyButton);
        addComponent(drinkButton);
        addComponent(actionLogTable);
    }
    
    private void refreshActionLogTable (String userId) {
        actionLogTable.removeAllItems();
        for(org.rootbeer.rbms.model.Action action : Database.getActions(userId) ){
            actionLogTable.addItem(new Object[] {action.getActorUserId(), action.getAct().toString(), action.getActedTime().toString()}, null);
        }
        actionLogTable.sort(new Object[] {"Date", "UserID", "Act"}, new boolean[] {false, true, true});
    }
    
    private void refreshRemainRootBeer (String userId) {
        remainRootBeerLabel.setValue(String.valueOf(ActionManagement.countStock(userId)));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}