<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
    <meta name="keywords" content="jQuery,easyform,form validation,javascript,js,jq,jquery,表单验证,插件">
    <meta name="description" content="jQuery.Easyform表单验证插件">

    <title>jQuery.Easyform Demo</title>

    <meta name="description" content="">
    <meta http-equiv="cleartype" content="on">

    <link rel="stylesheet" href="css/myLogin.css">
    <link rel="stylesheet" href="js/easyform/easyform.css">

    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/easyform/easyform.js"></script>

</head>
<body>

<div class="page">
	<div class="background" style="background-image:url(image/background/background.jpg)"></div>
    <div class="form-div">
        <form id="reg-form" action="customerLogin" method="post">
			<h1>用户登录</h1>

				<input name="username" type="text" class="input" id="username"
                               data-easyform="length:4 16;char-normal;real-time"
                               data-message="用户名必须为4—16位的英文字母或数字"
                               data-easytip="position:right;class:easy-blue;" 
							   data-message-ajax="用户名已存在!">

				<input name="password" type="password" class="input" id="password"
							   data-easyform="length:6 16;real-time"
                               data-message="密码必须为6—16位"
                               data-easytip="position:right;class:easy-blue;">

				<input class="submit" type="submit" value="用户登录" accesskey="e">
				<input class="forgive" type="text" value="忘记密码" >
				<br><a id="regist" href="Regist">用户注册</a>
        </form>
        <p>${requestScope.message }</p>
    </div>
    <div class="footer-hd">
    	<hr style="width:1200px"/>
	    <p>
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
</div>

<script>

    $(document).ready(function ()
    {
        var v = $('#reg-form').easyform();
    });

	function getfocus(i) {
		var txt = document.getElementsByClassName("input");
		txt[i].focus();
	}
	function getfocus2() {
		var txt = document.getElementById("sex1");
		txt.checked="checked";		
	}
	function getfocus3() {
		var txt = document.getElementById("ageinput");
		txt.focus();
	}
</script>

</body>
</html>
