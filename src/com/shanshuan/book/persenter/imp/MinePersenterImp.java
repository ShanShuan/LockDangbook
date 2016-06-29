package com.shanshuan.book.persenter.imp;

import com.shanshuan.book.modle.IModle.CommonCallBack;
import com.shanshuan.book.modle.IUserModle;
import com.shanshuan.book.modle.imp.UserModleImp;
import com.shanshuan.book.persenter.IMinePresenter;
import com.shanshuan.book.view.IMineView;

/**
 *Created by Zifeng Wang 2016-6-28ÉÏÎç11:25:59
 */
public class MinePersenterImp implements IMinePresenter {
	private IMineView view;
	private IUserModle modle;
	public MinePersenterImp(IMineView view){
		this.view=view;
		modle=new UserModleImp();
	}

	@Override
	public void loginWithoutPassword() {
		modle.loginWithoutPassword(new CommonCallBack() {
			
			@Override
			public void onSeccesse(Object obj) {
				view.showLogin();
				
			}
			
			@Override
			public void onFialed(Object error) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
