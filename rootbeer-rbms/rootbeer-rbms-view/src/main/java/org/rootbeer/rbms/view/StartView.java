package org.rootbeer.rbms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * スタート画面
 */
public class StartView extends VerticalLayout implements View {

    public StartView() {
        addComponent(new Label("<h1>ルートビア管理システム</h1>", ContentMode.HTML));
        addComponent(new LoginView());
        
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
