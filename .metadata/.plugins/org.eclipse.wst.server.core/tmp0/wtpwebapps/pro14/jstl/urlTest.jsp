<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:url var="url" value="/jstl/member1.jsp" >
	<c:param name="id" value="hong" />
	<c:param name="pwd" value="1234" />
	<c:param name="name" value="홍길동" />
	<c:param name="email" value="hong@naver.com" />
</c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${contextPath}/jstl/member1.jsp">회원정보 출력</a>
	<a href="${url}">회원정보 출력</a>
</body>
</html>