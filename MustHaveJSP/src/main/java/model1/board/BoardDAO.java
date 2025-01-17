package model1.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class BoardDAO extends JDBConnect {

	// 0.DB 연결
	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	// 1-1.게시물 개수 세기
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;  // 결과(게시물 수)를 담을 변수
		
		// 모든 게시물 수를 얻어오는 쿼리
		String sql = "select count(*) from board";
		
		// 검색 조회 : select count(*) from board where title like '%지금은%';
		if(map.get("searchWord") != null) {
			sql += " where " + map.get("searchField") + " "
					+ " like '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = conn.createStatement();  //쿼리문 생성
			rs = stmt.executeQuery(sql);  // 쿼리 실행
			rs.next();  // 커서를 첫번째 생으로 이동
			totalCount = rs.getInt(1);  // 첫번째 컬럼 값을 가져옴
		}catch(Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	// 1-2.게시물 목록 가져오기
	public List<BoardVO> selectList(Map<String, Object> map){
		List<BoardVO> list = new ArrayList<BoardVO>();  // 결과(게시물 목록)를 담을 변수
		
		// 모든 게시물 조회
		String sql = "select * from board";
		
		// 검색 조회 : select * from board where title like '%지금은%';
		if(map.get("searchWord") != null) {
			sql += " where " + map.get("searchField") + " "
					+ " like '%" + map.get("searchWord") + "%'";
		}
		sql += " order by num desc";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				// 한 행(게시물 하나)의 내용을 VO에 저장
				BoardVO bVo = new BoardVO();
				
				bVo.setNum(rs.getString("num"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setPostdate(rs.getDate("postdate"));
				bVo.setId(rs.getString("id"));
				bVo.setVisitcount(rs.getString("visitcount"));
				
				list.add(bVo);
			}
		}catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 1-3.게시물 목록 가져오기(페이징 기능 추가)
	public List<BoardVO> selectListPage(Map<String, Object> map){
		List<BoardVO> list = new ArrayList<BoardVO>();  // 결과(게시물 목록)를 담을 변수
		
		// 모든 게시물 조회
		String sql = "select * from ("
				+ "		select Tb.*, rownum rNum from ("
				+ "			select * from board";				
		
		// 검색 조회 : select * from board where title like '%지금은%';
		if(map.get("searchWord") != null) {
			sql += " where " + map.get("searchField") + " "
					+ " like '%" + map.get("searchWord") + "%'";
		}
		sql += "		order by num desc"
			+ "    ) Tb"
			+ ") where rNum between ? and ?";
		
		try {
			// 동적 쿼리문
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, map.get("start").toString());
			pstmt.setString(2, map.get("end").toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 한 행(게시물 하나)의 내용을 VO에 저장
				BoardVO bVo = new BoardVO();
				
				bVo.setNum(rs.getString("num"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setPostdate(rs.getDate("postdate"));
				bVo.setId(rs.getString("id"));
				bVo.setVisitcount(rs.getString("visitcount"));
				
				list.add(bVo);
			}
		}catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 2.작성한 글 받아 DB에 추가
	public int insertWrite(BoardVO bVo) {
		int result = 0;
		
		try {
			// insert 쿼리
			String sql = "insert into board(num, title, content, id, visitcount) "+
						"values(seq_board_num.nextval, ?, ?, ?, 0)";
			
			pstmt = conn.prepareStatement(sql);  // 동적 쿼리
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getContent());
			pstmt.setString(3, bVo.getId());
			
			result = pstmt.executeUpdate();  // 쿼리 성공하면 추가한 행 개수 반환
		}catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 3-1.선택한 게시물 찾아 내용 반환
	public BoardVO selectView(String num) {
		BoardVO bVo = new BoardVO();
		
		// 쿼리문
		String sql = "select B.*, M.name from member M, board B where M.id=B.id and num=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);  // 인파라미터를 일련번호로 설정
			rs = pstmt.executeQuery();
			
			// 결과 처리
			if(rs.next()) {
				bVo.setNum(rs.getString("num"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setPostdate(rs.getDate("postdate"));
				bVo.setId(rs.getString("id"));
				bVo.setVisitcount(rs.getString("visitcount"));
				bVo.setName(rs.getString("name"));
			}
		}catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return bVo;
	}
	
	// 3-2.선택한 게시물의 조회수 1증가
	public void updateVisitCount(String num) {
		// 쿼리문
		String sql = "update board set visitcount=visitcount+1 where num=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	// 4.선택한 게시물 수정
	public int updateEdit(BoardVO bVo) {
		int result = 0;
		
		try {
			// 쿼리문
			String sql = "update board set title=?, content=? where num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getContent());
			pstmt.setString(3, bVo.getNum());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 5.선택한 게시물 삭제
	public int deletePost(BoardVO bVo) {
		int result = 0;
		
		try {
			// 쿼리문
			String sql = "delete from board where num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getNum());
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
}
