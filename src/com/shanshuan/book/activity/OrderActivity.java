package com.shanshuan.book.activity;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shanshuan.book.R;
import com.shanshuan.book.adapter.OrderAdapter;
import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.Address;
import com.shanshuan.book.entity.CartItem;
import com.shanshuan.book.persenter.imp.OrderPersenterImp;
import com.shanshuan.book.view.IOrderView;

public class OrderActivity extends Activity implements IOrderView{
	private OrderPersenterImp persenter;
	@ViewInject(R.id.tv_order_default_address)
	private TextView tvDefalutAddress;
	@ViewInject(R.id.lv_order)
	private ListView lvOrder;
	@ViewInject(R.id.tv_oreder_info)
	private TextView  tvOrderInfo;
	@ViewInject(R.id.tv_order_submit)
	private TextView tvOrderSubmit;
	private List<CartItem> items;
	private MyApplication app;
	private Address address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		x.view().inject(this);
		persenter=new OrderPersenterImp(this);
		persenter.getDefaultAddress();
		app=MyApplication.getContext();
		items=app.getCart().getItems();
		setadapter(items);
		showBottomTotalPrice();
		setLitenners();
	}
	
	/**
	 * 设置监听器
	 */
	private void setLitenners() {
		tvOrderSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				persenter.submitOrder(address);
			}
		});
		
	}


	/**
	 * 显示底部总价格
	 */
	private void showBottomTotalPrice() {
		int totalPrice = app.getCart().getSumPrice();
		int itemCounts=app.getCart().getItems().size();
		String source="<h1>共<font color='red'>"+itemCounts+"</font>件，总金额：<font color='red'>￥"+totalPrice+"</font></h1>";
			tvOrderInfo.setText(Html.fromHtml(source));
	}
	/**
	 * 显示已经加入购物车的item
	 * @param items
	 */
	private void setadapter(List<CartItem> items) {
		OrderAdapter adapter=new OrderAdapter(items, this);
		lvOrder.setAdapter(adapter);
	}

	/**
	 * 设置默认收获地址
	 */
	@Override
	public void setDefalutAddress(Address add) {
		address=add;
		String name=add.getReceiveName();
		String phone=add.getPhone();
		String address=add.getFull_address();
		String source="<h1>收货人：<font color='yellow'>"+name+"</font></h1><h2>收获电话：<font color='green'>"+phone+"</font></h2><h3>收获地址：<font color='blue'>"+address+"</font></h3>";
		tvDefalutAddress.setText(Html.fromHtml(source));
	}
	/**
	 * 获取默认地址 失败原因显示
	 */
	@Override
	public void setDefalutAddressFail(String error) {
		Toast.makeText(this, "获取默认地址失败："+error, Toast.LENGTH_SHORT).show();
		
	}
	/**
	 * 提交订单成功
	 */
	@Override
	public void submitOrderSecesse() {
		Toast.makeText(this, "提交订单成功", Toast.LENGTH_SHORT).show();
		app.clearItems();
		finish();
	}
	/**
	 * 提交订单失败
	 */
	@Override
	public void submitOrderFail(String error) {
		Toast.makeText(this, "提交订单失败:"+error, Toast.LENGTH_SHORT).show();
		
	}

}
