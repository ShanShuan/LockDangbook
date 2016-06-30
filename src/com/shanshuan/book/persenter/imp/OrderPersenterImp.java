package com.shanshuan.book.persenter.imp;


import com.shanshuan.book.entity.Address;
import com.shanshuan.book.modle.IModle.CommonCallBack;
import com.shanshuan.book.modle.IOrderModle;
import com.shanshuan.book.modle.imp.OrderModleImp;
import com.shanshuan.book.persenter.IOrderPersenter;
import com.shanshuan.book.view.IOrderView;

/**
 *Created by Zifeng Wang 2016-6-29����11:08:24
 */
public class OrderPersenterImp implements IOrderPersenter{
	private IOrderView view;
	private IOrderModle modle;
	public OrderPersenterImp(IOrderView view){
		this.view=view;
		modle=new OrderModleImp();
	}
	/**
	 * ���Ĭ�ϵ�ַ
	 */
	@Override
	public void getDefaultAddress() {
		modle.loadDefaultAddress(new CommonCallBack() {
			
			@Override
			public void onSeccesse(Object obj) {
				view.setDefalutAddress((Address)obj);
				
			}
			
			@Override
			public void onFialed(Object error) {
				 view.setDefalutAddressFail((String)error);
			}
		});
	}
	
	/**
	 * �ύ����
	 */
	@Override
	public void submitOrder(Address add) {
		modle.submitOrder(add,new CommonCallBack() {
			
			@Override
			public void onSeccesse(Object obj) {
				view.submitOrderSecesse();
				
			}
			
			@Override
			public void onFialed(Object error) {
				view.submitOrderFail((String)error);
				
			}
		});
	}

}
