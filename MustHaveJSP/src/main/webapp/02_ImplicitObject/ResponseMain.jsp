<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 - response</title>
</head>
<body>
	<h2>1. 로그인 폼</h2>
	<%
	String loginErr = request.getParameter("loginErr");
	if(loginErr != null) out.print("로그인 실패");
	%>
	<form action="./ResponseLogin.jsp" method="post">
		아이디 : <input type="text" name="user_id"><br>
		패스워드 : <input type="password" name="user_pwd"><br>
		<button type="submit">로그인</button>
	</form>
	
	<h2>2. HTTP 응답 헤더 설정하기</h2>
	<form action="./ResponseHeader.jsp" method="get">
		날짜 형식 : <input type="text" name="add_date" value="2004-10-25 09:00"><br>
		숫자 형식 : <input type="text" name="add_int" value="8282"><br>
		문자 형식 : <input type="text" name="add_str" value="홍길동"><br>
		<button type="submit">응답 헤더 설정/출력</button>
	</form>

</body>
</html>