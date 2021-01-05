<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>


<h2> 영화 목록 </h2>
<table border="1">
	<tr align="center">
		<th width="200">영화 이미지</th>
		<th width="100">시간</th>
		<th width="100">제목</th>
	</tr>
	<c:forEach var="movie" items="${list}">
		<tr align="center" onclick="location.href='MovieSchedule.do?movieNo=${movie.getMovieNo()}'">
			<th width="200"><img src="./images/${movie.getImg()}" width="200"></th>
			<td>${movie.getRuntime()}분</td>
			<td width="100">${movie.getMovieName()}</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="footer.jsp"/> 