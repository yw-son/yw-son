<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="sec01.ex01.MemberBean"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<jsp:useBean id="m" class="sec01.ex01.MemberBean" />
<jsp:setProperty property="*" name="m" />
<jsp:useBean id="membersList" class="java.util.ArrayList" />
<!-- 회원 정보를 저장할 HashMap 객체 생성 -->
<jsp:useBean id="membersMap" class="java.util.HashMap"></jsp:useBean>
<%
	membersMap.put("id", "park2");
	membersMap.put("pwd", "4321");
	membersMap.put("name", "박지성");
	membersMap.put("email", "park2@naver.com");

// 새로운 회원 정보를 저장하는 MemberBean 객체 생성 - ArrayList에 저장
MemberBean m2 = new MemberBean("son", "1234", "손흥민", "son@naver.com");
membersList.add(m);
membersList.add(m2);
membersMap.put("membersList", membersList);
%>
<html>
<head>
<meta charset="UTF-8">
<title>member5.jsp</title>
</head>
<body>
	<ol>
		<li>${membersMap.id}</li>
		<li>${membersMap.pwd}</li>
		<li>${membersMap.name}</li>
		<li>${membersMap.email}</li>
	</ol>

	<!-- 1.HashMap에 저장된 ArrayListdp .(마침표)로 접근
		 2. 다시 각각의 속성에 .(마침표)를 이용해 접근
	 -->

	<ol>
		<li>${membersMap.membersList[0].id}</li>
		<li>${membersMap.membersList[0].pwd}</li>
		<li>${membersMap.membersList[0].name}</li>
		<li>${membersMap.membersList[0].email}</li>
	</ol>

	<ol>
		<li>${membersMap.membersList[1].id}</li>
		<li>${membersMap.membersList[1].pwd}</li>
		<li>${membersMap.membersList[1].name}</li>
		<li>${membersMap.membersList[1].email}</li>
	</ol>
</body>
</html>
