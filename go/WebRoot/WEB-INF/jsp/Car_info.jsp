<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<% String path=request.getContextPath(); %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myCar_info.css">
<meta name="author" content="Stephen Korecky">	
<script type="text/javascript" src="jquery/jquery.min.js"></script>
	<script type="text/javascript" src="js/addclear.js"></script>
	
</head>
<body>
	<div id="header" >
	
		<div id="op_regist">
			<a id="regist" href="Regist">免费注册</a>
		</div>
	
		<div id="usermsg">
			<a id="user_info" href="Customer_info">个人信息</a><b>|</b>
			<a id="user_collect" href="Order_info">收藏夹</a><b>|</b>
			<a id="user_car" href="Car_info">购物车</a><b>|</b>
			<a id="service" href="">联系客服</a>
		</div>
	</div>
	
	<!-- 引入User.jsp -->
	<jsp:include page="customer/User.jsp"></jsp:include>
	
	<div id="div_car">
		<p>购物车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}</font></p>
		<hr style="width:920px"/><br>
		<div id="car_good">
			<form action="addOrder/${username}" method="post">
				<table cellspacing="0">
					<tr>
						<th width="15%">图片</th>
						<th width="25%">商品</th>
						<th width="15%">价格</th>
						<th width="15%">数量</th>
						<th width="15%">小计</th>
						<th width="15%">操作</th>
					</tr>
				</table>
				<table style="overflow:scroll;">	
						<!-- 遍历商品集合 -->
						<c:forEach items="${car.goods}" var="good">
							<tr>
								<td width="15%">
									<img width="50px" height="50px"src="${pageContext.request.contextPath}/${good.image }">
								</td>
								<td width="25%">
									<textarea name="goodname" style="border:0px;width:190px;height:50px;resize:none;outline:none;"readonly="readonly">${good.name}</textarea>
								</td>
								<td width="15%">
									<input name="price" style="border:0px;width:100px;text-align:center;outline:none;" value="${good.price }" />
								</td>
								<td width="15%">
									<div class="jian">-</div>
										<input class="number" readonly="readonly"  type="text" value="1" />
									<div class="jia">+</div>
								</td>
								<td width="15%">
									<input id="account" border="0">
								</td>
								<td width="15%">
									<a class="del" href="${pageContext.request.contextPath}/removeCarGoodLinkByGoodId/${good.id}">删除</a>
								</td>						
							</tr>
						</c:forEach>
						<tr>
							<td colspan="6">
								总计：<input name="total" style="border:0px;width:100px;text-align:center;outline:none;" value="0">
								<button type="submit" >提交</button>
							</td>
						</tr>
				</table>
			</form>	
		</div>
	</div>
	
	<!-- 引入Index.jsp -->
	<jsp:include page="customer/Index.jsp"></jsp:include>
	
	<div class="footer-hd" >
	    <p>
	    <hr style="width:1200px"/>
	    <a href="">阿里巴巴集团</a>
	    <b>|</b>
	    <a href="">阿里巴巴国际站</a>
	    <b>|</b>
	    <a href="">阿里巴巴中国站</a>
	    <b>|</b>
	    <a href="">全球速卖通</a>
	    <b>|</b>
	    <a href="//www.taobao.com">淘宝网</a>
	    <b>|</b>
	    <a href="">天猫</a>
	    <b>|</b>
	    <a href="">聚划算</a>
	    <b>|</b>
	    <a href="">一淘</a>
	    <b>|</b>
	    <a href="">阿里妈妈</a>
	    <b>|</b>
	    <a href="">阿里云计算</a>
	    <b>|</b>
	    <a href="">云OS</a>
	    <b>|</b>
	    <a href="">万网</a>
	    <b>|</b>
	    <a href="">支付宝</a>
	    </p>
	    <hr style="width:1200px"/>
	</div>
	
</body>
<script>
	$(function(){
		  $("#scan_input").addClear();
		  $("input").addClear({top : 10, right : -440});
	});
	
	
	$(document).ready(function(){
		//加的效果
		$(".jia").click(function(){
			var n=parseInt($(this).prev().val());
			if(num==0){ return;}
			$(this).prev().val(n+1);
		});
		//减的效果
		$(".jian").click(function(){
			var n=parseInt($(this).next().val());
			if(num==0){ return}
			$(this).next().val(n+1);
		});
	})
	

	var total = 0;
	$("input[name='price']").each(  
		function(){  
  			var n = parseInt($(this).val());
  			total = total+n;
  			$("input[name='total']").val(total);
  	})  
	
	</script>
</html>