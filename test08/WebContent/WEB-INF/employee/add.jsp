<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script>
	$(document).ready(function() {
		$("#upload").click(function() {			
			var formData = new FormData();
			for(var i=0;i<$("[name=photo]")[0].files.length;i++){
				formData.append("myFile", $("[name=photo]")[0].files[i]); //文件上传
			}
			
			$.ajax({
				url : "ES?type=upload",
				type : "post",
				data : formData,
				cache : false,
				processData : false,
				contentType : false,
				dataType : "text",
				success : function(data) {
					var str = "<img src='pic/"+data+"'>";
					str+="<input type='hidden' name='photo1' value='"+data+"'>"
					$("#picture").append(str);					
				}
			})
			$(document).on("click","#picture img",function(){
				var img=$(this);
				var src=img.attr("src");
				src=src.substring(4);
				$.ajax({
					url:"ES",
					type:"post",
					data:{
						type:"deletePicture",
						name:src,
					},
					dataType:"text",
					success:function(data){
						if(data=="true"){
							img.next().remove();
							img.remove();
						}
					}
				})
				
			})
		})
	})
</script>
<style>
#main {
	width: 400px;
	margin: 20px auto;
}
#picture img{
	width:100px;
	height:100px;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<form action="ES?type=add" class="form-horizontal" role="form"
			method="post" >
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="firstname" name="name"
						placeholder="请输入姓名">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<div class="checkbox">
						<label> <input type="radio" name="sex" checked value="男">
							男 <input type="radio" name="sex" value="女"> 女
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="lastname" name="age"
						placeholder="请输年龄">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">部门</label>
				<div class="col-sm-10">
					<select name="depId" class="form-control">
						<option value="">部门</option>
						<c:forEach items="${depList}" var="dep">
							<option value="${dep.id}">${dep.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">照片</label>
				<div class="col-sm-7">
					<input type="file" name="photo" multiple/>
				</div>
				<div class="col-sm-3">
					<input type="button" id="upload" class="btn btn-primary" value="上传">
				</div>
			</div>
			<div id="picture"></div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">保存</button>
				</div>
			</div>
		</form>
	</div>

</body>
</html>