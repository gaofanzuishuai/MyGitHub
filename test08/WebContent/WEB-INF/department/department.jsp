<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						var selectId=-1
						var selectName;
						$("tr").click(function(){
							$(this).toggleClass("select");
							selectId=$(this).data("id");
							selectName=$(".select").children().eq(1).text();
						})
						$("#zengjia").click(function() {
							location.href = "DS?type=showAdd";
						})
						$("#delete")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "DS?type=delete&id="
														+ selectId + "";
											} else {
												alert("请选中一条数据");
											}
										})
						$("#update")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "DS?type=showUpdate&id="
														+ selectId + "";
											} else {
												alert("请选中一条数据");
											}
										})
						$("#promanager").click(function(){
							if(selectId>-1){
								window.location.href="DPS?depId="+selectId;
							}else{
								alert("请选中一条数据");
							}
						})
						$("#promanager2").click(function(){
							if(selectId>-1){
								window.location.href="DPS?type=search2&depId="+selectId;
							}else{
								alert("请选中一条数据");
							}
						})
						$("#promanager3").click(function(){
							if(selectId>-1){
								window.location.href="DPS?type=search3&depId="+selectId;
							}else{
								alert("请选中一条数据");
							}
						})
						$("#promanager4").click(function(){
							if(selectId>-1){
								window.location.href="DPS?type=search4&depId="+selectId;
							}else{
								alert("请选中一条数据");
							}
						})
						$("#promanager5").click(function(){
							if(selectId>-1){
								//window.location.href="DPS?type=search4&depId="+selectId;
								var url="DPS?type=search4&depId="+selectId;
								$(".modal-body").load(url);
								$("#myModal").modal("show")
							}else{
								alert("请选中一条数据");
							}
						})
						if (${p.ye} == 1) {  
							$("#pre").find("a").attr("onclick", "return false");
							$("#pre").addClass("disabled");
						}
						if (${p.ye} == ${p.maxYe}) {    
							$("#next").find("a").attr("onclick", "return false");
							$("#next").addClass("disabled");
						}
					})
</script>
<style type="text/css">
#main {
	width: 600px;
	margin: 20px auto;
}

#dep .select {
	background-color: #286090
}

#dep .dblselect {
	background-color: #ccc
}

button {
	margin-top: 5px;
}

#box1, #box3 {
	width: 500px;
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
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<form action="DS" class="form-horizontal" role="form">
			<div class="form-group">
				<div class="col-sm-4">
					<input type="text" class="form-control" id="lastname" name="name"
						placeholder="请输入部门名称" value=${c.name}>
				</div>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="lastname"
						name="emp_count" placeholder="请输入人数"
						value=${c.emp_count!=-1?c.emp_count:''}>
				</div>
				<div class="form-group">
					<div class="col-sm-4">
						<button type="submit" class="btn btn-primary">搜索</button>
					</div>
				</div>
			</div>
		</form>
		<table id="dep" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>id</th>
					<th>部门名称</th>
					<th>部门人数</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${list}" var="dep">
					<tr data-id="${dep.id} ">
						<td class="id">${dep.id}</td>
						<td class="name">${dep.name}</td>
						<td class="emp_count">${dep.emp_count}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<ul class="pagination">
			<li id="pre"><a
				href="DS?ye=${p.ye-1}&name=${c.name}&emp_count=${c.emp_count!=-1?c.emp_count:''}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li <c:if test="${p.ye==status.index}">class="active"</c:if>><a
					href="DS?ye=${status.index}&name=${c.name}&emp_count=${c.emp_count!=-1?c.emp_count:''}">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a
				href="DS?ye=${p.ye+1}&name=${c.name}&emp_count=${c.emp_count!=-1?c.emp_count:''}">下一页</a></li>
		</ul>
		<button type="button" class="btn btn-primary" id="zengjia">增加</button>
		<button type="button" class="btn btn-primary" id="update">修改</button>
		<button type="button" class="btn btn-primary" id="delete">删除</button>
		<button type="button" class="btn btn-primary" id="promanager">项目管理</button>
		<button type="button" class="btn btn-primary" id="promanager2">项目管理2</button>
		<button type="button" class="btn btn-primary" id="promanager3">项目管理3</button>
		<button type="button" class="btn btn-primary" id="promanager4">项目管理拖拽</button>
		<button type="button" class="btn btn-primary" id="promanager5">项目管理模态</button>
		<!-- 		<button class="btn btn-primary" data-toggle="modal"
			data-target="#myModal" id="promanager5">项目管理模态框</button> -->
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body">
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
			</div>
		</div>
	</div>

</body>
</html>