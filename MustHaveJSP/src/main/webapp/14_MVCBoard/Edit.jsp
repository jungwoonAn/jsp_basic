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
		if(form.name.value == ""){
			alert("작성자를 입력하세요.");
			form.name.focus();
			return false;
		}
		if(form.title.value == ""){
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		}
		if(form.content.value==""){
			alert("내용을 입력하세요");
			form.content.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container">
		<h2 class="my-5">파일 첨부형 게시판 - 수정하기(Edit)</h2>
		
		<form action="/mvcboard/edit.do" method="post" name="editFrm" enctype="multipart/form-data"
			onsubmit="return validateForm(this);">
			<input type="hidden" name="idx" value="${ vo.idx }">
			<input type="hidden" name="prevOfile" value="${ vo.ofile }">
			<input type="hidden" name="prevSfile" value="${ vo.sfile }">
			
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">작성자</label>
			    <div class="col-sm-10">
			    	<input type="text" class="form-control" name="name" value="${ vo.name }">
			    </div>
			</div>
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">제목</label>
			    <div class="col-sm-10">
			    	<input type="text" class="form-control" name="title" value="${ vo.title }">
			    </div>
			</div>
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">내용</label>
			    <div class="col-sm-10">
			    	<textarea class="form-control" rows="5"  name="content">${ vo.content }
			    	</textarea>
			    </div>
			</div>
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">첨부파일</label>
			    <div class="col-sm-10">
			    	<input type="file" class="form-control" name="ofile">
			    </div>
			</div>
			
			<div class="mb-3 text-center">
				<button type="submit" class="btn btn-success">수정 완료</button>
				<button type="reset" class="btn btn-secondary">다시 입력</button>
				<button type="button" class="btn btn-primary" onclick="location.href='/mvcboard/list.do';">목록 보기</button>
			</div>	
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>

</body>
</html>