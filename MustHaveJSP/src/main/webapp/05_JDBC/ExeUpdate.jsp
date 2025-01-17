<%@page import="java.sql.PreparedStatement"%>
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
	<h2>회원 추가 테스트(executeUpdate() 사용)</h2>
	<%
	// 1.DB 연결
	JDBConnect jdbc = new JDBConnect();
	
	// 테스트용 입력값 준비
	String id="test02";
	String pass = "2222";
	String name="테스트02";
	
	// 2.쿼리문 생성
	String sql = "insert into member(id, pass, name) values(?, ?, ?)";
	PreparedStatement pstmt = jdbc.conn.prepareStatement(sql);
	pstmt.setString(1, id);
	pstmt.setString(2, pass);
	pstmt.setString(3, name);
	
	// 3.쿼리 수행
	int inResult = pstmt.executeUpdate();  // 저장성공:1, 실패:0
	out.println(inResult + "행이 입력되었습니다.");
	
	// 4.연결 닫기
	jdbc.close();
	%>

</body>
</html>