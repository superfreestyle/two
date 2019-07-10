<%@page import="org.omg.PortableInterceptor.USER_EXCEPTION" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myCustomer_info.css">
<meta name="author" content="Stephen Korecky">	
<script type="text/javascript" src="jquery/jquery.min.js"></script>
	<script type="text/javascript" src="js/addclear.js"></script>
	<script>
	
	$(function(){
		  $("#scan_input").addClear();
		  $("input").addClear({top : 10, right : -440});
	});
	
	$(document).ready(function(){
		$(".index1").mouseover(function(){
			$(this).find("img").fadeIn("0.001");
		});
		$(".index1").mouseleave(function(){
			$(this).find("img").fadeOut("0.001");
		});
		$(".index2").mouseover(function(){
			$(this).find("img").fadeIn("0.001");
		});
		$(".index2").mouseleave(function(){
			$(this).find("img").fadeOut("0.001");
		});
		$(".index3").mouseover(function(){
			$(this).find("img").fadeIn("0.001");
		});
		$(".index3").mouseleave(function(){
			$(this).find("img").fadeOut("0.001");
		});
		$(".index4").mouseover(function(){
			$(this).find("img").fadeIn("0.001");
		});
		$(".index4").mouseleave(function(){
			$(this).find("img").fadeOut("0.001");
		});
	});
	</script>
</head>
<body>
	<div id="header" >
		<div id="op_regist">
			<a id="regist" href="Regist">免费注册</a>
		</div>
	
		<div id="usermsg">
			<a id="user_info" href="Customer_info">个人信息</a><b>|</b>
			<a id="user_collect" href="">收藏夹</a><b>|</b>
			<a id="user_car" href="Car_info">购物车</a><b>|</b>
			<a id="service" href="">联系客服</a>
		</div>
		
	</div>
	
	<!-- 引入Scan.jsp -->
	<jsp:include page="customer/User.jsp"></jsp:include>
	
	<!-- 引入User.jsp -->
	<jsp:include page="customer/User.jsp"></jsp:include>
	
	<div id="div_customermsg">
		<form id="form_msg" action="modifyCustomer" method="post">
			<p>我的信息</p>
			<hr style="width:900px"/><br>
			<table>				
				<tr>
					<th>用户名：</th>
					<td><input name="username" value="${customer.getUsername() }"></td>
				</tr>
				<tr>
					<th>性别：</th>
					<td>
						<input type="radio" name="sex" id="sex1" value="male" $customer.getSex()eq"male"? "checked" : "" } > 
							<font size="3px">男</font>
						<input type="radio" name="sex" id="sex2" value="female" ${customer.getSex()eq"female"? "checked" : "" } >
							<font size="3px">女</font>
					</td>
				</tr>
				<tr>
					<th>年龄：</th>
					<td><select name="age"  id="ageinput" >
						<%
						for(int i = 16;i<101;i++){
						%>
							<option value="<%=i %>"><%=i %></option>
						<%
						}
						%>	
					</select></td>
				</tr>
				<tr>
					<th>手机号码：</th>
					<td><input name="telnumber"  value="${customer.getTelnumber() }"></td>
				</tr>
				<tr>
					<th>邮箱地址：</th>
					<td><input name="email"  value="${customer.getEmail() }"></td>
				</tr>
				<tr>
					<th>支付密码：</th>
					<td><input name="paynumber"  value="${customer.getPaynumber() }"></td>
				</tr>	
				<center>${message }</center>
			</table>
			<br><hr style="width:900px"/>
			<button class="buf_submit" type="submit" >提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交</button>
		</form>
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
</html>