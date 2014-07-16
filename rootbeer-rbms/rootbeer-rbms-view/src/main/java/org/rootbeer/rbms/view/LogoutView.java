/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rootbeer.rbms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author kumar1
 */
public class LogoutView extends VerticalLayout implements View {

    public LogoutView() {
        addComponent(new LoginView());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }

}
