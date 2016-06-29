package com.shanshuan.book.view;

import com.shanshuan.book.R;
import com.shanshuan.book.entity.Address;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 *Created by Zifeng Wang 2016-6-28ÏÂÎç3:27:09
 */
public class AddressAlertDialog extends Dialog{
	private Context context;
	private  CallBack callBack;
	private EditText etReceiveName;
	private EditText etAddress;
	private EditText etCode;
	private EditText etPhone;
	private EditText etTel;
	private Button btnSubmit;

	public AddressAlertDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public AddressAlertDialog(Context context, int theme) {
		super(context, theme);
	}

	public AddressAlertDialog(Context context,CallBack callBack){
		super(context);
		this.callBack=callBack;
		this.context=context;
	}
	
	@Override
	public void show() {
		
			Window window=getWindow();
			View view=View.inflate(context, R.layout.dialog_add_address, null);
			window.setContentView(view);
			setView(view);
			
			setListenner();
		
		super.show();
	}
	private void setListenner() {
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Address address = new Address();
				address.setReceiveName(etReceiveName.getText().toString());
				address.setFull_address(etAddress.getText().toString());
				address.setMobile(etPhone.getText().toString());
				address.setPostalCode(etCode.getText().toString());
				address.setPhone(etTel.getText().toString());
				callBack.onSubmit(address);
				
			}
		});
	}

	private void setView(View view) {
		etReceiveName = (EditText) view.findViewById(R.id.etReceiveName);
		etAddress = (EditText) view.findViewById(R.id.etReceiveAddress);
		etCode = (EditText) view.findViewById(R.id.etCode);
		etPhone = (EditText) view.findViewById(R.id.etPhone);
		etTel = (EditText) view.findViewById(R.id.etTel);
		btnSubmit = (Button) view.findViewById(R.id.btnAddressSubmit);
		
	}
	public interface CallBack{
		 void onSubmit(Address address);
	}
}
