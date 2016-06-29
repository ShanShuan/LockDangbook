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
	 * ��ʾ�Ѿ����빺�ﳵ��item
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
	 * ����Ĭ���ջ��ַ
	 */
	@Override
	public void setDefalutAddress(Address add) {
		String name=add.getReceiveName();
		String phone=add.getPhone();
		String address=add.getFull_address();
		String source="<h1>�ջ��ˣ�<font color='yellow'>"+name+"</font></h1><h2>�ջ�绰��<font color='green'>"+phone+"</font></h2><h3>�ջ��ַ��<font color='blue'>"+address+"</font></h3>";
		tvDefalutAddress.setText(Html.fromHtml(source));
	}
	/**
	 * ��ȡĬ�ϵ�ַ ʧ��ԭ����ʾ
	 */
	@Override
	public void setDefalutAddressFail(String error) {
		Toast.makeText(this, "��ȡĬ�ϵ�ַʧ�ܣ�"+error, Toast.LENGTH_SHORT).show();
		
	}

}
