package org.roorbeer.rbms.cli;

import java.util.Date;
import org.rootbeer.rbms.logic.CreateAccountException;
import static org.rootbeer.rbms.logic.UserManagement.createNewAccount;
import org.rootbeer.rbms.model.*;
import org.rootbeer.rbms.model.Action.Act;
import static org.rootbeer.rbms.util.Database.*;

public class Main {
    public static void main(String[] args) throws CreateAccountException{
        if(args.length == 0){
            System.out.printf("引数を指定して下さい");
            System.exit(0);
        }
        
        switch (args[0]){
            case "adduser":  
                createNewAccount(args[1]);
                break;
            case "buy":
            	long dateBuy = System.currentTimeMillis();
            	Action actionBuy = new Action(Act.BUY, args[1], new Date(dateBuy));
            	addAction(actionBuy);
            	break;
            case "drink":
            	long dateDrink = System.currentTimeMillis();
            	Action actionDrink = new Action(Act.DRINK, args[1], new Date(dateDrink));
            	addAction(actionDrink);
            	break;
            case "getposts":
        }
    }
}
