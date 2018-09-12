<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script>
var websocket = null;

//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	websocket = new WebSocket("ws://192.168.0.146:8080/test08/websocket");
} else {
	alert('没有建立websocket连接');
}
//连接发生错误的回调方法
websocket.onerror = function() {
	//setMessage("错误");
};

//连接成功建立的回调方法
websocket.onopen = function(event) {
	//setMessage("建立连接");
}

//接收到消息的回调方法
websocket.onmessage = function(event) {
	$("#count").html(event.data);
}

//连接关闭的回调方法
websocket.onclose = function() {
	//setMessage("close");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口。
window.onbeforeunload = function() {
	websocket.close();
}

//将消息显示在网页上
function setMessage(text) {
	 $("#count").html(text);
}
//关闭连接
function closeWebSocket() {
	websocket.close();
}
$().ready(function() {
	$(".yi").click(function() {
		$(this).next().slideToggle();
	})
})
</script>
<style>
#count{
width:40px;
height:40px;
background-color:red;
color:white;
}
#left {
	float: left;
	height: 600px;
	width: 300px;
	border: 1px solid #000;
}

#right {
	float: left;
	width: 1000px;
	height: 700px;
}

#top, #bottom {
	clear: both;
	height: 80px;
	background-color: #337ab7;
}

.yi {
	width: 160px;
	height: 40px;
	background-color: #337ab7;
	border-radius: 3px;
	text-align: center;
	line-height: 40px;
	margin-top: 10px;
	margin-left: 40px;
	font-size: 18px;
}

.er {
	list-style: none;
	margin-left: 35px;
}

.er li {
	width: 100px;
	height: 20px;
	background-color: lightgray;
	margin-top: 10px;
	text-align: center;
	line-height: 20px;
	border-radius: 2px;
}

a {
	text-decoration: none;
	color: #337ab7;
}
</style>
</head>
<body>
	<div id="container">
		<div id="top">
		<div id="count">app</div>
		</div>
		<div id="main">
			<div id="left">
				<div class="yi">员工管理</div>
				<ul class="er">
					<li><a href="ES?type=search" target="right">员工管理</a></li>
					<li><a href="ES?type=showAdd" target="right">员工增加</a></li>
				</ul>
				<div class="yi">部门管理</div>
				<ul class="er">
					<li><a href="DS" target="right">部门管理</a></li>
					<li><a href="DS?type=showAdd" target="right">部门增加</a></li>
				</ul>
				<div class="yi">项目管理</div>
				<ul class="er">
					<li><a href="PS" target="right">项目管理</a></li>
					<li><a href="PS?type=showAdd" target="right">项目增加</a></li>
				</ul>
				<div class="yi">绩效管理</div>
				<ul class="er">
					<li><a href="SS" target="right">绩效界面</a></li>
					<li><a href="SS?type=manage" target="right">绩效管理</a></li>
				</ul>
			</div>
			<iframe id="right" name="right" scrolling="no" frameborder="0"
				src="ES?type=search"></iframe>
		</div>
		<div id="bottom"></div>
	</div>
</body>
</html>