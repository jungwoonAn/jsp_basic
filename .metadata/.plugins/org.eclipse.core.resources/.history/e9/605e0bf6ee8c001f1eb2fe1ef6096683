<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 - response</title>
</head>
<body>
	<%
	String id = request.getParameter("user_id");
	String pwd = request.getParameter("user_pwd");
	
	if(id.equalsIgnoreCase("test01") && pwd.equalsIgnoreCase("1234")){
		// 응답 페이지로 이동
		response.sendRedirect("ResponseWelcome.jsp");
	}else {
		// 인증 실패하면, ResponseMain.jsp로 포워드(전달)
		RequestDispatcher dispatcher = request.getRequestDispatcher("ResponseMain.jsp?loginErr=1");
		dispatcher.forward(request, response);
	}
	%>
</body>
</html>