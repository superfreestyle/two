package com.xkjsj.go.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xkjsj.go.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import com.xkjsj.go.service.GoService;
import com.xkjsj.go.util.tag.PageModel;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("goService")
public class GoServiceImpl implements GoService{
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private GoodDao goodDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CarDao carDao;
	@Autowired
	private CarGoodLinkDao carGoodLinkDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderGoodLinkDao orderGoodLinkDao;
	//seller service
	@Override
	public int addSeller(Seller seller){
		
		return  sellerDao.save(seller);
		 
	}
	
	
	@Transactional(readOnly=true)
	@Override
	public Seller sellerLogin(String username, String password){
		return sellerDao.selectByUsernameAndPassword(username, password);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Seller findSellerById(Integer id){
		return sellerDao.selectById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Seller> findSeller(Seller seller,PageModel pageModel){
		Map<String, Object> params=new HashMap<>();
		params.put("seller", seller);
		int recordCount = sellerDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		return sellerDao.selectByPage(params);
	}


	
	
	@Override
	public int modifySeller(Seller seller) {
		// TODO Auto-generated method stub
		return sellerDao.update(seller);
	}


	@Override
	public int removeSeller(Integer id) {
		// TODO Auto-generated method stub
		removeStoreBySellerId(id);
		return sellerDao.deleteById(id);
	}

	
	
	
	//store service
	@Override
	public int addStore(Store store) {

		//store.setSeller(seller);
		return storeDao.save(store);
	}


	@Override
	public Store findStoreById(Integer id) {
		// TODO Auto-generated method stub
		return storeDao.selectById(id);
	}

	
	@Override
	public Store findStoreBySellerId(Integer id) {
		// TODO Auto-generated method stub
		return storeDao.selectBySellerId(id);
	}

	
	@Override
	public List<Store> findStore(Store store, PageModel pageModel) {
		Map<String, Object> params=new HashMap<>();
		params.put("store", store);
		int recordCount = storeDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		return storeDao.selectByPage(params);
	}


	@Override
	public int modifyStore(Store store) {
		// TODO Auto-generated method stub
		return storeDao.update(store);
	}


	@Override
	public int removeStore(Integer id) {
		// TODO Auto-generated method stub
		return storeDao.deleteById(id);
	}

	
	@Override
	public int removeStoreBySellerId(Integer id) {
		// TODO Auto-generated method stub
		Store store=storeDao.selectBySellerId(id);
		int c=store.getId();
		removeGoodByStoreId(c);
		return storeDao.deleteBySellerId(id);
	}

	
	
	//good service
	@Override
	public int addGood(Good good) {
		// TODO Auto-generated method stub
		return goodDao.save(good);
	}


	@Override
	public List<Good> findGoodByOrderId(Integer id) {
		// TODO Auto-generated method stub
		return goodDao.selectByOrderId(id);
	}


	@Override
	public List<Good> findGoodByCarId(Integer id) {
		// TODO Auto-generated method stub
		return goodDao.selectByCarId(id);
	}


	@Override
	public Good findGoodById(Integer id) {
		// TODO Auto-generated method stub
		return goodDao.selectById(id);
	}

	@Override
	public List<Good> findGoodByStoreId(Integer id) {
		// TODO Auto-generated method stub
		return goodDao.selectByStoreId(id);
	}

	@Override
	public List<Good> findGood(Good good, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String, Object> params=new HashMap<>();
		params.put("good", good);
		int recordCount = goodDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		return goodDao.selectByPage(params);
	}


	@Override
	public int modifyGood(Good good) {
		// TODO Auto-generated method stub
		return goodDao.update(good);
	}


	@Override
	public int removeGood(Integer id) {
		// TODO Auto-generated method stub
		removeCarGoodLinkByGoodId(id);
		removeOrderGoodLinkByGoodId(id);
		return goodDao.deleteById(id);
	}

	@Override
	public int removeGoodByStoreId(Integer id) {
		// TODO Auto-generated method stub
		return goodDao.deleteByStoreId(id);
	}


	@Override
	public int removeGoodByCategoryId(Integer id) {
		// TODO Auto-generated method stub
		
		return goodDao.deleteByCategoryId(id);
	}

	
	
	
	//category service
	@Override
	public int addCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.save(category);
	}


	@Override
	public Category findCategoryById(Integer id) {
		// TODO Auto-generated method stub
		return categoryDao.selectById(id);
	}


	@Override
	public Category findCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryDao.selectByName(name);
	}


