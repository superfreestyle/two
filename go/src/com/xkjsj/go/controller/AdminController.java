package com.xkjsj.go.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xkjsj.go.domain.Admin;
import com.xkjsj.go.domain.Customer;
import com.xkjsj.go.domain.Seller;
import com.xkjsj.go.service.GoService;

@Controller
public class AdminController {
	@Autowired
	@Qualifier("goService")
	private GoService goService;
	
	@RequestMapping(value="/adminLogin")
	 public ModelAndView sellerlogin(@RequestParam("username") String username,
			 @RequestParam("password") String password,
			 HttpSession session,
			 ModelAndView mv){
		Admin admin = goService.adminLogin(username, password);
		if(admin != null){		
			session.setAttribute("admin", admin);
			
			mv.setViewName("AdminHomepage");
		
		}else{
			mv.addObject("message", "用户名或密码输入错误!请重新输入");
			mv.setViewName("AdminLogin");
		}
		return mv;		
	}
	
	
	@RequestMapping(value="/findSellerById")
	 public ModelAndView findSellerById(String id,
			 ModelAndView mv){
		Seller seller =goService.findSellerById(Integer.parseInt(id));
		if(seller != null){
			mv.addObject("seller", seller);
		    mv.setViewName("SellerId");
		}else{
			mv.addObject("message", "输入错误!请重新输入");
			mv.setViewName("SellerId");
		}
		return mv;	
	}
	
	@RequestMapping(value="/findCustomerById")
	 public ModelAndView findcustomerById(String id,
			 ModelAndView mv){
		Customer customer =goService.findCustomerById(Integer.parseInt(id));
		if(customer != null){
			mv.addObject("customer", customer);
		    mv.setViewName("CustomerId");
		}else{
			mv.addObject("message", "输入错误!请重新输入");
			mv.setViewName("CustomerId");
		}
		return mv;	
	}
}
