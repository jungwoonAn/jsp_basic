package servlet;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/12_Servlet/LifeCycle.do")
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
         
	@PostConstruct
	public void myPostConstruct() {
		System.out.println("myPostConstruct() 호출");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init() 호출");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 호출");
		// 전송 방식을 확인해 doGet() 또는 doPost() 호출
		super.service(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출");
		request.getRequestDispatcher("/12_Servlet/LifeCycle.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 호출");
		request.getRequestDispatcher("/12_Servlet/LifeCycle.jsp").forward(request, response);
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() 호출");
	}
	
	@PreDestroy
	public void myPreDestory() {
		System.out.println("myPreDestory() 호출");
	}	
}
