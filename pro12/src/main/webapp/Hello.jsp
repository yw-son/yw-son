<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
    String name = "cat";
    public String getName() { return name; } // 메서드 getName()
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선언문 연습</title>
</head>
<body>
	
	<h1>Hello, <%=name %>님!</h1>
</body>
</html>