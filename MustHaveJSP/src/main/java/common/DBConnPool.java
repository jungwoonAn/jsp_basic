package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection conn;
	public Statement stmt;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	// 기본 생성자
	public DBConnPool() {
		try {
			// 커넥션 풀(DataSource)얻기
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();  // 전역적으로 설정해야함!!

			System.out.println("DB 커넥션 풀 연결 성공");
		}catch(Exception e) {
			System.out.println("DB 커넥션 풀 연결 실패");
			e.printStackTrace();
		}
	}
	
	// 연결 해제(자원 반납)
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();  // 자동으로 커넥션 풀로 반답됨
			
			System.out.println("DB 커넥션 풀 자원 반납");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
