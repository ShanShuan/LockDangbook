package com.shanshuan.book.modle;

import java.util.List;

import android.graphics.Bitmap;

import com.shanshuan.book.entity.Book;

public interface IModle {
	public interface AyskCallBack{
		public void onSeccesse(List<Book> books);
		public void onFialed();
	}
	public interface CommonCallBack{
		public void onSeccesse(Object obj);
		public void onFialed(Object error);
	}
	public interface ImageCodeCallBack{
		public void onSeccesse(Bitmap bitmap);
		public void onFialed();
	}
}
