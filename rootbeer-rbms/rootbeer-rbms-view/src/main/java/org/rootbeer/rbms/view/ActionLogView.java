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

    private final Table actionLogTable;
    private final Label remainRootBeerLabel;
    private final String userId;

    public ActionLogView(String userId) {

        setSizeFull();
        
        this.userId = userId;

        // Initialize Table
        actionLogTable = new Table("ACTION LOG");
        actionLogTable.addContainerProperty("UserID", String.class, null);
        actionLogTable.addContainerProperty("Act", Action.Act.class, null);
        actionLogTable.addContainerProperty("Date", Date.class, null);

        refreshActionLogTable(userId);
        addComponent(actionLogTable);

        remainRootBeerLabel = new Label();
    }

    public void refreshActionLogTable(String userId) {
        actionLogTable.removeAllItems();
        Action[] actions = Database.getActions(userId);
        if (actions == null) {
            return;
        }
        for (org.rootbeer.rbms.model.Action action : actions) {
            actionLogTable.addItem(new Object[]{action.getActorUserId(), action.getAct(), action.getActedTime()}, null);
        }
        actionLogTable.sort(new Object[]{"Date", "UserID", "Act"}, new boolean[]{false, true, true});
    }

}
