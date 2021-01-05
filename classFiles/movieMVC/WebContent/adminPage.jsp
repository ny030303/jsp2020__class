<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>
<h2>여기는 관리자 모드</h2>
<br>
<br>
<h4> 전체 예매 목록 </h4>
<table border="1">
	<tr align="center">
		<th width="100">영화 이미지</th>
		<th width="100">유저 아이디</th>
		<th width="100">영화 제목</th>
		<th width="100">상영관 번호</th>
		<th width="100">상영날짜</th>
		<th width="100">영화시간</th>
		<th width="100">예매좌석</th>
		<th width="100">예매한 날짜</th>
	</tr>
	<c:forEach var="ticket" items="${ticketList}">
		<tr align="center">
			<th width="100"><img src="./images/${ticket.getImg()}" width="100"></th>
			<td width="100">${ticket.getId()}</td>
			<td width="100">${ticket.getMovieName()}</td>
			<td width="100">${ticket.getRoomNo()}관</td>
			<td width="100">${ticket.getRunDay()}</td>
			<td width="100">${ticket.getRuntime()}</td>
			<td width="100">${ticket.getSeatNo()}석</td>
			<td width="100">${ticket.getBookDay()}</td>
		</tr>
	</c:forEach>
</table>
<jsp:include page="footer.jsp"/> 