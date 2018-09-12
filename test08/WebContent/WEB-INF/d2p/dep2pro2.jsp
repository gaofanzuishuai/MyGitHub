<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script type="text/jscript">
		
	
	$().ready(function(){
		$("#add").click(function(){
			var proId=$(".select").data("id");
			var proName=$(".select").text();
 			$.ajax({
				url:"DPS",
				type:"post",
				data:{type:"add2",depId:${dep.id},proId:proId},
				dataType:"text",
				success:function(data){
					if(data=="true"){
						$("#box3").find(".select").remove();
						var str="<div class='pro' data-id="+proId+">"+proName+"</div>";
						$("#box1").append(str);
					}
				}
			})
		})
		$("#delete").click(function(){
			var proId=$(".select").data("id");
			var proName=$(".select").text();
 			$.ajax({
				url:"DPS",
				type:"post",
				data:{type:"delete2",depId:${dep.id},proId:proId},
				dataType:"text",
				success:function(data){
					if(data=="true"){
						$("#box1").find(".select").remove();
						var str="<div class='pro' data-id="+proId+">"+proName+"</div>";
						$("#box3").append(str);
					}
				}
			})
		})
		$("#addBatch").click(function(){
			var str="";
			$(".select").each(function(index,element){
				var proId=$(this).data("id");
				str+=proId+",";
			})
			var proIdList=str.substring(0,str.length-1);
			$.ajax({
				url:"DPS",
				type:"post",
				data:{type:"addBatch",depId:${dep.id},proIdList:proIdList},
				dataType:"text",
				success:function(data){
					if(data=="true"){
						$("#box3").find(".select").each(function(index,element){	
							var proId=$(this).data("id");
							var proName=$(this).text();
							var str="<div class='pro' data-id="+proId+">"+proName+"</div>";
							$("#box1").append(str);
							$(this).remove();
						})
					}
				}
			})
		})
		$("#deleteBatch").click(function(){
			var str="";
			$(".select").each(function(index,element){
				var proId=$(this).data("id");
				str+=proId+",";
			})
			var proIdList=str.substring(0,str.length-1);
			$.ajax({
				url:"DPS",
				type:"post",
				data:{type:"deleteBatch",depId:${dep.id},proIdList:proIdList},
				dataType:"text",
				success:function(data){
					if(data=="true"){
						$("#box1").find(".select").each(function(index,element){	
							var proId=$(this).data("id");
							var proName=$(this).text();
							var str="<div class='pro' data-id="+proId+">"+proName+"</div>";
							$("#box3").append(str);
							$(this).remove();
						})
					}
				}
			})
		})
		$(document).on("click",".pro",function(){
			$(this).toggleClass("select");
		})
})
	








</script>
<style type="text/css">
#box1, #box3 {
	width: 800px;
	height: 300px;
	border: 1px solid #337ab7;
	border-radius: 10px;
	margin: 0 auto;
	margin-top: 30px
}

#box2 {
	width: 800px;
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

#delete {
	width: 50px;
	height: 50px;
	background-color: #337ab7;
	color: #fff;
	font-size: 30px;
	margin-right: 10px;
	outline: none;
	border: 1px solid #337ab7;
}
#addBatch{
	width: 50px;
	height: 50px;
	background-color: #337ab7;
	color: #fff;
	font-size: 30px;
	margin-right: 120px;
	outline: none;
	border: 1px solid #337ab7;
}
#deleteBatch{
	width: 50px;
	height: 50px;
	background-color: #337ab7;
	color: #fff;
	font-size: 30px;
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

.select {
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
	<div id="box1">
		<c:forEach items="${proList}" var="pro">
			<div class="pro" data-id="${pro.id}">${pro.name}</div>
		</c:forEach>
	</div>
	<div id="box2">
		<div id="btn">
			<button type="button" id="add">↑</button>
			<button type="button" id="addBatch">↑↑</button>
			<button type="button" id="delete">↓</button>			
			<button type="button" id="deleteBatch">↓↓</button>
		</div>
	</div>
	<div id="box3">
		<c:forEach items="${noList}" var="pro">
			<div class="pro" data-id="${pro.id}">${pro.name}</div>
		</c:forEach>
	</div>
</body>
</html>