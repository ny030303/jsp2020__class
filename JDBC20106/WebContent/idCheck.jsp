<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
	//utf-8 인코딩	
	request.setCharacterEncoding("UTF-8");
	// id 받아옴.
	String id = request.getParameter("id");
	// MemberDAO 인스턴스 가져옴.
	MemberDAO instance = MemberDAO.getInstance();
	// 아이디 맞는지 체크한 결과 값을 result에 저장.
	boolean result = instance.idAvailableChk(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복 아이디 확인</title>
</head>
<body bgcolor="yellow">
<div align="center">
	<b><%=id %></b>는
	<%
		
		if(result) {
			// result 값이 true 일때
	%> <font color="blue">사용 가능</font> 합니다.
	<% } else { 
			// result 값이 false 일때
	%> <font color="red">사용 불가능</font> 합니다.
	<% } 
	%> 
	<hr>
	<a href="javascript:self.close()">창 닫기</a>
</div>
</body>
</html>