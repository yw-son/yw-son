<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <%
    	String name = (String)request.getAttribute("name");
    	String address = (String)request.getAttribute("address");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>�̸��� <%=name %> �Դϴ�.</h1>
	<h1>�ּҴ� <%=address %> �Դϴ�.</h1>
</body>
</html>