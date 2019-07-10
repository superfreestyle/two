package com.xkjsj.go.controller;

import java.util.Date;
import java.util.List;

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

import com.xkjsj.go.domain.Car;
import com.xkjsj.go.domain.Customer;
import com.xkjsj.go.domain.Good;
import com.xkjsj.go.domain.Order;
import com.xkjsj.go.domain.OrderGoodLink;
import com.xkjsj.go.domain.Seller;
import com.xkjsj.go.domain.Store;
import com.xkjsj.go.service.GoService;

@Controller
public class OrderController {
	@Autowired
	@Qualifier("goService")
	private GoService goService;
	
	@RequestMapping(value="/addOrder/{username}")
	 public ModelAndView addOrder(@PathVariable String username,@RequestParam("total")String total,@ModelAttribute Order order,@ModelAttribute OrderGoodLink link,
			 HttpSession session,ModelAndView mv){
		//通过username获取用户信息，取得用户id
		Customer customer = goService.findCustomerByUsername(username);
		int a=customer.getId();
		//保存数据进javabean
	    order.setCustomer(customer);
	    order.getCustomer().setId(a);
	    order.setTotal(total);
	    //执行添加订单语句
	    int c=goService.addOrder(order);
		System.out.print(c);
		
		//保存order和good进OrderGoodLink link
		link.setOrder(order);
		Car car = goService.findCarByCustomerId(a);
		List<Good> good = goService.findGoodByCarId(car.getId());
		//遍历获取购物车中商品
		for(int i = 0 ; i < good.size() ; i++) {
			link.setGood(good.get(i));
			goService.addOrderGoodLink(link);
		}
		
		if (c==1){
			mv.addObject("message", "创建订单成功");
			//返回订单页面并且显示该用户订单
			List<Good> goods = goService.findGoodByOrderId(order.getId());
			session.setAttribute("goods", goods);
			mv.setViewName("Car_info");
		}else{
			mv.addObject("message", "创建订单失败");
			mv.setViewName("Car_info");
		}
		return mv;
	}

	@RequestMapping(value="/findOrderByCustomerId")
	 public String findOrderByCustomerId(
			 Model model, HttpSession session){
		Customer customer=(Customer) session.getAttribute("customer");
		int a=customer.getId();
		List<Order> orders = goService.findOrderByCustomerId(a);
		model.addAttribute("orders", orders);
		session.setAttribute("orders", orders);
		return "Order_info";
	}
	
	@RequestMapping(value="/findOrderById")
	 public ModelAndView findOrderById(String id,
			 ModelAndView mv){
		Order order =goService.findOrderById(Integer.parseInt(id));
		if(order != null){
			mv.addObject("order", order);
		    mv.setViewName("OrderCode");
		}else{
			
		}
		return mv;	
	}
	
	@RequestMapping(value="/modifyOrder")
	 public ModelAndView modifyOrder(@RequestParam("paynumber") String paynumber,@ModelAttribute Order order,HttpSession session,
			 ModelAndView mv){
		//List<Order> ors= (List<Order>) session.getAttribute("orders");
		Customer customer=(Customer) session.getAttribute("customer");
		
		if(Integer.parseInt(paynumber)!=(customer.getPaynumber())){
//			Order target=goService.findOrderById(order.getId());
		mv.addObject("messege","支付密码输入错误，请重新输入");
		mv.addObject("id", order.getId());
		//mv.addObject("createDate", order.getCreateDate());
		mv.setViewName("OrderModify");		
		}else{

			Order target=goService.findOrderById(order.getId());
			int q=order.getId();
			System.out.println(q);
			target.setStatus(1);
			int c =goService.modifyOrder(target);
			//System.out.print(c);
			mv.addObject("message", "支付成功");
			mv.setViewName("OrderModify");
		}
		return mv;	
	}	
	
