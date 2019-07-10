package com.xkjsj.go.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.xkjsj.go.domain.Seller;

public class SellerDynaSqlProvider {
	public String selectWhitParam(final Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("seller_inf");
				if(params.get("seller") != null){
					Seller seller = (Seller)params.get("seller");
					if(seller.getUsername() != null && !seller.getUsername().equals("")){
						WHERE("  username LIKE CONCAT ('%',#{seller.username},'%') ");
					}
					if(seller.getStatus() != null && !seller.getStatus().equals("")){
						WHERE(" status LIKE CONCAT ('%',#{seller.status},'%') ");
					}
					if(seller.getSex() != null && !seller.getSex().equals("")){
						WHERE(" sex LIKE CONCAT ('%',#{seller.sex},'%') ");
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
				FROM("seller_inf");
				if(params.get("seller") != null){
					Seller seller = (Seller)params.get("seller");
					if(seller.getUsername() != null && !seller.getUsername().equals("")){
						WHERE("  username LIKE CONCAT ('%',#{seller.username},'%') ");
					}
					if(seller.getStatus() != null && !seller.getStatus().equals("")){
						WHERE(" status LIKE CONCAT ('%',#{seller.status},'%') ");
					}
					if(seller.getSex() != null && !seller.getSex().equals("")){
						WHERE(" sex LIKE CONCAT ('%',#{seller.sex},'%') ");
					}
				}
			}
		}.toString();
	}	
	

	public String insertSeller(final Seller seller){
		
		return new SQL(){
			{
				INSERT_INTO("seller_inf");
				if(seller.getUsername() != null && !seller.getUsername().equals("")){
					VALUES("username", "#{username}");
				}
				if(seller.getPassword() != null && !seller.getPassword().equals("")){
					VALUES("password", "#{password}");
				}
				if(seller.getPaynumber() != null && !seller.getPaynumber().equals("")){
					VALUES("paynumber", "#{paynumber}");
				}
				if(seller.getAddress() != null && !seller.getAddress().equals("")){
					VALUES("address", "#{address}");
				}
				if(seller.getSex() != null && !seller.getSex().equals("")){
					VALUES("sex", "#{sex}");
				}
				if(seller.getAge() != null && !seller.getAge().equals("")){
					VALUES("age", "#{age}");
				}
				if(seller.getTelnumber() != null && !seller.getTelnumber().equals("")){
					VALUES("telnumber", "#{telnumber}");
				}
				if(seller.getEmail() != null && !seller.getEmail().equals("")){
					VALUES("email", "#{email}");
				}
				if(seller.getStatus() != null && !seller.getStatus().equals("")){
					VALUES("status", "#{status}");
				}
				
			}
		}.toString();
	}

		public String updateSeller(final Seller seller){
			
			return new SQL(){
				{
					UPDATE("seller_inf");

					if(seller.getUsername() != null){
						SET(" username = #{username} ");
					}
					if(seller.getPassword()!= null){
						SET(" password = #{password} ");
					}
					if(seller.getPaynumber()!= null){
						SET(" paynumber = #{paynumber} ");
					}
					if(seller.getAddress()!= null){
						SET(" address = #{address} ");
					}
					if(seller.getSex()!= null){
						SET(" sex = #{sex} ");
					}
					if(seller.getAge()!= null){
						SET(" age = #{age} ");
					}
					if(seller.getTelnumber()!= null){
						SET(" telnumber = #{telnumber} ");
					}
					if(seller.getEmail()!= null){
						SET(" email = #{email} ");
					}
					if(seller.getStatus()!= null){
						SET(" status = #{status} ");
					}
//					if(seller.getCreateDate()!= null){
//						SET(" create_date = #{createDate} ");
//					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
}
