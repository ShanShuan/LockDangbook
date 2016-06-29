package com.shanshuan.book.modle;

import com.shanshuan.book.entity.Address;
import com.shanshuan.book.modle.IModle.CommonCallBack;

/**
 *Created by Zifeng Wang 2016-6-28обнГ12:57:14
 */
public interface IAddressModle {
	public void addAddress(Address address,CommonCallBack callBack);
	public void listAddress(CommonCallBack callBack);
	public void setDefaultAddress(int id,CommonCallBack callBack);
}
