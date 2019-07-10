<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myHomepage.css">
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
	<jsp:include page="customer/Scan.jsp"></jsp:include>
	
	<!-- 引入User.jsp -->
	<jsp:include page="customer/User.jsp"></jsp:include>
	
	<div id="div_category" >分类

	</div>
	
	<div id="div_good">
	<c:forEach items="${goods}" var="good" end="2">
		<a href="findGoodById?id=${good.id}&username=${username}"><div class="good"><img src="${good.image}"><center>${good.name}</center></div></a>
	</c:forEach>
	<c:forEach items="${goods}" var="good" begin="3" end="5">
		<a href="findGoodById?id=${good.id}&username=${username}"><div class="good good2"><img src="${good.image}"><center>${good.name}</center></div></a>
	</c:forEach>
		
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