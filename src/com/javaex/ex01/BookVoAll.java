package com.javaex.ex01;

public class BookVoAll {
	//필드
	private int bookID;
	private String title;
	private String pubs;
	private String pubDate;
	private String authorName;	
	
	//생성자
	public BookVoAll() {
		super();
	}

	public BookVoAll(int bookID, String title, String pubs, String pubDate, String authorName) {
		super();
		this.bookID = bookID;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorName = authorName;
	}

	//메소드 g.s
	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	//메소드
	@Override
	public String toString() {
		return "BookVoAll [bookID=" + bookID + ", title=" + title + ", pubs=" + pubs + ", pubDate=" + pubDate
				+ ", authorName=" + authorName + "]";
	}
		


	

}
