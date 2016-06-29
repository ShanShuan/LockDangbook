package com.shanshuan.book.modle.imp;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.graphics.Point;

import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.Book;
import com.shanshuan.book.entity.Cart;
import com.shanshuan.book.modle.ICartModle;

public class CartModleImp implements ICartModle{
	private Cart cart;
	public CartModleImp(){
		cart=MyApplication.getContext().getCart();
	}
	@Override
	public void addBook(Book book) {
		
			cart.addBook(book);
		
	}
	@Override
	public void removeItem(int position) {
		
			cart.removeItem(position);
		
	}
	@Override
	public int getSumPrice() {
		return cart.getSumPrice();
		
	}
	@Override
	public void addaddBookCount(int position) {
	
			cart.addBookCount(position);
		
		
	}
	@Override
	public void removeBookCount(int position) {
		
			cart.removeBookCount(position);
		
		
	}

}
