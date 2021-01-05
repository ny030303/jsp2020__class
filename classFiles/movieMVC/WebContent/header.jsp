<%@page import="movie.MovieDAO"%>
<%@page import="movie.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 에매 프로젝트</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}


ol, ul {
	list-style: none;
}

body {
	text-align: center;
}

table {
	margin: 0 auto;
}

table tr {
	cursor: pointer;
}

#header {
	display: flex;
	justify-content: center;
}

#header li {
	margin: 10px;
}

.seat-btn {
	width: 40px;
	height: 40px;
	margin: 5px;
}

.seat-wrap {
	display: flex;
    width: 500px;
    flex-flow: row wrap;
    margin: 0 auto;
}
</style>
</head>
<body>
<%	
	MemberVO member = (MemberVO) session.getAttribute("USER");
	MovieDAO instance = MovieDAO.getInstance();
%>
<% if(member != null) { 
		if(member.getId().equals("admin") && member.getPw().equals("admin")) {
%>
<ul id="header">
	<li><a href="#">영화 추가하기</a></li>
	<li><a href="TicketList.do?type=admin">전체 예매 목록 보여주기</a></li>
	<li><a href="MemberLogout.do">로그아웃</a></li>
</ul>
header(영화 추가하기 / 전체 예매 목록 보여주기)
<% } else { %>
<ul id="header">
	<li><a href="index.jsp">메인</a></li>
	<li><a href="MovieList.do?category=01">액선</a></li>
	<li><a href="MovieList.do?category=02">로멘스</a></li>
	<li><a href="MovieList.do?category=03">코미디</a></li>
	<li><a href="MovieList.do?category=04">스릴러</a></li>
	<li><a href="MovieList.do?category=05">애니메이션</a></li>
	<li><a href="TicketList.do?type=user">내 예매 내역</a></li>
	<li><a href="MemberLogout.do">로그아웃</a></li>
</ul>
<%		}
	} %>