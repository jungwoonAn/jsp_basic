<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LifeCycle.jsp</title>
<script type="text/javascript">
	function requestAction(frm, met){
		if(met == 1){
			frm.method = 'get';
		}else {
			frm.method = 'post';
		}
		
		frm.submit();
	}
</script>
</head>
<body>
	<h2>서블릿 수명주기(Life Cycle) 메서드</h2>
	<form action="LifeCycle.do">
		<button type="button" onclick="requestAction(this.form, 1)">
		GET 방식 요청하기</button>
		<button type="button" onclick="requestAction(this.form, 2)">
		POST 방식 요청하기</button>
	</form>

</body>
</html>