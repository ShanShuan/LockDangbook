package com.shanshuan.book.persenter.imp;

import android.graphics.Bitmap;

import com.shanshuan.book.entity.User;
import com.shanshuan.book.modle.IModle.CommonCallBack;
import com.shanshuan.book.modle.IModle.ImageCodeCallBack;
import com.shanshuan.book.modle.IUserModle;
import com.shanshuan.book.modle.imp.UserModleImp;
import com.shanshuan.book.persenter.ISignupPresenter;
import com.shanshuan.book.view.ISignupView;

/**
 *Created by Zifeng Wang 2016-6-27ÏÂÎç3:30:37
 */
public class SignupPresenterImp implements ISignupPresenter{
	private ISignupView view;
	private IUserModle modle;
	public SignupPresenterImp(ISignupView view){
		this.view=view;
		this.modle=new  UserModleImp();
	}

	@Override
	public void getImageCode() {
		modle.getImageCode(new ImageCodeCallBack() {
			
			@Override
			public void onSeccesse(Bitmap bitmap) {
				view.showCode(bitmap);
			}
			
			@Override
			public void onFialed() {
			}
		});
		
	}

	@Override
	public void signup(User user, String code) {
		modle.signup(user, code, new CommonCallBack() {
			
			@Override
			public void onSeccesse(Object obj) {
				view.signupSeccess();
			}
			
			@Override
			public void onFialed(Object error) {
				view.signupFail((String) error);
				
			}
		});
		
	}


}
