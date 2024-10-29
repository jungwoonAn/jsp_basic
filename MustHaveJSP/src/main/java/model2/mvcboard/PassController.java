package model2.mvcboard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

import java.io.IOException;

import fileupload.FileUtil;

@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setAttribute("mode", request.getParameter("mode"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/14_MVCBoard/Pass.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 매개변수 저장
		String idx = request.getParameter("idx");
		String mode = request.getParameter("mode");
		String pass = request.getParameter("pass");
		
		// 비밀번호 확인
		MVCBoardDAO dao = new MVCBoardDAO();
		boolean confirmed = dao.confirmPassword(pass, idx);
		
		if(confirmed) {  // true: 비밀번호 일치
			if(mode.equals("edit")) {  // 수정 모드
				HttpSession session = request.getSession();
				session.setAttribute("pass", pass);  // session영역에 비밀번호 저장한 후
				response.sendRedirect("/mvcboard/edit.do?idx=" + idx);  // 수정 페이지로 이동
			}else if(mode.equals("delete")) {  // 삭제 모드
				MVCBoardVO vo = dao.selectView(idx);
				int result = dao.deletePost(idx);
				
				if(result == 1) {  // 게시물 삭제 성공시 첨부 파일도 삭제
					String sfileName = vo.getSfile();
					FileUtil.deleteFile(request, "/Uploads", sfileName);	
				}
				JSFunction.alertLocation(response, "삭제되었습니다.", "/mvcboard/list.do");				
			}
			dao.close();
		}else {  // 비밀번호 불일치
			JSFunction.alertBack(response, "비밀번호 검증에 실패했습니다.");
		}		
	}
}
