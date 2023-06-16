<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="sec01.ex01.MemberBean"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<!-- 화원 정브를 저장할 빈 생성 (=MemberBean의 객체 생성) -->
<jsp:useBean id="m" class="sec01.ex01.MemberBean" />
<!-- name="userBean 성정한 id 값(사용할 객체의 참조변수명)" property="속성명(멤버 변수명)" -->
<!-- 전송된 회원 정보를 빈의 속성에 설정(setter) -->
<jsp:setProperty property="*" name="m" />

<!-- ArrayList 객체 생성 - 참조 변수명 : memberList -->
<jsp:useBean id="memberList" class="java.util.ArrayList" />
<%
// 새로운 회원 정보를 저장하는 MemberBean 객체 생성 - ArrayList에 저장
MemberBean m2 = new MemberBean("son", "1234", "손흥민", "son@naver.com");
memberList.add(m);
memberList.add(m2);
%>
<html>
<head>
<meta charset="UTF-8">
<title>member4.jsp</title>
</head>
<body>
	<!-- memberList[0] > m 갹체 (사용자가 입력한 정보)
		 memberList[1] > m 갹체 (손흥민...) -->

	<ol>
		<li>표현언어 사용</li>
		<li>빈 id와 속성 이름으로 접근해 회원 정보 출력</li>
		<li>${memberList[0].id}</li>
		<li>${memberList[0].pwd}</li>
		<li>${memberList[0].name}</li>
		<li>${memberList[0].email}</li>
	</ol>

	<ol>
		<li>표현언어 사용</li>
		<li>빈 id와 속성 이름으로 접근해 회원 정보 출력</li>
		<li>${memberList[1].id}</li>
		<li>${memberList[1].pwd}</li>
		<li>${memberList[1].name}</li>
		<li>${memberList[1].email}</li>
	</ol>
</body>
</html>
