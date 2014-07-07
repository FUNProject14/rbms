package org.rootbeer.rbms.logic;

import org.rootbeer.rbms.model.Action;
import org.rootbeer.rbms.model.Action.Act;
import static org.rootbeer.rbms.util.Database.*;
import org.rootbeer.rbms.model.Action;
        
        
/**
 * アクション関連のクラスです。
 */
public class ActionManagement {
	public ActionManagement(){
	}
	
	/**
	 * ストックの数をカウント
	 * @param ActorId 本数を数えるアクターID
	 * @return buyStock-drinkStock 購入数-服用数
	 */
	public static int countStock(String ActorId){
		assert ActorId != null;
		int buyStock  = 0;
		int drinkStock = 0;
		Action[] Actions = getActions(ActorId);
		if(Actions != null){
			for (Action action : Actions){
				switch (action.getAct()){
				case BUY:
					buyStock++;
					break;
				case DRINK:
					drinkStock++;
					break;
				}
			}
		}
		return buyStock-drinkStock;
	}
        /**
         * ストック数を考慮しルートビアを追加する
         * @param action
         * @return 100本以下でBUYまたは１本以上でDRINKはtrueを返す　それ以外はfalseを返す
         */       
        public static boolean addActionWithChecking(Action action){
                int stock = countStock(action.getActorUserId());
                switch(action.getAct()){
                    case BUY:
                        if(stock <= 10000){
                            addAction(action);
                            return true;
                        } else {
                            return false;
                        }
                    case DRINK:
                        if(stock >= 1){
                            addAction(action);
                            return true;
                        } else {
                            return false;
                        }
                }
                return false;
        }
}
