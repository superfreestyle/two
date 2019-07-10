package com.xkjsj.go.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import com.xkjsj.go.domain.CarGoodLink;

public class CarGoodLinkDynaSqlProvider {
	public String insertCarGoodLink(final CarGoodLink cargoodlink){
		
		return new SQL(){
			{
				INSERT_INTO("cargoodlink_inf");
				if(cargoodlink.getCar() != null){
					VALUES("car_id", "#{car.id}");
				}
				if(cargoodlink.getGood() != null){
					VALUES("good_id", "#{good.id}");
				}
				if(cargoodlink.getAmount() != null){
					VALUES("AMOUNT", "#{amount}");
				}
				
				
			}
		}.toString();
	}
	
public String updateCarGoodLink(final CarGoodLink cargoodlink){
		
		return new SQL(){
			{
				UPDATE ("cargoodlink_inf");
//				if(cargoodlink.getCar() != null){
//					SET("car_id=#{car.id}");
//				}
//				if(cargoodlink.getGood() != null){
//					SET("good_id=#{good.id}");
//				}
				if(cargoodlink.getAmount() != null){
					SET("AMOUNT=#{amount}");
				}
				
				
			}
		}.toString();
	}
}
