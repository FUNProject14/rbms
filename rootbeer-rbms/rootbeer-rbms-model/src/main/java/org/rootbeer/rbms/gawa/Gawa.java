package org.rootbeer.rbms.gawa;

import java.text.SimpleDateFormat;
import java.util.*;

import spark.*;
import static spark.Spark.*;
import static org.rootbeer.rbms.logic.UserManagement.createNewAccount;
import static org.rootbeer.rbms.util.Database.*;

import org.rootbeer.rbms.logic.CreateAccountException;
import org.rootbeer.rbms.model.Action;
import org.rootbeer.rbms.model.Action.Act;

public class Gawa {
	public static void main(String[] args) {
	    try{
	        main_i(args);
	    } finally {
	        //close(); // うまく動かないのでコメントアウト
	    }
	}

	private static class UserActionComparator implements Comparator<Action> {

		@Override
		public int compare(Action userAction1, Action userAction2) {
			return -userAction1.getActedTime().compareTo(userAction2.getActedTime());
		}
	}

	private static String loginID = null;
	
	private static void main_i(String[] args) {

		get(new Route("/") {
			@Override
			public Object handle(Request request, Response response) {
				loginID = null;
				response.redirect("/login");
				return "";
			}
			
		});
		
		post(new Route("/addusercheck") {
			@Override
			public Object handle(Request request, Response response) {
				loginID = request.queryParams("name");
                try{
                    createNewAccount(loginID);
                } catch(CreateAccountException e){
                	;
                }
				response.redirect("/");
				return "";
			}
			
		});

		get(new Route("/adduser") {
			@Override
			public Object handle(Request request, Response response) {
				return "<html>" +
"<body>" +
	"<h1>RootBeer Management System ver.0.1 管理画面</h1>" +
	"<form method='post' action='/addusercheck'>" +
	"<p>ユーザーID：<input type='text' name='name' size='50'></p>" +
	"<p><input type='submit' value='Add User'></p>" +
"</body>" +
"</html>";
			}
		});

		post(new Route("/logincheck") {
			@Override
			public Object handle(Request request, Response response) {
				loginID = request.queryParams("name");
				response.redirect("/top");
				return "";
			}
			
		});

		get(new Route("/login") {
			@Override
			public Object handle(Request request, Response response) {
				return "<html>" +
"<body>" +
	"<h1>RootBeer Management System ver.0.1</h1>" +
	"<form method='post' action='/logincheck'>" +
	"<p>ユーザーID：<input type='text' name='name' size='50'></p>" +
	"<p>パスワード：<input type='password' name='pass' size='50'></p>" +
	"<p><input type='submit' value='Login'></p>" +
"</body>" +
"</html>";
			}
		});

		post(new Route("/logincheck") {
			@Override
			public Object handle(Request request, Response response) {
				loginID = request.queryParams("name");
				response.redirect("/top");
				return "";
			}
			
		});

		post(new Route("/buy") {
			@Override
			public Object handle(Request request, Response response) {
		    	long dateBuy = System.currentTimeMillis();
		    	Action actionBuy = new Action(Act.BUY, loginID, new Date(dateBuy));
		    	addAction(actionBuy);
		    	
				response.redirect("/top");
				return "";
			}
			
		});

		post(new Route("/drink") {
			@Override
			public Object handle(Request request, Response response) {
		    	long dateDrink = System.currentTimeMillis();
		    	Action actionDrink = new Action(Act.DRINK, loginID, new Date(dateDrink));
		    	addAction(actionDrink);
		    	
				response.redirect("/top");
				return "";
			}
			
		});

		get(new Route("/top") {
			@Override
			public Object handle(Request request, Response response) {
            	String[] userIDs = getUserIDs();
            	ArrayList<Action> userActions = new ArrayList<Action>();
            	for(String userID : userIDs){
            		Action[] actions = getActions(userID);
            		if(actions != null)
            			userActions.addAll(Arrays.asList(actions));
            	}
            	
            	Collections.sort(userActions, new UserActionComparator());
            	
        		StringBuilder b = new StringBuilder();
            	
            	for(Action userAction : userActions){
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
            		b.append("<br/>\n<hr/>\n");
            	}
				
				return "<html>" +
"<body>" +
	"<h1>RootBeer Management System ver.0.1</h1>" + b.toString() +
	"<p> LoginID: " + loginID + "</p>" +
	"<form method='post' action='/drink'>" +
	"<button type='submit'>飲む</button>" +
	"</form>" +
	"<form method='post' action='/buy'>" +
	"<button type='submit'>買う</button>" +
	"</form>" +
"</body>" +
"</html>";
			}
		});

		
	}
}
