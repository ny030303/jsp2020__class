<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDAO"%>
<%@page import="board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>간단한 방명록 만들기 Ver1.0</h1>
	<hr>
	<a href="selectBoard.jsp">[방명록 보기]</a>
	<a href="insertBoard.jsp">[방명록 쓰기]</a>
	<a href="index.jsp">[홈으로]</a>
	<hr>
	
	<%
		BoardDAO instance = BoardDAO.getInstance();
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		BoardVO vo = instance.selectABoard(boardNum);
	%>

	<form method="post" action="updateBoard.jsp"> <!-- BoardUpdate.do -->
		<table border="1" style="width:400">
			<input type="hidden" name="boardNum" value="<%=vo.getBoardNum() %>">
			<input type="hidden" name="writeDate" value="<%=vo.getWriteDate() %>">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="name" value="<%=vo.getName() %>" readonly></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pass" value="<%=vo.getPass() %>" readonly></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="2" cols="65" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정">
					<input type="reset" value="재작성">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>