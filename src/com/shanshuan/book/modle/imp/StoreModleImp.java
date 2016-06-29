package com.shanshuan.book.modle.imp;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.Book;
import com.shanshuan.book.modle.IStoreModle;
import com.shanshuan.book.utils.GlobalConsts;
import com.shanshuan.book.utils.JsonParser;

public class StoreModleImp implements IStoreModle {
	private Context context;
	private RequestQueue q;
	
	public StoreModleImp(Context context){
		this.context=context;
	}

	@Override
	public void loadRecommendBook(final AyskCallBack callBack) {
		MyApplication app=(MyApplication) MyApplication.getContext();
		q=app.getQueue();
		StringRequest r=new StringRequest(GlobalConsts.URL_LOAD_RECOMMEND_BOOK_LIST,
				new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						//解析json字符串
						List<Book> books=JsonParser.getBooks(arg0);
						callBack.onSeccesse(books);
					}
				},
				new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.i("info", "加载推荐书，失败！");
					}
				});
		q.add(r);
	}

	@Override
	public void loadHotBook(final AyskCallBack callBack) {
		StringRequest r=new StringRequest(GlobalConsts.URL_LOAD_HOT_BOOK_LIST,
				new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						//解析json字符串
						List<Book> books=JsonParser.getBooks(arg0);
						callBack.onSeccesse(books);
					}
				},
				new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.i("info", "加载热书，失败！");
					}
				});
		q.add(r);
		
	}

	@Override
	public void loadNewBook(final AyskCallBack callBack) {
		StringRequest r=new StringRequest(GlobalConsts.URL_LOAD_NEW_BOOK_LIST,
				new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						//解析json字符串
						List<Book> books=JsonParser.getBooks(arg0);
						callBack.onSeccesse(books);
					}
				},
				new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.i("info", "加载新书，失败！");
					}
				});
		q.add(r);
		
	}

}
