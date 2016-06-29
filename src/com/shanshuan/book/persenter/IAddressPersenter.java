package com.shanshuan.book.persenter;

import com.shanshuan.book.entity.Address;

/**
 *Created by Zifeng Wang 2016-6-28ÏÂÎç3:14:34
 */
public interface IAddressPersenter {
	public void addAddress(Address address);
	public void listAddress();
	public void setDefaultAddress(int id);
}
