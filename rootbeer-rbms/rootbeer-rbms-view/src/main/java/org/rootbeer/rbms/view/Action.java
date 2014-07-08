package org.rootbeer.rbms.view;

import com.vaadin.annotations.*;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.Date;
import javax.servlet.annotation.WebServlet;
import org.rootbeer.rbms.logic.ActionManagement;
import org.rootbeer.rbms.logic.AddActionIllegalStateException;
import org.rootbeer.rbms.util.Database;

// import org.rootbeer.rbms.model.*;


@Title("Hello Window")
public class Action extends UI {
    
    Table actionLogTable = new Table("ACTION LOG");
    Label remainRootBeerLabel = new Label();


    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = Action.class)
    public static class Servlet extends VaadinServlet {
    }
    
    @Override
    protected void init(VaadinRequest request) {
        
        // Initialize Table
        actionLogTable.addContainerProperty("UserID", String.class, null);
        actionLogTable.addContainerProperty("Act", String.class, null);
        actionLogTable.addContainerProperty("Date", String.class, null);
        
        // refresh table
        refreshActionLogTable("michiko2");
        refreshRemainRootBeer("michiko2");
        
        // Create the content root layout for the UI
        VerticalLayout content = new VerticalLayout();
        setContent(content);

        
        
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
        content.addComponent(remainRootBeerLabel);
        content.addComponent(buyButton);
        content.addComponent(drinkButton);
        content.addComponent(actionLogTable);
    }
    
    
    
    void refreshActionLogTable (String userId) {
        actionLogTable.removeAllItems();
        for(org.rootbeer.rbms.model.Action action : Database.getActions(userId) ){
            actionLogTable.addItem(new Object[] {action.getActorUserId(), action.getAct().toString(), action.getActedTime().toString()}, null);
        }
    }
    
    void refreshRemainRootBeer (String userId) {
        remainRootBeerLabel.setValue(String.valueOf(ActionManagement.countStock(userId)));
    }
}