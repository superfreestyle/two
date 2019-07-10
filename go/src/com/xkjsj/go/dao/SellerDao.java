package com.xkjsj.go.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;

import com.xkjsj.go.dao.provider.SellerDynaSqlProvider;
import com.xkjsj.go.domain.Seller;

public interface SellerDao {
	@Select("select * from seller_inf where username = #{username} and password = #{password}")
	Seller selectByUsernameAndPassword(
			@Param("username") String username,
			@Param("password") String password);
	
	@Select("select * from seller_inf where ID = #{id}")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="USERNAME",property="username"),
		@Result(column="PASSWORD",property="password"),
		@Result(column="PAYNUMBER",property="paynumber"),
		@Result(column="ADDRESS",property="address"),
		@Result(column="SEX",property="sex"),
		@Result(column="AGE",property="age"),
		@Result(column="TELNUMBER",property="telnumber"),
		@Result(column="EMAIL",property="email"),
		@Result(column="STATUS",property="status"),
		@Result(column="createdate",property="createDate"),
		@Result(column="remark",property="remark"),
		@Result(column="store_ID",property="store",
			one=@One(select="com.xkjsj.go.dao.StoreDao.selectById",
		fetchType=FetchType.EAGER)),

	})
	Seller selectById(Integer id);
	

	@Delete("delete from seller_inf where ID = #{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=SellerDynaSqlProvider.class,method="updateSeller")
	int update(Seller seller);
		
	
	@SelectProvider(type=SellerDynaSqlProvider.class,method="selectWhitParam")
	List<Seller> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=SellerDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@InsertProvider(type=SellerDynaSqlProvider.class,method="insertSeller")
	@Options(useGeneratedKeys=true,keyProperty="id")  
	int save(Seller seller);
}
