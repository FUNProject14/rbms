package org.rootbeer.rbms.cli;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.rootbeer.rbms.logic.CreateAccountException;

import static org.rootbeer.rbms.logic.UserManagement.createNewAccount;

import org.rootbeer.rbms.model.*;
import org.rootbeer.rbms.model.Action.Act;

import static org.rootbeer.rbms.util.Database.*;

public class Main {
    public static void main(String[] args) throws CreateAccountException{
        try{
            main_i(args);
        } finally {
            close();
        }
    }
    
    private static class userActionComparator implements Comparator<Action> {

		@Override
		public int compare(Action userAction1, Action userAction2) {
			return -userAction1.getActedTime().compareTo(userAction2.getActedTime());
		}
	}
    
    public static void main_i(String[] args) throws CreateAccountException{
        if(args.length == 0){
            System.out.println("引数を指定して下さい");
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
            	String[] userIDs = getUserIDs();
            	ArrayList<Action> userActions = new ArrayList<Action>();
            	for(String userID : userIDs){
            		Action[] actions = getActions(userID);
            		if(actions != null)
            			userActions.addAll(Arrays.asList(actions));
            	}
            	
            	Collections.sort(userActions, new Main.userActionComparator());
            	
            	for(Action userAction : userActions){
            		StringBuilder b = new StringBuilder();
            		b.append(userAction.getActorUserId() + "が");
            		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日HH時mm分ss秒");
            		b.append(sdf1.format(userAction.getActedTime()) + "に");
            		switch(userAction.getAct()){
            		case BUY :
            			b.append("買いました");
            			break;
            		case DRINK :
            			b.append("飲みました");
            			break;
            		}
            		System.out.println(b);
            	}
            	break;
        }
    }
}
