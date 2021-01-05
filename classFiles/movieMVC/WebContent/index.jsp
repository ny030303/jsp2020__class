<%@page import="movie.MemberVO"%>
<%@page import="movie.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="movie.MovieDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>

<%	
	MemberVO member = (MemberVO) session.getAttribute("USER");
	MovieDAO instance = MovieDAO.getInstance();
	ArrayList<MovieVO> list = instance.selectMovieList(0);
%>
<% if(member == null) { %>
	<h2>로그인 페이지</h2>
	<br>
	<form method="post" action="MemberLogin.do" name="loginForm">
		<input type="text" name="id" placeholder="id를 입력">
		<input type="password" name="pw" placeholder="password를 입력">
		<input type="submit" value="로그인">
	</form>
	
<%} else { %>
<h2> 영화 전체 목록 </h2>
<table border="1">
	<tr align="center">
		<th width="200">영화 이미지</th>
		<th width="100">시간</th>
		<th width="100">제목</th>
	</tr>
	<%	for(MovieVO vo : list){
	%>
	<tr align="center" onclick="location.href='MovieSchedule.do?movieNo=<%=vo.getMovieNo()%>'">
		<td width="200"><img src="./images/<%=vo.getImg() %>" width="200"></td>
		<td width="100"><%=vo.getRuntime()%>분</td>
		<td width="100"><%=vo.getMovieName()%></td>
	</tr>
	<%  }  %>
</table>

 <%} %>
<jsp:include page="footer.jsp"/>  