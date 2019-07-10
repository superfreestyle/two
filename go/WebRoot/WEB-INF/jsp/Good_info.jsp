<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myGood_info.css">
<meta name="author" content="Stephen Korecky">	
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/addclear.js"></script>
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
	
		
	
	
	<div id="div_good">
		<!-- 引入Good.jsp -->
		<jsp:include page="customer/Good.jsp"></jsp:include>
	</div>
	
	<!-- 引入Index.jsp -->
	<jsp:include page="customer/Index.jsp"></jsp:include>

	
</body>
</html>