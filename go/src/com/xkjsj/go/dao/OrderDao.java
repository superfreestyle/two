package com.xkjsj.go.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;

import com.xkjsj.go.dao.provider.OrderDynaSqlProvider;
import com.xkjsj.go.domain.Good;
import com.xkjsj.go.domain.Order;

public interface OrderDao {
	@Select("select * from order_inf where ID IN(select order_ID from ordergoodlink_inf where good_ID = #{id})")
	List<Order> selectByGoodId(Integer id);
	
	
	@Select("select * from order_inf where ID = #{id}")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="CODE",property="code"),
		@Result(column="TOTAL",property="total"),
		@Result(column="STATUS",property="status"),
		@Result(column="createdate",property="createDate"),
		@Result(column="ADDRESS",property="address"),
		@Result(column="remark",property="remark"),
		@Result(column="ID",property="goods",
			many=@Many(select="com.xkjsj.go.dao.GoodDao.selectByOrderId",
		fetchType=FetchType.EAGER))

	})
	Order selectById(Integer id);
	
	@Select("select * from order_inf where CODE = #{code}")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="CODE",property="code"),
		@Result(column="TOTAL",property="total"),
		@Result(column="STATUS",property="status"),
		@Result(column="createdate",property="createDate"),
		@Result(column="ADDRESS",property="address"),
		@Result(column="remark",property="remark"),
		@Result(column="ID",property="goods",
			many=@Many(select="com.xkjsj.go.dao.GoodDao.selectByOrderId",
		fetchType=FetchType.EAGER))

	})
	Order selectByCode(Integer code);
	
	@Select("select * from order_inf where customer_ID = #{id}")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="CODE",property="code"),
		@Result(column="TOTAL",property="total"),
		@Result(column="STATUS",property="status"),
		@Result(column="createdate",property="createDate"),
		@Result(column="ADDRESS",property="address"),
		@Result(column="remark",property="remark"),
		@Result(column="ID",property="goods",
			many=@Many(select="com.xkjsj.go.dao.GoodDao.selectByOrderId",
		fetchType=FetchType.EAGER))

	})
	List<Order> selectByCustomerId(Integer id);
	
	@InsertProvider(type=OrderDynaSqlProvider.class,method="insertOrder")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int save(Order order);
	
	@UpdateProvider(type=OrderDynaSqlProvider.class,method="updatetOrder")
	int update(Order order);
	
	@Delete("delete from order_inf where ID = #{id}")
	int deleteById(Integer id);
	
	@Delete("delete from order_inf where customer_ID = #{id}")
	int deleteByCustomerId(Integer id);
}
