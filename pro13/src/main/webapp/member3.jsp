<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberDAO"%>
<%@page import="sec01.ex01.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
    <!-- id가 m인 MemberBean 객체를 생성 -->
    <!-- == MemberBean m = new MemberBean(); -->
    <jsp:useBean id="m" class="sec01.ex01.MemberBean" scope="page"></jsp:useBean>
    <!-- jsp:setProperty name="userBean에서 지정한 id값"
    Property = setxxx() 메서드 사용 위한 멤버 변수 이름" value= "멤버 변수에 지정한 값" -->
    <!-- request.getParameter() - 사용자 요청이 있을 때 해당 name에 설정된 value를 반환함 -->
    <!-- value 속성을 이용한 방법 -->
    <%-- <jsp:setProperty name="m" property="id" value='<%= request.getParameter("id") %>'/>
    <jsp:setProperty name="m" property="pwd" value='<%= request.getParameter("pwd") %>'/>
    <jsp:setProperty name="m" property="name" value='<%= request.getParameter("name") %>'/>
    <jsp:setProperty name="m" property="email" value='<%= request.getParameter("email") %>'/> --%>
     <!-- 회원 가입창에서 전달된 매개변수 이름과 속성 이름이 같으면 같은 이름으로 값을 설정 -->
     <!-- param 속성을 이용한 방법 -->
    <%-- <jsp:setProperty name="m" property="id" param="id"/>
    <jsp:setProperty name="m" property="pwd" param="pwd"/>
    <jsp:setProperty name="m" property="name" param="name"/>
    <jsp:setProperty name="m" property="email" param="email"/> --%>
    
    <!-- value, param을 안 적어도 property가 속성의 getter, setter값이 있는지 찾고 인지값을 있는지 매핑을 해서 찾는다 -->
    <!-- 회원 가입창에서 전달받은 매개변수 이름이 일치하는 useBean 속성에 자동으로 값을 설정 -->
    <!-- value, parm 속성 사용하지 않고 값 실행하는 방법1 -->
    <%-- <jsp:setProperty name="m" property="id"/>
    <jsp:setProperty name="m" property="pwd"/>
    <jsp:setProperty name="m" property="name"/>
    <jsp:setProperty name="m" property="email"/> --%>
    
    <!-- value, parm 속성 사용하지 않고 값 실행하는 방법2 -->
    <!-- 1. 회원 가입창에서 전달받은 매개변수 이름과 빈 속성을 비교 
    	 2. 동일한 매개변수 이름과 빈 속성의 이름이 있다면 자동으로 매갸변수로 넘어온 값을 실행
    -->
     <%-- <jsp:setProperty name="m" property="*" /> --%>
     <jsp:setProperty name="m" property="*" />
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member.jsp(회원 등록창)</title>
</head>
<body>
	<table align="center" width="100%">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이름</td>
			<td width="11%">이메일</td>
			<td width="5%">가입일</td>
		</tr>
		<tr align="center">
			<!-- jsp:Property name="사용할 userBean의 id"  property="bean에 저장된 속성명"-->
			<!-- 속성에 해당하는 getter 메서드 실행 -->
			<td><jsp:getProperty name="m" property="id" /></td>
			<td><jsp:getProperty name="m" property="pwd" /></td>
			<td><jsp:getProperty name="m" property="name" /></td>
			<td><jsp:getProperty name="m" property="email" /></td>
		</tr>

	</table>
</body> 
</html>