<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl core 라이브러리 사용하기 위한 taglib 설정-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="id" value="hong" scope="page" />
<c:set var="pwd" value="1234"></c:set>
<%-- <c:set var="name" value="${'홍길동'}"></c:set> --%>
<c:set var="age" value="${22}"></c:set>
<c:set var="height" value="${177}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member3.jsp</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty name}">
			<h2>이름을 입력하세요!.</h2>
		</c:when>
		<c:otherwise>
			<ul>
				<li>${id}</li>
				<li>${pwd}</li>
				<li>${name}</li>
				<li>${age}</li>
				<li>${height}</li>
			</ul>
		</c:otherwise>
	</c:choose>
</body>
</html>