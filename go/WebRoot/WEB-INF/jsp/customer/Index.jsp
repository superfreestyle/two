<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/myHomepage.css">
<meta name="author" content="Stephen Korecky">	
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="js/addclear.js"></script>
	<script>
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
	
	<div id="div_index">
		<div class="index1" class="index">
			<img class="index1_img" src="${pageContext.request.contextPath}/image/index/shouye.jpg" style="display:none;">
			<a class="index1_a" href="${pageContext.request.contextPath}/Homepage">首页</a>
		</div>
		<hr style="width:40px;height:1px;background-color:#CDCDCD;border:0px;"/>
		<div class="index2" class="index">
			<img class="index2_img" src="${pageContext.request.contextPath}/image/index/gerenxinxi.jpg" style="display:none;">	
			<a  class="index2_a" href="${pageContext.request.contextPath}/Customer_info" >个人信息</a>
		</div>
		<hr style="width:40px;height:1px;background-color:#CDCDCD;border:0px;"/>
		<div class="index3" class="index">
			<img class="index3_img" src="${pageContext.request.contextPath}/image/index/shoucangjia.jpg" style="display:none;">
			<a class="index3_a" href="/go/findOrderByCustomerId">订单表</a>
		</div>
		<hr style="width:40px;height:1px;background-color:#CDCDCD;border:0px;"/>
		<div class="index4" class="index">
			<img class="index4_img" src="${pageContext.request.contextPath}/image/index/gouwuche.jpg" style="display:none;">
			<a class="index4_a" href="/go/findCarByCustomerId">购物车</a>
		</div>
	</div>

</body>
</html>