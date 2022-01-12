

package com.javaex.ex08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorDao {

	// 필드 -> 한번에 바꿀 수 있게 공통인것 묶기
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; //->select문에만 필요
	
	private String driver = "oracle.jdbc.driver.OracleDriver" ;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe" ;
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자
	public AuthorDao() { // 생략가능
	}

	// 메소드 gs

	// 메소드 일반
	private void getConnection() { //내부에서만 사용하는 메소드라 (main class에서 사용 불가)private
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) { //->1번오류 잡음
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) { //2번 오류 잡음
			System.out.println("error:" + e);}		
	}
	
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) { 
				rs.close(); 
			}
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


	// authorInsert
	public void authorInsert(AuthorVo authorVo) {
		
		getConnection();
			
		try {		
	
			// 3. SQL문 준비 / 바인딩 / 실행
	
			// 문자열 만들기
			String query = "";
			query += " insert into author ";
			query += " values(seq_author_id.nextval, ?, ? ) ";
			System.out.println(query);
	
			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
	
			// 바인딩
			pstmt.setString(1, authorVo.getAuthorName()); // 첫번째 물음표의 데이터
			pstmt.setString(2, authorVo.getAuthorDesc()); // 두번째 물음표의 데이터
	
			// 실행
			int count = pstmt.executeUpdate(); // 쿼리문 실행
	
			// 4.결과처리
			System.out.println(count + " 건이 저장되었습니다.(작가)");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();

	}


	// authorDelete
	public void authorDelete(int index) {

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 만들기
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";
			System.out.println(query);

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setInt(1, index);

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 삭제되었습니다.(작가)");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();

	}


	// authorUpdate
	public void authorUpdate(AuthorVo authorVo) {

		getConnection();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 만들기
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += "     author_desc = ? ";
			query += " where author_id = ? ";
			System.out.println(query);

			// 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 수정되었습니다.(작가)");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();

	}


	// authorSelect
	public List<AuthorVo> authorSelect() { //따라가면 List<AuthorVo>있음. 이게 return 형태임.
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		getConnection();		

		try {			
			// 3. SQL문 준비 / 바인딩 / 실행
			  //문자열 만들기
			String query ="";
			query += " select  author_id, ";
			query += "         author_name, ";
			query += "         author_desc ";
			query += " from author ";
			System.out.println(query);
			
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩 -->생략  ?표 없음
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int authorId= rs.getInt("author_id");    
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				AuthorVo vo= new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

		return authorList; // 주소를 보내주는것
	}

}