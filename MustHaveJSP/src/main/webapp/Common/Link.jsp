<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table border="1" style="width:100%;">
	<tr>
		<td style="text-align:center;">
		<!-- 로그인 여부에 따른 메뉴 변화 -->
		<% if(session.getAttribute("UserId")==null) {
		%>
			<a href="../06_Session/LoginForm.jsp">로그인</a>
		<%
		}else {
		%>
			<a href="../06_Session/Logout.jsp">로그아웃</a>
		<%	
		}
		%>
			<!-- 게시판 링크 -->
			<!--
			&nbsp;&nbsp;&nbsp;
			<a href="../06_Session/List.jsp">멤버</a>
			-->
			 
			&nbsp;&nbsp;&nbsp;
			<a href="../08_Board/List.jsp">게시판(페이징X)</a>		
			&nbsp;&nbsp;&nbsp;
			<a href="../09_PagingBoard/List.jsp">게시판(페이징O)</a>
			
		</td>
	</tr>

</table>