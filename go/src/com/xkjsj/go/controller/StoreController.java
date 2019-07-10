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

import com.xkjsj.go.domain.Category;
import com.xkjsj.go.domain.Good;
import com.xkjsj.go.domain.Order;
import com.xkjsj.go.domain.Seller;
import com.xkjsj.go.domain.Store;
import com.xkjsj.go.service.GoService;

@Controller
//@RequestMapping(value="/store")
public class StoreController {
	@Autowired
	@Qualifier("goService")
	private GoService goService;
	
	@RequestMapping(value="/addStore")
	 public String addStore(@RequestParam("flag") String flag,@ModelAttribute Store store,HttpSession session,
			 Model model){
		if(flag.equals("1")){
			return "store/addStoreForm";
		}
		else{
		Seller seller=(Seller) session.getAttribute("seller");
		int a=seller.getId();
	    store.setSeller(seller);
	    store.getSeller().setId(a);
		int c=goService.addStore(store);
		System.out.print(c);
		if (c==1){
			model.addAttribute("message", "创建店铺成功");
		
			return "redirect:/findStoreBySellerId";}
		else {
			model.addAttribute("message", "创建店铺失败");
			return "store/addStore";}}

	}
	
	
	
	
	@RequestMapping(value="/findStoreById")
	 public String findStoreById(String id,HttpSession session,
			 Model model){
		Store store =goService.findStoreById(Integer.parseInt(id));
		if(store != null){
			model.addAttribute("store", store);
			
			return "store/showStoreById";
		}else{
			model.addAttribute("message", "输入错误!请重新输入");
			return "store/findStoreById";
		}
		
	}
	
	
	@RequestMapping(value="/findStore")
	 public String findGood(Integer pageIndex,@ModelAttribute Store store,
			 Model model){
		System.out.println("store = " + store);
		com.xkjsj.go.util.tag.PageModel pageModel = new com.xkjsj.go.util.tag.PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Store> stores = goService.findStore(store, pageModel);
		model.addAttribute("stores", stores);
		model.addAttribute("pageModel", pageModel);
		return "store/store";
	}
	
	@RequestMapping(value="/findStoreBySellerId")
	 public String findStoreBySellerId(
			 Model model, HttpSession session){
		Seller seller=(Seller) session.getAttribute("seller");
		int a=seller.getId();
	    Store store = goService.findStoreBySellerId(a);
		model.addAttribute("store", store);
		session.setAttribute("store", store);
		return "store/showStoreBySellerId";
	}
	
	
	@RequestMapping(value="/findStoreByStoreId")
	 public String findOrderBySellerId(
			 Model model, HttpSession session){
		Store store=(Store) session.getAttribute("store");
		int a=store.getId();
	    List<Good> goods = goService.findGoodByStoreId(a);
		for(Good o:goods){
			int c=o.getId();
			goService.findOrderGoodLinkByGoodId(c);
		}
		model.addAttribute("store", store);
		session.setAttribute("store", store);
		return "store/showStoreBySellerId";
	}
	
	@RequestMapping(value="/modifyStore")
	 public ModelAndView modifyStore(String flag,@ModelAttribute Store store,
			 ModelAndView mv){
		if(flag.equals("1")){
			Store target=goService.findStoreById(store.getId());
		mv.addObject("store",target);
		mv.setViewName("store/showModifyStore");		
		}else{
			int c =goService.modifyStore(store);
			System.out.print(c);
			mv.addObject("message", "修改成功");
			mv.setViewName("store/store");
		}
		return mv;	
	}	
	
	@RequestMapping(value="/removeStore")
	 public ModelAndView removeStore(@ModelAttribute Store store,
			 ModelAndView mv){
		int c=goService.removeStore(store.getId());
		System.out.print(c);
		mv.setViewName("store/findStoreById");
		return mv;	
	}	
}
