package com.xkjsj.go.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import com.xkjsj.go.dao.provider.CarDynaSqlProvider;
import com.xkjsj.go.domain.Car;

public interface CarDao {
	@Select("select * from car_inf where ID = #{id}")
/*	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="ID",property="good",
			many=@Many(select="com.xkjsj.go.dao.GoodDao.selectByPage",
		fetchType=FetchType.EAGER))

	})*/
	Car selectById(Integer id);
	//@Select("select * from car_inf where customer_ID = #{car.customer.id}")
	@Select("select * from car_inf where customer_ID = #{id}")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="ID",property="goods",
			many=@Many(select="com.xkjsj.go.dao.GoodDao.selectByCarId",			
		fetchType=FetchType.EAGER))

	})
	Car selectByCustomerId(Integer id);
	
	
	
	@Delete("delete from car_inf where ID = #{id}")
	int deleteById(Integer id);
	
	@Delete("delete from car_inf where customer_ID = #{id}")
	int deleteByCustomerId(Integer id);
	//@Select("select * from store_inf where NAME = #{name}")
	//Store selectByName(String name);
	
	/*@Select("select * from car_inf ")
	List<Car> selectAllCar();
	
	@Delete("delete from car_inf where ID = #{id}")
	void deleteById(Integer id);
	
	@Delete("delete from car_inf where NAME = #{name}")
	Car deleteByName(String name);
	
	@SelectProvider(type=CarDynaSqlProvider.class,method="selectWhitParam")
	List<Car> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=CarDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@SelectProvider(type=CarDynaSqlProvider.class,method="updateCar")
	void update(Car car);*/
		

	@InsertProvider(type=CarDynaSqlProvider.class,method="insertCar")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int save(Car car);
}
