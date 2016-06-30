package com.shanshuan.book.modle.imp;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Request.Method;
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
 * Created by Zifeng Wang 2016-6-29上午11:06:37
 */
public class OrderModleImp implements IOrderModle {

	private RequestQueue q;
	private MyApplication app;

	public OrderModleImp() {
		app = MyApplication.getContext();
		q = MyApplication.getContext().getQueue();
	}

	@Override
	public void loadDefaultAddress(final CommonCallBack callBack) {
		String url = GlobalConsts.URL_LOAD_DEFAULT_ADDRESS;
		CommRequest r = new CommRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String a) {
				try {
					JSONObject obj = new JSONObject(a);
					if (obj.getInt("code") == GlobalConsts.RESPONSE_CODE_SUCCESS) {
						Address address = new Address();
						JSONObject add = obj.getJSONObject("data");
						address.setId(add.getInt("id"));
						address.setReceiveName(add.getString("receiveName"));
						address.setFull_address(add.getString("full_address"));
						address.setPostalCode(add.getString("postalCode"));
						address.setMobile(add.getString("mobile"));
						address.setPhone(add.getString("phone"));
						callBack.onSeccesse(address);

					} else {
						callBack.onFialed(obj.getString("error_msg"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});
		q.add(r);
	}

	/**
	 * 提交订单业务
	 */
	@Override
	public void submitOrder(final Address add, final CommonCallBack callBack) {
		String url = GlobalConsts.URL_SUBMIT_ORDER;
		CommRequest r = new CommRequest(Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						JSONObject obj;
						try {
							obj = new JSONObject(arg0);
							if (GlobalConsts.RESPONSE_CODE_SUCCESS == obj
									.getInt("code")) {
								callBack.onSeccesse(null);
							} else {
								String s = obj.getString("error_msg");
								callBack.onFialed(s);
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {

					}
				}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("addressId", add.getId() + "");
				params.put("cartInfo", app.getCart().cartToString());
				return params;
			}

		};
		q.add(r);
	}

}
