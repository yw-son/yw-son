<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�̹��� ����Ʈ ���</title>
</head>
<body>
	<ul class="List_type">
	<li>
		<span style="margin-left: 50px">�̹���</span>
		<span>�̹��� �̸�</span>
		<span>�����ϱ�</span>
		</li>
		<%
			for(int i=0; i<10; i++) {
		%>
		<li>
		<a href="#" style="margin-left: 50px;">
    	<img src="image/cat.jpg" width="90" height="90">
		</a>
		<a href="#"><strong>�̹��� �̸� : ������<%=1 %></strong></a>
		<a href="#"><input name="chk<%=1 %>" type="checkbox" /></a>
		</li>
		
		<%
			}
		%>
	</ul>
</body>
</html>