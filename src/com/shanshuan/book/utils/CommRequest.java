package com.shanshuan.book.utils;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.shanshuan.book.app.MyApplication;

/**
 *Created by Zifeng Wang 2016-6-27下午3:56:26
 */
public class CommRequest extends StringRequest{
	public static String JESSIONID=null; 

	public CommRequest(int method, String url, Listener<String> listener,
			ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}
	public CommRequest(String url, Listener<String> listener,
			ErrorListener errorListener) {
		super(url, listener, errorListener);
	}
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String, String> headers=super.getHeaders();
		if(headers==null||headers.equals(Collections.EMPTY_MAP)){
			headers=new HashMap<String, String>();
		}
		if(JESSIONID!=null){
			headers.put("Cookie", JESSIONID);
		}
		//判断sharepreference中是否存有cart信息  有的话则一起发送数据
				SharedPreferences pref =MyApplication.getContext().getSharedPreferences("cart", Context.MODE_PRIVATE);
				String cart=pref.getString("cart", null);
				if(cart!=null){
					String cookie=headers.get("Cookie");
					headers.put("Cookie", cookie==null ? cart:cookie+", cart="+cart);
				}
		return headers;
	}
	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		Map<String, String> headers = response.headers;
		String session=headers.get("Set-Cookie");
		if(session!=null){
			JESSIONID=session.split(";")[0];
		}
		String parsed;
		try {
			parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
		} catch (UnsupportedEncodingException e) {
			parsed = new String(response.data);
		}
		return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
	}

}
