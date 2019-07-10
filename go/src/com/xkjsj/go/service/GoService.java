package com.xkjsj.go.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;


import com.xkjsj.go.domain.Admin;
import com.xkjsj.go.domain.Car;
import com.xkjsj.go.domain.CarGoodLink;
import com.xkjsj.go.domain.Category;
import com.xkjsj.go.domain.Customer;
import com.xkjsj.go.domain.Good;
import com.xkjsj.go.domain.Order;
import com.xkjsj.go.domain.OrderGoodLink;
import com.xkjsj.go.domain.Seller;
import com.xkjsj.go.domain.Store;
import com.xkjsj.go.util.tag.PageModel;

public interface GoService {
	//seller service
	int addSeller(Seller seller);//注册插入卖家
	
	Seller sellerLogin(String username, String password);//登录
	
	Seller findSellerById(Integer id);//根据id查找卖家
	
	List<Seller> findSeller(Seller seller,PageModel pageModel);//根据各种参数动态查询卖家
	
	int modifySeller(Seller seller);//修改卖家信息
	
	int removeSeller(Integer id);//删除卖家
	
	
	//store service
	int addStore(Store store);//创建店铺
	
	Store findStoreById(Integer id);//根据id查找店铺
	
	Store findStoreBySellerId(Integer id);
	
	List<Store> findStore(Store store,PageModel pageModel);//根据各种参数动态查找店铺
	
	int modifyStore(Store store);//修改店铺信息
	
	int removeStore(Integer id);//删除店铺
	
	int removeStoreBySellerId(Integer id);//删除店铺
	
	
	//good service
	int addGood(Good good);
	
	List<Good> findGoodByStoreId(Integer id);
	
	List<Good> findGoodByOrderId(Integer id);
	
	List<Good> findGoodByCarId(Integer id);
	
	Good findGoodById(Integer id);
	
	List<Good> findGood(Good good,PageModel pageModel);
	
	int modifyGood(Good good);
	
	int removeGood(Integer id);
	
	int removeGoodByStoreId(Integer id);
	
	int removeGoodByCategoryId(Integer id);
	
	
	//category service
	int addCategory(Category category);
	
	Category findCategoryById(Integer id);
	
	Category findCategoryByName(String name);
	
	List<Category> findAllCategory();
	
	int modifyCategory(Category category);
	
	int removeCategory(Integer id);
	
	int removeCategoryByName(String name);
	
	
	
	//admin service
	Admin adminLogin(String username, String password);//登录
	
	Admin findAdminById(Integer id);
	
	int modifyAdmin(Admin admin);
	
	int removeAdmin(Integer id);
	
	
	
	//customer service
	int addCustomer(Customer customer);//注册插入卖家
		
	Customer customerLogin(String username, String password);//登录
		
	Customer findCustomerById(Integer id);//根据id查找卖家
		
	List<Customer> findCustomer(Customer customer,PageModel pageModel);//根据各种参数动态查询卖家
		
	int modifyCustomer(Customer customer);//修改卖家信息
		
	int removeCustomer(Integer id);//删除卖家
	
	Customer findCustomerByUsername(String username);//
	
	
	//car service
	int addCar(Car car);
	
	Car findCarById(Integer id);
	
	Car findCarByCustomerId(Integer id);
	
	int removeCar(Integer id);
	
	int removeCarByCustomerId(Integer id);
	
	//cargoodlink service
	void addCarGoodLink(CarGoodLink cargoodlink);
	
	List<CarGoodLink> findCarGoodLinkByCarId(Integer id);
	
	CarGoodLink  findCarGoodLinkByCarIdAndGoodId(Integer cid,Integer gid);
	
	int modifyCarGoodLink(CarGoodLink cargoodlink);
	
	int removeCarGoodLinkByCarId(Integer id);
	
	int removeCarGoodLinkByCarIdAndGoodId(Integer cid, Integer gid);

	int removeCarGoodLinkByGoodId(Integer gid);
	
	//order service
	int addOrder(Order order);
		
	Order findOrderById(Integer id);
	
	Order findOrderByCode(Integer code);
	
	List<Order> findOrderByCustomerId(Integer id);
		
	int modifyOrder(Order order);
	
	int removeOrder(Integer id);
	
	int removeOrderByCustomerId(Integer id);
	
	
	//ordergoodlink service
	void addOrderGoodLink(OrderGoodLink orderGoodLink);
	
	List<OrderGoodLink> findOrderGoodLinkByOrderId(Integer id);
	
	OrderGoodLink findOrderGoodLinkByOrderIdAndGoodId(Integer oid,Integer gid);
	
	int modifyOrderGoodLink(OrderGoodLink orderGoodLink);
	
	int removeOrderGoodLinkByGoodId(Integer id);
	
	int removeOrderGoodLinkByOrderId(Integer id);//删除订单时先执行这条

	List<OrderGoodLink> findOrderGoodLinkByGoodId(Integer id);

	

	


}
