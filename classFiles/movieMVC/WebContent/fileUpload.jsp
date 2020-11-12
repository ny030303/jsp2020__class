<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Form Page</title>
</head>
<body>
<h1>File Form Page</h1>
<hr>
<!-- 파일 업로드를 위해서는 form에 enctype="multipart/form-data" 부여해야 가능하다.  -->
<form action="uploadPro.jsp" method="post" enctype="multipart/form-data">
    파일 : <input type="file" name="file"><br/>
    <input type="submit" value="업로드"><br>
</form>
</body>
</html>