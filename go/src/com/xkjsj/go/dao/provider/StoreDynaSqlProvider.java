package com.xkjsj.go.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.xkjsj.go.domain.Seller;
import com.xkjsj.go.domain.Store;

public class StoreDynaSqlProvider {
public String updateStore(final Store store){
		
		return new SQL(){
			{
				UPDATE("store_inf");
				
				if(store.getName() != null){
					SET(" name = #{name} ");
				}
				if(store.getRemark()!= null){
					SET(" remark = #{remark} ");
				}

				WHERE(" id = #{id} ");
			}
		}.toString();
	}

public String insertStore(final Store store){
	
	return new SQL(){
		{
			INSERT_INTO("store_inf");
			
			if(store.getName() != null){
				VALUES("name", "#{name}");
			}
		
			if(store.getRemark()!= null){
				VALUES(" remark"," #{remark} ");
			}
			if(store.getSeller() != null && ((Seller) store.getSeller()).getId() != null){
				VALUES("seller_ID", "#{seller.id}");
			}
		}
	}.toString();
}

public String selectWhitParam(final Map<String, Object> params){
	String sql =  new SQL(){
		{
			SELECT("*");
			FROM("store_inf");
			if(params.get("good") != null){
				Store store = (Store)params.get("store");
				if(store.getName() != null && !store.getName().equals("")){
					WHERE("  name LIKE CONCAT('%',#{store.name},'%') ");
				}
				if(store.getRemark() != null && !store.getRemark().equals("")){
					WHERE(" remark LIKE CONCAT('%',#{store.remark},'%') ");
				}
				if(store.getSeller() != null && ((Seller) store.getSeller()).getId() != null && ((Seller) store.getSeller()).getId() != 0){
					WHERE(" seller_ID = #{store.seller.id} ");
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
			FROM("store_inf");
			if(params.get("good") != null){
				Store store = (Store)params.get("store");
				if(store.getName() != null && !store.getName().equals("")){
					WHERE("  name LIKE CONCAT('%',#{store.name},'%') ");
				}
				if(store.getRemark() != null && !store.getRemark().equals("")){
					WHERE(" remark LIKE CONCAT('%',#{store.remark},'%') ");
				}
				if(store.getSeller() != null && ((Seller) store.getSeller()).getId() != null && ((Seller) store.getSeller()).getId() != 0){
					WHERE(" seller_ID = #{store.seller.id} ");
				}
			}
		}
	}.toString();
}	

}
