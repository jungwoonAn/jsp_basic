package model2.mvcboard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/mvcboard/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 게시물 불러오기
		MVCBoardDAO dao = new MVCBoardDAO();
		String idx = request.getParameter("idx");
		dao.updateVisitCount(idx);  // 조회수 1 증가
		MVCBoardVO vo = dao.selectView(idx);
		dao.close();
		
		// 첨부 파일 확장자 추출 및 이미지 타입 확인
		String ext = null, fileName = vo.getSfile();
		
		if(fileName != null) {
			ext = (fileName.substring(fileName.lastIndexOf(".") + 1)).toLowerCase();
		}
		String[] mimeStr = {"png","jpg","gif"};  // 이미지 확장자들을 배열에 저장
		List<String> mimeList = Arrays.asList(mimeStr);  // 배열을 리스트타입으로 변환
		
		boolean isImage = false;
		if(mimeList.contains(ext)) {  // 리스트에 포함된 확장자이면
			isImage = true;
		}
		
		// 게시물 저장후 뷰(JSP)로 포워드
		request.setAttribute("vo", vo);
		request.setAttribute("isImage", isImage);
		request.getRequestDispatcher("/14_MVCBoard/View.jsp").forward(request, response);
	}
}
