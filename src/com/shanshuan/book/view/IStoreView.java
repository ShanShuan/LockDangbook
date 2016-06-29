package com.shanshuan.book.view;

import java.util.List;

import com.shanshuan.book.entity.Book;

public interface IStoreView {
	public void showRecommendBook(List<Book> books);
	public void showHotBook(List<Book> books);
	public void showNewBook(List<Book> books);
}
