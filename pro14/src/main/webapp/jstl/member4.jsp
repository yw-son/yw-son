<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	List dataList = new ArrayList();
	dataList.add("hello");
	dataList.add("world");
	dataList.add("안녕하세요!");
%>

<!-- 표현 언어에서 사용할 수 있도록 C:SET 태그 이용해 변수 생성 -->
<c:set var="list" value="<%=dataList %>"></c:set>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member4.jsp</title>
</head>
<body>
	<c:forEach var="i" begin="1" end="10" step="1" varStatus="loop">
		i = ${i} &nbsp;&nbsp;&nbsp; 반복횟수 : ${loop.count} <br>
	</c:forEach>
	<br>
	
	<c:forEach var="i" begin="1" end="10" step="2">
		5 * ${i} = ${5*i} <br>
	</c:forEach>
	<br>
	
	<!-- ArrayList 같은 컬렉션 객체에 저장된 객체(데이터)를 반복 items=${list} -->
	<!-- 변수 data에 하니씩 담아서 처리 -->
	<!-- for(ArrayList data : dataList) -->
	<c:forEach var="data" items="${list}">
		${data}<br>
	</c:forEach>
	<br>
	
	<!-- String fruits = "사과, 파인애플, 바나나, 귤"; -->
	<c:set var="fruits" value="사과, 파인애플, 바나나, 망고, 귤" />
	<c:forTokens items="${fruits}" var="token" delims="," >
		${token} <br>
	</c:forTokens>
</body>
</html>