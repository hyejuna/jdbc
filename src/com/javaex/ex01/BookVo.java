package com.javaex.ex01;

public class BookVo {
	//필드
	private int bookID;
	private String title;
	private String pubs;
	private String pubDate;
	private int authorId;	
	
	//생성자
	public BookVo() {
		super();
	}

	public BookVo(int bookID, String title, String pubs, String pubDate, int authorId) {
		super();
		this.bookID = bookID;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorId = authorId;
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

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	//메소드
	@Override
	public String toString() {
		return "BookVo [bookID=" + bookID + ", title=" + title + ", pubs=" + pubs + ", pubDate=" + pubDate
				+ ", authorId=" + authorId + "]";
	}
}
