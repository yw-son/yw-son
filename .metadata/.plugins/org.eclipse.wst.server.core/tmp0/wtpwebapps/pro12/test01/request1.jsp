<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
     <%
    	request.setAttribute("name", "�̼���");
    	request.setAttribute("address", "����� ������");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>request1 ��ü ���</title>
</head>
<body>
	<%
		RequestDispatcher dispatch = request.getRequestDispatcher("request2.jsp");
		dispatch.forward(request, response);
	%>
</body>
</html>