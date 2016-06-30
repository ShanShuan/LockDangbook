package com.shanshuan.book.persenter;

import com.shanshuan.book.entity.Address;

/**
 *Created by Zifeng Wang 2016-6-29ÉÏÎç11:07:24
 */
public interface IOrderPersenter {
	public void getDefaultAddress();
	public void submitOrder(Address add);
}
