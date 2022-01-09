package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	public void authorInsert(String authorName, String authorDesc) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			 // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

			String query ="";
			query += " insert into author ";
			query += " values(seq_author_id.nextval, ?, ? ) " ;

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorName);    
			pstmt.setString(2, authorDesc);  
			
			int count = pstmt.executeUpdate();  
			System.out.println(count + " 건이 저장되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {               
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	public void authorUpdate(int authorId, String authorName, String authorDesc) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			 // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");


			String query ="";
			query += " update author ";
		    query += " set author_name = ?, ";
		    query += " 	   author_desc = ? " ;
		    query += " where author_id = ? " ;

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorName);   //첫번째 물음표의 데이터
		    pstmt.setString(2, authorDesc); //두번째 물음표의 데이터
		    pstmt.setInt(3, authorId);    
			
			int count = pstmt.executeUpdate();  
			System.out.println(count + " 건이 수정되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {               
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
	

	public void authorDelete(int authorId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			 // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");


			String query ="";
			query += " delete from author ";
		    query += " where author_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);      
			
			int count = pstmt.executeUpdate();  
			System.out.println(count + " 건이 삭제되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {               
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	public List<AuthorVo> authorSelect() {
		List<AuthorVo> authorList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			 // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");


			String query ="";
			query += " select author_id id, "; // as 사용 가능
			query += " 	 	  author_name, ";
			query += " 		  author_desc ";
			query += " from author ";

			pstmt = conn.prepareStatement(query);   
			
			rs = pstmt.executeQuery();  
			
			while(rs.next()) {
				AuthorVo vo = new AuthorVo(rs.getInt(1), rs.getString(2), rs.getString(3));
				authorList.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {               
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		return authorList;
	}
	
}