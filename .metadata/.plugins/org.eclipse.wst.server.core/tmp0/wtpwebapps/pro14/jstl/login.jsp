<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!-- pageContext 내장 객체 - 해당 jsp 페이지의 정보, 내장 객체 저장 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
	<form action="result.jsp">
		아이디 : <input type="text" size="20"><br>
		비밀번호 : <input type="password" size="20"><br>
		<input type="submit" value="로그인">
	</form>
	
	<!-- 긴 내장 객체 속성 사용시 변수로 등록한 후 변수 이름을 이용해서 값 출력 -->
	<a href="${contextPath}/memberForm.jsp">회원가입</a>
</body>
</html>