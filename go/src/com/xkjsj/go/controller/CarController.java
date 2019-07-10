package com.xkjsj.go.controller;

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
import com.xkjsj.go.domain.CarGoodLink;
import com.xkjsj.go.domain.Customer;
import com.xkjsj.go.domain.Good;
import com.xkjsj.go.domain.Order;
import com.xkjsj.go.domain.OrderGoodLink;
import com.xkjsj.go.domain.Seller;
import com.xkjsj.go.domain.Store;
import com.xkjsj.go.service.GoService;

@Controller
public class CarController {
	@Autowired
	@Qualifier("goService")
	private GoService goService;
	
	@RequestMapping(value="/findCarByCustomerId")
	 public String findCarByCustomerId(
			 Model model, HttpSession session){
		Customer customer=(Customer) session.getAttribute("customer");
		int a=customer.getId();
	    Car car = goService.findCarByCustomerId(a);
	    int b=car.getId();
	    List<Good> goods = goService.findGoodByCarId(b);
		for(Good g: goods) {
			CarGoodLink temp=goService.findCarGoodLinkByCarIdAndGoodId(b,g.getId());
			int amount=temp.getAmount();
		}
	    
	    
		model.addAttribute("car", car);
		session.setAttribute("car", car);
		return "Car_info";
	}

	@RequestMapping(value="/addCarGoodLink")
	 public String addCarGoodLink(@RequestParam("id")int id,
			 HttpSession session, Model model){
			//username获取对象customer，id获取对象good
			//Customer customer=goService.findCustomerByUsername(username);
			Good good=goService.findGoodById(id);
			//通过customer的id获取购物车对象car
			Customer customer=(Customer) session.getAttribute("customer");
			int a= customer.getId();
			Car car = goService.findCarByCustomerId(a);
			//Car car=(Car)session.getAttribute("car");
			int cid = car.getId();
			//通过购物车id获取购物车中已有的商品
			CarGoodLink temp=goService.findCarGoodLinkByCarIdAndGoodId(cid,id);
			if(temp!= null){
				int amount=temp.getAmount();
				temp.setAmount(++amount);
				goService.modifyCarGoodLink(temp);
				model.addAttribute("good", good);
				model.addAttribute("message", "添加成功");
			    
			}else{
				CarGoodLink link = new CarGoodLink();
				link.setCar(car);
				link.getCar().setId(cid);
			
				link.setGood(good);
				link.getGood().setId(id);
				goService.addCarGoodLink(link);
				model.addAttribute("good", good);
				model.addAttribute("message", "添加成功");
			}
			return "Good_info";	
			
//			List<Good> goods = goService.findGoodByCarId(car.getId());
//			//flag标记是否已经存在商品
//			boolean flag = true;
//			for(Good goodid : goods) {
//				  if(goodid.getId().equals(id)){
//					  flag = false;
//				  }
//			}
//			
//			if(flag){
//				CarGoodLink link = new CarGoodLink();
//				link.setCar(car);
//				System.out.print(link.getCar().getId());
//				link.setGood(good);
//				goService.addCarGoodLink(link);
//				model.addAttribute("good", good);
//				model.addAttribute("message", "添加成功");
//			}else{
//				
//				model.addAttribute("message", "购物车中已有此商品");
//			}
//			return "Good_info";		
	}
	
	
	@RequestMapping(value="/modifyCarGoodLink")
	 public ModelAndView modifyCarGoodLink(String flag,@ModelAttribute CarGoodLink carGoodLink,HttpSession session,
			 ModelAndView mv){
		if(flag.equals("1")){
			Car car=(Car) session.getAttribute("car");
			int cid=car.getId();
			Integer gid=carGoodLink.getGood().getId();
			//Integer cid=carGoodLink.getCar().getId();
			CarGoodLink target=goService.findCarGoodLinkByCarIdAndGoodId(cid,gid);
		mv.addObject("car",target);
		mv.setViewName("car/showModifyCarGoodLink");		
		}else{
			int c =goService.modifyCarGoodLink(carGoodLink);
			System.out.print(c);
			mv.addObject("message", "修改成功");
			mv.setViewName("car/carGoodLink");
		}
		return mv;	
	}	
	
	@RequestMapping(value="/removeCarGoodLinkByGoodId/{gid}")
	 public ModelAndView removeCarGoodLinkByGoodId(@PathVariable int gid,
			 @ModelAttribute CarGoodLink carGoodLink,HttpSession session,ModelAndView mv){
		//获取相应customer
		Customer customer = (Customer)session.getAttribute("customer");
		int uid = customer.getId();
		//通过uid获取用户购物车
		Car car= goService.findCarByCustomerId(uid);
		int cid=car.getId();
//		Integer gid=carGoodLink.getGood().getId();
//		Integer cid=carGoodLink.getCar().getId();
//		OrderGoodLink target=goService.findOrderGoodLinkByOrderIdAndGoodId(cid,gid);
		int c=goService.removeCarGoodLinkByCarIdAndGoodId(cid, gid);
		System.out.print(c);
		List<Good> goods = goService.findGoodByCarId(cid);
		mv.addObject("goods",goods);
		mv.setViewName("Car_info");
		return mv;	
	}	
	
	
}
