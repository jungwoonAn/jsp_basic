package utils;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

public class JSFunction {
	// 메시지 알림창을 띄운 후 명시한 URL로 이동
	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = ""
					+ "<script>"
					+ "    alert('"+msg+"');"
					+ "    location.href='"+url+"';"
					+ "</script>";
			out.println(script);  // 자바스크립트 코드를 out 내장 객체로 출력(삽입)
		}catch(Exception e) {}
	}
	
	// 메시지 알림창을 띄운 후 이전 페이지로 이동
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = ""
					+ "<script>"
					+ "    alert('"+msg+"');"
					+ "    history.back();"
					+ "</script>";
			out.println(script);
		}catch(Exception e) {}
	}
	
	// 위 메서드 오버로드 : 메시지 알림창을 띄운 후 명시한 URL로 이동
	public static void alertLocation(HttpServletResponse response, String msg, String url) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			String script = ""
					+ "<script>"
					+ "    alert('"+msg+"');"
					+ "    location.href='"+url+"';"
					+ "</script>";
			out.println(script);  // 자바스크립트 코드를 out 내장 객체로 출력(삽입)
		}catch(Exception e) {}
		
	}
	
	// 위 메서드 오버로드: 메시지 알림창을 띄운 후 이전 페이지로 이동
	public static void alertBack(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			String script = ""
					+ "<script>"
					+ "    alert('"+msg+"');"
					+ "    history.back();"
					+ "</script>";
			out.println(script);
		}catch(Exception e) {}
	}
}
