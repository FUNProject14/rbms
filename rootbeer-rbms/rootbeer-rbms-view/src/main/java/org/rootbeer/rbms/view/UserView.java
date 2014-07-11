package org.rootbeer.rbms.view;

import com.vaadin.navigator.*;
import com.vaadin.ui.*;
import org.rootbeer.rbms.logic.ActionManagement;

/**
 * ユーザページを表示するビューです
 */
public class UserView extends GridLayout implements View {

    private final GridLayout drinkLayout;
    private final TabSheet userTab;
    private final VerticalLayout logTab;
    private final VerticalLayout graphTab;
    private final VerticalLayout albumTab;
    private ActionLogView UserAction;

    private String userId;
    Label remainRootBeerLabel;

    public UserView() {

        setColumns(2);
        setRows(1);

        // 飲んだ本数表示部分
        remainRootBeerLabel = new Label();
        drinkLayout = new GridLayout(2, 2);
        drinkLayout.addComponent(remainRootBeerLabel, 1, 0);
        drinkLayout.setSizeFull();
        addComponent(drinkLayout, 0, 0);

        // タブ表示部分
        userTab = new TabSheet();
        addComponent(userTab, 1, 0);
        userTab.setSizeFull();
        logTab = new VerticalLayout();
        logTab.setSizeFull();
        userTab.addTab(logTab, "Log");
        graphTab = new VerticalLayout();
        albumTab = new VerticalLayout();
        userTab.addTab(graphTab, "Graph");
        userTab.addTab(albumTab, "Album");

        setSizeFull();

    }

    private void refreshRemainRootBeer(String userId) {
        remainRootBeerLabel.setValue(userId + "さんは" + String.valueOf(ActionManagement.countStock(userId)) + "本持っています");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        //userId = LoginSession.getLoginUserId();
        userId = "michiko2";
        refreshRemainRootBeer(userId);

        // log表示
        UserAction = new ActionLogView(userId);
        logTab.addComponent(UserAction);

        // Graph表示
        Label chinko = new Label("CHINKO");
        graphTab.addComponent(chinko);
    }

}
