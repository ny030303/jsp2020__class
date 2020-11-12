<%@page import="member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="member.MemberDAO"%>

<%	
	// utf-8 인코딩
	request.setCharacterEncoding("utf-8");
	// memno와 pass 받아옴.
	int memno = Integer.parseInt(request.getParameter("memno"));
	String pass = request.getParameter("pass");
	// MemberDAO 인스턴스 가져옴.
	MemberDAO instance = MemberDAO.getInstance();
	
	// 삭제할 멤버 memno로 멤버 정보 가져옴.
	MemberVO vo = instance.getAMember(memno);
	String msg = null;
	
	// 가져온 맴버 비밀번호(db에 저장된 번호)가 입력된 비밀번호와 일치한지 
	if(vo.getPass().equals(pass)) {
		// 맞으면 지우기
		int cnt = instance.deleteMember(memno);
		// 결과에 맞게 메시지 수정
		if(cnt > 0) {msg = "회원 탈퇴 완료";}
	} else {msg = "비밀번호 오류";}
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>회원 탈퇴</title></head>
<body>
	<script type="text/javascript">
		alert('<%=msg%>');
		// history.go(-1);
		location.href('selectMember.jsp');
	</script>
</body>
</html>