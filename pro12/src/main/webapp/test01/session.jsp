<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    	String name=(String)session.getAttribute("name");
    session.setAttribute("address", "����� ������");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	�̸��� <%=name %>�Դϴ�. <br>
	<a href=session1.jsp>�� ��° �������� �̵�</a> 
</body>
</html>