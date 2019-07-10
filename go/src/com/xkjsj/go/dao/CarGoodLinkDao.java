package com.xkjsj.go.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.xkjsj.go.dao.provider.CarGoodLinkDynaSqlProvider;
import com.xkjsj.go.domain.CarGoodLink;
import com.xkjsj.go.domain.Good;


public interface CarGoodLinkDao {
	
	@InsertProvider(type=CarGoodLinkDynaSqlProvider.class,method="insertCarGoodLink")
	void save(CarGoodLink cargoodlink);
	
	@UpdateProvider(type=CarGoodLinkDynaSqlProvider.class,method="updateCarGoodLink")
	int update(CarGoodLink cargoodlink);
	
	@Select("select * from cargoodlink_inf where car_ID = #{id}")
	List<CarGoodLink> selectByCarId(Integer id);
	
	@Select("select * from cargoodlink_inf where car_ID = #{0} and good_ID = #{1}")
	CarGoodLink selectByCarIdAndGoodId(Integer cid,Integer gid);
	
	@Delete(" delete from cargoodlink_inf where good_id = #{gid} ")
	int deleteByGoodId(Integer gid);
	
	@Delete(" delete from cargoodlink_inf where car_id = #{id} ")
	int deleteByCarId(Integer id);
	
	@Delete(" delete from cargoodlink_inf where car_id = #{cid} and good_id = #{gid} ")
	int deleteByCarIdAndGoodId(Integer cid, Integer gid);
}
