package model2.mvcboard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

import java.io.IOException;

import fileupload.FileUtil;

@WebServlet("/mvcboard/edit.do")
@MultipartConfig(
	maxFileSize = 1024*1024*1,
	maxRequestSize = 1024*1024*10
)
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String idx = request.getParameter("idx");
		MVCBoardDAO dao = new MVCBoardDAO();
		MVCBoardVO vo = dao.selectView(idx);
	
		request.setAttribute("vo", vo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/14_MVCBoard/Edit.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1. 파일 업로드 처리
		// 업로드 디렉터리의 물리적 경로 확인
		String saveDirectory = getServletContext().getRealPath("/Uploads");
		
		// 파일 업로드
		String originalFileName = "";
		try {
			originalFileName = FileUtil.uploadFile(request, saveDirectory);
		}catch(Exception e) {
			// 파일 업로드 실패
			JSFunction.alertBack(response, "파일 업로드 오류입니다.");
			e.printStackTrace();
		}
		
		// 2. 파일 업로드 외 처리
		// 수정 내용을 매개변수에서 얻어옴
		String idx = request.getParameter("idx");
		String prevOfile = request.getParameter("prevOfile");
		String prevSfile = request.getParameter("prevSfile");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");	
		
		// session에서 비밀번호 가져옴
		HttpSession session = request.getSession();
		String pass = (String)session.getAttribute("pass");
		
		// 폼값을 VO에 저장
		MVCBoardVO vo = new MVCBoardVO();
		vo.setIdx(idx);
		vo.setName(name);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPass(pass);
		
		// 원본 파일명과 저장된 파일 이름 설정
		if(originalFileName != "") {
			// 첨부파일이 있을 경우 파일명 변경
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			
			vo.setOfile(originalFileName);  // 원본 파일 이름
			vo.setSfile(savedFileName);  // 서버에 저장된 파일 이름
			
			// 기존 파일 삭제
			FileUtil.deleteFile(request, "/Uploads", prevSfile);
		}else {  // 첨부파일이 없으면 기존 이름 유지
			vo.setOfile(prevOfile);
			vo.setSfile(prevSfile);
		}
		
		// DAO를 통해 DB에 게시 내용 수정
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.updatePost(vo);
		dao.close();
		
		// 성공 or 실패?
		if(result == 1) {  // 수정 성공
			session.removeAttribute("pass");
			response.sendRedirect("/mvcboard/view.do?idx=" + idx);
		}else {  // 실패
			JSFunction.alertLocation(response, "비밀번호 검증을 다시 진행해주세요", "/mvcboard/view.do?idx=" + idx);
		}
	}
	
}