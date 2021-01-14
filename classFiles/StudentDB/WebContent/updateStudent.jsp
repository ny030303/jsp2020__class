<%@page import="student.StudentVO"%>
<%@page import="student.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file = "header.jsp" %>
	<%
		request.setCharacterEncoding("utf-8");
		int stuNo = Integer.parseInt(request.getParameter("stuNo"));
		
		StudentDAO instance = StudentDAO.getInstance();
		StudentVO student = instance.getStudent(stuNo);
		
		if (student.getStuName() != null) {
	%>		<form action="updateStudentPro.jsp" method="post">
				학번 : <%=stuNo %><br><br>
				비밀번호 : <input type="text" name="password" value="<%=student.getPassword() %>"><br><br>
				이름 : <input type="text" name="name" value="<%=student.getStuName() %>"><br><br>
				학년 : <input type="text" name="grade" value="<%=student.getGrade() %>"><br><br>
				국어점수 : <input type="text" name="kor" value="<%=student.getKor() %>"><br><br>
				수학점수 : <input type="text" name="math" value="<%=student.getMath() %>"><br><br>
				영어점수 : <input type="text" name="eng" value="<%=student.getEng() %>"><br><br>
				<input type="hidden" value="<%=stuNo %>" name="stuNo">
				<input type="submit" value="수정하기">
			</form>
	<%
		}
	%>

<%@include file = "footer.jsp" %>