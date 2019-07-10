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
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;

import com.xkjsj.go.dao.provider.CategoryDynaSqlProvider;
import com.xkjsj.go.domain.Category;

public interface CategoryDao {
	@Select("select * from category_inf where ID = #{id}")
	@Results({
		@Result(id=true,column="ID",property="id"),
		@Result(column="NAME",property="name"),
		@Result(column="remark",property="remark"),
		@Result(column="ID",property="goods",
			many=@Many(select="com.xkjsj.go.dao.GoodDao.selectByCategoryId",
		fetchType=FetchType.EAGER))

	})
	Category selectById(Integer id);
	
	@Select("select * from category_inf where NAME = #{name}")
	Category selectByName(String name);
	
	@Select("select * from category_inf ")
	List<Category> selectAllCategory();
	
	@Delete("delete from category_inf where ID = #{id}")
	int deleteById(Integer id);
	
	@Delete("delete from category_inf where NAME = #{name}")
	int deleteByName(String name);
	
	@UpdateProvider(type=CategoryDynaSqlProvider.class,method="updateCategory")
	int update(Category category);
		

	@InsertProvider(type=CategoryDynaSqlProvider.class,method="insertCategory")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int save(Category category);
}
