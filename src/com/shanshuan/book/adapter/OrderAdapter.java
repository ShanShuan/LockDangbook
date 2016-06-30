package com.shanshuan.book.adapter;

import java.util.List;

import org.xutils.x;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shanshuan.book.R;
import com.shanshuan.book.entity.CartItem;
import com.shanshuan.book.persenter.ICartPresenter;
import com.shanshuan.book.utils.GlobalConsts;

/**
 *Created by Zifeng Wang 2016-6-29ÏÂÎç12:22:18
 */
public class OrderAdapter extends BaseAdapter{
	private List<CartItem> items;
	private Context context;
	public OrderAdapter(List<CartItem> items,Context context){
		this.items=items;
		this.context=context;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public CartItem getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler hodler=null;
		CartItem item=items.get(position);
		if(convertView==null){
			convertView=View.inflate(context, R.layout.item_lv_order, null);
			hodler=new ViewHodler();
			hodler.ivBook=(ImageView) convertView.findViewById(R.id.iv_order_book);
			hodler.tvProductName=(TextView) convertView.findViewById(R.id.tv_order_product_name);
			hodler.tvPrice=(TextView) convertView.findViewById(R.id.tv_order_price);
			hodler.tvCount=(TextView) convertView.findViewById(R.id.tv_order_count);
			hodler.tvTotalPrice=(TextView) convertView.findViewById(R.id.tv_order_total_price);
			convertView.setTag(hodler);
		}else{
			hodler=(ViewHodler) convertView.getTag();
		}
		hodler.tvProductName.setText(item.getBook().getProductName());
		hodler.tvPrice.setText(item.getBook().getDangPrice()+"£¤");
		hodler.tvCount.setText("x"+(item.getBookCount()));
		hodler.tvTotalPrice.setText("£¤"+(item.getBookCount())*(item.getBook().getDangPrice()));
		//ÏÔÊ¾Í¼Æ¬
		String path=GlobalConsts.BASEURL+ "productImages/"+item.getBook().getProduct_pic();
		x.image().bind(hodler.ivBook, path);
		return convertView;
	}
	class ViewHodler{
		private TextView tvTotalPrice;
		private ImageView ivBook;
		private TextView tvProductName;
		private TextView tvPrice;
		private TextView tvCount;
	}

	

	
	
	
	


}
