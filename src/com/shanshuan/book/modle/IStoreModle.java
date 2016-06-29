package com.shanshuan.book.modle;

public interface IStoreModle extends IModle {
	public void loadRecommendBook(AyskCallBack callBack);
	public void loadHotBook(AyskCallBack callBack);
	public void loadNewBook(AyskCallBack callBack);
}
