<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어 - import 속성</title>
</head>
<body>
	<%
		Calendar date = Calendar.getInstance();
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat now = new SimpleDateFormat("HH:mm:ss");
	%>
	
	오늘은 <%= today.format(date.getTime()) %> 입니다.<br>
	지금 시각은 <%= now.format(date.getTime()) %> 입니다.
</body>
</html>