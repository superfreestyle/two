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
	

	


		
		<div id="car_good">
			<p>订单详情</p>
		<hr style="width:920px"/><br>
		<div id="car_good">
		<c:forEach items="${requestScope.goods}" var="good">
			
			<input name="id" style="border:0px;width:100px;text-align:center;outline:none;" value="${good.id}">
				<table cellspacing="0">
					<tr>
						<th width="12%">编号</th>
						<th width="12%">总价</th>
						<th width="12%">顾客id</th>
						<th width="12%">状态</th>
						<th width="25%">创建日期</th>
						<th width="25%">地址</th>
						<th width="25%">备注</th>
						<th width="15%">操作</th>
					</tr>
				</table>
					
						<!-- 遍历商品集合 -->
						<c:forEach items="${good.orders}" var="order">
						
						
						<table style="overflow:scroll;">
							<tr>
								<td width="10%">
									<input name="code" style="border:0px;width:100px;text-align:center;outline:none;" value="${order.code }" />
								</td>
								<td width="6%">
									<input name="total" style="border:0px;width:100px;text-align:center;outline:none;" value="${order.total }" />
								</td>
								<td width="10%">
									<input name="cid" style="border:0px;width:100px;text-align:center;outline:none;" value="${order.customer.id }" />
								</td>
								<td width="15%">
								<input name="status" style="border:0px;width:100px;text-align:center;outline:none;" value="${order.status }" />
								</td>
								<td width="10%">
									<textarea name="createDate" style="border:0px;width:190px;height:50px;resize:none;outline:none;"readonly="readonly">${order.createDate}</textarea>
								</td>
								<td width="15%">
									<textarea name="address" style="border:0px;width:190px;height:50px;resize:none;outline:none;"readonly="readonly">${order.address}</textarea>
								</td>		
								<td width="10%">
									<textarea name="remark" style="border:0px;width:190px;height:50px;resize:none;outline:none;"readonly="readonly">${order.remark}</textarea>
								</td>	
								<td width="15%">
									<a class="del" href="/go/modifyOrder1?id=${order.id}">发货</a>
								</td>
							
									
												
							</tr>
							</table>
						${requestScope.message}
						</c:forEach>
						
				
			
			</c:forEach>
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
  