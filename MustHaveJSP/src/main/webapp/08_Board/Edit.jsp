<%@page import="model1.board.BoardVO"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="IsLoggedIn.jsp" %>  <!-- 로그인 확인 -->
<%
//쿼리스트링 파라미터값 받기
String num = request.getParameter("num");

//DB 연결
BoardDAO bDao = new BoardDAO(application);
BoardVO bVo = bDao.selectView(num);  // 게시물 가져오기
// 로그인ID 얻어 본인 확인
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
	
	<h2>회원제 게시판 - 수정하기(Edit)</h2>
	<form action="EditProcess.jsp" method="post" name="editFrm" 
		onsubmit="return validateForm(this);">
		<input type="hidden" name="num" value="<%= num %>">
		<table border="1" style="width: 100%;">
			<tbody>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="title" style="width: 90%;"
							value="<%= bVo.getTitle() %>">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="content" style="width: 90%; height: 100px;"><%= bVo.getContent() %>
						</textarea>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2" style="text-align: center;">
						<button type="submit">수정 완료</button>
						<button type="reset">다시 입력</button>
						<button type="button" onclick="location.href='List.jsp';">목록 보기</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</body>
</html>