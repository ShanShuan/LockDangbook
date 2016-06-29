package com.shanshuan.book.persenter.imp;

import com.shanshuan.book.entity.Book;
import com.shanshuan.book.modle.ICartModle;
import com.shanshuan.book.modle.imp.CartModleImp;
import com.shanshuan.book.persenter.ICartPresenter;
import com.shanshuan.book.view.ICartView;

public class CartPresenterImp implements ICartPresenter{
	private ICartView view;
	private ICartModle modle;
	public CartPresenterImp(ICartView view){
		this.view=view;
		modle=new CartModleImp();
		
	}
	@Override
	public void addBookToCart(Book b) {
		modle.addBook(b);
		
	}
	@Override
	public void removeItem(int position) {
		modle.removeItem(position);
		
	}
	@Override
	public int getSumPrice() {
		return modle.getSumPrice();
		
	}
	@Override
	public void addaddBookCount(int position) {
		modle.addaddBookCount(position);
		
	}
	@Override
	public void removeBookCount(int position) {
		modle.removeBookCount(position);
		
	}
	
}
