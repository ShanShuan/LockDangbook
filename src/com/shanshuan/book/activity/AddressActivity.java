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
	 * ���ü�����
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
	 * ��ʾ�Ի���
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
	 * ��ӵ�ַ�ɹ�
	 */
	@Override
	public void addAddressSucesse() {
		Toast.makeText(this, "��ӳɹ�", Toast.LENGTH_SHORT).show();
		dismissDialog();
	}
	/**
	 * ���ضԻ���
	 */
	private void dismissDialog() {
		dialog.cancel();
		 persenter.listAddress();
		
	}
	/**
	 * ��ӵ�ַʧ��
	 */
	@Override
	public void addAddressFail(String error) {
		Toast.makeText(this, "���ʧ��"+error, Toast.LENGTH_SHORT).show();
		
	}
	/**
	 * ��ȡ��ַ���ϳɹ�
	 * 
	 */
	@Override
	public void listAddressSucesse(List<Address> adds) {
		//����adapter
		setAdapter(adds);
	}
	/**
	 * ����������
	 */
	private void setAdapter(List<Address> adds) {
		adapter=new AddressAdapter(this, adds,persenter);
		lvAddress.setAdapter(adapter);
		
	}
	/**
	 * ��ȡ��ַ����ʧ��
	 */
	@Override
	public void listAddressFail(String error) {
		// TODO Auto-generated method stub
		
	}



}
