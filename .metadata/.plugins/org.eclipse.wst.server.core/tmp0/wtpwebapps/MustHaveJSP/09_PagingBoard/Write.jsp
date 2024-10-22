<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="IsLoggedIn.jsp" %> <!-- 로그인 확인 -->
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
	<jsp:include page="../Common/Link.jsp" />
	
	<div class="container">
	    <h2 class="mt-5">회원제 게시판 - 글쓰기(Write)</h2>
		<form action="WriteProcess.jsp" method="post" name="writeFrm" 
			onsubmit="return validateForm(this);">
			<table border="1" style="width: 100%;">
				<tbody>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="title" style="width: 90%;">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea name="content" style="width: 90%; height: 100px;"></textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2" style="text-align: center;">
							<button type="submit">작성 완료</button>
							<button type="reset">다시 입력</button>
							<button type="button" onclick="location.href='List.jsp';">목록 보기</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>