package com.shanshuan.book.activity;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shanshuan.book.R;
import com.shanshuan.book.adapter.AddressAdapter;
import com.shanshuan.book.entity.Address;
import com.shanshuan.book.persenter.imp.AddressPersenterImp;
import com.shanshuan.book.view.AddressAlertDialog;
import com.shanshuan.book.view.AddressAlertDialog.CallBack;
import com.shanshuan.book.view.IAddressView;

public class AddressActivity extends Activity implements IAddressView{
	@ViewInject(R.id.tv_address_add)
	private TextView tvAdd;
	@ViewInject(R.id.lv_address_address)
	private ListView lvAddress;
	private AddressPersenterImp persenter;
	private AddressAlertDialog dialog;
	private AddressAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		x.view().inject(this);
		 persenter=new AddressPersenterImp(this);
		 persenter.listAddress();
		setLitenners();
	}
	
	@Override
	protected void onRestart() {
		
		super.onRestart();
	}
	/**
	 * 设置监听器
	 */
	private void setLitenners() {
		tvAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showAlertDialog();
			}
		});
		
	}
	/**
	 * 显示对话框
	 */
	protected void showAlertDialog() {
		dialog = new AddressAlertDialog(this, new CallBack() {
			
			@Override
			public void onSubmit(Address address) {
				persenter.addAddress(address);
				
			}
		});
		
		dialog.show();
	}
	/**
	 * 添加地址成功
	 */
	@Override
	public void addAddressSucesse() {
		Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
		dismissDialog();
	}
	/**
	 * 隐藏对话框
	 */
	private void dismissDialog() {
		dialog.cancel();
		 persenter.listAddress();
		
	}
	/**
	 * 添加地址失败
	 */
	@Override
	public void addAddressFail(String error) {
		Toast.makeText(this, "添加失败"+error, Toast.LENGTH_SHORT).show();
		
	}
	/**
	 * 获取地址集合成功
	 * 
	 */
	@Override
	public void listAddressSucesse(List<Address> adds) {
		//设置adapter
		setAdapter(adds);
	}
	/**
	 * 设置适配器
	 */
	private void setAdapter(List<Address> adds) {
		adapter=new AddressAdapter(this, adds,persenter);
		lvAddress.setAdapter(adapter);
		
	}
	/**
	 * 获取地址集合失败
	 */
	@Override
	public void listAddressFail(String error) {
		// TODO Auto-generated method stub
		
	}



}
