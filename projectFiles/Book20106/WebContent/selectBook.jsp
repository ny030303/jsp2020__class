<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<%-- book 전체 데이터를 보여줄 곳 --%>
<table border="1">
	<tr>
		<th>도서코드</th>
		<th>도서제목</th>
		<th>도서저자</th>
		<th>출판사</th>
		<th>가격</th>
		<th>출간날짜</th>
		<th>삭제</th>
	</tr>
	<%-- JSTL 사용함. --%>
	<%-- <c:forEach>는 List, 배열 요소를 순서대로 반복해서 처리할 수 있는 태그 --%>
	<%--book이라는 이름으로 배열요소를 하나씩 출력 가능. --%>
	<c:forEach var="book" items="${list}">

	<tr>
		<%-- updateBook.jsp 로 가는 링크--%>
		<td><a href ="updateBook.jsp?bcode=${book.bcode}">${book.bcode}</a></td>
		<td>${book.btitle}</td>
		<td>${book.bwriter}</td>
		<td>
			<%--<c:choose>는 Java 언어의 switch~ case와 비슷하게 여러 조건중에 하나를 선택함.
			 <c:choose>는 switch에 해당되고 <c:when>는 case에 해당되며
			 <c:otherwise>는 default에 해당됨.  --%>
			<c:choose>
				<c:when test = "${book.bpub == 1001}">양영디지털</c:when>
				<c:when test = "${book.bpub == 1002}">북스미디어</c:when>
				<c:when test = "${book.bpub == 1003}">한빛아카데미</c:when>
				<c:when test = "${book.bpub == 1004}">이지스</c:when>
			</c:choose>
		</td>
		
		<%--<fmt:formatNumber>는 숫자를 양식에 맞춰 문자열로 변환해줌.  --%>
		<td><fmt:formatNumber value="${book.bprice}" pattern="#,###" /></td>
		<td>
		<%--<fmt:formatDate> => 날짜 정보를 담고 있는 객체를 포맷팅하여 출력할 때 사용하는 태그 --%>
		<%--yyyy년MM월dd일 형태로 출력 --%>
		<%--*** 주의할 점:  사용하기 전, 다음의 태그 라이브러리를 jsp 파일 상단에 적어줘야 한다. *** --%>
		<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
        <fmt:formatDate pattern="yyyy년MM월dd일" value="${book.bdate}"/>
		</td>
		<%-- deleteBook.jsp 로 가는 링크--%>
		<td><a href ="deleteBook.jsp?bcode=${book.bcode}">삭제</a></td>
	</tr>
	</c:forEach>
</table>
<jsp:include page="footer.jsp"/>