package com.javaex.ex08;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		List<AuthorVo> list;
		AuthorDao authorDao = new AuthorDao();
		
		//Data 하나하나 넣는것보다는 박스로 묶어서 넣는게 좋음. vo로 미리 박스 만들어서 주소만 입력해줌
		AuthorVo vo01 = new AuthorVo("이문열", "경북영양");
		authorDao.authorInsert(vo01);
		
		AuthorVo vo02 = new AuthorVo("박경리", "경남 통영");
		authorDao.authorInsert(vo02);
		
		AuthorVo vo03 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(vo03);
		
		System.out.println("------------------------------");
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("------------------------------");

		//작가 수정
		AuthorVo voUpdate = new AuthorVo(2, "박경리(수정)", "경남 통영(수정)" );
		authorDao.authorUpdate(voUpdate);
		
		System.out.println("------------------------------");
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("------------------------------");
		
		//작가 삭제 -> 변수 1개라 묶지 않음
		authorDao.authorDelete(1);
		
		System.out.println("------------------------------");
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("------------------------------");
	
	}

}
