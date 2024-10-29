package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import membership.MemberDAO;
import membership.MemberVO;
import utils.JSFunction;

@WebFilter(filterName="LoginFilter", urlPatterns="/15_FilterListener/LoginFilter.jsp" )
public class LoginFilter implements Filter {

	// 회원 정보를 얻어오기 위해 필요한 데이터베이스 접속 정보
	String oracleDriver, oracleURL, oracleId, oraclePwd;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext application = filterConfig.getServletContext();
		// 데이터 베이스 접속 정보 받아옴
		oracleDriver = application.getInitParameter("OracleDriver");
		oracleURL = application.getInitParameter("OracleURL");
		oracleId = application.getInitParameter("OracleId");
		oraclePwd = application.getInitParameter("OraclePwd");
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		String method = req.getMethod();
		
		// 로그인 처리
		if(method.equals("POST")) {
			//로그인 정보와 일치하는 회원 확인
			String user_id = request.getParameter("user_id");
			String user_pass = request.getParameter("user_pass");
			
			MemberDAO mDao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
			MemberVO mVo = mDao.getMember(user_id, user_pass);
			mDao.close();

			if(mVo.getId() != null) {  // 일치하는 회원 존재
				// 세션에 로그인 정보 저장
				session.setAttribute("UserId", mVo.getId());
				session.setAttribute("UserName", mVo.getName());
				
				// 다음 페이지로 이동
				String backUrl = request.getParameter("backUrl");
				System.out.println(backUrl);
				if(backUrl != null && !backUrl.equals("")) {
					JSFunction.alertLocation(resp, "로그인 전 요청한 페이지로 이동합니다.", backUrl);
					return;
				}else {
					resp.sendRedirect("../15_FilterListener/LoginFilter.jsp");
				}
			}else {  // 일치하는 회원 없음
				req.setAttribute("LoginErrMsg", "로그인에 실패했습니다.");
				req.getRequestDispatcher("../15_FilterListener/LoginFilter.jsp")
					.forward(req, resp);				
			}
		}else if(method.equals("GET")) {  // 로그아웃 처리
			String mode = request.getParameter("mode");
			if(mode != null && mode.equals("logout")) {
				session.invalidate();
			}
		}
		
		chain.doFilter(request, response);		
	}

}
