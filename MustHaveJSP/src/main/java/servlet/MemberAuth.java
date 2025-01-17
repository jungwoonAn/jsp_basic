package servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import membership.MemberDAO;
import membership.MemberVO;

import java.io.IOException;

public class MemberAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	MemberDAO mDao;
	
	@Override
	public void init() throws ServletException {
		// application 내장 객체 얻기
		ServletContext application = this.getServletContext();
		
		// web.xml에서 DB연결 정보 얻기
		String dirver = application.getInitParameter("OracleDriver");
		String connectUrl = application.getInitParameter("OracleURL");
		String oId = application.getInitParameter("OracleId");
		String oPass = application.getInitParameter("OraclePwd");
		
		// DAO 생성
		mDao = new MemberDAO(dirver, connectUrl, oId, oPass);
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서블릿 초기화 매개변수에서 관리자 ID 받기
		String admin_id = this.getInitParameter("admin_id");
		
		// 인증을 요청한 ID/패스워드
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		// 회원 테이블에서 인증 요청한 ID/패스워드에 해당하는 회원 찾기
		MemberVO mVo = mDao.getMember(id, pass);
		
		// 찾은 회원의 이름에 따른 처리
		String memberName = mVo.getName();
		if(memberName != null) {  // 일치하는 회원 찾음
			request.setAttribute("authMessage", memberName+" 회원님 로그인");
		}else {  // 일치하는 회원 없음
			if(admin_id.equals(id))  // 관리자
				request.setAttribute("authMessage", admin_id+" 는 관리자입니다.");
			else  //비회원
				request.setAttribute("authMessage", "비회원 입니다.");
		}
		request.getRequestDispatcher("/12_Servlet/MemberAuth.jsp").forward(request, response);
	}
	
	@Override
	public void destroy() {
		mDao.close();
	}

}
