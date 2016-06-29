package com.shanshuan.book.view;

import java.util.List;

import com.shanshuan.book.entity.Address;

/**
 *Created by Zifeng Wang 2016-6-28обнГ3:16:57
 */
public interface IAddressView {
	public void addAddressSucesse();
	public void addAddressFail(String error);
	public void listAddressSucesse(List<Address> adds);
	public void listAddressFail(String error);
}
