<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
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

    MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());

    String fileName = multipartRequest.getOriginalFileName("file");
    String fileRealName = multipartRequest.getFilesystemName("file");

    String bbsTitle = multipartRequest.getParameter("bbsTitle");
    String bbsContent = multipartRequest.getParameter("bbsContent");
    
    int bbsID = Integer.parseInt(multipartRequest.getParameter("bbsID")); // 수정: bbsID 값 설정

    if (bbsTitle == null || bbsContent == null || bbsTitle.equals("") || bbsContent.equals("")) {
        out.println("<script>");
        out.println("alert('입력이 안 된 사항이 있습니다.')");
        out.println("history.back()");
        out.println("</script>");
    } else {
        BbsDAO bbsDAO = new BbsDAO();
        
        
        if (multipartRequest.getParameter("updateFile") != null ) {
            fileName = null;
            fileRealName = null;
        } else {
        	fileName = multipartRequest.getOriginalFileName("file");
            fileRealName = multipartRequest.getFilesystemName("file");
        } 
    	 
        
        
        int result = bbsDAO.updateWithoutFile(bbsID, bbsTitle, bbsContent, fileName, fileRealName);
        if (result == -1) {
            out.println("<script>");
            out.println("alert('글 수정에 실패했습니다.')");
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