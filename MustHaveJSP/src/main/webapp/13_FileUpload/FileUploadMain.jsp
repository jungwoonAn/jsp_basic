<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FileUpload</title>
<script type="text/javascript">
	function validateForm(form){  // 폼내용 검증
		if(form.title.value == ""){
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		}
		if(form.ofile.value==""){
			alert("첨부 파일은 필수 입력입니다.");
			return false;
		}
	}
</script>
</head>
<body>
	<h3>파일 업로드</h3>
	<span style="color: red;">${ errorMessage }</span>
	<form action="UploadProcess.do" method="post" enctype="multipart/form-data" name="fileForm"
		onsubmit="return validateForm(this);">
		제목 : <input type="text" name="title"> <br>
		카테고리(선택사항) : 
			<input type="checkbox" name="cate" value="사진" checked> 사진 <br>			
			<input type="checkbox" name="cate" value="과제"> 과제 <br>			
			<input type="checkbox" name="cate" value="워드"> 워드 <br>			
			<input type="checkbox" name="cate" value="음원">	음원 <br>		
		첨부 파일 : <input type="file" name="ofile"> <br>
		<button type="submit">전송하기</button>
	</form>
</body>
</html>