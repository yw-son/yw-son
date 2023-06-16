<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberDAO"%>
<%@page import="sec01.ex01.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String id =request.getParameter("id");
    	String pwd =request.getParameter("pwd");
    	String name =request.getParameter("name");
    	String email =request.getParameter("email");
    	
    	// MemberBean 갹체를 생성한 후 외뤈 정보를 속성에 설정
    	MemberBean m = new MemberBean(id, pwd, name, email);
    	MemberDAO memberDAO = new MemberDAO();
    	
    	// addMember() - 회원 정보를 db 테이블에 추가 (m) - 회원 생성
    	memberDAO.addMember(m);
    	
    	// ListMembers() - 전체 회원 정보를 db 테이블에서 조회해 오는 메서드
    	List memberList = memberDAO.listMembers();
    %>
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
		<%
			// size() - List에 저장된 데이터 개수 - size() == 0? 데이터가 x (화원정보 x)
			if(memberList.size() == 0) {
		%>
		<tr>
			<td colspan="5">
			<p align="center">
			<b>
				<span style="font-size: 9pt">등록된 회원이 없습니다.</span>
			</b>
		</tr>
		<%
			}
		// size() != 0 데이터가 0(회원 정보가 0)
			else{
				// for 반복문을 이용해 memberList에 저장된 memberBean 갹채를 한 개씩 가져와 각 속성에 회원 정보를 getter를 이용해 츌력
				for(int i = 0; i < memberList.size(); i++) {
					MemberBean bean = (MemberBean) memberList.get(i);
					
			%>
				<tr align="center">
					<td>
						<%= bean.getId() %>
					</td>
					<td>
						<%= bean.getPwd() %>
					</td>
					<td>
						<%= bean.getName() %>
					</td>
					<td>
						<%= bean.getEmail() %>
					</td>
					<td>
						<%= bean.getJoinDate() %>
					</td>
					
				</tr>
		 	<%
				} //  for문 끝
			
		%>
		
		<%
			} // if-else문 끝
		%>
	</table>
</body>
</html>