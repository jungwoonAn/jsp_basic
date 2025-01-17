<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h2 class="my-5">파일 첨부형 게시판 - 상세보기(View)</h2>
	
		<form name="viewFrm">			
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">번호</label>
			    <div class="col-sm-4">
			    	<input type="text" class="form-control" name="num" readonly value="${ vo.idx }">
			    </div>
			    <label class="col-sm-2 col-form-label">작성자</label>
			    <div class="col-sm-4">
			    	<input type="text" class="form-control" name="name" readonly value="${ vo.name }">
			    </div>
			</div>
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">작성일</label>
			    <div class="col-sm-4">
			    	<input type="text" class="form-control" name="postdate" readonly value="${ vo.postdate }">
			    </div>
			    <label class="col-sm-2 col-form-label">조회수</label>
			    <div class="col-sm-4">
			    	<input type="text" class="form-control" name="visitcount" readonly value="${ vo.visitcount }">
			    </div>
			</div>
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">제목</label>
			    <div class="col-sm-10">
			    	<input type="text" class="form-control" name="title" readonly value="${ vo.title }">
			    </div>
			</div>
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">내용</label>
			    <div class="col-sm-10">
			    	<textarea class="form-control" rows="5"  name="content" readonly>${ vo.content }			    	
			    	</textarea>
			    	<c:if test="${ not empty vo.ofile and isImage eq true }">
			    	<img alt="첨부파일" src="/Uploads/${ vo.sfile }" style="max-width:100%;">
			    	</c:if>
			    </div>
			</div>
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">첨부파일</label>
			    <div class="col-sm-4">
			    <c:if test="${ not empty vo.ofile }">			    
			    	${ vo.ofile }
			    	<a href="/mvcboard/download.do?ofile=${ vo.ofile }&sfile=${ vo.sfile }&idx=${ vo.idx }">[다운로드]</a>
			    </c:if>
			    </div>
			    <label class="col-sm-2 col-form-label">다운로드수</label>
			    <div class="col-sm-4">
			    	<input type="text" class="form-control" name="name" readonly value="${ vo.downcount }">
			    </div>
			</div>
			
			<div class="mb-3 text-center">				
				<button type="button" class="btn btn-success"
					onclick="location.href='/mvcboard/pass.do?mode=edit&idx=${ param.idx }';">수정하기</button>
				<button type="button" class="btn btn-danger" onclick="location.href='/mvcboard/pass.do?mode=delete&idx=${ param.idx }';">삭제하기</button>
									
				<button type="button" class="btn btn-primary" onclick="location.href='/mvcboard/list.do';">목록 보기</button>
			</div>	
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>