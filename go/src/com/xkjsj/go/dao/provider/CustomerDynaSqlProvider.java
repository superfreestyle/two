package com.xkjsj.go.dao.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import com.xkjsj.go.domain.Customer;

public class CustomerDynaSqlProvider {
	public String selectWhitParam(final Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("customer_inf");
				if(params.get("customer") != null){
					Customer customer = (Customer)params.get("customer");
					if(customer.getUsername() != null && !customer.getUsername().equals("")){
						WHERE("  username LIKE CONCAT ('%',#{customer.username},'%') ");
					}
					if(customer.getStatus() != null && !customer.getStatus().equals("")){
						WHERE(" status LIKE CONCAT ('%',#{customer.status},'%') ");
					}
					if(customer.getSex() != null && !customer.getSex().equals("")){
						WHERE(" sex LIKE CONCAT ('%',#{customer.sex},'%') ");
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
				FROM("customer_inf");
				if(params.get("customer") != null){
					Customer customer = (Customer)params.get("customer");
					if(customer.getUsername() != null && !customer.getUsername().equals("")){
						WHERE("  username LIKE CONCAT ('%',#{customer.username},'%') ");
					}
					if(customer.getStatus() != null && !customer.getStatus().equals("")){
						WHERE(" status LIKE CONCAT ('%',#{customer.status},'%') ");
					}
					if(customer.getSex() != null && !customer.getSex().equals("")){
						WHERE(" sex LIKE CONCAT ('%',#{customer.sex},'%') ");
					}
				}
			}
		}.toString();
	}	
	

	public String insertCustomer(final Customer customer){
		//CarDynaSqlProvider cardao=new CarDynaSqlProvider();
		//cardao.insertCar();
		return new SQL(){
			{
				INSERT_INTO("customer_inf");
				if(customer.getUsername() != null && !customer.getUsername().equals("")){
					VALUES("username", "#{username}");
				}
				if(customer.getPassword() != null && !customer.getPassword().equals("")){
					VALUES("password", "#{password}");
				}
				if(customer.getPaynumber() != null && !customer.getPaynumber().equals("")){
					VALUES("paynumber", "#{paynumber}");
				}
				if(customer.getAddress() != null && !customer.getAddress().equals("")){
					VALUES("address", "#{address}");
				}
				if(customer.getSex() != null && !customer.getSex().equals("")){
					VALUES("sex", "#{sex}");
				}
				if(customer.getAge() != null && !customer.getAge().equals("")){
					VALUES("age", "#{age}");
				}
				if(customer.getTelnumber() != null && !customer.getTelnumber().equals("")){
					VALUES("telnumber", "#{telnumber}");
				}
				if(customer.getEmail() != null && !customer.getEmail().equals("")){
					VALUES("email", "#{email}");
				}
				if(customer.getStatus() != null && !customer.getStatus().equals("")){
					VALUES("status", "#{status}");
				}
				

			}
		}.toString();
	}

		public String updateCustomer(final Customer customer){
			
			return new SQL(){
				{
					UPDATE("customer_inf");
					if(customer.getUsername() != null){
						SET(" username = #{username} ");
					}
					if(customer.getPassword()!= null){
						SET(" password = #{password} ");
					}
					if(customer.getPaynumber()!= null){
						SET(" paynumber = #{paynumber} ");
					}
					if(customer.getAddress()!= null){
						SET(" address = #{address} ");
					}
					if(customer.getSex()!= null){
						SET(" sex = #{sex} ");
					}
					if(customer.getAge()!= null){
						SET(" age = #{age} ");
					}
					if(customer.getTelnumber()!= null){
						SET(" telnumber = #{telnumber} ");
					}
					if(customer.getEmail()!= null){
						SET(" email = #{email} ");
					}
					if(customer.getStatus()!= null){
						SET(" status = #{status} ");
					}
//					if(customer.getCreateDate()!= null){
//						SET(" create_date = #{createDate} ");
//					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
}
