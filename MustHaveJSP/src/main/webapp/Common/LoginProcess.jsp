<%@page import="membership.MemberDAO"%>
<%@page import="membership.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 로그인 폼으로부터 받은 파라미터(아이디/패스워드)
String userId = request.getParameter("user_id");
String userPass = request.getParameter("user_pass");

// web.xml에서 가져온 데이터베이스 연결 정보
String oracleDriver = application.getInitParameter("OracleDriver");
String oracleURL = application.getInitParameter("OracleURL");
String oracleId = application.getInitParameter("OracleId");
String oraclePwd = application.getInitParameter("OraclePwd");

// 회원 테이블 DAO를 통해 회원 정보 VO 획득
MemberDAO mDao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
MemberVO mVo = mDao.getMember(userId, userPass);
mDao.close();

// 로그인 성공 여부에 따른 처리
if(mVo.getId() != null){
	//로그인 성공
	session.setAttribute("UserId", mVo.getId());
	session.setAttribute("UserName", mVo.getName());
	response.sendRedirect("LoginForm.jsp");
}else {
	//로그인 실패
	request.setAttribute("LoginErrMsg", "로그인 오류입니다.");
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("LoginForm.jsp");
	dispatcher.forward(request, response);
}
%>    