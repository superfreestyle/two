package com.xkjsj.go.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import com.xkjsj.go.domain.Category;
import com.xkjsj.go.domain.Seller;

public class CategoryDynaSqlProvider {
public String updateCategory(final Category category){
		
		return new SQL(){
			{
				UPDATE("category_inf");
				if(category.getName() != null){
					SET(" name = #{name} ");
				}
				if(category.getRemark()!= null){
					SET(" remark = #{remark} ");
				}

				WHERE(" id = #{id} ");
			}
		}.toString();
	}
public String insertCategory(final Category category){
	
	return new SQL(){
		{
			INSERT_INTO("category_inf");
			if(category.getName() != null){
				VALUES("name", "#{name}");
			}
			if(category.getRemark()!= null){
				VALUES(" remark"," #{remark} ");
			}
			
		}
	}.toString();
}
}
