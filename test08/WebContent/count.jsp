<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int num =0;
		/* int i = 0;
		if (application.getAttribute("i") != null) {
			i = (Integer) application.getAttribute("i");
		}
		if (session.isNew()) {
			i++;
		}
		application.setAttribute("i", i);
		System.out.println(request.getRemoteAddr()); */
		/* if (session.isNew()) {
			String ip = request.getRemoteAddr();
			Set<String> set = new HashSet<>();
			if (application.getAttribute("set") != null) {
				set = (Set<String>) application.getAttribute("set");
			}
			set.add(ip);
			num= set.size();
			application.setAttribute("set", set);
		} */
	%>
	本网站共有<%=application.getAttribute("num") %>
	人访问
</body>
</html>