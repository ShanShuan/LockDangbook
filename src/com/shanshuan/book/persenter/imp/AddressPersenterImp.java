package com.shanshuan.book.persenter.imp;

import java.util.List;


import com.shanshuan.book.entity.Address;
import com.shanshuan.book.modle.IAddressModle;
import com.shanshuan.book.modle.IModle.CommonCallBack;
import com.shanshuan.book.modle.imp.AddressModleImp;
import com.shanshuan.book.persenter.IAddressPersenter;
import com.shanshuan.book.view.IAddressView;

/**
 *Created by Zifeng Wang 2016-6-28ÏÂÎç3:16:01
 */
public class AddressPersenterImp implements IAddressPersenter{
	private IAddressModle modle;
	private IAddressView view;
	public AddressPersenterImp(IAddressView view){
		modle=new AddressModleImp();
		this.view=view;
	}

	@Override
	public void addAddress(Address address) {
		modle.addAddress(address, new CommonCallBack() {
			
			@Override
			public void onSeccesse(Object obj) {
				view.addAddressSucesse();
			}
			
			@Override
			public void onFialed(Object error) {
				view.addAddressFail((String)error);
			}
		});
	}

	@Override
	public void listAddress() {
		modle.listAddress(new CommonCallBack() {
			
			@Override
			public void onSeccesse(Object obj) {
				view.listAddressSucesse((List<Address>)obj);
				
			}
			
			@Override
			public void onFialed(Object error) {
				view.listAddressFail((String)error);
			}
		});
		
	}

	@Override
	public void setDefaultAddress(int id) {
		modle.setDefaultAddress(id, new CommonCallBack() {
			
			@Override
			public void onSeccesse(Object obj) {
				listAddress();
			}
			
			@Override
			public void onFialed(Object error) {
				
			}
		});
	}

}
