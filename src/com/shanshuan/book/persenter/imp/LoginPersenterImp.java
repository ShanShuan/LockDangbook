package com.shanshuan.book.persenter.imp;

import com.shanshuan.book.entity.User;
import com.shanshuan.book.modle.IModle.CommonCallBack;
import com.shanshuan.book.modle.IUserModle;
import com.shanshuan.book.modle.imp.UserModleImp;
import com.shanshuan.book.persenter.ILoginPrestenter;
import com.shanshuan.book.view.ILoginView;

/**
 *Created by Zifeng Wang 2016-6-27ÏÂÎç6:14:04
 */
public class LoginPersenterImp implements ILoginPrestenter{
	private ILoginView view;
	private IUserModle modle;
	public LoginPersenterImp(ILoginView view){
		this.view=view;
		modle=new UserModleImp();
		
	}
	@Override
	public void login(User user) {
		modle.login(user, new CommonCallBack() {
			
			@Override
			public void onSeccesse(Object obj) {
				view.loginSeccess();
				
			}
			
			@Override
			public void onFialed(Object error) {
				view.loginFail((String)error);
				
			}
		});
		
	}



	

}
