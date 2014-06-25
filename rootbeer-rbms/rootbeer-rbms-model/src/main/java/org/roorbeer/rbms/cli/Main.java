package org.roorbeer.rbms.cli;

import static org.rootbeer.rbms.util.Database.*;
import org.rootbeer.rbms.model.*;
import org.rootbeer.rbms.model.Action.Act;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        if(args.length == 0){
            System.out.printf("引数を指定して下さい");
            System.exit(0);
        }
        
        switch (args[0]){
            case "adduser":  
            case "buy":
            	long dateBuy = System.currentTimeMillis();
            	Action actionBuy = new Action(Act.BUY, args[1], new Date(dateBuy));
            	addAction(actionBuy);
            case "drink":
            	long dateDrink = System.currentTimeMillis();
            	Action action = new Action(Act.DRINK, args[1], new Date(dateDrink));
            	addAction(action);
            case "getposts":
        }
    }
}
