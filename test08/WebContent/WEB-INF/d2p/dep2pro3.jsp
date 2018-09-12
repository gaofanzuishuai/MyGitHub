<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script>
	$(document).ready(function() {
		$("#yi .pro").click(function() {
			$(this).toggleClass("choose");
		})
		var box3left = $("#box3").offset().left;
		var box3top = $("#box3").offset().top;
		var box1left = $("#box1").offset().left;
		var box1top = $("#box1").offset().top;
		var width=parseFloat($("#box3").css("width"));
		var height=parseFloat($("#box3").css("height"));
		var startleft;
		var starttop;
		$("#yi .pro").draggable({
			start : function() {
				startleft=$(this).offset().left;
				starttop=$(this).offset().top;
			},
			stop : function() {
				var proId=$(this).data("id");
				var pro=$(this);
				var stopleft = $(this).offset().left;
				var stoptop = $(this).offset().top;
				if(stopleft>=box3left&&stopleft<=(box3left+width)&&stoptop>=box3top&&stoptop<=(box3top+height)){
					$.ajax({
						url:"DPS",
						type:"post",
						data:{type:"delete2",depId:${dep.id},proId:proId}, 
						dataType:"text",
						success:function(data){
							pro.css("position","static");
							$("#box3").append(pro);
							pro.css("position","relative");
							pro.css("left",0);
							pro.css("top",0);
						}
					})
				}else if(stopleft>=box1left&&stopleft<=(box1left+width)&&stoptop>=box1top&&stoptop<=(box1top+height)){
					var proId=$(this).data("id");
					var pro=$(this);
					var stopleft = $(this).offset().left;
					var stoptop = $(this).offset().top;
					$.ajax({
						url:"DPS",
						type:"post",
						data:{type:"add2",depId:${dep.id},proId:proId}, 
						dataType:"text",
						success:function(data){
							pro.css("position","static");
							$("#box1").append(pro);
							pro.css("position","relative");
							pro.css("left",0);
							pro.css("top",0);
						}
					})
				}else{
					$(this).offset({left:startleft,top:starttop});
				}
			}
		});
	})
</script>
<style type="text/css">
#box1, #box3 {
	width: 500px;
	height: 300px;
	border: 1px solid #337ab7;
	border-radius: 10px;
	margin: 0 auto;
	margin-top: 30px
}

#box2 {
	width: 50px;
	height: 60px;
	margin: 0 auto;
	margin-top: 10px;
	margin-bottom: 10px;
}

#add {
	width: 50px;
	height: 50px;
	background-color: #337ab7;
	color: #fff;
	font-size: 30px;
	margin-right: 10px;
	outline: none;
	border: 1px solid #337ab7;
}

#btn {
	width: 360px;
	margin: 0 auto;
	margin-top: 5px;
}

h2 {
	width: 100px;
	margin-left: 550px;
}

.pro {
	padding: 10px;
	float: left;
	background-color: #337ab7;
	color: #fff;
	margin-right: 10px;
	border: 1px solid #337ab7;
	border-radius: 5px;
	margin: 10px;
}

.choose {
	background-color: #f0ad4e;
	border: 1px solid #f0ad4e;
}

.pro:hover {
	cursor: pointer;
}
</style>
</head>
<body>
	<h2>${dep.name}</h2>
	<div id="yi">
		<div id="box1">
			<c:forEach items="${proList}" var="pro">
				<div class="pro" data-id="${pro.id}">${pro.name}</div>
			</c:forEach>
		</div>
		<div id="box2">
			<div id="btn">
				<button type="button" id="add">↑↓</button>
			</div>
		</div>
		<div id="box3">
			<c:forEach items="${noList}" var="pro">
				<div class="pro" data-id="${pro.id}">${pro.name}</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>