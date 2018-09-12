<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(":text").on("input",function(){
			$.ajax({
				url:"baidu",
				type:"post",
				data:{"type":"prompt",
					  "content":$(this).val(),					
				},
				dataType:"text",
				success:function(data){
					$("#mes").html(data)
				}
				
			})
		})
	})
</script>
</head>
<body>
	<input type="text" />
	<input type="button" id="s" value="搜索">
	<div id="mes"></div>
</body>
</html>