<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bbs.BbsDAO"%>
<%@ page import="java.io.File"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%
String userID = null;
if (session.getAttribute("userID") != null) {
	userID = (String) session.getAttribute("userID");
}
if (userID == null) {
	out.println("<script>");
	out.println("alert('로그인을 하세요.')");
	out.println("location.href = 'login.jsp'");
	out.println("</script>");
} else {
	String directory = application.getRealPath("/upload/");
	int maxSize = 1024 * 1024 * 100;
	String encoding = "UTF-8";

	MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding,
	new DefaultFileRenamePolicy());

	String fileName = multipartRequest.getOriginalFileName("file");
	String fileRealName = multipartRequest.getFilesystemName("file");

	String bbsTitle = multipartRequest.getParameter("bbsTitle");
	String bbsContent = multipartRequest.getParameter("bbsContent");

	if (bbsTitle == null || bbsContent == null) {
		out.println("<script>");
		out.println("alert('입력이 안 된 사항이 있습니다.')");
		out.println("history.back()");
		out.println("</script>");
	} else {
		BbsDAO bbsDAO = new BbsDAO();
		int result = bbsDAO.write(bbsTitle, userID, bbsContent, fileName, fileRealName);
		if (result == -1) {
	out.println("<script>");
	out.println("alert('글쓰기에 실패했습니다.')");
	out.println("history.back()");
	out.println("</script>");
		} else {
	out.println("<script>");
	out.println("location.href = 'bbs.jsp'");
	out.println("</script>");
		}
	}
}
%>