<%@page import="model1.board.BoardVO"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 로그인 확인 -->
<%@ include file="../Common/IsLoggedIn.jsp" %> 
<%
 // 쿼리스트링 파라미터값 받기
 String num = request.getParameter("num");

 // DB 연결
 BoardDAO bDao = new BoardDAO(application);
 // 게시물 가져오기
 BoardVO bVo = bDao.selectView(num);
 // 로그인한 Session ID얻어 본인 확인
 String sessionId = session.getAttribute("UserId").toString();
 if(!sessionId.equals(bVo.getId())){
	 JSFunction.alertBack("작성자 본인만 수정할 수 있습니다.", out);
	 return;
 }
 bDao.close();
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script type="text/javascript">
	function validateForm(form){  // 폼내용 검증
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
	<jsp:include page="../Common/BSLink.jsp" />
	
	<div class="container">
		<h2 class="my-5">회원제 게시판 - 수정하기(Edit)</h2>
		
		<form action="EditProcess.jsp" method="post" name="editFrm" 
			onsubmit="return validateForm(this);">
			<input type="hidden" name="num" value="<%= num %>">
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">제목</label>
			    <div class="col-sm-10">
			    	<input type="text" class="form-control" name="title" value="<%= bVo.getTitle() %>">
			    </div>
			</div>
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">내용</label>
			    <div class="col-sm-10">
			    	<textarea class="form-control" rows="5"  name="content"><%= bVo.getContent() %>
			    	</textarea>
			    </div>
			</div>
			
			<div class="mb-3 text-center">
				<button type="submit" class="btn btn-success">수정 완료</button>
				<button type="reset" class="btn btn-secondary">다시 입력</button>
				<button type="button" class="btn btn-primary" onclick="location.href='List.jsp';">목록 보기</button>
			</div>	
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>