<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%
String fileName = request.getParameter("fileName");

if (fileName != null && !fileName.isEmpty()) {
    String directory = application.getRealPath("/upload/");
    String filePath = directory + fileName;
    File deleteFile = new File(filePath);
    if (deleteFile.exists()) {
        deleteFile.delete();
    }
}
%>