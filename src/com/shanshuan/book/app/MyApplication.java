package com.shanshuan.book.app;

import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.shanshuan.book.entity.Cart;
import com.shanshuan.book.entity.User;

import android.app.Application;
import android.content.SharedPreferences;

public class MyApplication extends Application {
	private static MyApplication context; 
	private RequestQueue queue;
	private Cart cart;
	private User user;
	private String token;
	
	public String getToken() {
		SharedPreferences p=getSharedPreferences("token",  MODE_PRIVATE);
		String token=p.getString("token", null);
			return token;
	}
	public void setToken(String token) {
		this.token = token;
		SharedPreferences p=getSharedPreferences("token",  MODE_PRIVATE);
		SharedPreferences.Editor editor=p.edit();
		editor.putString("token", token);
		editor.commit();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public static MyApplication getContext(){
		return context;
	}
	public Cart getCart() {
		return cart;
	}
	

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public RequestQueue getQueue() {
		return queue;
	}

	public void setQueue(RequestQueue queue) {
		this.queue = queue;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		context=this;
		queue=Volley.newRequestQueue(this);
		cart=Cart.readCart();
	}
	public void clearItems() {
		cart.removeAll();
	}
}
