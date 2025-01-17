<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 - request</title>
</head>
<body>
	<%
	// 전송된 값이 한글인 경우 깨짐 현상 방지를 위한 인코딩
	request.setCharacterEncoding("utf-8");
	
	String id = request.getParameter("id");
	String gender = request.getParameter("gender");
	String[] favo = request.getParameterValues("favo");
	String favoStr = "";
	if(favo != null){
		for(String f : favo)
			favoStr += f + " ";
	}
	String intro = request.getParameter("intro").replace("\r\n", "<br>");
	%>
	
	<h2>2. 클라이언트의 요청 매개변수 읽기</h2>	
	<ul>
		<li>아이디 : <%= id %></li>
		<li>성별 : <%= gender %></li>
		<li>관심사항 : <%= favoStr %></li>
		<li>자기소개 : <%= intro %></li>
	</ul>

</body>
</html>