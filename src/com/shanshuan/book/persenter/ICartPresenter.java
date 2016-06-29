package com.shanshuan.book.persenter;

import com.shanshuan.book.entity.Book;

public interface ICartPresenter {
	public void addBookToCart(Book b);
	public void removeItem(int position);
	public int getSumPrice();
	public void addaddBookCount(int position);
	public void removeBookCount(int position);
}
