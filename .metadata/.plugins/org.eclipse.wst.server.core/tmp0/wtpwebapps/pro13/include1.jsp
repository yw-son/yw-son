<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("utf-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include1.jsp</title>
</head>
<body>
	안녕하세요. jsp 시작입니다.
	<br>
	<jsp:include page="catdog.jsp" flush="true">
		<jsp:param value="캣" name="name" />
		<jsp:param value="cat.jpg" name="imgName" />
	</jsp:include>
	<br>
	안녕하세요. jsp 끝입니다.
</body>
</html>