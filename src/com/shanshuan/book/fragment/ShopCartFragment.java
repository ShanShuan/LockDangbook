package com.shanshuan.book.fragment;

import java.io.IOException;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shanshuan.book.R;
import com.shanshuan.book.activity.OrderActivity;
import com.shanshuan.book.adapter.CartAdapter;
import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.Cart;
import com.shanshuan.book.entity.CartItem;
import com.shanshuan.book.persenter.ICartPresenter;
import com.shanshuan.book.persenter.imp.CartPresenterImp;
import com.shanshuan.book.view.ICartView;

public class ShopCartFragment extends Fragment implements ICartView{
	private Cart cart;
	private List<CartItem> items;
	@ViewInject(R.id.lv_cart)
	private ListView lvCart;
	@ViewInject(R.id.tv_edit)
	private TextView tvEdit;
	@ViewInject(R.id.tv_cart_price)
	private TextView tvPrice;
	
	@ViewInject(R.id.btn_cart_submit)
	private Button btnCartSubmit;
	
	private CartAdapter adapter;
	private ICartPresenter cartPresenter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.cart_fragment, null);
		x.view().inject(this,view);
		cartPresenter=new CartPresenterImp(this);
		setListenners();
//		//设置listview 动画
//		Animation animation=AnimationUtils.loadAnimation(getActivity(), R.anim.item_animation);
//		
//		LayoutAnimationController c=new LayoutAnimationController(animation);
//		c.setDelay(300);
//		c.setOrder(LayoutAnimationController.ORDER_NORMAL);
//		lvCart.setLayoutAnimation(c);
		return view;
	}
	
	
	private void setListenners() {
		tvEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapter.deleteLog();
				
			}
		});
		
		btnCartSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(items.size()==0){
					Toast.makeText(getActivity(), "你还没有购买任何书，无法结算", Toast.LENGTH_SHORT).show();
					return;
				}
				Intent i=new Intent(getActivity(), OrderActivity.class);
				startActivity(i);
			}
		});
		
	}


	//onresume 时显示添加的购物信息
	@Override
	public void onResume() {
		cart=MyApplication.getContext().getCart();
		items=cart.getItems();
		setAdapter(items);
		super.onResume();
	}
	
	private void setAdapter(List<CartItem> items2) {
		adapter=new CartAdapter(items, getActivity(),lvCart);
		adapter.setCartPresenter(cartPresenter);
		adapter.setTvPrice(tvPrice);
		lvCart.setAdapter(adapter);
	}


	@Override
	public void addCartSuccess() {
		
	}


	@Override
	public void addCartFail() {
		
	}
}
