<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardVO"%>
<%@page import="board.BoardDAO"%>
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
		ArrayList<BoardVO> list = instance.selectAllBoards();
		
		for (int i = 0; i < list.size(); i++) {
			BoardVO vo = list.get(i);
	%>
			<hr style="border:4 solid">
			<%=vo.getBoardNum() %>번째 글  &nbsp;&nbsp;&nbsp;작성날짜 : <%=vo.getWriteDate() %> <br>
			작성자 : <%=vo.getName() %> <br>
			제목 : <%=vo.getTitle() %> <br>
			내용 : <%=vo.getContent() %> <br>
			<a href="updateForm.jsp?boardNum=<%=vo.getBoardNum() %>">글 수정</a>&nbsp;
			<a href="#">글 삭제</a>
	<%		
		}
	%>
</body>
</html>