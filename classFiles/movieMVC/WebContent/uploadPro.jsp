<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>File Upload Result</title>
</head>
<body>
<%
    // 실제로 서버에 저장되는 path
    String directory = application.getRealPath("/upload/");
	int maxSize = 1024 * 1024 * 100;//100MB
	String encoding = "UTF-8";//한글이면 깨지니깐 방지
	MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
	
	String fileName = multipartRequest.getOriginalFileName("file");
	String fileRealName = multipartRequest.getFilesystemName("file");
%>
<h1>디렉토리 : <%= directory %></h1>
<h1> 파일명 : <%= fileName %> </h1>
<h1> 실제 파일명 : <%= fileRealName %></h1>
</body>
</html>