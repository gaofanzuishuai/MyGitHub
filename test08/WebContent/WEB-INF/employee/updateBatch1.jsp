<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery.js"></script>
<script>
	$().ready(function() {
		$("#save").click(function(){
			
		})
	})
</script>
<style>
#main {
	width: 400px;
	margin: 20px auto;
}
</style>
</head>
<body>
	<div id="main">
		<form action="ES" class="form-horizontal" role="form" method="post">
			<input type="hidden" name="type" value="updateBatch1">
			<input type="hidden" name="ids" value="${ids}">
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="firstname" name="name"
						placeholder="${emp.name}">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<div class="checkbox">
						<label> <input type="radio" name="sex" <c:if test="男==${emp.sex}">checked</c:if> value="男">
							男 <input type="radio" name="sex" <c:if test="女==${emp.sex}">checked</c:if> value="女"> 女
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="lastname" name="age"
						placeholder="${emp.age}">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">保存</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>