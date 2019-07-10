package com.xkjsj.go.controller;



import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xkjsj.go.domain.Car;
import com.xkjsj.go.domain.Category;
import com.xkjsj.go.domain.Good;
import com.xkjsj.go.domain.Order;
import com.xkjsj.go.domain.Seller;
import com.xkjsj.go.domain.Store;
import com.xkjsj.go.service.GoService;

@Controller
public class GoodController {
	@Autowired
	@Qualifier("goService")
	private GoService goService;
	
	@RequestMapping(value="/addGood")
	 public String addGood(@RequestParam("flag") String flag,Integer category_ID,@ModelAttribute Good good,HttpSession session,
			 Model model){
		if(flag.equals("1")){
			return "good/addGoodForm";
		}
		else{
		Store store=(Store) session.getAttribute("store");
		int a=store.getId();
	    good.setStore(store);
	    good.getStore().setId(a);
	    this.genericAssociation(category_ID,good);
		int c=goService.addGood(good);
		System.out.print(c);
		if (c==1){
			model.addAttribute("message", "创建商品成功");
		
			return "redirect:/findStoreBySellerId";}
		else {
			model.addAttribute("message", "创建商品失败");
			return "good/addGoodForm";}}

	}
	
	@RequestMapping(value="/findGoodById")
	 public String findGoodById(@RequestParam("id") String id,HttpSession session,
			 Model model){
		Good good =goService.findGoodById(Integer.parseInt(id));
		if(good != null){
			model.addAttribute("good", good);

			return "Good_info";
		}else{
			model.addAttribute("message", "输入错误!请重新输入");
			return "good/findGoodById";
		}
		
	}
	
	
	@RequestMapping(value="/findGood")
	 public String findGood(Integer pageIndex,@ModelAttribute Good good,
			 Model model){
		System.out.println("good = " + good);
		com.xkjsj.go.util.tag.PageModel pageModel = new com.xkjsj.go.util.tag.PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Good> goods = goService.findGood(good, pageModel);
		model.addAttribute("goods", goods);
		model.addAttribute("pageModel", pageModel);
		return "SearchPage";
	}
	
	@RequestMapping(value="/findGoodByStoreId")
	 public String findGoodByStoreId(
			 Model model, HttpSession session){
		Store store=(Store) session.getAttribute("store");
		int a=store.getId();
	    List<Good> goods = goService.findGoodByStoreId(a);
		model.addAttribute("goods", goods);
		return "good/showGoodByStoreId";
	}
	
	
	@RequestMapping(value="/findGoodByOrderId")
	 public String findGoodByOrderId(
			 Model model, HttpSession session){
		Order order=(Order) session.getAttribute("order");
		int a=order.getId();
		List<Good> goods = (List)goService.findGoodByOrderId(a);
	    model.addAttribute("goods", goods);
		return "good/showGoodByOrderId";
	}
	
	@RequestMapping(value="/findGoodByCarId")
	 public String findGoodByCarId(
			 Model model, HttpSession session){
		Car car=(Car) session.getAttribute("car");
		int a=car.getId();
		List<Good> goods = (List)goService.findGoodByCarId(a);
	    model.addAttribute("goods", goods);
		return "good/showGoodByCarId";
	}
	
	@RequestMapping(value="/modifyGood")
	 public ModelAndView modifyGood(@RequestParam("flag") String flag,String id,@ModelAttribute Good good,HttpSession session,
			 ModelAndView mv){
		if(flag.equals("1")){
			Good target=goService.findGoodById(Integer.parseInt(id));
			session.setAttribute("good",target);
		mv.setViewName("good/modifyGoodForm");		
		}else{
			Good good1=(Good) session.getAttribute("good");
			int a=good1.getId();
			good.setId(a);
			int c =goService.modifyGood(good);
			System.out.print(c);
			mv.addObject("message", "修改成功");
			mv.setViewName("good/good");
		}
		return mv;	
	}	
	
	@RequestMapping(value="/removeGood")
	 public ModelAndView removeGood(@ModelAttribute Good good,
			 ModelAndView mv){
		int c=goService.removeGood(good.getId());
		System.out.print(c);
		mv.setViewName("redirect:/findStoreBySellerId");
		return mv;	
	}	
	
	private void genericAssociation(Integer category_ID,
			Good good){
		if(category_ID != null){
			Category category = new Category();
			category.setId(category_ID);
			good.setCategory(category);
		}
	}
	
}
