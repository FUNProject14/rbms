package org.rootbeer.rbms.view;

import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import org.rootbeer.rbms.model.Action;
import org.rootbeer.rbms.util.Database;

/**
 *
 */
public final class ActionLogView extends VerticalLayout {

    Table actionLogTable = new Table("ACTION LOG");
    Label remainRootBeerLabel = new Label();
    private String userId;

    public ActionLogView(String userId) {

        setSizeFull();
        
        setUserId(userId);

        // Initialize Table
        actionLogTable.addContainerProperty("UserID", String.class, null);
        actionLogTable.addContainerProperty("Act", Action.Act.class, null);
        actionLogTable.addContainerProperty("Date", Date.class, null);

        refreshActionLogTable(userId);
        addComponent(actionLogTable);

    }

    private void setUserId(String userId) {
        this.userId = userId;
    }

    public void refreshActionLogTable(String userId) {
        actionLogTable.removeAllItems();
        for (org.rootbeer.rbms.model.Action action : Database.getActions(userId)) {
            actionLogTable.addItem(new Object[]{action.getActorUserId(), action.getAct(), action.getActedTime()}, null);
        }
        actionLogTable.sort(new Object[]{"Date", "UserID", "Act"}, new boolean[]{false, true, true});
    }

}
