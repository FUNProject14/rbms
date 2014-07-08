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

// import org.rootbeer.rbms.model.*;


@Title("Hello Window")
public class Action extends UI {
    
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = Action.class)
    public static class Servlet extends VaadinServlet {
    }
    
    @Override
    protected void init(VaadinRequest request) {
        // Create the content root layout for the UI
        VerticalLayout content = new VerticalLayout();
        setContent(content);

        // Display the greeting
        content.addComponent(new Label("Hello World!"));
        
        // 
        final Button drinkButton = new Button("Drink");
        drinkButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                org.rootbeer.rbms.model.Action action = new org.rootbeer.rbms.model.Action(
                        org.rootbeer.rbms.model.Action.Act.DRINK, "michiko2", new Date());
                try {
                    ActionManagement.addActionWithChecking(action);
                } catch (AddActionIllegalStateException e) {
                    drinkButton.setComponentError(new UserError(e.getMessage()));
                }
            }
        });
        
        content.addComponent(drinkButton);
    }
}