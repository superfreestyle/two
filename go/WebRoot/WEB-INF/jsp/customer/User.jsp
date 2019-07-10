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
<script type="text/javascript" src="jquery/jquery.min.js"></script>
	<script type="text/javascript" src="js/addclear.js"></script>
</head>
<body>
	<div id="div_user" >
		<img alt="" src="${pageContext.request.contextPath}/image/icon.png">
		<%-- JSTL判断用户登录时显示用户名，未登录时显示“用户登录” --%>
		<c:if test="${customer == null }">
			<c:set scope="session" var="username" value="用户登录"></c:set>
		</c:if>
		<c:if test="${customer != null }">
			<c:set scope="request" var="username" value="${customer.getUsername()}"></c:set>
		</c:if>
		<p><a id="a_login" href="Login"><font id="username">${username }</font></a></p>
	</div>
</body>
</html>