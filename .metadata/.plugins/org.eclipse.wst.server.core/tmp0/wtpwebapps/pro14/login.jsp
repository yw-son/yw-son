<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="result.jsp">
		아이디 : <input type="text" size="20"><br>
		비밀번호 : <input type="password" size="20"><br>
		<input type="submit" value="로그인">
	</form>
	
	<%-- 직접 컨텍스트 이름을 입력해 요청 - http://localhost:8080/pro14/memberForm.jsp
	> 컨덱스트가 이름이 바뀌면 요청 실패 (개발자는 일일히 코드를 변경해 주어햐 함)
	
	표현식(자바코드) - <%=request.getContextPath %>/memberForm.jsp
	> 자바 코드를 작성하여 화면이 복잡해짐
	
	표현언어 (pageContext 객체 사용) - ${pageContext.request.ContextPath}/memberForm.jsp --%>
	
	<a href="${pageContext.request.contextPath}/memberForm.jsp">회원가입</a>
</body>
</html>