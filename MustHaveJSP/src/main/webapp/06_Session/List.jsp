<%@page import="java.util.List"%>
<%@page import="membership.MemberVO"%>
<%@page import="membership.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 리스트</title>
</head>
<body>
	<%
	// web.xml에서 가져온 데이터베이스 연결 정보
	String oracleDriver = application.getInitParameter("OracleDriver");
	String oracleURL = application.getInitParameter("OracleURL");
	String oracleId = application.getInitParameter("OracleId");
	String oraclePwd = application.getInitParameter("OraclePwd");
	
	// DB 연결
	MemberDAO mDao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
	// 리스트로 멤버 리스트 받아오기
	List<MemberVO> members = mDao.getAllMember();
	%>  
	
	<%= members %><br>
	<%= members.get(0) %><br>
	<%= members.get(0).getId() %>

	<table border="1">	
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>가입날짜</th>
		</tr>	
		<%
		for(MemberVO member : members) {
		%>
			<tr>
				<td><%= member.getId() %></td>
				<td><%= member.getPass() %></td>
				<td><%= member.getName() %></td>
				<td><%= member.getRegidate() %></td>
			</tr>
		<%
		}
		%>		
	</table>
	
	<a href="LoginForm.jsp">로그인 화면 이동</a>

</body>
</html>