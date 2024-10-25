package fileupload;

import java.util.ArrayList;
import java.util.List;

import common.DBConnPool;

public class MyFileDAO extends DBConnPool {
	// 새로운 게시물 입력
	public int insertFile(MyFileVO fVo) {
		int applyResult = 0;
		
		try {
			String sql = "insert into myfile(idx, title, cate, ofile, sfile)"
					+ "values(seq_myfile_num.nextval,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fVo.getTitle());
			pstmt.setString(2, fVo.getCate());
			pstmt.setString(3, fVo.getOfile());
			pstmt.setString(4, fVo.getSfile());
			
			applyResult = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("insert 중 예외 발생");
			e.printStackTrace();
		}
		
		return applyResult;
	}
	
	// 파일 목록 반환
	public List<MyFileVO> myFileList(){
		List<MyFileVO> fileList = new ArrayList<MyFileVO>();
		
		// 쿼리
		String sql = "select * from myfile order by idx desc";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				// VO에 저장
				MyFileVO fVo = new MyFileVO();
				fVo.setIdx(rs.getString("idx"));
				fVo.setTitle(rs.getString("title"));
				fVo.setCate(rs.getString("cate"));
				fVo.setOfile(rs.getString("ofile"));
				fVo.setSfile(rs.getString("sfile"));
				fVo.setPostdate(rs.getString("postdate"));
				
				fileList.add(fVo);
			}
		}catch(Exception e) {
			System.out.println("select 시 예외 발생");
			e.printStackTrace();
		}		
		
		return fileList;
	}
}
