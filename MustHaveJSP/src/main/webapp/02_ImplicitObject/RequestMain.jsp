<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 - request</title>
</head>
<body>
	<h2>1. 클라이언트와 서버의 환경정보 읽기</h2>
	<!-- GET 방식으로 요청 -->
	<a href="./RequestWebInfo.jsp?eng=Hello&kor=안녕">GET 방식 전송</a><br><br>
	
	<!-- POST 방식으로 요청 -->
	<form action="RequestWebInfo.jsp" method="post">
		영어 : <input type="text" name="eng" value="Bye"><br>
		한글 : <input type="text" name="kor" value="잘가"><br>
		<button type="submit">POST 방식 전송</button>
	</form>
	
	<h2>2. 클라이언트의 요청 매개변수 읽기</h2>
	<form action="RequestParameter.jsp" method="post">
		아이디 : <input type="text" name="id" value=""><br>
		성별 : 
		<input type="radio" name="gender" value="man"> 남자 
		<input type="radio" name="gender" value="woman" checked> 여자<br>
		관심사항 : 
		<input type="checkbox" name="favo" value="eco"> 경제 
		<input type="checkbox" name="favo" value="pol" checked> 정치 
		<input type="checkbox" name="favo" value="ent"> 연예<br>
		자기소개 : <br>
		<textarea rows="4" cols="30" name="intro"></textarea><br>
		<button type="submit">전송하기</button>
	</form>
	
	<h2>3. HTTP 요청 헤더 정보 읽기</h2>
	<a href="RequestHeader.jsp">요청 헤더 정보 읽기</a>
</body>
</html>