<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%!
    	String name= "�̼���";
    	public String getName() { return name; }
    %>
    
    <% String age=request.getParameter("age"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ũ��Ʈ��(���๮) ����</title>
</head>
<body>
	
	<h1>����ϼ��� <%=name  %>��!</h1>
	<h1>���̴� <%=age %>�Դϴ�!</h1>
</body>
</html>