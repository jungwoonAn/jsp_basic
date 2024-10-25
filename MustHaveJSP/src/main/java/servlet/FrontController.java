package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.one")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		int lastSlash = uri.lastIndexOf("/");
		String commandStr = uri.substring(lastSlash);
		System.out.println(commandStr);
		
		if(commandStr.equals("/regist.one"))
			request.setAttribute("resultValue", "<h4>회원가입</h4>");
		else if(commandStr.equals("/login.one"))
			request.setAttribute("resultValue", "<h4>로그인</h4>");
		else if(commandStr.equals("/freeboard.one"))
			request.setAttribute("resultValue", "<h4>자유게시판</h4>");
		
		request.setAttribute("uri", uri);
		request.setAttribute("commandStr", commandStr);
		request.getRequestDispatcher("/12_Servlet/FrontController.jsp")
			.forward(request, response);
	}
}
