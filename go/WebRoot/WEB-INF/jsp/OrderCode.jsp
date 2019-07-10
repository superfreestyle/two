<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/myHomepage.css">
<meta name="author" content="Stephen Korecky">	
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/addclear.js"></script>
	<script>
	$(function(){
		  $("#scan_input").addClear();
		  $("input").addClear({top : 10, right : -440});
	});
	
	</script>

  </head>
  
  <body>
	<div id="header" >
		<a>首页</a>
	
		<div id="usermsg">
		</div>
		
	</div>
	

	
	
		<div id="div_good">
		<form action="findOrderById" method="post">
			<input name="id" type="text" >请输入订单编号
			<button type="submit" class="scan_btn">提交</button>
		</form>  

	
		
		<div id="car_good">
			<form action= method="post">

				<table style="overflow:scroll;">	
						<!-- 遍历商品集合 -->
						<c:forEach items="${order.goods}" var="good">
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
  </body>
</html>
