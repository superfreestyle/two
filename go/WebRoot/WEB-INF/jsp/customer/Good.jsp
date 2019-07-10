<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="jquery/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myGood_info.css">
<title>Insert title here</title>
</head>
<body>
	<div id="div_goodselect">
	
		<div id="goodimage">
			<img id="showimg" class="showimg" src="${good.image}">
		</div>
		
		<p class="name">${ good.name }</p>
		
		<div id="pricebackground">
			<p class="price">价格：
				<font class="pricenumber">¥${ good.price }</font>
			</p>
			<p class="postage">运费：
				<font class="postagenumber">¥10.00</font>
			</p>
		</div>
			
		<div id="op">
			<div id="style">
				<p>颜色分类：</p>
				<div id="style1" class="styledefault"><img src="${good.image}"  onclick="change()"></div>
			</div>
			<hr style="width:420px;margin-left:0px;border:0.5px solid #e4e4e4"/>
			<div id="size">
				<p>尺码：</p>
				<div id="size1" class="sizedefault">S</div>
				<div id="size2" class="sizedefault">M</div>
				<div id="size3" class="sizedefault">L</div>
				<div id="size4" class="sizedefault">XL</div>
			</div>
			<hr style="width:420px;margin-left:0px;border:0.5px solid #e4e4e4"/>
			<div id="amount">
				<p>数量：</p>
				<a href="javascript:void(0)" ><div class="jian">-</div></a>
					<input class="number" readonly="readonly" disabled="disabled" type="text" value="0" />
				<a href="javascript:void(0)" ><div class="jia">+</div></a>
				
				<div id="div_shownumber">
					<span>您选择了<input class="msg_number" value="0"></span>件</span>
				</div>
			</div>
		</div>
		
		<div id="buttonop">
			<a href="addCarGoodLink?id=${good.id}"><input id="joincar" type="submit" value="加入购物车" /></a>
		</div>
		
		<div id="promise">
			<p>
				<font>服务承诺：</font>
				30天质保&nbsp;&nbsp;正品保证&nbsp;&nbsp;极速退款&nbsp;&nbsp;七天无理由退换
			</p>
		</div>
		
	</div>
	
	<%--<div id="div_goodparameter">
		<div class="divtop">商品参数</div>
		<table  cellspacing="0" >
			<tr>
				<th width="30%">款式</th>
				<td>裙子</td>
			</tr>
			<tr>
				<th width="30%">风格</th>
				<td>韩式</td>
			</tr>
			<tr>
				<th width="30%">质地</th>
				<td>纯棉</td>
			</tr>
		</table>
	</div>
	
	<div id="div_goodintroduce">
		<div class="divtop">商品介绍</div>
	</div>
	
	<div id="div_goodremark">
		<div class="divtop">商品评论</div>
		<p>暂无商品评论信息</p>
	</div>
--%></body>
<script type="text/javascript">
	$("div#style img").click(function(){
		$("#showimg").attr("src",this.src);
	});
	
	$(".styledefault").mousedown(function(){
		$(".stylechecked").removeClass("stylechecked").addClass("styledefault");
		$(this).removeClass("styledefault").addClass("stylechecked");
	});
	
	$(".sizedefault").mousedown(function(){
		$(".sizechecked").removeClass("sizechecked").addClass("sizedefault");
		$(this).removeClass("sizedefault").addClass("sizechecked");
	});
	
	$(document).ready(function(){
		$(".styledefault").click(function(){
			$(".sizedefault").click(function(){
				$(".number").attr("disabled",false);
			})
		})
		$(".sizedefault").click(function(){
			$(".styledefault").click(function(){
				$(".number").attr("disabled",false);
			})
		})
	});
	
	
	$(".jia").click(function(){
		if($("input.number").attr("disabled") != "disabled"){
			var num = parseInt($("input.number").val());
			$("input.number").val(num+1);		
		}
	});
		
	$(".jian").click(function(){
		if($("input.number").attr("disabled") != "disabled"){
			var num = parseInt($("input.number").val());
			$("input.number").val(num-1);
			if(num < 1 ){
				alert("不能小于0！");
				$("input.number").val(0);
			}
		}
	});
	
	$(".jian").click(function(){
		var num = parseInt($("input.number").val());
		$("input.msg_number").val(num);
	});
	$(".jia").click(function(){
		var num = parseInt($("input.number").val());
		$("input.msg_number").val(num);
	});
	
</script>
</html>