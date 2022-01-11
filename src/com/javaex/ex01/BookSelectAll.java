package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelectAll {

	public static void main(String[] args) {
		// 책데이터를 작가명까지 가져오기
		List<BookVoAll> bookAllList = new ArrayList<BookVoAll>();

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			
		    // 3. SQL문 준비 / 바인딩 / 실행
			//문자열 만들기
			String query ="";
			query += " select  book_id, " ;
			query += "         title,  " ;
			query += "         pubs,  " ;
			query += "         to_char(pub_date,'yyyy-mm-dd') pub_date,  " ;
			query += "         author_name  " ;
			query += " from book, author " ;
			query += " where book.author_id = author.author_id" ;
			System.out.println(query);
			
			//문자열 쿼리문으로 만들기
		    pstmt = conn.prepareStatement(query);
		    
		    //바인딩 생략 -> ? 없음
		    
		    //실행
		    rs = pstmt.executeQuery();
						
			// 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				String authorName = rs.getString("author_name");
				
				//System.out.println(bookId + "\t " + title + "\t " + pubs + "\t " + pubDate + "\t " + authorName);
				BookVoAll vo = new BookVoAll(bookId, title, pubs, pubDate, authorName);
				bookAllList.add(vo);
			}
			
			for(int i=0; i<bookAllList.size(); i++) {
				BookVoAll vo = bookAllList.get(i);
				System.out.println(vo.getBookID() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPubDate() + ", " + vo.getAuthorName());
			}

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
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

	}

}
