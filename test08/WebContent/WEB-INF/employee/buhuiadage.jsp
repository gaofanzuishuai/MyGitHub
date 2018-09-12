
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
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
							location.href = "ES?type=showAdd";
						})
						$("#delete")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "ES?type=delete&id="
														+ selectId + "";
											} else {
												alert("请选中一条数据");
											}
										})
						$("#update")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "ES?type=showUpdate&id="
														+ selectId + "";
											} else {
												alert("请选中一条数据");
											}
										})
						$("#deleteBatch")
								.click(
										function() {
											var length = $("#emp .select").length;
											//var ids = "";
											var ids = new Array();
											s
											$("#emp .select").each(
													function(index, element) {
														//ids+=$(this).data("id")+",";
														ids.push($(this).data(
																"id"));
													})
											//ids=ids.substring(0,ids.length-1);
											window.location.href = "ES?type=deleteBatch&ids="
													+ ids + "";
										})
						$("#updateBatch1")
								.click(
										function() {
											var length = $("#emp .select").length;
											var ids = "";
											$("#emp .select").each(
													function(index, element) {
														ids += $(this).data(
																"id")
																+ ",";
													})
											ids = ids.substring(0,
													ids.length - 1);
											window.location.href = "ES?type=showUpdateBatch1&ids="
													+ ids + "";
										})
						$("#updateBatch2")
								.click(
										function() {
											var length = $("#emp .select").length;
											var ids = "";
											$("#emp .select").each(
													function(index, element) {
														ids += $(this).data(
																"id")
																+ ",";
													})
											ids = ids.substring(0,
													ids.length - 1);
											window.location.href = "ES?type=showUpdateBatch2&ids="
													+ ids + "";
										})
						$("tr")
								.dblclick(
										function() {
											$(this).unbind("dblclick");
											$(this).toggleClass("dblselect");
											var oldname = $(this).children()
													.eq(0).text();
											var oldsex = $(this).children().eq(
													1).text();
											var oldage = $(this).children().eq(
													2).text();
											var str1 = "<input type='text' name='test1' value='"
													+ oldname
													+ "'style='width:50%;'>";
											var str2 = "";
											if (oldsex == "男") {
												str2 = "<select><option selected>男</option><option></option>女</select>"
											} else {
												str2 = "<select><option>男</option><option selected>女</option></select>"
											}
											var str3 = "<input type='text' name='test3' value='"
													+ oldage
													+ "'style='width:50%;'>";
											$(this).children().eq(0).html(str1);
											$(this).children().eq(1).html(str2);
											$(this).children().eq(2).html(str3);
										})
						$("#updateBatch3")
								.click(
										function() {
											var emparray = new Array();
											var str = "";
											$("#emp .dblselect")
													.each(
															function(index,
																	element) {

																var id = $(this)
																		.data(
																				"id");
																var newname = $(
																		this)
																		.find(
																				"[name=test1]")
																		.val();
																var newsex = $(
																		this)
																		.find(
																				"[name=test2]")
																		.val();
																var newage = $(
																		this)
																		.find(
																				"[name=test3]")
																		.val();
																str += id
																		+ ","
																		+ newname
																		+ ","
																		+ newsex
																		+ ","
																		+ newage
																		+ ";";
															})
											str = str.substring(0,
													str.length - 1);
											emparray.push(str);
											location.href = "ES?type=updateBatch3&emps="
													+ emparray + "";
										})
						$("#updateBatch4").click(
								function() {
									var emparray = new Array();
									$("#emp .dblselect").each(
											function(index, element) {
												var id = $(this).data("id");
												var newname = $(this).find(
														"[name=test1]").val();
												var newsex = $(this).find(
														"[name=test2]").val();
												var newage = $(this).find(
														"[name=test3]").val();
												var emp = {
													id : id,
													name : newname,
													sex : newsex,
													age : newage
												}
												emparray.push(emp);
											})
									var str = JSON.stringify(emparray);
									str = str.replace(/{/g, "%7b");
									str = str.replace(/}/g, "%7d");
									alert(str);
									//location.href="ES?type=updateBatch4&emps="+;
								})
								$("#searchByCondition").click(function(){
									var name=$("#nametext").val();
									var sex=$("#sextext").val();
									var age=$("#agetext").val();
									location.href="ES?type=searchByCondition&searchname="+name+"&searchsex="+sex+"&searchage="+age;
								})
								$("#main img").hover(function(event){
									var src=$(this).attr("src");									
									$("#bigPicture img").attr("src",src);
									$("#bigPicture").show();
									$("#bigPicture").css({left:event.pageX+20,top:event.pageY+20});
								},function(){$("#bigPicture").hide();})
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

