package com.xkjsj.go.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import com.xkjsj.go.service.GoService;
import com.xkjsj.go.domain.Seller;
//import com.xkjsj.go.util.common.GoConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SellerController {
	@Autowired
	@Qualifier("goService")
	private GoService goService;
	
	@RequestMapping(value="/addSeller")
	 public ModelAndView addSeller(@ModelAttribute Seller seller,
			 ModelAndView mv){
		int c=goService.addSeller(seller);
		System.out.print(c);
		if (c==1){
		mv.addObject("message", "注册成功");
		mv.setViewName("sellerLoginForm");}
		else {
			mv.addObject("message", "注册失败");
			mv.setViewName("sellerRegisterForm");}
		return mv;
	}
	
	
	@RequestMapping(value="/sellerLogin")
	 public ModelAndView sellerlogin(@RequestParam("username") String username,
			 @RequestParam("password") String password,
			 HttpSession session,
			 ModelAndView mv){
		Seller seller = goService.sellerLogin(username, password);
		if(seller != null){
			session.setAttribute("seller", seller);
			mv.setViewName("SellerHomepage");
		}else{
			mv.addObject("message", "输入错误!请重新输入");
			mv.setViewName("Sellerlogin");
		}
		return mv;		
	}

	
	/*	@RequestMapping(value="/seller/findSellerById")
	 public ModelAndView findSellerById(String id,
			 ModelAndView mv){
		Seller seller =goService.findSellerById(Integer.parseInt(id));
		if(seller != null){
			
		    mv.setViewName("seller/showSellerById");
		}else{
			mv.addObject("message", "输入错误!请重新输入");
			mv.setViewName("seller/findSellerById");
		}
		return mv;	
	}
	
	@RequestMapping(value="/seller/findSeller")
	 public String findSeller(Integer pageIndex,@ModelAttribute Seller seller,
			 Model model){
		System.out.println("seller = " + seller);
		com.xkjsj.go.util.tag.PageModel pageModel = new com.xkjsj.go.util.tag.PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Seller> sellers = goService.findSeller(seller, pageModel);
		model.addAttribute("sellers", sellers);
		model.addAttribute("pageModel", pageModel);
		return "seller/seller";
	}*/
	
	@RequestMapping(value="/modifySeller")
	 public ModelAndView modifySeller(String flag,@ModelAttribute Seller seller,
			 ModelAndView mv){
		if(flag.equals("1")){
		Seller target=goService.findSellerById(seller.getId());
		mv.addObject("seller",target);
		mv.setViewName("seller/showModifySeller");		
		}else{
			int c =goService.modifySeller(seller);
			System.out.print(c);
			mv.setViewName("seller/findSellerById");
		}
		return mv;	
	}	
	
	@RequestMapping(value="/removeSeller")
	 public ModelAndView removeSeller(String id,
			 ModelAndView mv){
		int c=goService.removeSeller(Integer.parseInt(id));
		System.out.print(c);
		mv.setViewName("seller/findSellerById");
		return mv;	
	}	
}
