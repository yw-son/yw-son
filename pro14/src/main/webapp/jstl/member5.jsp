<%@page import="sec01.ex02.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="membersList" class="java.util.ArrayList" />
<jsp:useBean id="m1" class="sec01.ex02.MemberBean" />
<jsp:setProperty name="m1" property="id" value="son" />
<jsp:setProperty name="m1" property="pwd" value="1234" />
<jsp:setProperty name="m1" property="name" value="손흥민" />
<jsp:setProperty name="m1" property="email" value="son@naver.com" />

<%
	MemberBean m2 =new MemberBean("k1", "4321", "기성용", "ki@naver.com");

	membersList.add(m1);
	membersList.add(m2);	
%>

<c:set var="membersList" value="<%=membersList%>" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member5.jsp</title>
</head>
<body>
	<c:forEach var="member" items="${membersList}">
		${member.id} <br>
		${member.pwd} <br>
		${member.name} <br>
		${member.email} <br>
	</c:forEach>
	<br>
	
	<c:forEach var="i" begin="0" end="${membersList.size()}" step="1">
		${membersList[i].id} <br>
		${membersList[i].pwd} <br>
		${membersList[i].name} <br>
		${membersList[i].email} <br>
	</c:forEach>
</body>
</html>