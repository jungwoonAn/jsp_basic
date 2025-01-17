<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="IsLoggedIn.jsp" %>
<%
// 수정 내용 얻기(post방식으로 전송된 param)
String num = request.getParameter("num");
String title = request.getParameter("title");
String content = request.getParameter("content");

// VO에 저장
BoardVO bVo = new BoardVO();
bVo.setNum(num);
bVo.setTitle(title);
bVo.setContent(content);

// DB에 반영
BoardDAO bDao = new BoardDAO(application);
int editResult = bDao.updateEdit(bVo);
bDao.close();

// 성공/실패 처리
if(editResult == 1){
	// 성공시 상세보기 페이지로 이동
	response.sendRedirect("View.jsp?num=" + bVo.getNum());
}else {
	// 실패시 이전 페이지로 이동
	JSFunction.alertBack("수정하기에 실패하였습니다.", out);
}
%>  