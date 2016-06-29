package com.shanshuan.book.modle;

import com.shanshuan.book.entity.User;


/**
 *Created by Zifeng Wang 2016-6-27обнГ3:25:27
 */
public interface IUserModle extends IModle{
	public void getImageCode(ImageCodeCallBack callback);
	public void signup(User user,String code ,CommonCallBack callBack);
	public void login(User user,CommonCallBack callBack);
	public void loginWithoutPassword(CommonCallBack callBack);
}
