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
         * ストック数を考慮し、ルートビアを飲む、あるいは買うという動作をデータベースに追加する
         * @param action 追加するAction
         * @throws AddActionIllegalStateException 現在の所持数(在庫)が10000本より多い時にBUYまたは0本以下でDRINKした時
         */       
        public static void addActionWithChecking(Action action) throws AddActionIllegalStateException {
                int stock = countStock(action.getActorUserId());
                switch(action.getAct()){
                    case BUY:
                        if (10000 < stock) {
                            throw new AddActionIllegalStateException("ルートビアの在庫が10000本より多い状態で購入しました");
                        }
                        addAction(action);
                        break;
                    case DRINK:
                        if(stock < 1){
                            throw new AddActionIllegalStateException("ルートビアの在庫がない状態で飲みました");
                        }
                        addAction(action);
                        break;
                }
        }
}
