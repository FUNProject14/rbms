package org.rootbeer.rbms.view;

import com.vaadin.navigator.*;
import com.vaadin.ui.*;
import org.rootbeer.rbms.logic.ActionManagement;
import org.rootbeer.rbms.view.util.LoginSession;
import org.rootbeer.rbms.util.Database;

/**
 * ユーザページを表示するビューです
 */
public class UserView extends GridLayout implements View {

    private final GridLayout drinkLayout;
    private final Label drinkLabel;
    private final TabSheet userTab;
    private final VerticalLayout logTab;
    private final VerticalLayout graphTab;
    private final VerticalLayout albumTab;

    private final String userId;
    Label remainRootBeerLabel;

    public UserView() {

        //userId = LoginSession.getLoginUserId();
        userId = "michiko2";
        
        remainRootBeerLabel = new Label();
        
        refreshRemainRootBeer(userId);

        setRows(2);
        setColumns(3);
        setSizeFull();

        // 飲んだ本数表示部分
        drinkLayout = new GridLayout(2, 2);
        drinkLayout.setSizeFull();
        drinkLabel = new Label("今まで" + remainRootBeerLabel + "本飲みました");
        drinkLayout.addComponent(drinkLabel, 1, 1);
        addComponent(drinkLayout, 1, 0);

        //タブ表示部分
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
        Label chinko = new Label("CHINKO");
        graphTab.addComponent(chinko);
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
