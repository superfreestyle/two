package com.xkjsj.go;

import org.springframework.beans.factory.annotation.Autowired;

import com.xkjsj.go.dao.*;
import com.xkjsj.go.domain.Seller;
public class test {

	/**
	 * @param args
	 */
	static SellerDao sellerDao;
	
	public static SellerDao getSellerDao() {
		return sellerDao;
	}

	public static void setSellerDao(SellerDao sellerDao) {
		test.sellerDao = sellerDao;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

       Seller seller =new Seller();
       seller.setUsername("b");
       seller.setPassword("b");
	   int c=sellerDao.save(seller);
	   System.out.print(c);
       
	}
	 
}
