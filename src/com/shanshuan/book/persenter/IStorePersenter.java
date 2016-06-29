package com.shanshuan.book.persenter;

import com.shanshuan.book.modle.IModle.AyskCallBack;

public interface IStorePersenter extends IPersenter{
	public void getRecommendBook();
	public void getHotBook();
	public void getNewBook();
}
