package org.rootbeer.rbms.view;

import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import org.rootbeer.rbms.logic.ActionManagement;
import org.rootbeer.rbms.util.Database;

/**
 *
 */
public class ActionLogView extends VerticalLayout {

    Table actionLogTable = new Table("ACTION LOG");
    Label remainRootBeerLabel = new Label();
    private String userId;

    public ActionLogView() {

        setSizeFull();

        // Initialize Table
        actionLogTable.addContainerProperty("UserID", String.class, null);
        actionLogTable.addContainerProperty("Act", String.class, null);
        actionLogTable.addContainerProperty("Date", String.class, null);

        refreshActionLogTable(userId);
        addComponent(actionLogTable);

    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private void refreshActionLogTable(String userId) {
        actionLogTable.removeAllItems();
        for (org.rootbeer.rbms.model.Action action : Database.getActions(userId)) {
            actionLogTable.addItem(new Object[]{action.getActorUserId(), action.getAct().toString(), action.getActedTime().toString()}, null);
        }
        actionLogTable.sort(new Object[]{"Date", "UserID", "Act"}, new boolean[]{false, true, true});
    }

}
