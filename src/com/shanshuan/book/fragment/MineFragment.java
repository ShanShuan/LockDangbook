package com.shanshuan.book.fragment;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanshuan.book.R;
import com.shanshuan.book.activity.AddressActivity;
import com.shanshuan.book.activity.LoginActivity;
import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.User;
import com.shanshuan.book.persenter.imp.MinePersenterImp;
import com.shanshuan.book.view.IMineView;

public class MineFragment extends Fragment implements IMineView{
	@ViewInject(R.id.iv_mine_photo)
	private ImageView ivPhoto;
	@ViewInject(R.id.tv_mine_name)
	private TextView tvUserName;
	@ViewInject(R.id.tv_mine_addrass)
	private TextView tvMineAddress;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =inflater.inflate(R.layout.mine_fragmenyt, null);
		x.view().inject(this, view);
		setLitenners();
		//自动登录
		String token=MyApplication.getContext().getToken();
		MinePersenterImp p=new MinePersenterImp(this);
		if(token!=null){
			p.loginWithoutPassword();
			Log.i("token", "!=null");
		}else{
		}
		return view;
	}
	private void setLitenners() {
		//图像设置监听器
		ivPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					Intent intent=new Intent(getActivity(),LoginActivity.class);
					startActivity(intent);
					int exitAnim=R.anim.fade;
					int enterAnim=R.anim.hold;
					getActivity().overridePendingTransition(enterAnim, exitAnim);
			}
		});
		//我的地址管理监听器
		tvMineAddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getActivity(), AddressActivity.class);
				startActivity(i);
			}
		});
	}
	@Override
	public void onResume() {
		User user=MyApplication.getContext().getUser();
		if(user!=null){
		tvUserName.setText(user.getNickname());
		}
		super.onResume();
	}
	@Override
	public void showLogin() {
		User user=MyApplication.getContext().getUser();
		tvUserName.setText(user.getNickname());
	}
	
}
