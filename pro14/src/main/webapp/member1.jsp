<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");
String email = request.getParameter("email");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member1.jsp - 회원 출력 창</title>
</head>
<body>
	<ul>
		<li><%=id%></li>
		<li><%=pwd%></li>
		<li><%=name%></li>
		<li><%=email%></li>
	</ul>

	<ol>
		<li>${param.id}</li>
		<li>${param.pwd}</li>
		<li>${param.name}</li>
		<li>${param.email}</li>
	</ol>
</body>
</html>