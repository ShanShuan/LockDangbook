package com.shanshuan.book.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.google.gson.Gson;
import com.shanshuan.book.entity.Address;
import com.shanshuan.book.entity.Book;
import com.shanshuan.book.entity.InBooks;
import com.shanshuan.book.entity.User;

public class JsonParser {
	public static List<Book> getBooks(String json){
		Gson gson=new Gson();
		InBooks i=gson.fromJson(json, InBooks.class);
		return i.getData();
	}
	public static User paserUser(JSONObject obj) throws JSONException{
		User user = new User();
		user.setEmail(obj.getString("email"));
		user.setEmailVerify(obj.getBoolean("emailVerify"));
		user.setEmailVerifyCode(obj.getString("emailVerifyCode"));
		user.setId(obj.getInt("id"));
		user.setLastLoginIp(obj.getString("lastLoginIp"));
		user.setLastLoginTime(obj.getLong("lastLoginTime"));
		user.setNickname(obj.getString("nickname"));
		user.setPassword(obj.getString("password"));
		return user;
	}
	public static List<Address> paserAddress(JSONArray ary) throws JSONException {
		List<Address> adds=new ArrayList<Address>();
		for (int i = 0; i < ary.length(); i++) {
			JSONObject  obj=ary.getJSONObject(i);
			Address a=new Address();
			a.setId(obj.getInt("id"));
			a.setPhone(obj.getString("phone"));
			a.setPostalCode(obj.getString("postalCode"));
			a.setMobile(obj.getString("mobile"));
			a.setFull_address(obj.getString("full_address"));
			a.setIs_default(obj.getInt("is_default"));
			a.setReceiveName(obj.getString("receiveName"));
			adds.add(a);
			
			}
		return adds;
	}
}
