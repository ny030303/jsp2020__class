<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"/>
<%

%>
<h2>영화 스케줄</h2>
<form action="SeatList.do" method="post">
<table border="1">
	<tr align="center">
		<th width="200">이미지</th>
		<th width="100">제목</th>
		<th width="100">날짜</th>
		<th width="100">상영시간</th>
		<th width="100">장르</th>
		<th width="100">정보</th>
		<th width="100">스캐줄</th>
	</tr>
	<tr align="center">
		<th ><img src='./images/${scheduleList.get(0).getImg()}' width='200'></th>
		<td width="100" >${scheduleList.get(0).getMovieName()}</td>
		<td width="100">${scheduleList.get(0).getRunDay()}</td>
		<td width="100">${scheduleList.get(0).getRuntime()}분</td>
		<td width="100">${scheduleList.get(0).getCategory()}</td>
		<td>${scheduleList.get(0).getInfo()}</td>
		<td>
		<select name="schNo">
			<c:forEach var="sch" items="${scheduleList}">
			    <option value="${sch.getSchNo()}">${sch.getRoomNo()}관 ${sch.getTime()}</option>
		    </c:forEach>
		</select>
		<input type="submit" value="예매하기">
		</td>
	</tr>
</table>
</form>



<jsp:include page="footer.jsp"/> 