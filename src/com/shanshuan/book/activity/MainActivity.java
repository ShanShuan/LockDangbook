package com.shanshuan.book.activity;

import java.util.ArrayList;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.shanshuan.book.R;
import com.shanshuan.book.fragment.BookStoreFragment;
import com.shanshuan.book.fragment.MineFragment;
import com.shanshuan.book.fragment.ShopCartFragment;

public class MainActivity extends FragmentActivity {
	@ViewInject(R.id.vp)
	private ViewPager vp;
	
	@ViewInject(R.id.rg_main_bottom)
	private RadioGroup rg;
	
	@ViewInject(R.id.rb_chucheng)
	private RadioButton rbBookStore;
	
	@ViewInject(R.id.rb_shopcart)
	private RadioButton rbShopCart ;
	
	@ViewInject(R.id.rb_mine)
	private RadioButton rbMine;

	 
	private ArrayList<Fragment> fragments;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		x.view().inject(this);
		intitData();
		vp.setOffscreenPageLimit(3);
		setAdapter();
		setListenners();
	}
	/**
	 * ÉèÖÃ¼àÌýÆ÷
	 */
	private void setListenners() {
		
		/**
		 * radioGroup¼àÌýÆ÷
		 */
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_chucheng:
					vp.setCurrentItem(0);
					break;
				case R.id.rb_shopcart:
					vp.setCurrentItem(1);
					break;
				case R.id.rb_mine:
					vp.setCurrentItem(2);
					break;

				}
				
			}
		});
		/**
		 * vp¼àÌýÆ÷
		 */
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					rbBookStore.setChecked(true);
					break;
				case 1:
					rbShopCart.setChecked(true);
					break;
				case 2:
					rbMine.setChecked(true);
					break;

				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	/**
	 * ÉèÖÃadapter
	 */
	private void setAdapter() {
		PagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				return fragments.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				
				return fragments.get(arg0);
			}
			
		};
		vp.setAdapter(adapter);
	}
	/**
	 * ³õÊ¼»¯Êý¾Ý
	 */
	private void intitData() {
		fragments=new ArrayList<Fragment>();
		fragments.add(new BookStoreFragment());
		fragments.add(new ShopCartFragment());
		fragments.add(new MineFragment());
		
	}
	



}
