<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member2.jsp</title>
</head>
<body>
	<ul>
		<li>${param.id}</li>
		<li>${param.pwd}</li>
		<li>${param.name}</li>
		<li>${param.email}</li>
		<li>${param.address}</li>
		<%-- <li>${requestScope.tel}</li> --%>
	</ul>
</body>
</html>