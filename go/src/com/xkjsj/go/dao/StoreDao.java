package com.xkjsj.go.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;


import com.xkjsj.go.dao.provider.StoreDynaSqlProvider;

import com.xkjsj.go.domain.Store;

public interface StoreDao {
	@Select("select * from store_inf where ID = #{id}")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="NAME",property="name"),
		@Result(column="remark",property="remark"),
		@Result(column="ID",property="goods",
			many=@Many(select="com.xkjsj.go.dao.GoodDao.selectByStoreId",
		fetchType=FetchType.EAGER))

	})
	Store selectById(Integer id);
	
	//@Select("select * from store_inf where NAME = #{name}")
	//Store selectByName(String name);
	
	@Select("select * from store_inf ")
	List<Store> selectAllStore();
	
	@Select("select * from store_inf where seller_ID = #{id}")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="NAME",property="name"),
		@Result(column="remark",property="remark"),
		@Result(column="ID",property="goods",
			many=@Many(select="com.xkjsj.go.dao.GoodDao.selectByStoreId",
		fetchType=FetchType.EAGER))

	})
	Store selectBySellerId(Integer id);
	
	@Delete("delete from store_inf where ID = #{id}")
	int deleteById(Integer id);
	
	@Delete("delete from store_inf where NAME = #{name}")
	int deleteByName(String name);
	
	@Delete("delete from store_inf where seller_ID = #{id}")
	int deleteBySellerId(Integer id);
	
	@SelectProvider(type=StoreDynaSqlProvider.class,method="selectWhitParam")
	List<Store> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=StoreDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@UpdateProvider(type=StoreDynaSqlProvider.class,method="updateStore")
	int update(Store store);
		

	@InsertProvider(type=StoreDynaSqlProvider.class,method="insertStore")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int save(Store store);
}
