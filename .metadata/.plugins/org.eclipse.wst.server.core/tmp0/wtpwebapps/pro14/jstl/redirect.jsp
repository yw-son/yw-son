<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>redirect.jsp</title>
</head>
<body>
	<c:redirect url="/jstl/member1.jsp">
		<c:param name="id" value="${'park'}" />
	</c:redirect>
</body>
</html>