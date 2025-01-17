<%@page import="java.sql.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>회원 목록 조회 테스트(executeQuery() 사용)</h2>
	<%
	// 1.DB에 연결
	JDBConnect jdbc = new JDBConnect();
	
	// 2.쿼리문 생성
	String sql = "select id, pass, name, regidate from member";  // select * from member
	Statement stmt = jdbc.conn.createStatement();
	
	// 3.쿼리 수행
	ResultSet rs = stmt.executeQuery(sql);
	
	// 결과 확인(웹페이지에 출력)
	while(rs.next()){
		String id = rs.getString(1);
		String pass = rs.getString(2);
		String name = rs.getString("name");
		Date regidate = rs.getDate("regidate");
		
		out.println(String.format("%s %s %s %s <br>", id, pass, name, regidate));		
	}
	
	// 4.연결 닫기
	jdbc.close();
	%>

</body>
</html>