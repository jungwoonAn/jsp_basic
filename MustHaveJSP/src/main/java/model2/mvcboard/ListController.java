package model2.mvcboard;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// DAO 생성
		MVCBoardDAO dao = new MVCBoardDAO();
		
		// 뷰에 전달할 매개변수 저장용 맵 생성
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		if(searchWord != null){
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}

		int totalCount = dao.selectCount(map);  // 게시물 수 확인
		
		/* 페이징 처리 start */
		// 전체 페이지 수 계산(web.xml파라미터 값 받아와서 계산)
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		int totalPage = (int)Math.ceil((double)totalCount / pageSize);  // 전체 페이지수

		// 현재 페이지 확인
		int pageNum = 1;  // 기본값
		String pageTemp = request.getParameter("pageNum");
		if(pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);

		// 화면에 표시할 게시물 범위 계산
		int start = (pageNum -1) * pageSize + 1;  // 첫 게시물 번호
		int end = pageNum * pageSize;  // 마지막 게시물 번호
		// DAO 전달인자 값으로 파라미터에 실어 보냄
		map.put("start", start);
		map.put("end", end);
		/* 페이징 처리 end */

		// 페이징된 게시물 목록 받기
		List<MVCBoardVO> boardList = dao.selectListPage(map);
		dao.close();
		
		// 뷰에 전달할 매개변수 추가
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "/mvcboard/list.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		// 전달할 데이터를 request영역에 저장 후 List.jsp로 포워드
		request.setAttribute("boardList", boardList);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/14_MVCBoard/List.jsp").forward(request, response);
	}
}
