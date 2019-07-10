package com.xkjsj.go.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

import com.xkjsj.go.domain.Category;
import com.xkjsj.go.domain.Seller;
import com.xkjsj.go.domain.Store;
import com.xkjsj.go.service.GoService;

@Controller
public class CategoryController {
	@Autowired
	@Qualifier("goService")
	private GoService goService;
	
	@RequestMapping(value="/category/findAllCategory")
	public String findAllCategory(
			 Model model){
		List<Category> categorys = goService.findAllCategory();
		model.addAttribute("categorys", categorys);
		return "category/showAllCategory";
	}
	
	@RequestMapping(value="/category/findCategoryById")
	 public String findCategoryById(@RequestParam("id") String id,
			 Model model){
		System.out.print(id);
		Category category =goService.findCategoryById(Integer.parseInt(id));
		if(category != null){
			model.addAttribute("category", category);
			return "category/showCategoryById";
		}else{
			model.addAttribute("message", "输入错误!请重新输入");
			return "category/showAllCategory";
		}
		
	}
}
