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
		<form action="findCustomerById" method="post">
			<input name="id" type="text" >请输入买家编号
			<button type="submit" class="scan_btn">提交</button>
		</form>   
		${requestScope.message}
		${requestScope.customer}
	<table >
		<tr>
						<th width="10%">用户名</th>
						<th width="10%">密码</th>
						<th width="5%">性别</th>
						<th width="5%">年龄</th>
						<th width="15%">电话</th>
						<th width="5%">状态</th>
						<th width="15%">创建日期</th>
						<th width="15%">地址</th>
					</tr>
		</table>
		
		<table >	
						
						
							<tr>
								
								<th width="10%">
									${customer.username}
								</th>
								<th width="10%">
									${customer.password }
								</th>
								<td width="5%">
									${customer.sex}
								</td>
								<td width="5%">
									${customer.age }
								</td>
								<td width="15%">
								${customer.telnumber }
								</td>
								<td width="5%">
									${customer.status }
								</td>
								<td width="15%">
								${customer.createDate}
								</td>
								<td width="15%">
									${customer.address}
								</td>
							</tr>
						
				</table>
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