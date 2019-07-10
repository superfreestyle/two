package com.xkjsj.go.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.xkjsj.go.dao.provider.OrderGoodLinkDynaSqlProvider;
import com.xkjsj.go.domain.OrderGoodLink;

public interface OrderGoodLinkDao {
	@InsertProvider(type=OrderGoodLinkDynaSqlProvider.class,method="insertOrderGoodLink")
	void save(OrderGoodLink ordergoodlink);
	@UpdateProvider(type=OrderGoodLinkDynaSqlProvider.class,method="updateOrderGoodLink")
	int update(OrderGoodLink ordergoodlink);
	
	@Select("select * from ordergoodlink_inf where order_ID = #{id}")
	List<OrderGoodLink> selectByOrderId(Integer id);
	
	@Select("select * from ordergoodlink_inf where good_ID = #{id}")
	List<OrderGoodLink> selectByGoodId(Integer id);
	
	@Select("select * from ordergoodlink_inf where order_ID = #{oid} and good_id = #{gid}")
	OrderGoodLink selectByOrderIdAndGoodId(Integer oid,Integer gid);
	
	@Delete(" delete from ordergoodlink_inf where good_id = #{id} ")
	int deleteByGoodId(Integer id);
	
	@Delete(" delete from ordergoodlink_inf where order_id = #{id} ")
	int deleteByOrderId(Integer id);//删除订单时先执行这条
}
