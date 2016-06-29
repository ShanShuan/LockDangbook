package com.shanshuan.book.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.shanshuan.book.R;
import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.Book;
import com.shanshuan.book.persenter.imp.CartPresenterImp;
import com.shanshuan.book.utils.BitmapUtils;
import com.shanshuan.book.utils.GlobalConsts;
import com.shanshuan.book.view.ICartView;

public class BookInfoActivity extends Activity implements ICartView{
	@ViewInject(R.id.tv_title)
	private TextView tvTitle;
	@ViewInject(R.id.tv_author)
	private TextView tvAuthor;
	@ViewInject(R.id.tv_catogery)
	private TextView tvCategory;
	@ViewInject(R.id.tv_publish_time)
	private TextView tvPublishTime;
	@ViewInject(R.id.tv_publish_name)
	private TextView tvPublishName;
	@ViewInject(R.id.tv_description)
	private TextView tvDesripyion;
	@ViewInject(R.id.tv_price)
	private TextView tvPrive;
	@ViewInject(R.id.ll_background)
	private RelativeLayout llBackground;
	@ViewInject(R.id.iv_book_info)
	private ImageView ivBookInfo;
	@ViewInject(R.id.iv_mohu)
	private ImageView ivMohu;
	@ViewInject(R.id.ib_add_book)
	private Button btnAdd;
	private Intent i;
	private MyApplication app;
	private RequestQueue q;
	private Book b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_info);
		x.view().inject(this);
		i=getIntent();
		show();
		setLitenners();
	}

	
	
	private void setLitenners() {
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CartPresenterImp cpi=new CartPresenterImp(BookInfoActivity.this);
				cpi.addBookToCart(b);
				btnAdd.setEnabled(false);
				Toast.makeText(BookInfoActivity.this,"已添加到购物车",Toast.LENGTH_SHORT).show();
			}
		});
		
	}



	private void show() {
		b = (Book) i.getSerializableExtra("book");
		tvTitle.setText(
				b.getProductName());
		tvAuthor.setText("作者： "+
				b.getAuthor());
		tvCategory.setText("类别： "
				+b.getCatalogue());
		tvPublishTime.setText("出版时间： "
				+b.getPublishedTime());
		tvPublishName.setText("出版社: "
				+b.getPublishing());
		tvDesripyion.setText(
				b.getDescription());
		tvPrive.setText(
				b.getDangPrice()+"￥");
		String p=b.getProduct_pic();
		 app=(MyApplication) this.getApplication();
		 q=app.getQueue();

		 ImageRequest ir=new ImageRequest(GlobalConsts.BASEURL+ "productImages/"+p, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap arg0) {
				
				ivBookInfo.setImageBitmap(arg0);
			}
		}, 60,
		100,
		ScaleType.FIT_XY,
		Config.ARGB_8888, 
		new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				
			}
		});
		 q.add(ir);
	
		 
		 ImageRequest ir1=new ImageRequest(GlobalConsts.BASEURL+ "productImages/"+p, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap arg0) {
				Bitmap bit = BitmapUtils.createBlurBitmap(arg0, 10);
				ivMohu.setImageBitmap(bit);
			}
		}, 180,
		75, 
		ScaleType.FIT_XY, 
		Config.RGB_565, 
		new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				
			}
		});
		 q.add(ir1);
		 
	}



	@Override
	public void addCartSuccess() {
		
	}



	@Override
	public void addCartFail() {
		// TODO Auto-generated method stub
		
	}

	


}
