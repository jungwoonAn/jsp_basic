<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<style type="text/css">
	a {text-decoration: none;}
</style>
</head>
<body>
	<div class="container">
		<h2 class="my-5">파일 첨부형 게시판 - 목록 보기(List)</h2>

		<!-- 검색 폼 -->
		<!-- form태그에 action이 없으면 어디로?? => 직전화면으로 이동 -->
		<form method="get">
			<div class="mb-3 row">
				<div class="col-2">
					<select class="form-select" name="searchField">
					    <option value="title" selected>제목</option>
					    <option value="content">내용</option>
					</select>
				</div>
				<div class="col-4">
					<input type="text" class="form-control" name="searchWord">
				</div>
				<div class="col-2">
					<button type="submit" class="btn btn-success">검색하기</button>
				</div>
			</div>
		</form>
		
		<table class="table table-hover text-center">
			<thead>
			    <tr>
				    <th width="10%">번호</th>
					<th width="*">제목</th>
					<th width="15%">작성자</th>
					<th width="10%">조회수</th>
					<th width="15%">작성일</th>
					<th width="8%">첨부</th>
			    </tr>
			</thead>
		<c:choose>
			<c:when test="${ empty boardList }">  <!-- 게시물이 없을 때 -->
				<tbody>
					<tr>
						<td colspan="5" style="text-align:center;">등록된 게시물이 없습니다.</td>
					</tr>	
				</tbody>
			</c:when>	
			<c:otherwise>  <!-- 게시물이 있을 때 -->
				<c:forEach items="${ boardList }" var="row" varStatus="loop">
					<tbody>
						<tr style="text-align:center;">
							<td>${ map.totalCount - (((map.pageNum - 1) * map.pageSize) + loop.index) }</td>
							<td>
								<a href="/mbcboard/view.do?idx=${ row.idx }">${ row.title }</a> 
							</td>
							<td>${ row.name }</td>
							<td>${ row.visitcount }</td>
							<td>${ row.postdate }</td>
							<td>
							<c:if test="${ not empty row.ofile }">
								<a href="/mvcboard/download.do?ofile=${ row.ofile }&sfile=${ row.sfile }
								&idx=${ row.idx }">[Down]</a>
							</c:if>
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</c:otherwise>		
		</c:choose>
		</table>
		<!-- 페이징 처리 -->
		<nav aria-label="Page navigation">
		    <ul class="pagination justify-content-center">
		    	${ map.pagingImg }
		    </ul>
		</nav>
				
		<!-- 목록 하단의 글쓰기 버튼 -->
		<div class="text-end">
			<button type="button" class="btn btn-primary" onclick="location.href='/mvcboard/write.do';">글쓰기</button>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>