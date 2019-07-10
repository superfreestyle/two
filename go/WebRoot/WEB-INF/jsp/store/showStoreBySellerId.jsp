<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/myCar_info.css">
<meta name="author" content="Stephen Korecky">	
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/addclear.js"></script>
	
</head>
<body>
	<div id="header" >
	
		<div id="op_regist">
			<a id="regist" href="Regist">免费注册</a>
		</div>
	
		<div id="usermsg">
			<a id="user_info" href="">个人信息</a><b>|</b>
			<a id="user_collect" href="">收藏夹</a><b>|</b>
			<a id="user_car" href="car_info">购物车</a><b>|</b>
			<a id="service" href="">联系客服</a>
		</div>
	</div>
	

	
	<div id="div_car">
		<p>商店详情</p>
		<hr style="width:920px"/><br>
		<a href="/go/findGoodByStoreId"><input type="submit" name="查看订单" value="查看订单"></a><br>
		 <a href="/go/addGood?flag=1"><input type="submit" name="创建商品" value="创建商品"></a><br>
		
		<div id="car_good">
			<form action= method="post">
				<table cellspacing="0">
					<tr>
						<th width="15%">图片</th>
						<th width="25%">商品</th>
						<th width="15%">价格</th>
						
						<th width="15%">类别</th>
						<th width="30%">操作</th>
					</tr>
				</table>
				<table style="overflow:scroll;">	
						<!-- 遍历商品集合 -->
						<c:forEach items="${store.goods}" var="good">
							<tr>
								<td width="15%">
									<img width="50px" height="50px"src="${pageContext.request.contextPath }/${good.image }">
								</td>
								<td width="25%">
									<textarea name="goodname" style="border:0px;width:190px;height:50px;resize:none;outline:none;"readonly="readonly">${good.name}</textarea>
								</td>
								<td width="15%">
									<input name="price" style="border:0px;width:100px;text-align:center;outline:none;" value="${good.price }" />
								</td>
								<td width="15%">
									<input id="account" border="0">
								</td>
								<td width="15%">
									<a class="del" href="/go/removeGood?id=${good.id}">删除</a>
								</td>		
								<td width="15%">
									<a  href="/go/modifyGood?flag=1&id=${good.id}">修改</a>
								</td>				
							</tr>
						</c:forEach>
						<tr>
						</tr>
				</table>
			</form>	
		</div>
	</div>
	

	
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
  
  
  

    
    
    
 

	
