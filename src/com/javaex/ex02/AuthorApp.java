package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

		List<AuthorVo> list ; // select 출력 여러번 하려고 변수지정 위로 빼줌.
		AuthorDao authorDao = new AuthorDao();

		// 작가등록
		authorDao.authorInsert("이문열", "경북 영양");

		// 작가등록
		authorDao.authorInsert("박경리", "경상남도 통영");

		// 작가등록
		authorDao.authorInsert("유시민", "17대 국회의원");

		// 출력
		list = authorDao.authorSelect();
		for (int i = 0; i < list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("--------------------------------------------");

		
		
		// 작가수정
		authorDao.authorUpdate(2, "박경리(수정)", "경상남동 통영(수정)");

		// 출력
		//List<AuthorVo> list = authorDao.authorSelect(); -> authorDao에서 static에 있던 List 주소가 해당 class 끝나면서 지워짐. 지워지기 전에 주소 받는것.
		list = authorDao.authorSelect();
		for (int i = 0; i < list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("--------------------------------------------");

		
		
		// 작가삭제 
		authorDao.authorDelete(1);
		
		//출력
		list = authorDao.authorSelect();
		for (int i = 0; i < list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("--------------------------------------------");
		

	}

}
