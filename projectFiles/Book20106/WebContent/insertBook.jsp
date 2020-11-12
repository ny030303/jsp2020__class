<%@page import="book.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- header.jsp 를 가져온다--%>
<%@ include file="header.jsp" %>
<%
	// BookDAO 객체인 instance 의 createBcode() 메소드를 이용해서 bcode 최대값 +1 해서 만든 새로운 bcode 가져오기
	int newBcode = BookDAO.getInstance().createBcode();
%>

<%-- BookInsert.do 로 보내는 post 형식 form --%>
<form action="BookInsert.do" method="post">

<table border="1">
	<%-- value 에 newBcode 넣어주기, readonly로 수정 불가면서 값은 보낼 수 있게 만듦.--%>
	<%--input 태그에 required 를 넣어주면서 유효성 체크를 한다.--%>
	<%-- placeholder로 입력값에 대한 설명을 넣어줄 수 있다. --%>
	<%--bcode 이름으로 숫자 값이 보내진다. --%>
	<tr><th>도서코드</th><td><input type="text" name="bcode" value="<%=newBcode %>" required readonly></td></tr>
	<%--btitle 이름으로 문자 값이 보내진다. --%>
	<tr><th>도서제목</th><td><input type="text" name="btitle" placeholder="ex) 자바초고쑤" required></td></tr>
	<%--bwriter 이름으로 문자 값이 보내진다. --%>
	<tr><th>도서저자</th><td><input type="text" name="bwriter" placeholder="ex) 정길순" required></td></tr>
	<tr><th>출판사코드</th>
	<td>
	<%--select 태그는  post로 보내면 bpub 이름으로 양영디지털  북스미디어 등이 아닌 value(ex. 1001, 1002 ... ) 값이 들어간다.--%>
		<select name="bpub">
			<option value="1001">양영디지털</option>
			<option value="1002">북스미디어</option>
			<option value="1003">한빛아카데미</option>
			<option value="1004">이지스</option>
		</select>
	</td>
	</tr>
	<%--bprice 이름으로 숫자 값이 보내진다. --%>
	<tr><th>가격</th><td><input type="text" name="bprice" maxlength="10" placeholder="ex) 3000" required></td></tr>
	<%--date 이름으로 날짜 값이 보내진다. --%>
	<tr><th>출간날짜</th><td><input type="date" name="bdate" placeholder="ex) 2019-07-22" required></td></tr>
	<%--등록버튼을 누르면 전송되고,  재작성 버튼을 누르면 다시 작성 할 수 있다.--%>
	<tr><td colspan = "2" align = "center">
		<input type = "submit" value = "등록">
		<input type = "reset" value = "재작성">
	    </td>
    </tr>
</table>

</form>
<%-- footer.jsp 를 가져온다--%>
<jsp:include page="footer.jsp"/>