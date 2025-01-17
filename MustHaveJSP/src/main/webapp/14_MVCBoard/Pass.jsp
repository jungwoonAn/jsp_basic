<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script type="text/javascript">
	function validateForm(form){  // 폼내용 검증
		if(form.pass.value == ""){
			alert("비밀번호를 입력하세요.");
			form.pass.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container">
		<h2 class="my-5">파일 첨부형 게시판 - 비밀번호 검증(Pass)</h2>
		
		<form action="/mvcboard/pass.do" method="post" name="passFrm" 
			onsubmit="return validateForm(this);">
			<input type="hidden" name="idx" value="${ param.idx }">
			<input type="hidden" name="mode" value="${ param.mode }">
			
			<div class="mb-3 row">
			    <label class="col-sm-4 col-form-label">비밀번호</label>
			    <div class="col-sm-6">
			    	<input type="password" class="form-control" name="pass">
			    </div>
			</div>
			
			<div class="mb-3 text-center">
				<button type="submit" class="btn btn-success">검증하기</button>
				<button type="reset" class="btn btn-secondary">다시 입력</button>
				<button type="button" class="btn btn-primary" onclick="location.href='/mvcboard/list.do';">목록 바로가기</button>
			</div>	
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>