<%@page import="student.StudentVO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="student.StudentDAO"%>
<%@page import="java.sql.Connection"%>
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
		request.setCharacterEncoding("UTF-8");
		int stuNo = Integer.parseInt(request.getParameter("stuNo"));		
		String password = request.getParameter("password");
		String stuName = request.getParameter("stuName");
		int grade = Integer.parseInt(request.getParameter("grade"));
		int kor = Integer.parseInt(request.getParameter("kor"));
		int math = Integer.parseInt(request.getParameter("math"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		
		StudentVO student = new StudentVO();
		student.setStuNo(stuNo);
		student.setPassword(password);
		student.setStuName(stuName);
		student.setGrade(grade);
		student.setKor(kor);
		student.setMath(math);
		student.setEng(eng);
		
		StudentDAO instance = StudentDAO.getInstance();
		boolean check = instance.insertStudent(student);
		
		if (check) {
	%>	
			<script type="text/javascript">
				alert('학생 추가 완료');
				location.href= "selectStudent.jsp";
			</script>
	<%		
		} else {
	%>	
			<script type="text/javascript">
				alert('학생 추가 실패');
				hitory.back();
			</script>
	<%			
		}
	%>
	
	<jsp:forward page="selectStudent.jsp"/>
</body>
</html>