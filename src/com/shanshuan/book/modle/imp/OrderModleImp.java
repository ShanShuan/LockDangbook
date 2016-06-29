package com.shanshuan.book.modle.imp;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.Address;
import com.shanshuan.book.modle.IModle.CommonCallBack;
import com.shanshuan.book.modle.IOrderModle;
import com.shanshuan.book.utils.CommRequest;
import com.shanshuan.book.utils.GlobalConsts;

/**
 *Created by Zifeng Wang 2016-6-29ÉÏÎç11:06:37
 */
public class OrderModleImp implements IOrderModle{
	
	private RequestQueue q;

	public OrderModleImp(){
		q=MyApplication.getContext().getQueue();
	}

	@Override
	public void loadDefaultAddress(final CommonCallBack callBack) {
		String url=GlobalConsts.URL_LOAD_DEFAULT_ADDRESS;
		CommRequest r=new CommRequest(url,
				new Listener<String>() {

					@Override
					public void onResponse(String a) {
						try {
							JSONObject obj=new JSONObject(a);
							if(obj.getInt("code")==GlobalConsts.RESPONSE_CODE_SUCCESS){
								Address address = new Address();
								JSONObject add=obj.getJSONObject("data");
								address.setId(add.getInt("id"));
								address.setReceiveName(add.getString("receiveName"));
								address.setFull_address(add.getString("full_address"));
								address.setPostalCode(add.getString("postalCode"));
								address.setMobile(add.getString("mobile"));
								address.setPhone(add.getString("phone"));
								callBack.onSeccesse(address);
								
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
						
					}
				});
		q.add(r);
	}

}
