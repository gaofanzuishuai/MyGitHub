<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	$(document)
			.ready(
					function() {
						var selectId=-1
						$("tr").click(function(){
							$(this).toggleClass("select");
							selectId=$(this).data("id");
						})
						$("#zengjia").click(function() {
							location.href = "PS?type=showAdd";
						})
						$("#delete")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "PS?type=delete&id="
														+ selectId + "";
											} else {
												alert("请选中一条数据");
											}
										})
						$("#update")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "PS?type=showUpdate&id="
														+ selectId + "";
											} else {
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

#pro .select {
	background-color: #286090
}

#pro .dblselect {
	background-color: #ccc
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<form action="PS" class="form-horizontal" role="form">
			<div class="form-group">
				<div class="col-sm-4">
				<select name="name" class="form-control">
						<option value="">项目</option>
						<c:forEach items="${proList}" var="pro">
						<option value="${pro.name}" <c:if test="${pro.name==c.name}">selected</c:if>>${pro.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<div class="col-sm-4">
						<button type="submit" class="btn btn-primary">搜索</button>
					</div>
				</div>
			</div>

		</form>
		<table id="pro" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>id</th>
					<th>项目</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${list}" var="pro">
					<tr data-id="${pro.id} ">
						<td class="id">${pro.id}</td>
						<td class="name">${pro.name}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<ul class="pagination">
			<li id="pre"><a
				href="PS?ye=${p.ye-1}&name=${c.name}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li <c:if test="${p.ye==status.index}">class="active"</c:if>><a
					href="PS?ye=${status.index}&name=${c.name}">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a
				href="PS?ye=${p.ye+1}&name=${c.name}">下一页</a></li>
		</ul>
		<button type="button" class="btn btn-primary" id="zengjia">增加</button>
		<button type="button" class="btn btn-primary" id="update">修改</button>
		<button type="button" class="btn btn-primary" id="delete">删除</button>

	</div>
</body>
</html>