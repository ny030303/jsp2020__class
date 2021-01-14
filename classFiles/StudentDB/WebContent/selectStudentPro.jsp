<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="student.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		int stuNo = Integer.parseInt(request.getParameter("stuNo"));
		String name = "";
		
		StudentDAO instance = StudentDAO.getInstance();
		Connection conn = instance.getConnection(); // 오라클 연동
		String sql = "select * from STUDENT_TBL where stuNo = ? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql); // ? 넣어주는
		pstmt.setInt(1, stuNo); // ?에 stuNo넣어준다
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			name = rs.getString("stuName");
		} else {
			name = "존재하지 않는 학번입니다.";
		}
	%>
	
	<h1>이름 : <%=name %></h1>
</body>
</html>