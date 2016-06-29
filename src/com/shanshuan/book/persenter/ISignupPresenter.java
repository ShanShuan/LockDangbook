package com.shanshuan.book.persenter;

import com.shanshuan.book.entity.User;

/**
 *Created by Zifeng Wang 2016-6-27обнГ3:23:59
 */
public interface ISignupPresenter {
	public void getImageCode();
	public void signup(User user,String code);
}