	@RequestMapping(value="/modifyOrder1")
	 public ModelAndView modifyOrder1(@RequestParam("id") String id,HttpSession session,
			 ModelAndView mv){


			Order target=goService.findOrderById(Integer.parseInt(id));
			int q=target.getId();
			System.out.println(q);
			target.setStatus(2);
			int c =goService.modifyOrder(target);
			//System.out.print(c);
			mv.addObject("message", "发货成功");
			mv.setViewName("redirect:/findGoodByStoreId");
		
		return mv;	
	}	
	
	@RequestMapping(value="/modifyOrder2")
	 public ModelAndView modifyOrder2(@RequestParam("id") String id,HttpSession session,
			 ModelAndView mv){


			Order target=goService.findOrderById(Integer.parseInt(id));
			int q=target.getId();
			System.out.println(q);
			target.setStatus(3);
			int c =goService.modifyOrder(target);
			//System.out.print(c);
			mv.addObject("message", "确认收货成功");
			mv.setViewName("redirect:/findOrderByCustomerId");
		
		return mv;	
	}	
	
	@RequestMapping(value="/removeOrder")
	 public ModelAndView removeOrder(@ModelAttribute Order order,
			 ModelAndView mv){
		int c=goService.removeOrder(order.getId());
		System.out.print(c);
		mv.setViewName("redirect:/findOrderByCustomerId");
		return mv;	
	}	
	
	
	@RequestMapping(value="/addOrderGoodLink")
	 public String addOrderGoodLink(@ModelAttribute OrderGoodLink orderGoodLink,HttpSession session,
			 Model model){		
		Good good=(Good) session.getAttribute("good");	
		int b=good.getId();
		Order order=(Order) session.getAttribute("order");
		int a=order.getId();
		orderGoodLink.setGood(good);
		orderGoodLink.setOrder(order);
		//orderGoodLink.setAmount(amount);
		orderGoodLink.getGood().setId(b);
		orderGoodLink.getOrder().setId(a);
		
		goService.addOrderGoodLink(orderGoodLink);
		//System.out.print(c);
//		if (c==1){
			model.addAttribute("message", "添加商品成功");
			session.putValue("good", null);
			session.putValue("oeder", null);
			return "redirect:/findOrderByCustomerId";
//		else {
//			model.addAttribute("message", "创建店铺失败");
//			return "order/addOrderGoodLink";}}

		}
	
	@RequestMapping(value="/modifyOrderGoodLink")
	 public ModelAndView modifyOrderGoodLink(String flag,@ModelAttribute OrderGoodLink orderGoodLink,HttpSession session,
			 ModelAndView mv){
		if(flag.equals("1")){
			Order order=(Order) session.getAttribute("order");
			int oid=order.getId();
			Integer gid=orderGoodLink.getGood().getId();
//			Integer oid=orderGoodLink.getOrder().getId();
			OrderGoodLink target=goService.findOrderGoodLinkByOrderIdAndGoodId(oid,gid);
		mv.addObject("order",target);
		mv.setViewName("order/showModifyOrderGoodLink");		
		}else{
			int c =goService.modifyOrderGoodLink(orderGoodLink);
			System.out.print(c);
			mv.addObject("message", "修改成功");
			mv.setViewName("order/orderGoodLink");
		}
		return mv;	
	}	
	
	@RequestMapping(value="/removeOrderGoodLinkByGoodId")
	 public ModelAndView removeOrderGoodLinkByGoodId(@ModelAttribute OrderGoodLink orderGoodLink,HttpSession session,
			 ModelAndView mv){
		Order order=(Order) session.getAttribute("order");
		int oid=order.getId();
		Integer gid=orderGoodLink.getGood().getId();
//		Integer oid=orderGoodLink.getOrder().getId();
		OrderGoodLink target=goService.findOrderGoodLinkByOrderIdAndGoodId(oid,gid);
		int c=goService.removeOrderGoodLinkByGoodId(target.getGood().getId());
		System.out.print(c);
		mv.setViewName("order/findOrderGoodLinkByGoodId");
		return mv;	
	}	
	
}
