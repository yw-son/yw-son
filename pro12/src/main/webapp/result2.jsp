<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<%
    request.setCharacterEncoding("utf-8");
    String user_id = request.getParameter("user_id");
    String user_pw = request.getParameter("user_pw");
%>
<html>
<head>
<meta charset="UTF-8">
<title>��� ���â</title>
</head>
<body>
    <%
        if(user_id == null || user_id.length() == 0) {
    %>
    ���̵� �Է��ϼ���. <br>
    <a href="/pro12/Login.html">�α��� �ϱ�</a>
    <%
        } else {
        	if(user_id.equals("admin")){
    %>
    
    <h1>�����ڷ� �α��� �߽��ϴ�.</h1>
    <form>
    	<input type="button" value="�ܿ� ���� �����ϱ�">
    	<input type="button" value="�ܿ� ���� �����ϱ�">
    </form>
    
    <%
        	}else {
    %>
    <h1>ȯ���մϴ�. <%=user_id %>��</h1>
    <%
        	}
        	
        }
    %>
</body>
</html>