<%@page import="student.StudentVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="student.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>
	<%
		// INSERT into STUDENT_TBL(stuNo, password, stuName) values(20104, '0619', '이유진')
		// 쿼리스트링 값을 바로 지정 -> 정적쿼리
		// INSERT into STUDENT_TBL(?, ?, ?, ? , ?, ?) values(20104, '0619', '이유진')
		// 지정되어있지 않는 값을 사용 -> 동적쿼리
		// DAO *암기*
		StudentDAO instance = StudentDAO.getInstance();
		ArrayList<StudentVO> studnetlist = instance.getStudentList();
		// ArrayList<StudentVO> studentlist = StudentDAO.getInstance().getStudentList();
	%>
	
	<h1>학생 목록</h1>
	
	<table>
		<tr>
			<td>학번</td>
			<td>비번</td>
			<td>이름</td>
			<td>학년</td>
			<td>국어</td>
			<td>수학</td>
			<td>영어</td>
		</tr>
		
		<%
			for (StudentVO student : studnetlist) {
		%>	
				<tr>
					<td><%=student.getStuNo() %></td>
					<td><%=student.getPassword() %></td>
					<td><%=student.getStuName() %></td>
					<td><%=student.getGrade() %></td>
					<td><%=student.getKor() %></td>
					<td><%=student.getMath() %></td>
					<td><%=student.getEng() %></td>
					<td><a href="updateStudent.jsp?stuNo=<%=student.getStuNo() %>">수정</a></td> &nbsp;
					<td><a href="deleteStudent.jsp?stuNo=<%=student.getStuNo() %>">삭제</a></td>
				</tr>
		<%		
			}
		%>
	</table>
	<hr>
	
	<form method="get" action="selectStudentPro.jsp">
		학번 : <input type="text" name="stuNo">
		<input type="submit" value="검색">
	</form>
<%@include file = "footer.jsp" %>