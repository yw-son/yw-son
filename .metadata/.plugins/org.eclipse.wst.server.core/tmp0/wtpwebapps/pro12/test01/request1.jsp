<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
     <%
    	request.setAttribute("name", "이순신");
    	request.setAttribute("address", "서울시 강남구");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>request1 객체 사용</title>
</head>
<body>
	<%
		RequestDispatcher dispatch = request.getRequestDispatcher("request2.jsp");
		dispatch.forward(request, response);
	%>
</body>
</html>