package com.xkjsj.go.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import com.xkjsj.go.domain.Order;

public class OrderDynaSqlProvider {
public String insertOrder(final Order order){
		
		return new SQL(){
			{
				INSERT_INTO("order_inf");
				if(order.getCode() != null ){
					VALUES("code", "#{code}");
				}
				if(order.getAddress() != null ){
					VALUES("address", "#{address}");
				}
				
				if(order.getTotal() != null){
					VALUES("total", "#{total}");
				}
				if(order.getCreateDate() != null ){
					VALUES("createdate", "#{createdate}");
				}

				if(order.getStatus() != null ){
					VALUES("status", "#{status}");
				}
				if(order.getRemark() != null ){
					VALUES("remark", "#{remark}");
				}
				if(order.getCustomer() != null ){
					VALUES("customer_ID", "#{customer.id}");
				}	
				
			}
		}.toString();
	}

public String updatetOrder(final Order order){
	
	return new SQL(){
		{
			UPDATE ("order_inf");
//			if(order.getAddress() != null ){
//				SET("address=#{address}");
//			}
//			
//			if(order.getTotal() != null){
//				SET("total=#{total}");
//			}
//			if(order.getCreateDate() != null ){
//				SET("createdate=#{createdate}");
//			}

			if(order.getStatus() != null ){
				SET("status=#{status}");
			}
//			if(order.getRemark() != null ){
//				SET("remark=#{remark}");
//			}
//			if(order.getCustomer() != null ){
//				SET("customer_ID=#{customer.id}");
//			}	
//			
		}
	}.toString();
}
}
