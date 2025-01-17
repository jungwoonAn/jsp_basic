package membership;

import java.util.ArrayList;
import java.util.List;

import common.JDBConnect;

public class MemberDAO extends JDBConnect {
	// DB 연결이 완료된 MemberDAO 객체 생성
	public MemberDAO(String driver, String url, String id, String pwd) {
		super(driver, url, id, pwd);
	}
	
	// 아이디/패스워드와 일치하는 회원 정보 반환
	public MemberVO getMember(String id, String pass) {
		// 회원정보 VO객체 생성
		MemberVO mVo = new MemberVO();
		
		String sql = "select * from member where id=? and pass=?";
		
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			
			// 결과 처리
			if(rs.next()) {
				// 쿼리 결과로 얻은 회원정보를 VO객체에 저장
				mVo.setId(rs.getString("id"));
				mVo.setPass(rs.getString("pass"));
				mVo.setName(rs.getString("name"));
				mVo.setRegidate(rs.getString("regidate"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return mVo;
	}
	
	// 회원 목록 전체 조회
	public List<MemberVO> getAllMember() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		String sql = "select * from member";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mVo = new MemberVO();
				
				mVo.setId(rs.getString("id"));
				mVo.setPass(rs.getString("pass"));
				mVo.setName(rs.getString("name"));
				mVo.setRegidate(rs.getString("regidate"));
				
				list.add(mVo);
			}				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
