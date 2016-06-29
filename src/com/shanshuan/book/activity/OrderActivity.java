package com.shanshuan.book.activity;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.shanshuan.book.R;
import com.shanshuan.book.R.layout;
import com.shanshuan.book.R.menu;
import com.shanshuan.book.adapter.OrderAdapter;
import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.Address;
import com.shanshuan.book.entity.CartItem;
import com.shanshuan.book.persenter.imp.OrderPersenterImp;
import com.shanshuan.book.view.IOrderView;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends Activity implements IOrderView{
	private OrderPersenterImp persenter;
	@ViewInject(R.id.tv_order_default_address)
	private TextView tvDefalutAddress;
	@ViewInject(R.id.lv_order)
	private ListView lvOrder;
	private List<CartItem> items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		x.view().inject(this);
		persenter=new OrderPersenterImp(this);
		persenter.getDefaultAddress();
		items=MyApplication.getContext().getCart().getItems();
		setadapter(items);
	}
	/**
	 * 显示已经加入购物车的item
	 * @param items
	 */
	private void setadapter(List<CartItem> items) {
		OrderAdapter adapter=new OrderAdapter(items, this);
		lvOrder.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
		return true;
	}
	/**
	 * 设置默认收获地址
	 */
	@Override
	public void setDefalutAddress(Address add) {
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

}
