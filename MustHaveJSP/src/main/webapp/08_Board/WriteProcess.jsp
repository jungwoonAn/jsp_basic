<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="IsLoggedIn.jsp" %>    
<%
// 폼값 받기
String title = request.getParameter("title");
String content = request.getParameter("content");

// 폼값을 VO 객체에 저장
BoardVO bVo = new BoardVO();
bVo.setTitle(title);
bVo.setContent(content);
bVo.setId(session.getAttribute("UserId").toString());

// DAO 객체를 통해 DB에 VO 저장
BoardDAO bDao = new BoardDAO(application);
int iResult = bDao.insertWrite(bVo);
bDao.close();

// 성공 or 실패?
if(iResult == 1){
	response.sendRedirect("List.jsp");
}else {
	JSFunction.alertBack("글쓰기에 실패하였습니다.", out);
}
%>    