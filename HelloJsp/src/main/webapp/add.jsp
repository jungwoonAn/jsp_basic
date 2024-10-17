<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% // 스크립트릿 : jsp에서 Java코드 작성
	String aParam = request.getParameter("a");
	String bParam = request.getParameter("b");
	
	int a = (aParam != null) ? Integer.parseInt(aParam): 0;
	int b = (aParam != null) ? Integer.parseInt(bParam): 0;
	
	int sum = a+b;
%>

  <h1>The sum of a and b is : <%= sum %></h1>

</body>
</html>