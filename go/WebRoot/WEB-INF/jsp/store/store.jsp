<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'store.jsp' starting page</title>
    
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
    欢迎${sessionScope.customer.username }访问
 欢迎${sessionScope.seller.username }访问  
<br>
    ${requestScope.goods}
<table border="1">
	
		<tr>
		   
			<c:forEach items="${requestScope.stores}" var="good">
			
			<td>${store.id}</td>
			<td>${store.name}</td>
			<td>${store.seller.id}</td>
		
				</c:forEach>
		</tr>

</table>
  </body>
</html>
