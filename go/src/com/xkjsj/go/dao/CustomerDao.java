package com.xkjsj.go.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;

import com.xkjsj.go.dao.provider.CustomerDynaSqlProvider;
import com.xkjsj.go.domain.Customer;

public interface CustomerDao {
	@Select("select * from customer_inf where username = #{username} and password = #{password}")
	Customer selectByUsernameAndPassword(
			@Param("username") String username,
			@Param("password") String password);
	
	@Select("select * from customer_inf where username = #{username} ")
	Customer findCustomerByUsername(
			@Param("username") String username);
	
	@Select("select * from customer_inf where ID = #{id}")
	Customer selectById(Integer id);
	
//	@Results({
//	@Result(id=true,column="ID",property="id"),
//	@Result(column="USERNAME",property="USERNAME"),
//	@Result(column="PASSWORD",property="PASSWORD"),
//	@Result(column="PAYNUMBER",property="PAYNUMBER"),
//	@Result(column="ADDRESS",property="ADDRESS"),
//	@Result(column="SEX",property="SEX"),
//	@Result(column="AGE",property="AGE"),
//	@Result(column="TELNUMBER",property="TELNUMBER"),
//	@Result(column="EMAIL",property="EMAIL"),
//	@Result(column="STATUS",property="STATUS"),
//	@Result(column="createdate",property="createdate"),
//	@Result(column="remark",property="remark"),
//})
//	@Result(column="order_ID",property="orders",
//	many=@Many(select="com.xkjsj.go.dao.OrderDao.selectByCustomerId",
//fetchType=FetchType.EAGER)),
//@Result(column="car_ID",property="car",
//one=@One(select="com.xkjsj.go.dao.CarDao.selectByCustomerId",
//fetchType=FetchType.EAGER)),
	@Delete("delete from customer_inf where ID = #{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=CustomerDynaSqlProvider.class,method="updateCustomer")
	int update(Customer customer);
		
	
	@SelectProvider(type=CustomerDynaSqlProvider.class,method="selectWhitParam")
	List<Customer> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=CustomerDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@InsertProvider(type=CustomerDynaSqlProvider.class,method="insertCustomer")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int save(Customer customer);
//	@Insert("insert into customer_inf (car_ID) values ('#{id}') where ID = #{id}")
//	void saveCar(Integer id);
}
