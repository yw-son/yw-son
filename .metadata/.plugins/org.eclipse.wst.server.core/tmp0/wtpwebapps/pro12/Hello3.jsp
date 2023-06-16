<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%!
    String name = "이순신";
    public String getName() { return name; }
%>

<%
    String age = request.getParameter("age");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>표현식 연습</title>
</head>
<body>
    <h1>안녕하세요 <%=name %>님!</h1>
    <h1>나이는 <%=age %>입니다!</h1>
    <h1>키는 <%=180 %>입니다</h1>
    <h1>나이+10은 <%=Integer.parseInt(age)+10 %> 입니다</h1>
</body>
</html>