package com.shanshuan.book.modle.imp;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.widget.ImageView.ScaleType;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.User;
import com.shanshuan.book.modle.IUserModle;
import com.shanshuan.book.utils.CommRequest;
import com.shanshuan.book.utils.GlobalConsts;
import com.shanshuan.book.utils.JsonParser;

/**
 *Created by Zifeng Wang 2016-6-27下午3:26:52
 */
public class UserModleImp implements IUserModle {
	
	protected RequestQueue q;
	public UserModleImp(){
		 q = MyApplication.getContext().getQueue();
	}
	/**
	 * 获取验证码
	 */
	@Override
	public void getImageCode(final ImageCodeCallBack callback) {
		
				
				ImageRequest request=new ImageRequest(GlobalConsts.URL_GET_IMAGE_CODE,
						new Listener<Bitmap>() {

							@Override
							public void onResponse(Bitmap arg0) {
								callback.onSeccesse(arg0);
								
							}
						},
						35,
						70,
						ScaleType.CENTER_CROP,
						Config.RGB_565,
						new ErrorListener() {

							@Override
							public void onErrorResponse(VolleyError arg0) {
								
							}
						}){
					@Override
					protected Response<Bitmap> parseNetworkResponse(
							NetworkResponse response) {
						Map<String, String> header = response.headers;
						String jessionId=header.get("Set-Cookie");
						if(jessionId!=null){
							CommRequest.JESSIONID=jessionId.split(";")[0];
						}
						return super.parseNetworkResponse(response);
					}
				};
				q.add(request);
			
	}
	/**
	 * 注册用户
	 */
	@Override
	public void signup(final User user, final String code, final CommonCallBack callBack) {
		CommRequest commRequest=new CommRequest(Request.Method.POST, 
				GlobalConsts.URL_USER_REGIST,
				new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						try {
							JSONObject object=new JSONObject(arg0);
							if(GlobalConsts.SIGNUP_SUCCESSED==object.getInt("code")){
								callBack.onSeccesse(null);
							}else{
								callBack.onFialed(object.getString("error_msg"));
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
						
					}
				}, 
				new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						
					}
				}){
			@Override
			protected Map<String, String> getParams()
					throws AuthFailureError {
				Map<String, String> map=new HashMap<String, String>();
				map.put("user.email", user.getEmail());
				map.put("user.nickname", user.getNickname());
				map.put("user.password", user.getPassword());
				map.put("number", code);
				
				return map;
			}
		};
		q.add(commRequest);
		
	}
	/**
	 * 登录
	 */
	@Override
	public void login(final User user, final CommonCallBack callBack) {
		 CommRequest request=new CommRequest(Request.Method.POST, GlobalConsts.URL_USER_LOGIN,
				 new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						Log.i("123", arg0);
						try {
							JSONObject obj=new JSONObject(arg0);
							if(obj.getInt("code")==GlobalConsts.SIGNUP_SUCCESSED){
								JSONObject user=obj.getJSONObject("user");
								MyApplication.getContext().setUser(JsonParser.paserUser(user));
								String token = obj.getString("token");
								MyApplication.getContext().setToken(token);
								callBack.onSeccesse(null);
							}else{
								callBack.onFialed(obj.getString("error_msg"));
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				},
				 new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						
					}
				}){
			 @Override
			protected Map<String, String> getParams()
					throws AuthFailureError {
				 Map<String, String> map=new HashMap<String, String>();
				 map.put("email", user.getEmail());
				 Log.i("123", user.getEmail());
				 map.put("password", user.getPassword());
				 Log.i("123", user.getPassword());
				return map;
			}
		 };
		q.add(request);
	}
	/**
	 * 七天自动登录
	 */
	@Override
	public void loginWithoutPassword(final CommonCallBack callBack) {
		final MyApplication app=MyApplication.getContext();
		String token=app.getToken();
		if(token==null){
			return;
		}
		RequestQueue q=app.getQueue();
		String url=GlobalConsts.URL_USER_LOGIN_WITHOUT_PWD+"?token="+token;
		CommRequest r=new CommRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				try {
					JSONObject obj=new JSONObject(arg0);
					if(obj.getInt("code")==GlobalConsts.SIGNUP_SUCCESSED){
						JSONObject u = obj.getJSONObject("user");
						User user = JsonParser.paserUser(u);
						app.setUser(user);
						callBack.onSeccesse(null);
					}else{
						callBack.onFialed(obj.getString("error_msg"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		q.add(r);
	}
	

}
