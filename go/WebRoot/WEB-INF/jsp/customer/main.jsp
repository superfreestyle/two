<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <body>
  欢迎[${sessionScope.customer.username}] <a href="/go/customer/modifyCustomer?flag=1">修改个人信息</a><br>
     <a href="/go/customer/removeCustomer">删除账号</a><br>
    <a href="/go/car/findCarByCustomerId"><input type="submit" name="购物车" value="购物车"></a><br>
     <a href="/go/order/findOrderByCustomerId"><input type="submit" name="我的订单" value="我的订单"></a><br>
          <form action="good/findGood?pageIndex=6" method="post">
                  商品搜索：<br>
                  名字：<input type="text" name="name"><br>
                  特征：<input type="text" name="remark"><br>      
     <input type="submit" value="提交">
    </form>
             <form action="store/findStore?pageIndex=6" method="post">
                  店铺搜索：<br>
                  店铺名：<input type="text" name="name"><br>
                  特征：<input type="text" name="remark"><br>      
     <input type="submit" value="提交">
    </form>
  </body>
  </body>
</html>
