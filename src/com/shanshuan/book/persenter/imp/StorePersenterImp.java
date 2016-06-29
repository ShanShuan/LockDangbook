package com.shanshuan.book.persenter.imp;

import java.util.List;

import android.content.Context;

import com.shanshuan.book.entity.Book;
import com.shanshuan.book.modle.IModle.AyskCallBack;
import com.shanshuan.book.modle.IStoreModle;
import com.shanshuan.book.modle.imp.StoreModleImp;
import com.shanshuan.book.persenter.IStorePersenter;
import com.shanshuan.book.view.IStoreView;

public class StorePersenterImp implements IStorePersenter{
	private IStoreView view;
	private IStoreModle modle;
	public StorePersenterImp(IStoreView view,Context context){
		this.view=view;
		modle=new StoreModleImp(context);
	}

	@Override
	public void getRecommendBook() {
		modle.loadRecommendBook(new AyskCallBack() {
			
			@Override
			public void onSeccesse(List<Book> books) {
				view.showRecommendBook(books);
			}
			
			@Override
			public void onFialed() {
				
			}
		});
		
	}

	@Override
	public void getHotBook() {
		modle.loadHotBook(new AyskCallBack() {
			
			@Override
			public void onSeccesse(List<Book> books) {
				view.showHotBook(books);
			}
			
			@Override
			public void onFialed() {
				
			}
		});
		
	}

	@Override
	public void getNewBook() {
		modle.loadNewBook(new AyskCallBack() {
			
			@Override
			public void onSeccesse(List<Book> books) {
				view.showNewBook(books);
			}
			
			@Override
			public void onFialed() {
				
			}
		});
		
	}

}
