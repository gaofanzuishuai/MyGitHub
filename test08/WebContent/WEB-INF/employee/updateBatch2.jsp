<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery.js"></script>
<script>
	$().ready(function() {
		$("#save").click(function() {
			var emps = ""
			$(".emp").each(function(index, element) {
				var id = $(this).find("[name=id]").val();
				var name = $(this).find("[name=name]").val();
				var sex = $(this).find("[name=sex]:checked").val();
				var age = $(this).find("[name=age]").val();
				emps += id + "," + name + "," + sex + "," + age + ";";
			})
			location.href="ES?type=updateBatch2&emps="+emps+""
		})

	})
</script>
<style>
#main {
	width: 900px;
	margin: 20px auto;
}

#biaoge {
	padding: 20px;
	border: 1px dashed #ccc;
	float: left;
	margin: 10px 50px 10px 20px;
	border: 1px dashed #ccc;
}

#baocun {
	text-align: center;
}
</style>
</head>
<body>
	<div id="main">
		<c:forEach items="${emps}" var="emp">
		<form id="biaoge" action="ES" class="form-horizontal emp" role="form"
			method="post">
			<input type="hidden" name="type" value="updateBatch1"> <input
				type="hidden" name="id" value="${emp.id}">
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="firstname" name="name"
						value="${emp.name}">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<div class="checkbox">
						<label> <input type="radio" name="sex"
							<c:if test="男==${emp.sex}"> checked </c:if> value="男">
							男 <input type="radio" name="sex"
							<c:if test="女==${emp.sex}"> checked </c:if> value="女">
							女
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="lastname" name="age"
						value="${emp.age}">
				</div>
			</div>
		</form>
		</c:forEach>
		<div id="baocun" class="col-sm-offset-2 col-sm-10">
			<button id="save" type="button" class="btn btn-primary">保存</button>
		</div>
	</div>
</body>
</html>