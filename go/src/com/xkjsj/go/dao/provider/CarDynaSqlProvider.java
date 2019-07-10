package com.xkjsj.go.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import com.xkjsj.go.domain.Car;

public class CarDynaSqlProvider {
	public String insertCar(final Car car){
		
		return new SQL(){
			{
				INSERT_INTO("car_inf");
				
					VALUES("customer_ID", "#{customer.id}");
				
			}
		}.toString();
	}
}
