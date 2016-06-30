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
	 * ���ü�����
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
	 * ��ʾ�ײ��ܼ۸�
	 */
	private void showBottomTotalPrice() {
		int totalPrice = app.getCart().getSumPrice();
		int itemCounts=app.getCart().getItems().size();
		String source="<h1>��<font color='red'>"+itemCounts+"</font>�����ܽ�<font color='red'>��"+totalPrice+"</font></h1>";
			tvOrderInfo.setText(Html.fromHtml(source));
	}
	/**
	 * ��ʾ�Ѿ����빺�ﳵ��item
	 * @param items
	 */
	private void setadapter(List<CartItem> items) {
		OrderAdapter adapter=new OrderAdapter(items, this);
		lvOrder.setAdapter(adapter);
	}

	/**
	 * ����Ĭ���ջ��ַ
	 */
	@Override
	public void setDefalutAddress(Address add) {
		address=add;
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
	/**
	 * �ύ�����ɹ�
	 */
	@Override
	public void submitOrderSecesse() {
		Toast.makeText(this, "�ύ�����ɹ�", Toast.LENGTH_SHORT).show();
		app.clearItems();
		finish();
	}
	/**
	 * �ύ����ʧ��
	 */
	@Override
	public void submitOrderFail(String error) {
		Toast.makeText(this, "�ύ����ʧ��:"+error, Toast.LENGTH_SHORT).show();
		
	}

}
