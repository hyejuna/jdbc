package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorApp {

	public static void main(String[] args) {
		// 3., 4. 공부!!!. 여기서 작성한 커리문은 auto commit 됨.
		
		//insert문
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩 (class 메모리에 올리기)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기 (oracle 원하는 계정으로 접속)
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

		    // 3. SQL문 준비 / 바인딩 / 실행  *****
		    
			//문자열 만들기 --> ?주의
			String query ="";   //쿼리문 만들기 --> ?주의
		    
		    //query = query + "문자열"
		    query += " insert into author "; 
		    query += " values(seq_author_id.nextval, ?, ? ) " ; //맨 앞 띄어쓰기 필수!! 없으면 authorvalues로 되어 오류
		    System.out.println(query);
		    
		    
		    //문자열 쿼리문으로 만들기
		    pstmt = conn.prepareStatement(query);
		    
		    //바인딩
		    pstmt.setString(1, "이문열");   //첫번째 ? 데이터(문자라서 set String)
		    pstmt.setString(2, "경북 영양");//두번째 ? 데이터
		    
		    //실행
		    int count = pstmt.executeUpdate(); //쿼리문 실행(1이면 성공, 0이면 실패)
			
		    // 4.결과처리
		    System.out.println(count + " 건이 저장되었습니다.");
		    
		    
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
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

}
