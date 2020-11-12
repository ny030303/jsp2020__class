<%@page import="member.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>레코드 출력</title></head>
<body>
<%--header.jsp를 가져와 같이 보여줌. --%>
<jsp:include page="header.jsp"/>
<%	
	//MemberDAO 인스턴스 가져옴.
	MemberDAO instance = MemberDAO.getInstance();
	// 인스턴스에서 멤버 정보들을 가져와서 list에 담음.
	ArrayList<MemberVO> list = instance.selectMembers();
%>
<h2> 회원 목록 </h2>
<table border="1">
	<tr align="center">
		<th width="100">회원번호</th>
		<th width="100">이름</th><th width="100">아이디</th>
		<th width="100">비밀번호</th><th width="100">생년(4자리)</th>
		<th width="50">성별</th><th width="100">직업</th>
		<th width="100">도시</th><th width="100">가입일자</th>
		<th width="50">탈퇴</th>
	</tr>
	<%-- for 문을 돌면서 list 의 멤버 정보들을 보여줌. --%>
	<%	for(MemberVO vo : list){ %>
	<tr align="center">
		<%-- a 태그로  누르면 memno를 보내주고 updateMember.jsp 로 이동하게 함. (수정) --%>
		<td width="100"><a href="updateMember.jsp?memno=<%=vo.getMemno()%>"><%=vo.getMemno() %></a></td>
		<td width="100"><%=vo.getName() %></th><th width="100"><%=vo.getId() %></td>
		<td width="100"><%=vo.getPass() %></th><th width="100"><%=vo.getBirth() %></td>
		<td width="50"><%=vo.getGender() %></th><th width="100"><%=vo.getJob() %></td>
		<td width="100"><%=vo.getCity() %></th><th width="100"><%=vo.getJoinDate().toString() %></td>
		<%-- a 태그로  누르면 memno를 보내주고 deleteMember.jsp 로 이동하게 함. (삭제) --%>
		<td width="50"><a href="deleteMember.jsp?memno=<%=vo.getMemno()%>">탈퇴</a></th>
	</tr>
	<%  }  %>
</table>
<%--footer.jsp를 가져와 같이 보여줌. --%>
<jsp:include page="footer.jsp"/>
</body>
</html>