<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>upload.jsp</title>
</head>
<body>
	<h3>업로드 할 파일을 선택해 주세요.</h3>
	
	<!-- enctype - 폼 데이터가 서버로 제출될 때 해당 데이터가 인코딩되는 방법 명시 -->
	<!-- multipart/foem-data
		여러 부분으로 폼 데이터를 분할하여 전송
		분할된 데이터를 독립적으로 인코딩 (각각 데이터 구분은 식별자를 사용하여 식별)
		(폼 데이터와 파일 업로드가 함께 작성되는 경우, 폼 데이터와 파일 데이터를 각각 독립적으로 인코딩)
		 -->
	
	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file"> <br>
		<input type="submit" value="업로드">
	</form>
	
	<h3>파일 목록</h3>
	<h5>다운로드 할 파일을 선택해 주세요.</h5>
	<table border="1">
		<tr>
			<th>파일번호</th>
			<th>미리보기</th>
			<th>다운로드</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${fileList}" var="file">
			<tr>
				<td>${file.id}</td>
				<td>미리보기</td>
				<td>다운로드</td>
				<td>수정</td>
				<td>삭제</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>