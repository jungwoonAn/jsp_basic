<%@page import="utils.JSFunction"%>
<%@page import="utils.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 폼값으로 전달된 파라미터 읽기
String user_id = request.getParameter("user_id");
String user_pwd = request.getParameter("user_pwd");
String save_check = request.getParameter("save_check");

if("test01".equals(user_id) && "1234".equals(user_pwd)){
	//로그인 성공
	if(save_check != null && save_check.equals("Y")){
		// 쿠키 생성
		CookieManager.makeCookie(response, "loginId", user_id, 86400);
	}else{
		// 쿠키 삭제
		CookieManager.deleteCookie(response, "loginId");
	}
	
	JSFunction.alertLocation("로그인에 성공했습니다.", "IdSaveMain.jsp", out);
}else {
	// 쿠키 삭제
	CookieManager.deleteCookie(response, "loginId");
	// 로그인 실패
	JSFunction.alertBack("로그인에 실패했습니다.", out);
}
%>