package org.rootbeer.rbms.logic;

import java.util.Date;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.rootbeer.rbms.model.*;
import org.rootbeer.rbms.model.Action.Act;
import org.rootbeer.rbms.util.Database;
import static org.rootbeer.rbms.logic.ActionManagement.countStock;
import static org.rootbeer.rbms.util.Database.*;

public class ActionManagementTest {
	private static final String MICHIKO = "michiko_oba"; 
    @Before
    public void setUp() {
        getClient(Database.Bucket.ACTION).delete(MICHIKO);
        getClient(Database.Bucket.PICTURE).delete(MICHIKO);
        getClient(Database.Bucket.POST).delete(MICHIKO);
        getClient(Database.Bucket.USER).delete(MICHIKO);
    }
    
    @Test
    public void testCounting() {
    	assertThat(countStock(MICHIKO), is(0));
    	long date = System.currentTimeMillis();
    	Action buyAction = new Action(Act.BUY, MICHIKO, new Date(date));
    	Action drinkAction = new Action(Act.DRINK, MICHIKO, new Date(date));
    	
    	addAction(buyAction);
    	addAction(drinkAction);
    	assertThat(countStock(MICHIKO), is(0));
    	
    	addAction(buyAction);
    	assertThat(countStock(MICHIKO), is(1));
    	
    	addAction(drinkAction);
    	addAction(drinkAction);
    	assertThat(countStock(MICHIKO), is(-1));
    }
}