#emp .select {
	background-color: #286090
}

#emp .dblselect {
	background-color: #ccc
}

#emp .photo {
	width: 50px;
	height: 50px;
}

#emp img {
	width: 30px;
	height: 30px;
}
#bigPicture{
display:none;
position:absolute;
}
#bigPicture img{
	width:200px;
	height:200px;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<form action="ES?type=search" class="form-horizontal" role="form" method="post">
			<input type="hidden" name="id" value="${c.id}">
			<div class="form-group">
				<div class="col-sm-2">
					<input type="text" class="form-control" id="firstname" name="name"
						placeholder="姓名" value="${c.name}">
				</div>
				<div class="col-sm-2">
					<select name="sex" class="form-control">
						<option value="">性别</option>
						<option value="男" <c:if test="${c.sex=='男'}">selected</c:if>>男</option>
						<option value="女" <c:if test="${c.sex=='女'}">selected</c:if>>女</option>
					</select>
				</div>
				<div class="col-sm-2">
					<select name="depId" class="form-control">
						<option value="">部门</option>
						<c:forEach items="${depList}" var="dep">
							<option value="${dep.id}"
								<c:if test="${dep.id==c.dep.id}">selected</c:if>>${dep.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="lastname" name="age"
						placeholder="年龄" value="${c.age!=-1?c.age:''}">
				</div>
				<div class="form-group">
					<div class="col-sm-2">
						<button type="submit" class="btn btn-primary">搜索</button>
					</div>
				</div>
			</div>

		</form>
		<table id="emp" class="table table-bordered table-hover" method="post">
			<thead>
				<tr>
					<th>id</th>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>部门</th>
					<th>照片</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${list}" var="emp">
					<tr data-id="${emp.id} ">
						<td class="id">${emp.id}</td>
						<td class="name">${emp.name}</td>
						<td class="sex">${emp.sex}</td>
						<td class="age">${emp.age}</td>
						<td class="dep">${emp.dep.name}</td>
						<c:choose>
							<c:when test="${empty emp.photo}">
								<td class="photo"></td>
							</c:when>
							<c:when test="${!empty emp.photo}">
								<td class="photo"><img src="pic/${emp.photo}"></td>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<ul class="pagination">
			<li><a
				href="ES?type=search&ye=1&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&depId=${c.dep.id}">首页</a></li>
			<li id="pre"><a
				href="ES?type=search&ye=${p.ye-1}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&depId=${c.dep.id}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li <c:if test="${p.ye==status.index}">class="active"</c:if>><a
					href="ES?type=search&ye=${status.index}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&depId=${c.dep.id}">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a
				href="ES?type=search&ye=${p.ye+1}&nage=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&depId=${c.dep.id}">下一页</a></li>
			<li><a
				href="ES?type=search&ye=${p.maxYe}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&depId=${c.dep.id}">尾页</a></li>
		</ul>
		<button type="button" class="btn btn-primary" id="zengjia">增加</button>
		<button type="button" class="btn btn-primary" id="update">修改</button>
		<button type="button" class="btn btn-primary" id="delete">删除</button>
		<button type="button" class="btn btn-primary" id="deleteBatch">批量删除</button>
		<button type="button" class="btn btn-primary" id="updateBatch1">批量修改1</button>
		<button type="button" class="btn btn-primary" id="updateBatch2">批量修改2</button>
		<button type="button" class="btn btn-primary" id="updateBatch3">批量修改3</button>
		<button type="button" class="btn btn-primary" id="updateBatch4">JSON批量修改4</button>
		<div id="bigPicture">
			<img src="" />
		</div>
	</div>
</body>
</html>