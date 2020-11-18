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
	<%
		request.setCharacterEncoding("UTF-8");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String writeDate = request.getParameter("writeDate");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDAO instance = BoardDAO.getInstance();
		BoardVO vo = new BoardVO(boardNum, name, pass, title, content, writeDate);
		instance.updateBoard(vo);
	%>
	<jsp:forward page="selectBoard.jsp"/>
</body>
</html>