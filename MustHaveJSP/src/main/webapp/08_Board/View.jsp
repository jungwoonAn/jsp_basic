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
<title>회원제 게시판 - 상세보기(View)</title>
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
	<jsp:include page="../Common/Link.jsp" />
	
	<h2>회원제 게시판 - 상세보기(View)</h2>
	<form name="viewFrm">
		<input type="hidden" name="num" value="<%= num %>">
		<table border="1" style="width: 100%;">
			<tbody>
				<tr>
					<th>번호</th>
					<td><%= bVo.getNum() %></td>
					<th>작성자</th>
					<td><%= bVo.getName() %></td>
				</tr>
				<tr>
					<th>작성일</th>
					<td><%= bVo.getPostdate() %></td>
					<th>조회수</th>
					<td><%= bVo.getVisitcount() %></td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3"><%= bVo.getTitle() %></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3" style="height: 100px;">
						<%= bVo.getContent().replace("\r\n", "<br>") %>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4" style="text-align: center;">
					<%
					if(session.getAttribute("UserId")!=null 
						&& session.getAttribute("UserId").toString().equals(bVo.getId())){
					%>
						<button type="button" 
							onclick="location.href='Edit.jsp?num=<%= bVo.getNum() %>';">수정하기</button>
						<!-- 다른 방식으로 삭제
						<button type="button" 
							onclick="location.href='DeleteProcedss.jsp?num=<%= bVo.getNum() %>';">삭제하기</button> -->
						<button type="button" onclick="deletePost();">삭제하기</button>
					<%
					}
					%>						
						<button type="button" onclick="location.href='List.jsp';">목록 보기</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>

</body>
</html>