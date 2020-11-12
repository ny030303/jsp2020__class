<%@page import="member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="member.MemberDAO"%>

<%	
	//utf-8 인코딩	
	request.setCharacterEncoding("utf-8");
	//name, id, pass, passCheck, birth, gender, job, city 받아옴.
	String name = request.getParameter("name");
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	String passCheck = request.getParameter("passCheck");
	int birth = Integer.parseInt(request.getParameter("birth"));
	String gender = request.getParameter("gender");
	String job = request.getParameter("job");
	String city = request.getParameter("city");
	
	String msg = null;
	// 가져온 비밀번호 pass가 확인 비밀번호 passCheck와 일치한지 
	if (pass.equals(passCheck)) {
		// MemberDAO 인스턴스 가져옴.
		MemberDAO instance = MemberDAO.getInstance();
		
		// MemberVO 생성
		MemberVO vo = new MemberVO();
		
		// 생성한 vo에 값 세팅
		vo.setName(name);
		vo.setId(id);
		vo.setPass(pass);
		vo.setBirth(birth);
		vo.setGender(gender);
		vo.setJob(job);
		vo.setCity(city);
		// MemberVO 인 vo를 insert 하기
		int cnt = instance.insertMember(vo);
		// 결과에 맞게 메시지 수정
		if(cnt > 0) {msg = "회원 가입 성공";}
		else msg = "회원 가입 실패";
		
	} else {
		// 비밀번호가 일치하지 않을 때
		msg = "비밀번호가 일치하지 않습니다.";
	}
	
	
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>회원 가입</title></head>
<body>
	<script type="text/javascript">
		// 메시지를 alert에 띄우고 selectMember.jsp로 이동.
		alert('<%= msg %>');
		location.href('selectMember.jsp');
	</script>
</body>
</html>