	@Override
	public List<Category> findAllCategory() {
		// TODO Auto-generated method stub
		return (List<Category>)categoryDao.selectAllCategory();
	}


	@Override
	public int modifyCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.update(category);
	}


	@Override
	public int removeCategory(Integer id) {
		// TODO Auto-generated method stub
		removeGoodByCategoryId(id);
		return categoryDao.deleteById(id);
	}


	@Override
	public int removeCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryDao.deleteByName(name);
	}





	
	//admin service

	@Override
	public Admin adminLogin(String username, String password) {
		// TODO Auto-generated method stub
		return adminDao.selectByUsernameAndPassword(username, password);
	}
	
	
	@Override
	public Admin findAdminById(Integer id) {
		// TODO Auto-generated method stub
		return adminDao.selectById(id);
	}


	@Override
	public int modifyAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.update(admin);
	}


	@Override
	public int removeAdmin(Integer id) {
		// TODO Auto-generated method stub
		return adminDao.deleteById(id);
	}


	
	//customer service
	@Override
	public int addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		 customerDao.save(customer);
		 String username=customer.getUsername();
		 String password=customer.getPassword();
		customer= customerDao.selectByUsernameAndPassword(username, password);
		int c=customer.getId();
		Car car=new Car();
		car.setCustomer(customer);
		car.getCustomer().setId(c);
		 return addCar(car);
	}


	@Override
	public Customer customerLogin(String username, String password) {
		// TODO Auto-generated method stub
		return customerDao.selectByUsernameAndPassword(username, password);
	}

	@Override
	public Customer findCustomerByUsername(String username) {
		// TODO Auto-generated method stub
		return customerDao.findCustomerByUsername(username);
	}

	@Override
	public Customer findCustomerById(Integer id) {
		// TODO Auto-generated method stub
		return customerDao.selectById(id);
	}


	@Override
	public List<Customer> findCustomer(Customer customer, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String, Object> params=new HashMap<>();
		params.put("customer", customer);
		int recordCount = customerDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		return customerDao.selectByPage(params);
	}


	@Override
	public int modifyCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.update(customer);
	}


	@Override
	public int removeCustomer(Integer id) {
		// TODO Auto-generated method stub
		//removeOrderGoodLinkByOrderId(id);
		removeOrderByCustomerId(id);
		removeCarByCustomerId(id);
		return customerDao.deleteById(id);
	}


	//car service
	@Override
	public int addCar(Car car) {
		// TODO Auto-generated method stub
		return carDao.save(car);
	}


	@Override
	public Car findCarById(Integer id) {
		// TODO Auto-generated method stub
		return carDao.selectById(id);
	}


	@Override
	public Car findCarByCustomerId(Integer id) {
		// TODO Auto-generated method stub
		return carDao.selectByCustomerId(id);
	}

	@Override
	public int removeCar(Integer id) {
		// TODO Auto-generated method stub
		removeCarGoodLinkByCarId(id);
		return carDao.deleteById(id);
	}

	@Override
	public int removeCarByCustomerId(Integer id) {
		// TODO Auto-generated method stub
		Car car=carDao.selectByCustomerId(id);
		int c=car.getId();
		removeCarGoodLinkByCarId(c);
		return carDao.deleteByCustomerId(id);
	}

	
	
	
	//carGoodLinkDao service
	@Override
	public void addCarGoodLink(CarGoodLink cargoodlink) {
		// TODO Auto-generated method stub
		 carGoodLinkDao.save(cargoodlink);
	}


	@Override
	public List<CarGoodLink> findCarGoodLinkByCarId(Integer id) {
		// TODO Auto-generated method stub
		return carGoodLinkDao.selectByCarId(id);
	}

	@Override
	public CarGoodLink findCarGoodLinkByCarIdAndGoodId(Integer cid, Integer gid) {
		// TODO Auto-generated method stub
		return carGoodLinkDao.selectByCarIdAndGoodId(cid, gid);
	}


	@Override
	public int modifyCarGoodLink(CarGoodLink cargoodlink) {
		// TODO Auto-generated method stub
		return carGoodLinkDao.update(cargoodlink);
	}

	@Override
	public int removeCarGoodLinkByGoodId(Integer gid) {
		// TODO Auto-generated method stub
		return carGoodLinkDao.deleteByGoodId(gid);
	}

	@Override
	public int removeCarGoodLinkByCarIdAndGoodId(Integer cid,Integer gid) {
		// TODO Auto-generated method stub
		return carGoodLinkDao.deleteByCarIdAndGoodId(cid,gid);
	}
	
	@Override
	public int removeCarGoodLinkByCarId(Integer id) {
		// TODO Auto-generated method stub
		return carGoodLinkDao.deleteByCarId(id);
	}

	
	
	//order service
	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDao.save(order);
	}


	@Override
	public Order findOrderById(Integer id) {
		// TODO Auto-generated method stub
		return orderDao.selectById(id);
	}

	
	@Override
	public Order findOrderByCode(Integer code) {
		// TODO Auto-generated method stub
		return orderDao.selectByCode(code);
	}

	
	@Override
	public List<Order> findOrderByCustomerId(Integer id) {
		// TODO Auto-generated method stub
		return orderDao.selectByCustomerId(id);
	}


	@Override
	public int modifyOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDao.update(order);
	}


	@Override
	public int removeOrder(Integer id) {
		// TODO Auto-generated method stub
		removeOrderGoodLinkByOrderId(id);
		return orderDao.deleteById(id);
	}
	@Override
	public int removeOrderByCustomerId(Integer id) {
		List<Order> orders=(List<Order>) orderDao.selectByCustomerId(id);
		for(Order o:orders){
			int c=o.getId();
			removeOrderGoodLinkByOrderId(c);
		}
		
		return orderDao.deleteByCustomerId(id);
	}


	
	//orderGoodLink service
	@Override
	public void addOrderGoodLink(OrderGoodLink ordergoodlink) {
		// TODO Auto-generated method stub
		orderGoodLinkDao.save(ordergoodlink);
	}


	@Override
	public List<OrderGoodLink> findOrderGoodLinkByOrderId(Integer id) {
		// TODO Auto-generated method stub
		return orderGoodLinkDao.selectByOrderId(id);
	}

	@Override
	public List<OrderGoodLink> findOrderGoodLinkByGoodId(Integer id) {
		// TODO Auto-generated method stub
		return orderGoodLinkDao.selectByGoodId(id);
	}
	
	@Override
	public int modifyOrderGoodLink(OrderGoodLink ordergoodlink) {
		// TODO Auto-generated method stub
		return orderGoodLinkDao.update(ordergoodlink);
	}


	@Override
	public int removeOrderGoodLinkByGoodId(Integer id) {
		// TODO Auto-generated method stub
		return orderGoodLinkDao.deleteByGoodId(id);
	}


	@Override
	public int removeOrderGoodLinkByOrderId(Integer id) {
		// TODO Auto-generated method stub
		return  orderGoodLinkDao.deleteByOrderId(id);
	}


	

	@Override
	public OrderGoodLink findOrderGoodLinkByOrderIdAndGoodId(Integer oid,
			Integer gid) {
		// TODO Auto-generated method stub
		return orderGoodLinkDao.selectByOrderIdAndGoodId(oid, gid);
	}


	


	


	



















	
	
	
	
	
	
}
