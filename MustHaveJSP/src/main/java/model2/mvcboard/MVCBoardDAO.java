package model2.mvcboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBConnPool;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MVCBoardDAO extends DBConnPool {
	
	// 1-1.게시물 개수 세기
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;  // 결과(게시물 수)를 담을 변수
		
		// 모든 게시물 수를 얻어오는 쿼리
		String sql = "select count(*) from mvcboard";
		
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
	
	// 1-2.게시물 목록 가져오기(페이징 기능 추가)
	public List<MVCBoardVO> selectListPage(Map<String, Object> map){
		List<MVCBoardVO> list = new ArrayList<MVCBoardVO>();  // 결과(게시물 목록)를 담을 변수
		
		// 모든 게시물 조회
		String sql = "select * from ("
				+ "		select Tb.*, rownum rNum from ("
				+ "			select * from mvcboard";				
		
		// 검색 조회 : select * from board where title like '%지금은%';
		if(map.get("searchWord") != null) {
			sql += " where " + map.get("searchField") + " "
					+ " like '%" + map.get("searchWord") + "%'";
		}
		sql += "		order by idx desc"
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
				MVCBoardVO vo = new MVCBoardVO();
				
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setPostdate(rs.getDate("postdate"));
				vo.setOfile(rs.getString("ofile"));
				vo.setSfile(rs.getString("sfile"));
				vo.setDowncount(rs.getInt("downcount"));
				vo.setPass(rs.getString("pass"));
				vo.setVisitcount(rs.getInt("visitcount"));
				
				list.add(vo);
			}
		}catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 2.작성한 글 받아 DB에 추가
	public int insertWrite(MVCBoardVO vo) {
		int result = 0;
		
		try {
			// insert 쿼리
			String sql = "insert into mvcboard(idx, name, title, content, ofile, sfile, pass) "+
						"values(seq_mvcboard_num.nextval, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);  // 동적 쿼리
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getOfile());
			pstmt.setString(5, vo.getSfile());
			pstmt.setString(6, vo.getPass());
			
			result = pstmt.executeUpdate();  // 쿼리 성공하면 추가한 행 개수 반환
		}catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 3-1.선택한 게시물 찾아 내용 반환
	public MVCBoardVO selectView(String idx) {
		MVCBoardVO vo = new MVCBoardVO();
		
		// 쿼리문
		String sql = "select * from mvcboard where idx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);  // 인파라미터를 일련번호로 설정
			rs = pstmt.executeQuery();
			
			// 결과 처리
			if(rs.next()) {
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setPostdate(rs.getDate("postdate"));
				vo.setOfile(rs.getString("ofile"));
				vo.setSfile(rs.getString("sfile"));
				vo.setDowncount(rs.getInt("downcount"));
				vo.setPass(rs.getString("pass"));
				vo.setVisitcount(rs.getInt("visitcount"));
			}
		}catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return vo;
	}
	
	// 3-2.선택한 게시물의 조회수 1증가
	public void updateVisitCount(String idx) {
		// 쿼리문
		String sql = "update mvcboard set visitcount=visitcount+1 where idx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	// 4. 다운로드 횟수를 1 증가
	public void updateDownCount(String idx) {
		String sql = "update mvcboard set downcount=downcount+1 where idx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 5-1.입력한 비밀번호가 지정한 게시물의 비밀번호가 일치하는지 확인
	public boolean confirmPassword(String pass, String idx) {
		boolean isCorr = false;
		
		try {
			String sql = "select pass from mvcboard where idx=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(pass.equals(rs.getString("pass"))) {					
					isCorr = true;
				}else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			}
		}catch(Exception e) {
			System.out.println("비밀번호 체크 중 예외 발생");
			e.printStackTrace();
		}
		
		return isCorr;
	}
	
	// 5-2. 지정한 게시물 삭제
	public int deletePost(String idx) {
		int result = 0;
		
		try {
			// 쿼리문
			String sql = "delete from mvcboard where idx=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 6.선택한 게시물 수정(파일 업로드 지원)
	public int updatePost(MVCBoardVO vo) {
		int result = 0;
		
		try {
			// 쿼리문
			String sql = "update mvcboard set title=?, name=?, content=?, ofile=?, sfile=?"
					+ " where idx=? and pass=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getOfile());
			pstmt.setString(5, vo.getSfile());
			pstmt.setString(6, vo.getIdx());
			pstmt.setString(7, vo.getPass());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
}
