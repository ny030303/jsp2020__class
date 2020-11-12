<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>회원 가입</title></head>
<body>
<jsp:include page="header.jsp"/>
<h2>회원 가입</h2>
<%--insert 폼 --%>

<%-- post로 보냄. insertMemberPro.jsp에서 받음. --%>
<form method="post" action="insertMemberPro.jsp" name="regForm">
	<table border="1" style="width:400">
	<tr><td colspan="2" align="center">
			<b>회원 가입 정보 입력</b>
		</td>
	</tr>
	<%-- 이름 기본값이 강길동으로 설정됨. --%>
	<tr><th>이름</th><td><input type="text" name="name" value="강길동"></td>
	</tr>
	<tr><th>아이디</th>
		<td><input type="text" name="id">
			<%-- 중복확인 클릭시 아이디 체크 하게 설정 , 하단 <script> 태그에  idCheck(id) 참고.--%>
			<input type="button" value="중복확인" onclick="idCheck(this.form.id.value)">
		</td>
	</tr>
	<%-- 비밀번호 기본값이 1234으로 설정됨. --%>
	<tr><th>비밀번호</th><td><input type="password" name="pass" value="1234"></td>
	</tr>
	<%-- 비밀번호 확인 input 추가됨. --%>
	<tr><th>비밀번호 확인</th><td><input type="password" name="passCheck" value=""></td>
	</tr>
	<tr><th>생년(4자리)</th>
		<td><select name="birth">
		<%-- 생년 2001년부터 2010년까지 선택 가능함. --%>
			<% for(int i = 2001; i <= 2010; i++) { %>
				<option value="<%= i %>"><%= i %></option>
			<%} %>
			</select>
		</td>
	</tr>
	<tr><th>성별</th>
		<%-- 성별 기본값은 남자로 설정됨. --%>
		<td><input type="radio" name="gender" value="M" checked>M (남)
			<input type="radio" name="gender" value="F">F (여)
		</td>
	</tr>
	<%-- 직업 기본값은 학생으로 설정됨. --%>
	<tr><th>직업</th><td><input type="text" name="job" value="학생"></td>
	</tr>
	<%-- 도시 기본값은 성남으로 설정됨. --%>
	<tr><th>도시</th><td><input type="text" name="city" value="성남"></td>
	</tr>
	<tr><td colspan="2" align="center">
			<%-- 가입을 누르면  insertMemberPro.jsp 으로 넘어감.--%>
			<%-- 재작성을 누르면 입력한 값이 초기화 됨.--%>
			<input type="submit" value="가입"><input type="reset" value="재작성">
		</td>
	</tr>
	</table>
</form>
	<script type="text/javascript">
		// 이 태그 안은 javascript 구문임.
		// 아이디를 받는 idCheck() 
		function idCheck(id) {	
			if(id=="") {
				// 아이디가 없을때
				alert("아이디를 입력해주세요.");
				document.regForm.id.focus();
			} else {
				// 아이디가 있을때 idCheck.jsp로 아이디를 보냄.
				url = "idCheck.jsp?id="+id;
				window.open(url,"post","width=400,height=200");
			}
		}
	</script>
<jsp:include page="footer.jsp"/>
</body>
</html>