package himedia.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmaillistDaoOracleImpl implements EmaillistDao {
	private String dbuser;
	private String dbpass;
	
	// 생성자
	public EmaillistDaoOracleImpl(String dbuser, String dbpass) {
		this.dbuser = dbuser;
		this.dbpass = dbpass;
	}
	// 데이터베이스 접속 정보 -> Context Parameter로부터 받아옴
	// Connection 공통 메서드
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// SELECT 
	@Override
	public List<EmailVo> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<EmailVo> list = new ArrayList<>();
		
		try {
			// Connection
			conn = getConnection();
			// statement 생성
			stmt = conn.createStatement();
			// SQL Query 전송
			String sql = "SELECT * FROM emaillist ORDER BY created_at DESC";
			// ResultSet 받아오기
			rs = stmt.executeQuery(sql);
			// ResultSet -> 자바 객체로 전환하기
			while(rs.next()) {			// 뒤쪽 레코드 받아옴
				// JAVA 객체로 전환
					Long no = rs.getLong("no");
					String lastName = rs.getString("last_name");
					String firstName = rs.getString("first_name");
					String email = rs.getString("email");
					Date createdAt = rs.getDate("created_at");
					
					EmailVo vo = new EmailVo(no, lastName, firstName, email, createdAt);
					
					list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 자원해제
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	// INSERT
	@Override
	public boolean insert(EmailVo vo) { 
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			// Connection
			conn = getConnection();
			// SQL Query 전송
			String sql = "INSERT INTO emaillist (no, last_name, first_name, email) VALUES (seq_emaillist_pk.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			// Data 바인딩
			pstmt.setString(1, vo.getLastName());
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getEmail());
			
			// 확정된 Query 수행
			insertedCount = pstmt.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) pstmt.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
		return 1 == insertedCount;
	}

	@Override
	public boolean delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;

		try {
			conn = getConnection();
			String sql = "DELETE FROM emaillist WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			deletedCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1 == deletedCount;
	}
	

}
