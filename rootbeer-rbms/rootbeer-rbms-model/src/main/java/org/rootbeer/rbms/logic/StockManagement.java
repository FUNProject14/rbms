package org.rootbeer.rbms.logic;

import org.rootbeer.rbms.model.Action;
import org.rootbeer.rbms.model.Action.Act;
import static org.rootbeer.rbms.util.Database.*;
/**
 * アクション関連のクラスです。
 */
public class StockManagement {
	public StockManagement(){
	}
	
	/**
	 * ストックの数をカウント
	 * @param ActorId 本数を数えるアクターID
	 * @return buyStock-drinkStock 買った数-飲んだ数
	 */
	public static int countStock(String ActorId){
		assert ActorId != null;
		int buyStock  = 0;
		int drinkStock = 0;
		
		Action[] Actions = getActions(ActorId);
		for(int i=0; i < Actions.length; ++i){
			if(Actions[i].getAct() == Act.BUY){
				buyStock ++;
			}else if(Actions[i].getAct() == Act.DRINK){
				drinkStock ++;
			}
		}
		return buyStock-drinkStock;
	}
}
