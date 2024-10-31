<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String mode = request.getParameter("mode");
if(mode != null && mode.equals("1")){
	session.setAttribute("mySession", "세션 영역");
}else if(mode != null && mode.equals("2")){
	session.removeAttribute("mySession");
}else if(mode != null && mode.equals("3")){
	session.invalidate();
}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyListener.jsp</title>
<script type="text/javascript">
function formSubmit(form, modeValue){
	form.mode.value = modeValue;
	form.submit();
}
</script>
</head>
<body>
	<h2>리스너 활용하기</h2>
	<form>
		<input type="hidden" name="mode">
		
		<button type="button" onclick="formSubmit(this.form, 1);">세션 속성 저장</button>
		<button type="button" onclick="formSubmit(this.form, 2);">세션 속성 삭제</button>
		<button type="button" onclick="formSubmit(this.form, 3);">세션 전체 삭제</button>
	</form>

</body>
</html>