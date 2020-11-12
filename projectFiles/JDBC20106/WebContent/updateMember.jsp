<%@page import="member.MemberVO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>회원 정보 수정</title></head>
<jsp:include page="header.jsp"/>
<body>
<%	
	//utf-8 인코딩	
	request.setCharacterEncoding("utf-8");
	// memno 가져옴.
	int memno = Integer.parseInt(request.getParameter("memno"));
	// MemberDAO 인스턴스 가져옴.
	MemberDAO instance = MemberDAO.getInstance();
	
	// 수정할 멤버 memno로 멤버 정보 가져옴.
	MemberVO user = instance.getAMember(memno);
	// 가져온 맴버 memno(db에 저장된 memno)가 입력된 memno와 일치한지 
	if(user.getMemno() == memno) {
%>
<%-- post로 보냄. updateMemberPro.jsp에서 받음. --%>
<form action="updateMemberPro.jsp" method="post">
	<table border="1" style="width:400">
		<tr><td colspan="2" align="center"><b>회원 수정 정보 입력</b></td></tr>
		<%-- 폼으로 값을 보낼때 readonly의 값은 전송되지만,  disabled의 값은 전송이 안됨.--%>
		<%-- 회원번호 읽을 수만 있음. 수정불가. --%>
		<tr><th>회원번호</th><td><input type="text" name="memno" value="<%= user.getMemno() %>" readonly></td>
		</tr>
		<%-- 이름 읽을 수만 있음. 수정불가.  --%>
		<tr><th>이름</th>  <td><input type="text" name="name" value="<%= user.getName() %>" disabled></td>
		</tr>
		<%-- 아이디 읽을 수만 있음. 수정불가.  --%>
		<tr><th>아이디</th><td><input type="text" name="id" value="<%= user.getId() %>" disabled></td>
		</tr>
		<tr><th>비밀번호</th><td><input type="password" name="pass" value="<%= user.getPass() %>"></td>
		</tr>
		<%-- 비밀번호 확인 input 추가됨. --%>
		<tr><th>비밀번호 확인</th><td><input type="password" name="passCheck" value=""></td>
		</tr>
		<tr><th>생년(4자리)</th>
			<td><select name="birth">
			<%-- 생년 2001년부터 2010년까지 선택 가능함. --%>
<%			for(int i = 2001; i <= 2010; i++) {
				if(user.getBirth() == i) {
					// user의 생년일때 그 생년을 선택
%>					<option value="<%= i %>" selected><%= i %></option>
<%				} else {
%>					<option value="<%= i %>"> <%= i %> </option>
<%				}
			}
%>
			</select></td>
		</tr>
		<tr><th>성별</th>
			<td>
			
<%			if(user.getGender().equals("M")) {
				// 성별이 남자일 경우 남자 쪽에 체크
%>				<input type="radio" name="gender" value="M" checked>M (남)
				<input type="radio" name="gender" value="F">F (여)
<%			} else {
				// 성별이 여자일 경우 여자 쪽에 체크
%>				<input type="radio" name="gender" value="M">M (남)
				<input type="radio" name="gender" value="F" checked>F (여)
<%			}
%> 			</td>
		</tr>
		<tr><th>직업</th><td><input type="text" name="job" value="<%= user.getJob() %>"></td>
		</tr>
		<tr><th>도시</th><td><input type="text" name="city" value="<%= user.getCity() %>"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<%-- 수정을 누르면  updateMemberPro.jsp 으로 넘어감.--%>
			<%-- 취소를 누르면 입력한 값이 초기화 됨.--%>
				<input type="submit" value="수정"> <input type="reset" value="취소">
			</td>
		</tr>
	</table>
	</form>
<% }  %>
<jsp:include page="footer.jsp"/>
</body> </html>