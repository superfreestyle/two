package com.xkjsj.go.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.xkjsj.go.dao.provider.AdminDynaSqlProvider;
import com.xkjsj.go.domain.Admin;

public interface AdminDao {
	@Select("select * from admin_inf where username = #{username} and password = #{password}")
	Admin selectByUsernameAndPassword(
			@Param("username") String username,
			@Param("password") String password);
	
	@Select("select * from admin_inf where ID = #{id}")
	Admin selectById(Integer id);
	
	@Delete("delete from admin_inf where ID = #{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=AdminDynaSqlProvider.class,method="updateAdmin")
	@Options(useGeneratedKeys=true,keyProperty="id")
	int update(Admin admin);
}
