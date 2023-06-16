<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl core 라이브러리 사용하기 위한 taglib 설정-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="id" value="hong" scope="page" />
<c:set var="pwd" value="1234"></c:set>
<c:set var="name" value="${'홍길동'}"></c:set>
<c:set var="age" value="${22}"></c:set>
<c:set var="height" value="${177}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member1.jsp</title>
</head>
<body>
<!-- 표현 언어로 jstl을 이용해 지정한 변수에 바로 접근 가능 -->
	<ul>
		<li>${id}</li>
		<li>${pwd}</li>
		<li>${name}</li>
		<li>${age}</li>
		<li>${height}</li>
	</ul>
</body>
</html>