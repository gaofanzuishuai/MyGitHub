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
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script>
$(document)
.ready(
		function() {
			var selectId=-1;
			var newScore=0;
			$("tr").click(function(){
				$(this).toggleClass("select");
				selectId=$(this).data("id");
			})
			var name;
			var proName;
			$("tr").dblclick(function(){
				name=$(this).children().eq(1).text();
				proName=$(this).children().eq(3).text();
				var oldscore=$(this).children().eq(4).text();
				var str="<input type='text' value="+oldscore+" >";
				$(this).children().eq(4).html(str);			
			})
			$("tr").mouseleave(function(){
				var tr=$(this);
				newScore=tr.children().eq(4).children().eq(0).val();
				tr.children().eq(4).html(newScore);
				/* $.ajax({
					url:"SS",
					type:"post",
					data:{type:"update",name:name,proName:proName},
					dataType:"text",
					success:function(data){
						if(data=="true"){
							tr.children().eq(4).html(newScore);
						}
					}
				}) */
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
</style>
</head>
<body>
	<div id="main">
		<form action="SS" class="form-horizontal" role="form">
			<div class="form-group">
				<div class="col-sm-2">
					<input type="text" class="form-control" id="firstname" name="name"
						placeholder="姓名" value="${c.emp.name}">
				</div>
				<div class="col-sm-3">
					<select name="depName" class="form-control">
						<option value="">部门</option>
						<c:forEach items="${depList}" var="dep">
							<option value="${dep.name}"
								<c:if test="${dep.name==c.emp.dep.name}">selected</c:if>>${dep.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-3">
					<select name="proName" class="form-control">
						<option value="">项目</option>
						<c:forEach items="${proList}" var="pro">
							<option value="${pro.name}"
								<c:if test="${pro.name==c.pro.name}">selected</c:if>>${pro.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="firstname" name="value"
						placeholder="成绩" value="${c.value}">
				</div>
				<div class="col-sm-3">
					<select name="depName" class="form-control">
						<option value="">等级</option>
						<c:forEach items="${grades}" var="grade">
							<option value="${grade}"
								<c:if test="${grade==c.grade}">selected</c:if>>${grade.value}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<button type="submit" class="btn btn-primary">搜索</button>
					</div>
				</div>
			</div>

		</form>
		<table id="pro" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>id</th>
					<th>姓名</th>
					<th>部门</th>
					<th>项目</th>
					<th>成绩</th>
					<th>等级</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${list}" var="sco">
					<tr data-id="${sco.id} ">
						<td class="id">${sco.id}</td>
						<td class="name">${sco.emp.name}</td>
						<td class="depname">${sco.emp.dep.name}</td>
						<td class="proname">${sco.pro.name}</td>
						<td class="value">${sco.value}</td>
						<td class="grade">${sco.grade.value}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<ul class="pagination">
			<li id="pre"><a
				href="SS?ye=${p.ye-1}&name=${c.emp.name}&depName=${c.emp.dep.name}&proName=${c.pro.name}&value=${c.value!=-1?c.value:''}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li <c:if test="${p.ye==status.index}">class="active"</c:if>><a
					href="SS?ye=${status.index}&name=${c.emp.name}&depName=${c.emp.dep.name}&proName=${c.pro.name}&value=${c.value!=-1?c.value:''}">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a
				href="SS?ye=${p.ye+1}&name=${c.emp.name}&depName=${c.emp.dep.name}&proName=${c.pro.name}&value=${c.value!=-1?c.value:''}">下一页</a></li>
		</ul>
		<button type="button" class="btn btn-primary" id="zengjia">增加</button>
		<button type="button" class="btn btn-primary" id="update">修改</button>
		<button type="button" class="btn btn-primary" id="delete">删除</button>

	</div>
</body>
</html>