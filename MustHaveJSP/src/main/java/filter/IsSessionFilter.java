package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebFilter(urlPatterns= {"/09_PagingBoard/Write.jsp", "/09_PagingBoard/Edit.jsp",
		"/09_PagingBoard/DeleteProcess.jsp"})
public class IsSessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		if(session.getAttribute("UserId") == null) {  // 로그아웃 상태			
			String backUrl = req.getRequestURI();
			JSFunction.alertLocation(resp, "[Filter] 로그인 후 이용해주십시오.",
					"../15_FilterListener/LoginFilter.jsp?backUrl=" + backUrl);
			return;
		}else {  // 로그인 상태
			 chain.doFilter(request, response);  // 최종 리소스로 제어권을 넘겨 흐름을 유지
		}
	}

}
