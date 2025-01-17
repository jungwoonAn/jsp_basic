package model2.mvcboard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JSFunction;

import java.io.IOException;

import fileupload.FileUtil;

public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 페이지 포워드
		RequestDispatcher dispatcher = request.getRequestDispatcher("/14_MVCBoard/Write.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1. 파일 업로드 처리
		// 업로드 디렉터리의 물리적 경로 확인
		// C:\Works\JSP_Servlet\jsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MustHaveJSP\Uploads
		String saveDirectory = getServletContext().getRealPath("/Uploads");
		
		// 파일 업로드
		String originalFileName = "";
		try {
			originalFileName = FileUtil.uploadFile(request, saveDirectory);
		}catch(Exception e) {
			// 파일 업로드 실패
			JSFunction.alertLocation(response, "파일 업로드 오류입니다.", "/mvcboard/write.do");
			e.printStackTrace();
		}
		
		// 2. 파일 업로드 외 처리
		// 폼값을 VO에 저장
		MVCBoardVO vo = new MVCBoardVO();
		vo.setName(request.getParameter("name"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setPass(request.getParameter("pass"));
		
		// 원본 파일명과 저장된 파일 이름 설정
		if(originalFileName != "") {
			// 첨부파일이 있을 경우 파일명 변경
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			
			vo.setOfile(originalFileName);
			vo.setSfile(savedFileName);
		}
		
		// DAO를 통해 DB에 게시 내용 저장
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.insertWrite(vo);
		dao.close();
		
		// 성공 or 실패?
		if(result == 1) {  // 글쓰기 성공
			response.sendRedirect("/mvcboard/list.do");
		}else {  // 실패
			JSFunction.alertLocation(response, "글쓰기에 실패했습니다.", "/mvcboard/write.do");
		}		
	}
}
