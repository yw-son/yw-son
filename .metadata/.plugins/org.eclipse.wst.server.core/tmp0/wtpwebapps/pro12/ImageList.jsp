<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>이미지 리스트 출력</title>
</head>
<body>
	<ul class="List_type">
	<li>
		<span style="margin-left: 50px">이미지</span>
		<span>이미지 이름</span>
		<span>선택하기</span>
		</li>
		<%
			for(int i=0; i<10; i++) {
		%>
		<li>
		<a href="#" style="margin-left: 50px;">
    	<img src="image/cat.jpg" width="90" height="90">
		</a>
		<a href="#"><strong>이미지 이름 : 고양이<%=1 %></strong></a>
		<a href="#"><input name="chk<%=1 %>" type="checkbox" /></a>
		</li>
		
		<%
			}
		%>
	</ul>
</body>
</html>