package com.xkjsj.go.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import com.xkjsj.go.domain.Admin;

public class AdminDynaSqlProvider {
	public String updateAdmin(final Admin admin){
		
		return new SQL(){
			{
				UPDATE("admin_inf");
				if(admin.getUsername() != null){
					SET(" username = #{username} ");
				}
				if(admin.getPassword()!= null){
					SET(" password = #{password} ");
				}
				if(admin.getTelnumber()!= null){
					SET(" telnumber = #{telnumber} ");
				}

				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
