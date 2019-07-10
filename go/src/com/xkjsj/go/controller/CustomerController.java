package com.xkjsj.go.controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xkjsj.go.domain.Customer;
import com.xkjsj.go.domain.Good;
import com.xkjsj.go.service.GoService;

@Controller
public class CustomerController {
	@Autowired
	@Qualifier("goService")
	private GoService goService;
	
	@RequestMapping(value="/addCustomer")
	 public ModelAndView addCustomer(@ModelAttribute Customer customer,
			 ModelAndView mv){
		int c=goService.addCustomer(customer);
		System.out.print(c);
		if (c==1){
		mv.addObject("message", "注册成功");
		mv.setViewName("Login");}
		else {
			mv.addObject("message", "注册失败");
			mv.setViewName("Regist");}
		return mv;
	}
	
	
	@RequestMapping(value="/customerLogin")
	 public ModelAndView sellerlogin(@RequestParam("username") String username,
			 @RequestParam("password") String password,
			 HttpSession session,
			 ModelAndView mv){
		Customer customer = goService.customerLogin(username, password);
		List<Good> goods = goService.findGoodByStoreId(1);
		if(customer != null){		
			session.setAttribute("customer", customer);
			session.setAttribute("goods", goods);
			//mv.addObject(session);
			mv.setViewName("Homepage");
		}else{
			mv.addObject("message", "用户名或密码输入错误!请重新输入");
			mv.setViewName("Login");
		}
		return mv;		
	}
	
	/*@RequestMapping(value="/customer/findCustomerById")
	 public ModelAndView findcustomerById(String id,
			 ModelAndView mv){
		Customer customer =goService.findCustomerById(Integer.parseInt(id));
		if(customer != null){
			
		    mv.setViewName("customer/showCustomerById");
		}else{
			mv.addObject("message", "输入错误!请重新输入");
			mv.setViewName("customer/findCustomerById");
		}
		return mv;	
	}
	
	@RequestMapping(value="/customer/findCustomer")
	 public String findCustomer(Integer pageIndex,@ModelAttribute Customer customer,
			 Model model){
		System.out.println("customer = " + customer);
		com.xkjsj.go.util.tag.PageModel pageModel = new com.xkjsj.go.util.tag.PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Customer> customers = goService.findCustomer(customer, pageModel);
		model.addAttribute("customers", customers);
		model.addAttribute("pageModel", pageModel);
		return "customer/customer";
	}*/
	
//	@RequestMapping(value="/Customermsg/{username}")
//	 public void findCustomerByUsername(@PathVariable String username,
//			 Model model){
//		System.out.println("customer = " + username);
//		//判断是否登录
//		//若已经登录则查询用户的各项信息
//		if(username != null){
//			Customer customer = goService.findCustomerByUsername(username);
//			model.addAttribute("username", customer.getUsername());
//			model.addAttribute("sex", customer.getSex());
//			model.addAttribute("age", customer.getAge());
//			model.addAttribute("telnumber", customer.getTelnumber());
//			model.addAttribute("email", customer.getEmail());
//			model.addAttribute("paybumber", customer.getPaynumber());
//		}else{
//			model.addAttribute("tip", "请先登录");
//		}	
//	}
	
	@RequestMapping(value="/modifyCustomer")
	 public ModelAndView modifyCustomer(@ModelAttribute Customer customer,HttpSession session,
			 ModelAndView mv){
		Customer cus=(Customer) session.getAttribute("customer");
		int a=cus.getId();
			customer.setId(a);
			int c =goService.modifyCustomer(customer);
			System.out.print(c);
			Customer target=goService.findCustomerById(a);
			session.setAttribute("customer", target);
			mv.setViewName("Customer_info");
		
		return mv;	
	}	
	
	@RequestMapping(value="/removeCustomer")
	 public ModelAndView removeCustomer(HttpSession session,
			 ModelAndView mv){
		Customer cus=(Customer) session.getAttribute("customer");
		int a=cus.getId();
		int c=goService.removeCustomer(a);
		session.invalidate();
		System.out.print(c);
		mv.setViewName("Homepage");
		return mv;	
	}	
}
