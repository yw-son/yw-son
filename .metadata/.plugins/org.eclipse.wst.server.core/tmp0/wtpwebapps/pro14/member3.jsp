<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<!-- 화원 정브를 저장할 빈 생성 (=MemberBean의 객체 생성) -->
<jsp:useBean id="m" class="sec01.ex01.MemberBean" />
<!-- name="userBean 성정한 id 값(사용할 객체의 참조변수명)" property="속성명(멤버 변수명)" -->
<!-- 전송된 회원 정보를 빈의 속성에 설정(setter) -->
<jsp:setProperty property="*" name="m" />

<html>
<head>
<meta charset="UTF-8">
<title>member3.jsp</title>
</head>
<body>
	<ul>
		<li>표현식 사용</li>
		<li><%=m.getId() %></li>
		<li><%=m.getPwd() %></li>
		<li><%=m.getName() %></li>
		<li><%=m.getEmail() %></li>
	</ul>
	
	<ol>
		<li>표현언어 사용</li>
		<li>빈 id와 속성 이름으로 접근해 회원 정보 출력</li>
		<li>${m.id}</li>
		<li>${m.pwd}</li>
		<li>${m.name}</li>
		<li>${m.email}</li>
	</ol>
</body>
</html> 
