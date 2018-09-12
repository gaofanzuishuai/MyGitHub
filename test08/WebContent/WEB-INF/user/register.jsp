<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<style>
#main {
	width: 500px;
	height: 200px;
	margin: 50px auto;
}
</style>
</head>
<body>
	<div id="main">
		<form action="US?type=register" class="form-horizontal" role="form"
			method="post">
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">账号</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="userName"
						placeholder="请输入账号" >
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="passWord"
						placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">确认密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="insure"
						placeholder="再次输入密码">
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