package com.shanshuan.book.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.shanshuan.book.R;
import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.Book;
import com.shanshuan.book.utils.GlobalConsts;

public class BookAdapter extends BaseAdapter {
	private List<Book> books;
	private Context context;
	private MyApplication app;
	private RequestQueue q;
	private ImageLoader imageLoader;
	public BookAdapter(Context context,List<Book> books){
		app=(MyApplication) context.getApplicationContext();
		 q = app.getQueue();
		imageLoader=new ImageLoader(q, new BitmapCache());
		this.books=books;
		this.context=context;
	}

	@Override
	public int getCount() {
		return books.size();
	}

	@Override
	public Book getItem(int position) {
		return books.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler hodler=null;
		Book b=books.get(position);
		if(convertView==null){
			convertView=View.inflate(context, R.layout.item_book, null);
			hodler=new ViewHodler();
			hodler.iv=(ImageView) convertView.findViewById(R.id.iv_book);
			hodler.tv=(TextView) convertView.findViewById(R.id.tv_book_name);
			convertView.setTag(hodler);
		}else{
			hodler=(ViewHodler) convertView.getTag();
		}
		hodler.tv.setText(b.getProductName());
		ImageListener listener=ImageLoader.getImageListener(hodler.iv, R.drawable.ic_launcher, R.drawable.ic_launcher);
		imageLoader.get(GlobalConsts.BASEURL + "productImages/"+b.getProduct_pic(),listener);
		return convertView;
	}
	public class BitmapCache implements ImageCache{
		private LruCache<String, Bitmap> mCache;
		public BitmapCache(){
			int maxSize=10*1024*1024;
			mCache=new LruCache<String, Bitmap>(maxSize){
				@Override
				protected int sizeOf(String key, Bitmap value) {
					return value.getRowBytes()*value.getHeight();
				}
			};
		}
		@Override
		public Bitmap getBitmap(String arg0) {
			return mCache.get(arg0);
		}

		@Override
		public void putBitmap(String arg0, Bitmap arg1) {
			mCache.put(arg0, arg1);
		}
		
	}
	class ViewHodler{
		ImageView iv;
		TextView tv;
	}
}
