<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script type="text/javascript">
	function validateForm(form){
		if(!form.user_id.value){
			alert("아이디를 입력하세요.");
			return false;
		}
		if(form.user_pass.value==""){
			alert("패스워드를 입력하세요");
			return false;
		}
	}
</script>
</head>
<body>
	<jsp:include page="../Common/BSLink.jsp" />
	
	<div class="container">
		<h2 class="my-5 text-center">로그인 페이지</h2>
		
		<div class="mb-3 text-center">
			<span style="color: red; font-size: 1.2em;">
				<%= request.getAttribute("LoginErrMsg")  == null ?
						"" : request.getAttribute("LoginErrMsg") %>
			</span>
		</div>
		<%
		if(session.getAttribute("UserId") == null){  // 로그인 상태 확인
			// 로그아웃 상태
		%>	
		<form action="LoginProcess.jsp" method="post" name="loginFrm" onsubmit="return validateForm(this);">
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">아이디</label>
			    <div class="col-sm-10">
			      	<input type="text" class="form-control" name="user_id">
		    	</div>
		    </div>
		    <div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">패스워드</label>
			    <div class="col-sm-10">
			      	<input type="password" class="form-control" name="user_pass">
			    </div>
			</div>
			<div class="mb-3 text-center">
				<button type="submit" class="btn btn-primary">로그인</button>
			</div>			
		</form>	
		<%	
		} else { // 로그인된 상태	
		%>
			<div class="mb-3 text-center">
				<%= session.getAttribute("UserName") %> 회원님, 로그인하셨습니다. <br>
				<button type="submit" class="btn btn-primary mt-3" onclick="location.href='Logout.jsp'">로그아웃</button>
			</div>
		<%	
		}
		%>
	</div>		
	
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>