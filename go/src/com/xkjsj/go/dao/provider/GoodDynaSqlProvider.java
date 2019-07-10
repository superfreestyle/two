package com.xkjsj.go.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.xkjsj.go.domain.Category;
import com.xkjsj.go.domain.Good;
import com.xkjsj.go.domain.Store;
public class GoodDynaSqlProvider {
public String updateGood(final Good good){
		
		return new SQL(){
			{
				UPDATE("good_inf");
//				if(good.getStore() != null){
//					SET("store_id=#{store.id}");
//				}
				if(good.getCategory() != null){
					SET("category_id=#{category.id}");
				}
				if(good.getName() != null){
					SET(" name = #{name} ");
				}
				if(good.getImage() != null){
					SET(" image = #{image} ");
				}
				if(good.getPrice() != null){
					SET(" price = #{price} ");
				}
				if(good.getRemark()!= null){
					SET(" remark = #{remark} ");
				}

				WHERE(" id = #{id} ");
			}
		}.toString();
	}

public String insertGood(final Good good){
	
	return new SQL(){
		{
			INSERT_INTO("good_inf");
			if(good.getStore() != null&& ((Store) good.getStore()).getId() != null){
				VALUES("store_id", "#{store.id}");
			}
			if(good.getCategory() != null && ((Category) good.getCategory()).getId() != null){
				VALUES("category_id", "#{category.id}");
			}
			if(good.getName() != null){
				VALUES("name", "#{name}");
			}
			if(good.getImage() != null){
				VALUES(" image", " #{image} ");
			}
			if(good.getPrice() != null){
				VALUES(" price", " #{price} ");
			}
			if(good.getRemark()!= null){
				VALUES(" remark"," #{remark} ");
			}
			
		}
	}.toString();
}

public String selectWhitParam(final Map<String, Object> params){
	String sql =  new SQL(){
		{
			SELECT("*");
			FROM("good_inf");
			if(params.get("good") != null){
				Good good = (Good)params.get("good");
				if(good.getName() != null && !good.getName().equals("")){
					WHERE("  name LIKE CONCAT('%',#{good.name},'%') ");
				}
				if(good.getRemark() != null && !good.getRemark().equals("")){
					WHERE(" remark LIKE CONCAT('%',#{good.remark},'%') ");
				}
				if(good.getStore() != null && ((Store) good.getStore()).getId() != null && ((Store) good.getStore()).getId() != 0){
					WHERE(" store_ID = #{good.store.id} ");
				}
				if(good.getCategory() != null && ((Category) good.getCategory()).getId() != null && ((Category) good.getCategory()).getId() != 0){
					WHERE(" category_ID = #{good.category.id} ");
				}
			}
		}
	}.toString();
	
	if(params.get("pageModel") != null){
		sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
	}
	
	return sql;
}	

public String count(final Map<String, Object> params){
	return new SQL(){
		{
			SELECT("count(*)");
			FROM("good_inf");
			if(params.get("good") != null){
				Good good = (Good)params.get("good");
				if(good.getName() != null && !good.getName().equals("")){
					WHERE("  name LIKE CONCAT('%',#{good.name},'%') ");
				}
				if(good.getRemark() != null && !good.getRemark().equals("")){
					WHERE(" remark LIKE CONCAT('%',#{good.remark},'%') ");
				}
				if(good.getStore() != null && ((Store) good.getStore()).getId() != null && ((Store) good.getStore()).getId() != 0){
					WHERE(" store_ID = #{good.store.id} ");
				}
				if(good.getCategory() != null && ((Category) good.getCategory()).getId() != null && ((Category) good.getCategory()).getId() != 0){
					WHERE(" category_ID = #{good.category.id} ");
				}
			}
		}
	}.toString();
}	

}
