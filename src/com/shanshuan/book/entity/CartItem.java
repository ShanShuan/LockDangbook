package com.shanshuan.book.entity;

import java.io.Serializable;

public class CartItem implements Serializable{
	private Book book;
	private int bookCount;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	public  void addThisBookCount(){
		bookCount++;
	}
	public void removeThisBookCount() {
		if(bookCount==0){
			return;
		}
		bookCount--;
		
	}
}
