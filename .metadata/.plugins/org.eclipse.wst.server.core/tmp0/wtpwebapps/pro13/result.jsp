<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
    <%
    	String msg = "아이디를 입력하지 않았습니다. 아이디를 입력해 주세요.";
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result.jsp</title>
</head>
<body>
	<%
    	String userID = request.getParameter("userID");
	if(userID.length() == 0) {
    %>
    
    <jsp:forward page="login.jsp">
    	<jsp:param value="<%=msg %>" name="msg"/>
    </jsp:forward> // 다시 돌려 보낸다
   
    <%
	}
    %>
    
    <h1>환영합니다 <%=userID %>님!</h1>
</body>
</html>