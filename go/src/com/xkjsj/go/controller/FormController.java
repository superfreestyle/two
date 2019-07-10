package com.xkjsj.go.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class FormController {
	@RequestMapping(value="/{formName}")
	 public String Form(@PathVariable String formName){
		// 动态跳转页面
		return formName;}
	
//	@RequestMapping(value="/{formName}"//只要一个form跳转，多写会出现500错误HTTP Status 500 - Servlet.init() for servlet spring threw exception
//	 public String sellerLoginForm(@PathVariable String formName){// servlet  init()方法调用失败
//		// 动态跳转页面
//		return formName;}
	
}
