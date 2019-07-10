<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="keywords" content="jQuery,easyform,form validation,javascript,js,jq,jquery,表单验证,插件">
    <meta name="description" content="jQuery.Easyform表单验证插件">

    <title>jQuery.Easyform Demo</title>

    <meta name="description" content="">
    <meta http-equiv="cleartype" content="on">

    <link rel="stylesheet" href="css/myRegist.css">
    <link rel="stylesheet" href="js/easyform/easyform.css">

    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/easyform/easyform.js"></script>

</head>
<body>

<div class="page" >
    <div class="form-div">
    	<div class="background" style="background-image:url(image/background/background.jpg)"></div>
        <form id="reg-form" action="addCustomer" method="post">
			<h1>用户注册</h1>
            <table>
                <tr>
                    <th><label class="item" onclick="getfocus(0)" >用户名:</label></th>
                    <td>
					<input name="username" type="text" class="input" id="username"
                               data-easyform="length:4 16;char-normal;real-time"
                               data-message="用户名必须为4—16位的英文字母或数字"
                               data-easytip="position:right;class:easy-blue;" >
                    </td>
                </tr>
                <tr>
                    <th><label class="item" onclick="getfocus(1)" >用户密码:</label></th>
                    <td><input name="password" type="password" class="input" id="password"
							   data-easyform="length:6 16;real-time"
                               data-message="密码必须为6—16位"
                               data-easytip="position:right;class:easy-blue;"></td>
                </tr>
                <tr>
                    <th><label class="item" onclick="getfocus(2)" >确认密码:</label></th>
                    <td><input name="password2" type="password" class="input" ></td>
                </tr>            
				<tr>
					<th><label class="item" id="sex" onclick="getfocus2()">性别:</label></th>
					<td>
						<input type="radio" name="sex" id="sex1" value="male"">
							<font size="3px">男</font>
						<input type="radio" name="sex" id="sex2" value="female">
							<font size="3px">女</font>						
					</td>
				</tr>
            	<tr>
					<th><label class="item"  id="sex" onclick="getfocus3()" >年龄:</label></th>
					<td><select name="age"  id="ageinput" >
						<%
						for(int i = 16;i<101;i++){
						%>
							<option value="<%=i %>"><%=i %></option>
						<%
						}
						%>	
					</select></td>
				</tr>
				<tr>
					<th><label class="item" onclick="getfocus(3)" >手机号码:</label></th>
					<td><input class="input" name="telnumber" type="text"
							   data-easyform="required:true;length:11 11;number"
							   data-message="手机号码应为11位"
							   data-easytip="class:easy-blue;position:right"></td>
				</tr>
				<tr>
					<th><label class="item" onclick="getfocus(4)" >邮箱地址:</label></th>
					<td><input class="input" name="email" type="text" 
							   data-easyform="required;email;real-time"
                               data-message="Email格式要正确"
                               data-easytip="class:easy-blue;position:right"></td>
				</tr>
				<tr>
					<th><label class="item" onclick="getfocus(5)">支付密码:</label></th>
					<td><input class="input" name="paynumber" type="text"
							   data-easyform="required;length:4 4;real-time"
							   data-message="支付密码必须为4位数字"
							   data-easytip="class:easy-blue;position:right;"></td>
				</tr>
           </table>
					<button class="buf_regist" type="submit" >注册</button>
					<input class="buf_reset" type="reset" value="重置" >
					<br><a href="Login">我已有帐号，直接登录</a>
        </form>
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