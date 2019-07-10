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
import com.xkjsj.go.dao.provider.GoodDynaSqlProvider;


import com.xkjsj.go.domain.Good;

public interface GoodDao {
	@Select("select * from good_inf where ID IN(select good_ID from cargoodlink_inf where car_ID = #{id})")	
	List<Good> selectByCarId(Integer id);
	
	
	
	@Select("select * from good_inf where ID IN(select good_ID from ordergoodlink_inf where order_ID = #{id})")
	List<Good> selectByOrderId(Integer id);
	
	@Select(" select * from good_inf where store_ID = #{id} ")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="NAME",property="name"),
		@Result(column="image",property="image"),
		@Result(column="PRICE",property="price"),
		@Result(column="remark",property="remark"),
		@Result(column="store_ID",property="store",
			one=@One(select="com.xkjsj.go.dao.StoreDao.selectById",
		fetchType=FetchType.EAGER)),
		@Result(column="category_ID",property="category",
			one=@One(select="com.xkjsj.go.dao.CategoryDao.selectById",
		fetchType=FetchType.EAGER)),
		@Result(column="ID",property="orders",
			many=@Many(select="com.xkjsj.go.dao.OrderDao.selectByGoodId",
		fetchType=FetchType.EAGER))
	})
	List<Good> selectByStoreId(Integer id);
	
	@Select(" select * from good_inf where category_ID = #{id} ")
	List<Good> selectByCategoryId(Integer id);
	
	@Delete(" delete from good_inf where store_ID = #{id} ")
	int deleteByStoreId(Integer id);

	@Delete(" delete from good_inf where category_ID = #{id} ")
	int deleteByCategoryId(Integer id);
	
	@Delete(" delete from good_inf where id = #{id} ")
	int deleteById(Integer id);
	
	@Select("select * from good_inf where ID = #{id}")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="NAME",property="name"),
		@Result(column="image",property="image"),
		@Result(column="PRICE",property="price"),
		@Result(column="remark",property="remark"),
		@Result(column="store_ID",property="store",
			one=@One(select="com.xkjsj.go.dao.StoreDao.selectById",
		fetchType=FetchType.EAGER)),
		@Result(column="category_ID",property="category",
			one=@One(select="com.xkjsj.go.dao.CategoryDao.selectById",
		fetchType=FetchType.EAGER))
	})
	Good selectById(Integer id);//���һselect * from good_inf where ID = id and category_ID=category.id
								//���һselect * from good_inf where ID = id and store_ID=store.id
	
	//@Select("select * from good_inf where category_ID = #{category��id}")
	
	@InsertProvider(type=GoodDynaSqlProvider.class,method="insertGood")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int save(Good good);
	
	@UpdateProvider(type=GoodDynaSqlProvider.class,method="updateGood")
	int update(Good good);
	
	@SelectProvider(type=GoodDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	
	@SelectProvider(type=GoodDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="NAME",property="name"),
		@Result(column="image",property="image"),
		@Result(column="PRICE",property="price"),
		@Result(column="remark",property="remark"),
		@Result(column="store_ID",property="store",
			one=@One(select="com.xkjsj.go.dao.StoreDao.selectById",
		fetchType=FetchType.EAGER)),
		@Result(column="category_ID",property="category",
			one=@One(select="com.xkjsj.go.dao.CategoryDao.selectById",
		fetchType=FetchType.EAGER))
	})
	List<Good> selectByPage(Map<String, Object> params);
}
