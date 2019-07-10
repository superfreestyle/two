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
	<div id="div_scan">
		<form action="findGood?pageIndex=6" method="post">
			<input name="name" type="text" class="scan_input" id="scan_input" >
			<button type="submit" class="scan_btn">搜索</button>
		</form>   
	</div>
</body>
</html>