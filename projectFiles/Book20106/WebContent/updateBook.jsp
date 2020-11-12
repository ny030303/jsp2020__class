<%@page import="book.BookVO"%>
<%@page import="book.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- header.jsp 를 가져온다--%>
<%@ include file="header.jsp" %>
<%	
	//utf-8 인코딩
	request.setCharacterEncoding("utf-8");
	// bcode 받아옴.
	int bcode = Integer.parseInt(request.getParameter("bcode"));
	//BookDAO 객체인 instance 의 getABook(bcode) 메소드를 이용해서 bcode 를 가진 book 정보 가져오기 -> 수정 폼에 넣어주기
	BookVO vo = BookDAO.getInstance().getABook(bcode);
%>
<%-- BookUpdate.do 로 보내는 post 형식 form --%>
<form action="BookUpdate.do" method="post">
		
<table border="1">
	<%-- value 에 newBcode 넣어주기, readonly로 수정 불가면서 값은 보낼 수 있게 만듦.--%>
	<%--input 태그에 required 를 넣어주면서 유효성 체크를 한다.--%>
	<%-- placeholder로 입력값에 대한 설명을 넣어줄 수 있다. --%>
	<%--bcode 이름으로 숫자 값이 보내진다.(vo에 있는 기존 bcode를 보여줌) --%>
	<tr><th>도서코드</th><td><input type="text" name="bcode" value="<%=vo.getBcode() %>" required readonly></td></tr>
	<%--btitle 이름으로 문자 값이 보내진다. (vo에 있는 기존 btitle를 보여줌)  --%>
	<tr><th>도서제목</th><td><input type="text" name="btitle" value="<%=vo.getBtitle() %>" placeholder="ex) 자바초고쑤" required></td></tr>
	<%--bwriter 이름으로 문자 값이 보내진다. (vo에 있는 기존 bwriter를 보여줌) --%>
	<tr><th>도서저자</th><td><input type="text" name="bwriter" value="<%=vo.getBwriter() %>" placeholder="ex) 정길순" required></td></tr>
	
	<tr><th>출판사코드</th>
	<td>
	<%--select 태그는  post로 보내면 bpub 이름으로 양영디지털  북스미디어 등이 아닌 value(ex. 1001, 1002 ... ) 값이 들어간다.--%>
		<select name="bpub">
			<%-- 각각  vo 에 있는 bpub 값과 같은지 비교 후, 같은 것을 selected 해주기--%>
			<option value="1001"  <%if(vo.getBpub() == 1001) {%> selected <%} %>>양영디지털</option>
			<option value="1002" <%if(vo.getBpub() == 1002) {%> selected <%} %>>북스미디어</option>
			<option value="1003" <%if(vo.getBpub() == 1003) {%> selected <%} %>>한빛아카데미</option>
			<option value="1004" <%if(vo.getBpub() == 1004) {%> selected <%} %>>이지스</option>
		</select>
	</td>
	</tr>
	<%--bprice 이름으로 숫자 값이 보내진다. (vo에 있는 기존 bprice를 보여줌) --%>
	<tr><th>가격</th><td><input type="text" name="bprice" maxlength="10" value="<%=vo.getBprice() %>" placeholder="ex) 3000" required></td></tr>
	<%--date 이름으로 날짜 값이 보내진다.(vo에 있는 기존 date를 보여줌) --%>
	<tr><th>출간날짜</th><td><input type="date" name="bdate" value="<%=vo.getBdate() %>" placeholder="ex) 2019-07-22"  required></td></tr>
	<%--등록버튼을 누르면 전송되고,  재작성 버튼을 누르면 다시 작성 할 수 있다.--%>
	<tr><td colspan = "2" align = "center">
		<input type = "submit" value = "수정">
		<a href="BookList.do"><input type = "button" value = "취소"></a>
	    </td>
    </tr>
</table>

</form>
<%-- footer.jsp 를 가져온다--%>
<jsp:include page="footer.jsp"/>