<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script>
	$(document).ready(function(){
		var selectId=-1;
		$(document).on("click","tr",function(){
			selectId=$(this).data("id");
			$(this).toggleClass("select");
		})
		$("#add").click(function(){
			var proId=$("#selectId").val(); 
			var proName;
			var i;
			//location.href="DPS?type=add&depId=${dep.id}&proId="+proId;
			$.ajax({
				url:"DPS",
				type:"post",
				data:{type:"add2",depId:${dep.id},proId:proId},       
				dataType:"text",
				success:function(data){
					if(data=="true"){
						$("#selectId").children().each(function(index,element){
							if($(this).val()==proId){
								proName=$(this).text();
								i=index;
							}
						})
						var tr="<tr><td>"+proId+"</td><td>"+proName+"</td></tr>"
						$("#pro").append(tr);
						$("#selectId").children().eq(i).remove();
						if($("#selectId").children().length==0){
							
							$("#add").addClass("disabled");
						}
					}
				}
			})
		}) 
		$("#delete").click(function(){  
			//location.href="DPS?type=delete&depId=${dep.id}&proId="+selectId;  
			var proId=$(".select").children().eq(0).text();
			var proName=$(".select").children().eq(1).text(); 
			$.ajax({
				url:"DPS",
				type:"post",
				data:{type:"delete2",depId:${dep.id},proId:proId}, 
				dataType:"text",
				success:function(data){
					if(data=="true"){
						$("#pro").find(".select").remove(); 
						var str="<option value="+proId+">"+proName+"</option>";    
						$("#selectId").append(str);
						if($("#selectId").children().length!=0){
							  
							$("#add").removeClass("disabled");  
						} 
					}
				}
			})
		})
		<c:if test="${f:length(noList)==0}"> 
			$("#add").unbind("click");
			$("#add").addClass("disabled");
		</c:if>
	})		





</script>
<style type="text/css">
#main {
	width: 500px;
	margin: 0 auto;
}
#pro .select {
	background-color: #286090
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<h2>${dep.name}</h2>
		<table id="pro" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>id</th>
					<th>项目</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach items="${proList}" var="pro">
					<tr data-id="${pro.id}">
						<td>${pro.id}</td>
						<td>${pro.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="form-group">
			<div class="col-sm-5">
				<select class="form-control col-sm-3" id="selectId">
					<c:forEach items="${noList}" var="pro">
						<option value="${pro.id}">${pro.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-sm-4">
			<button type="button" class="btn btn-primary" id="add">增加</button>
			<button type="button" class="btn btn-primary" id="delete">删除</button>
		</div>
	</div>
</body>
</html>