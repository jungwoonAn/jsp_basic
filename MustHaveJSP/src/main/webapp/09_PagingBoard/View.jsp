<%@page import="model1.board.BoardVO"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
// 쿼리스트링 파라미터값 받기
String num = request.getParameter("num");

// DB 연결
BoardDAO bDao = new BoardDAO(application);
bDao.updateVisitCount(num);
BoardVO bVo = bDao.selectView(num);  // 게시물 가져오기
bDao.close();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script type="text/javascript">
	function deletePost(){
		let confirmed = confirm("정말로 삭제하겠습니까?");
		if(confirmed){
			const form = document.viewFrm;
			form.method = "post";  // 전송방식
			form.action = "DeleteProcess.jsp";  // 전송경로
			form.submit();  // 폼값 전송
		}
	}	
</script>
</head>
<body>
	<jsp:include page="../Common/BSLink.jsp" />
	
	<div class="container">
		<h2 class="my-5">회원제 게시판 - 상세보기(View)</h2>
	
		<form name="viewFrm">
			<input type="hidden" name="num" value="<%= num %>">
			
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">번호</label>
			    <div class="col-sm-4">
			    	<input type="text" class="form-control" name="num" readonly value="<%= bVo.getNum() %>">
			    </div>
			    <label class="col-sm-2 col-form-label">작성자</label>
			    <div class="col-sm-4">
			    	<input type="text" class="form-control" name="name" readonly value="<%= bVo.getName() %>">
			    </div>
			</div>
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">작성일</label>
			    <div class="col-sm-4">
			    	<input type="text" class="form-control" name="postdate" readonly value="<%= bVo.getPostdate() %>">
			    </div>
			    <label class="col-sm-2 col-form-label">조회수</label>
			    <div class="col-sm-4">
			    	<input type="text" class="form-control" name="visitcount" readonly value="<%= bVo.getVisitcount() %>">
			    </div>
			</div>
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">제목</label>
			    <div class="col-sm-10">
			    	<input type="text" class="form-control" name="title" readonly value="<%= bVo.getTitle() %>">
			    </div>
			</div>
			<div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">내용</label>
			    <div class="col-sm-10">
			    	<textarea class="form-control" rows="5"  name="content" readonly><%= bVo.getContent() %>
			    	</textarea>
			    </div>
			</div>
			
			<div class="mb-3 text-center">
				<%
				if(session.getAttribute("UserId")!=null 
					&& session.getAttribute("UserId").toString().equals(bVo.getId())){
				%>
					<button type="button" class="btn btn-success"
						onclick="location.href='Edit.jsp?num=<%= bVo.getNum() %>';">수정하기</button>
					<button type="button" class="btn btn-danger" onclick="deletePost();">삭제하기</button>
				<%
				}
				%>						
					<button type="button" class="btn btn-primary" onclick="location.href='List.jsp';">목록 보기</button>
			</div>	
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>