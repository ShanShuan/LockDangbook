package com.shanshuan.book.modle;

import com.shanshuan.book.entity.Book;

public interface ICartModle {
	public void addBook(Book book);
	public void removeItem(int pisition);
	public int getSumPrice();
	public void addaddBookCount(int position);
	public void removeBookCount(int position);
}
