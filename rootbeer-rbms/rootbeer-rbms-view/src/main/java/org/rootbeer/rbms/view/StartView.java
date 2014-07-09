package org.rootbeer.rbms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * スタート画面
 */
public class StartView extends VerticalLayout implements View {

    public StartView() {
        addComponent(new Label("ルートビア管理システムへようこそ"));
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
