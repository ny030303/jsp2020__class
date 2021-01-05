<%@page import="java.util.Arrays"%>
<%@page import="movie.MovieDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>

<h2>좌석 선택</h2>
<div class="seat-wrap">
	<c:forEach var="seat" items="${seatInfo}" varStatus="status">
		<c:choose>
			<c:when test="${seatInfo[status.count-1] == 1}"><button class="seat-btn" disabled>${status.count}</button></c:when>
			<c:when test="${seatInfo[status.count-1] != 1}">
			<button class="seat-btn" onclick="location.href='TicketInsert.do?seatNo=${status.count-1}&schNo=${schNo}';">${status.count}</button>
			</c:when>
		</c:choose>
	</c:forEach>
</div>

<jsp:include page="footer.jsp"/>  