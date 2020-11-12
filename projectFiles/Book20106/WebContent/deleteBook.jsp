<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	// utf-8 인코딩
	request.setCharacterEncoding("utf-8");
	// bcode 받아옴.
	int bcode = Integer.parseInt(request.getParameter("bcode"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>20106 정나영</title>
</head>
<body>
	<script type="text/javascript">
		// confirm return 값은 true(예)와 false(아니오)로 나뉨
		if(confirm("정말 삭제하겠습니까?")) {
			// true 일경우 BookDelete.do 로 get 방식으로 bcode 전송
			location.href('BookDelete.do?bcode=<%= bcode%>');
		}else {
			// false 일경우 뒤로가기
			history.go(-1);
		}
	</script>
</body>
</html>