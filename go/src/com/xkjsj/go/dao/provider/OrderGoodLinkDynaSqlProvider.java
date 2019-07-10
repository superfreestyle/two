package com.xkjsj.go.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import com.xkjsj.go.domain.OrderGoodLink;

public class OrderGoodLinkDynaSqlProvider {
public String insertOrderGoodLink(final OrderGoodLink ordergoodlink){
		
		return new SQL(){
			{
				INSERT_INTO("ordergoodlink_inf");
				if(ordergoodlink.getOrder() != null){
					VALUES("order_id", "#{order.id}");
				}
				if(ordergoodlink.getGood() != null){
					VALUES("good_id", "#{good.id}");
				}
				if(ordergoodlink.getAmount() != null){
					VALUES("AMOUNT", "#{amount}");
				}
				
				
			}
		}.toString();
	}
	
public String updateOrderGoodLink(final OrderGoodLink ordergoodlink){
		
		return new SQL(){
			{
				UPDATE ("ordergoodlink_inf");
//				if(ordergoodlink.getOrder() != null){
//					SET("order_id=#{order.id}");
//				}
//				if(ordergoodlink.getGood() != null){
//					SET("good_id=#{good.id}");
//				}
				if(ordergoodlink.getAmount() != null){
					SET("AMOUNT=#{amount}");
				}
				
				
			}
		}.toString();
	}
}
