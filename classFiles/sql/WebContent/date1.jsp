<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
vo - 데이터베이스에서 컬럼명
date 날짜 -> String 날짜
String -> date

date-> java.util.Date // Mon Dec 30 12:30:30 GMT-0700 2008 //범용 날짜
//각 분리 / 하나의 정보로도 다룰 수 있다.
-java.sql.Date //2020-11-05 12:30:30 오라클 연동 오라클의 date 타입과 일치한다.
</pre>

<%
//date 값을 저장하는 두가지 방법 H: 0 - 23 k: 1-24 h:1-12 K:0-11 hh:mm:ss
//a : 오전 (am) 오후(pm)
	//java.util.Date 2020년 11월 05일
	Date today = new Date();
	//DATE 타입 -> String 타입
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
	
	String today2 = sdf.format(today); //date 타입이 들어간다. 2020년 11월 5일
	
	//String 타입 -> Util Date타입  2020-11-05
	Date testDate = new SimpleDateFormat("yyyy년 MM월 dd일").parse(today2);
	
%>

<%= testDate %>
</body>
</html>