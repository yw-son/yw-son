<%@page import="sec01.ex02.Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
request.setCharacterEncoding("UTF-8");
%>
<!-- 자바 빈 클래스와 상호작용하기 위해 jsp에서 사용하는 액션 태그 -->
<!-- 자바 코드 사용하지 않고 useBean 액션 태그 이용하여 해당 쿨래스의 객체 생성(빈) -->
<jsp:useBean id="m" class="sec01.ex02.MemberBean"/>

<!-- name="등록한 빈의 id" property="해당 빈(갹체)의 속성" -->
<!-- value 속성을 이용하여 값 지정 -->
<!-- param 속성을 이용하여 전송된 매개변수 값 지정 -->
<!-- property="*" 전송된 매개변수(이름)와 일치하는 속성에 겂을 자동으로 저장 -->
<!-- setproperty == setxxx() -->
<jsp:setProperty name="m" property="*" />


<!-- Adress 빈을 생성한 후 도시와 우편번호를 설정 -->
<jsp:useBean id="addr" class="sec01.ex02.Address"></jsp:useBean>
<jsp:setProperty property="city" name="addr" value="서울" />
<jsp:setProperty property="zipcode" name="addr" value="07654" />
<%
	/* Address addr = new Address();
	addr.setCity("서울");
	addr.setZipcode("07654"); */
	
	m.setAddr(addr);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member6.jsp - 회원정보 출력창</title>
</head>
<body>
	<ul>
		<li>${m.id}</li>
		<li>${m.pwd}</li>
		<li>${m.name}</li>
		<li>${m.email}</li>
		
		<li>${m.addr.city}</li>
		<li>${m.addr.zipcode}</li>
	</ul>
</body>
</html>