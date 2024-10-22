<%@page import="model1.board.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// DAO 생성하여 DB 연결
BoardDAO bDao = new BoardDAO(application);

// 사용자가 입력한 검색 조건을 Map에 저장
Map<String, Object> param = new HashMap<String, Object>();
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
if(searchWord != null){
	param.put("searchField", searchField);
	param.put("searchWord", searchWord);
}

int totalCount = bDao.selectCount(param);  // 게시물 수 확인
List<BoardVO> boardList = bDao.selectList(param);  // 게시물 목록 받기
bDao.close();  // DB 연결 닫기
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	
	<h2>목록 보기(List)</h2>
	<!-- 검색 폼 -->
	<!-- form태그에 action이 없으면 어디로?? => 직전화면으로 이동 -->
	<form method="get">
		<table border="1" width="100%">
			<tr>
				<td style="text-align:center;">
					<select name="searchField">
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
					<input type="text" name="searchWord">
					<button type="submit">검색하기</button>
				</td>
			</tr>
		</table>
	</form>
	<!-- 게시물 목록 테이블 -->
	<table border="1" width="100%">
		<thead>
			<tr>
				<th width="10%">번호</th>
				<th width="50%">제목</th>
				<th width="15%">작성자</th>
				<th width="10%">조회수</th>
				<th width="15%">작성일</th>
			</tr>
		</thead>
	<%
	if(boardList.isEmpty()){
		// 게시물이 없을 때
	%>
		<tbody>
			<tr>
				<td colspan="5" style="text-align:center;">등록된 게시물이 없습니다.</td>
			</tr>	
		</tbody>	
	<%
	}else {
		// 게시물이 있을 때
		int virtualNum = 0;  // 화면상 게시물 번호
		for(BoardVO bVo : boardList){
			virtualNum = totalCount--;  // 전체 게시물 수에서 시작해 1씩 감소
		%>
			<tbody>
				<tr style="text-align:center;">
					<td><%= virtualNum %></td>
					<td>
						<a href="View.jsp?num=<%= bVo.getNum() %>"><%= bVo.getTitle() %></a> 
					</td>
					<td><%= bVo.getId() %></td>
					<td><%= bVo.getVisitcount() %></td>
					<td><%= bVo.getPostdate() %></td>
				</tr>
			</tbody>
	
		<%
		}
	}
	%>
		<!-- 목록 하단의 글쓰기 버튼 -->
		<tfoot>
			<tr>
				<td colspan="5" style="text-align:right;">
					<button type="button" onclick="location.href='Write.jsp';">글쓰기</button>					
				</td>
			</tr>
		</tfoot>
	</table>
	
</body>
</html>