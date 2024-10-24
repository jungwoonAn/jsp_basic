<%@page import="model1.board.BoardVO"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Common/IsLoggedIn.jsp" %>
<%
// 삭제할 글번호 얻기(post방식으로 전송된 param)
String num = request.getParameter("num");

// DB 연결
BoardDAO bDao = new BoardDAO(application);
BoardVO bVo = new BoardVO();
bVo = bDao.selectView(num);  // 글번호 해당 게시물 얻기

// 로그인한 ID 얻기
String sessionId = session.getAttribute("UserId").toString();

int delResult = 0;
if(sessionId.equals(bVo.getId())){
	// 작성자가 본인이면
	bVo.setNum(num);
	delResult = bDao.deletePost(bVo);
	bDao.close();
	
	// 성공/실패 처리
	if(delResult == 1){
		// 성공시 목록 페이지로 이동
		JSFunction.alertLocation("삭제되었습니다.", "List.jsp", out);
	}else {
		// 실패시 이전 페이지로 이동
		JSFunction.alertBack("삭제에 실패하였습니다.", out);
	}
}else {
	// 작성자 본인이 아니라면 이전페이지로 이동
	JSFunction.alertBack("본인만 삭제할 수 있습니다.", out);
	return;
}
%> 