package com.shanshuan.book.modle.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.Address;
import com.shanshuan.book.modle.IAddressModle;
import com.shanshuan.book.modle.IModle.CommonCallBack;
import com.shanshuan.book.utils.CommRequest;
import com.shanshuan.book.utils.GlobalConsts;
import com.shanshuan.book.utils.JsonParser;

/**
 * Created by Zifeng Wang 2016-6-28下午1:01:54
 */
public class AddressModleImp implements IAddressModle {
	private RequestQueue q;

	public AddressModleImp(){
		q=MyApplication.getContext().getQueue();
	}
	/**
	 * 发送请求 添加我的地址业务
	 */
	@Override
	public void addAddress(final Address address, final CommonCallBack callBack) {
		
		String url = GlobalConsts.URL_SAVE_ADDRESS;
		CommRequest r = new CommRequest(Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						try {
							JSONObject obj=new JSONObject(arg0);
							if(GlobalConsts.SIGNUP_SUCCESSED==obj.getInt("code")){
								callBack.onSeccesse(null);
							}else{
								callBack.onFialed((String)obj.getString("error_msg"));
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
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("address.receiveName", address.getReceiveName());
				headers.put("address.full_address", address.getFull_address());
				headers.put("address.postalCode", address.getPostalCode());
				headers.put("address.mobile", address.getMobile());
				headers.put("address.phone", address.getPhone());
				return headers;
			}

		};
		q.add(r);
	}
	
	
	/**
	 * 发送请求 查询我的地址业务
	 */
	@Override
	public void listAddress(final CommonCallBack callBack) {
		CommRequest r=new CommRequest(GlobalConsts.URL_LOAD_USER_ADDRESS,
				new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						try {
							JSONObject obj = new JSONObject(arg0);
							if(GlobalConsts.RESPONSE_CODE_SUCCESS==obj.getInt("code")){
								JSONArray ary=obj.getJSONArray("data");
								List<Address> adds=JsonParser.paserAddress(ary);
								callBack.onSeccesse(adds);
							}else{
								String error=obj.getString("error_msg");
								callBack.onFialed(error);
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
				});
		q.add(r);
	}
	
	/**
	 * 设置默认地址
	 */
	@Override
	public void setDefaultAddress(int id, final CommonCallBack callBack) {
		String url=GlobalConsts.URL_SET_ADDRESS_DEFAULT+"?id="+id;
		CommRequest r=new CommRequest(url,
				new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						try {
							JSONObject obj=new JSONObject(arg0);
							if(obj.getInt("code")==GlobalConsts.RESPONSE_CODE_SUCCESS){
								callBack.onSeccesse(null);
							}else{
								callBack.onFialed(obj.getString("error_msg"));
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				},
				new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						
					}
				});
		q.add(r);
	}

}
