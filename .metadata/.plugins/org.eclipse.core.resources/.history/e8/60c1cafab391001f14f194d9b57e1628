<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="../BS_Board/List.jsp">회원제 게시판</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <!-- 로그인 여부에 따른 메뉴 변화 -->
		<% if(session.getAttribute("UserId")==null) {
		%>
			<li class="nav-item">
	          <a class="nav-link" href="../BS_Board/LoginForm.jsp">로그인</a>
	        </li>
		<%
		}else {
		%>
			<li class="nav-item">
	          <a class="nav-link" href="../BS_Board/Logout.jsp">로그아웃</a>
	        </li>
		<%	
		}
		%>
        <li class="nav-item">
          <a class="nav-link" href="../BS_Board/List.jsp">게시판(페이징X)</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="../09_PagingBoard/List.jsp">게시판(페이징O)</a>
        </li>
      </ul>
    </div>
  </div>
</nav>