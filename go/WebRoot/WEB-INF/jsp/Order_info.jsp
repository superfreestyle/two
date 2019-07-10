<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myOrder_info.css">
<meta name="author" content="Stephen Korecky">	
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
	<% String path=request.getContextPath(); %>
</head>
<body>
	<div id="header" >
	
		<div id="op_regist">
			<a id="regist" href="Regist">免费注册</a>
		</div>
	
		<div id="usermsg">
			<a id="user_info" href="Customer_info">个人信息</a><b>|</b>
			<a id="user_collect" href="Order_info">订单表</a><b>|</b>
			<a id="user_car" href="car_info">购物车</a><b>|</b>
			<a id="service" href="">联系客服</a>
		</div>
	</div>
	
	<!-- 引入User.jsp -->
	<jsp:include page="customer/User.jsp"></jsp:include>
	
	<div id="div_car">
		<p>订单详情</p>
		<hr style="width:920px"/><br>
			<div id="tablediv" style="overflow-y:scroll;overflow-x:hidden;">
				<table >	
				<tbody>
					<c:forEach items="${requestScope.orders}" var="order">
					
						<tr>
							<th><input readonly="readonly" text="text" name="id" value="编号：${order.id}"></th>
							<th><input readonly="readonly" type="text" name="creaDate" value="提交日期：${order.createDate}"></th>
							<th><input readonly="readonly" type="text" name="total" value="总价：${order.total}"></th>
							<th><input readonly="readonly" type="text" name="status" value="状态：${order.status}"></th>
						</tr>	
					
						
							<c:forEach items="${order.goods}" var="good">
								<tr>
									<td >${good.id}</td>
									<td>${good.name}</td>
									<td><img weight="50px" height="50px" src="${good.image}"></td>
									<td>${good.price}</td>
								</tr>
							</c:forEach>
						
						
						<tr>
							<td colspan="5">
								<a href="OrderModify?id=${order.id}&createDate=${order.createDate}"><button type="submit" name="支付" value="支付">支付</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="removeOrder?id=${order.id}"><button name="删除" value="删除">删除</button></a>
								<a href="modifyOrder2?id=${order.id}"><button name="确认收货" value="确认收货">确认收货</button></a>
							</td>
						</tr>
						
					</c:forEach>
				</tbody>
				</table>
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