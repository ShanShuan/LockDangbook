package com.shanshuan.book.adapter;

import java.util.List;

import org.xutils.x;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shanshuan.book.R;
import com.shanshuan.book.app.MyApplication;
import com.shanshuan.book.entity.CartItem;
import com.shanshuan.book.persenter.ICartPresenter;
import com.shanshuan.book.utils.GlobalConsts;

public class CartAdapter extends BaseAdapter {
	private List<CartItem> items;
	private Context context;
	private MyApplication app;
	private boolean show=false;
	private TextView tvPrice;
	private ListView listView;
	private ICartPresenter cartPresenter;
	public CartAdapter(List<CartItem> items,Context context,ListView listView){
		this.items=items;
		this.context=context;
		this.listView=listView;
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
			convertView=View.inflate(context, R.layout.item_cart, null);
			hodler=new ViewHodler();
			hodler.btnRemove=(Button) convertView.findViewById(R.id.btn_cart_icon_remove);
			hodler.ivAdd=(ImageView) convertView.findViewById(R.id.iv_cart_add);
			hodler.ivBook=(ImageView) convertView.findViewById(R.id.iv_cart_book);
			hodler.ivMin=(ImageView) convertView.findViewById(R.id.iv_cart_min);
			hodler.tvProductName=(TextView) convertView.findViewById(R.id.tv_cart_product_name);
			hodler.tvPrice=(TextView) convertView.findViewById(R.id.tv_cart_price);
			hodler.tvCount=(TextView) convertView.findViewById(R.id.tv_cart_count);
			hodler.tvCounts=(TextView) convertView.findViewById(R.id.tv_cart_counts);
			convertView.setTag(hodler);
		}else{
			hodler=(ViewHodler) convertView.getTag();
		}
		if(!show){
			hodler.btnRemove.setVisibility(View.GONE);
		}else{
			hodler.btnRemove.setVisibility(View.VISIBLE);
		}
		hodler.tvProductName.setText(item.getBook().getProductName());
		hodler.tvPrice.setText(item.getBook().getDangPrice()+"￥");
		hodler.tvCount.setText("x"+(item.getBookCount()));
		hodler.tvCounts.setText((item.getBookCount())+"");
		//显示图片
		String path=GlobalConsts.BASEURL+ "productImages/"+item.getBook().getProduct_pic();
		x.image().bind(hodler.ivBook, path);
		tvPrice.setText(cartPresenter.getSumPrice()+"￥");
		hodler.btnRemove.setTag("btnRemove"+position);
		hodler.tvPrice.setTag("tvPrice"+position);
		hodler.tvCount.setTag("tvCount"+position);
		hodler.tvCounts.setTag("tvCounts"+position);
		hodler.tvPrice.setTag("tvPrice"+position);
		//设置监听器
		hodler.ivMin.setOnClickListener(new IvMinLitenner(position));
		hodler.ivAdd.setOnClickListener(new IvAddLitenner(position));
		hodler.btnRemove.setOnClickListener(new BtnRemoveListenner(position));
		return convertView;
	}
	class ViewHodler{
		private Button btnRemove;
		private ImageView ivBook;
		private TextView tvProductName;
		private TextView tvPrice;
		private TextView tvCount;
		private ImageView ivMin;
		private ImageView ivAdd;
		private TextView tvCounts;
	}
	
	class BtnRemoveListenner implements OnClickListener{
		private int position;
		
		public BtnRemoveListenner(int position) {
			super();
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			cartPresenter.removeItem(position);
			tvPrice.setText(
					cartPresenter.getSumPrice()+"￥");
			notifyDataSetChanged();
		}
		
	}
	
	//显示或者隐藏删除項按钮
	public void deleteLog() {
		if(!show){
			for (int i = 0; i < items.size(); i++) {
				Button b=(Button) listView.findViewWithTag("btnRemove"+i);
				b.setVisibility(View.VISIBLE);
				show=true;
			}
		}else{
			for (int i = 0; i < items.size(); i++) {
				Button b=(Button) listView.findViewWithTag("btnRemove"+i);
				b.setVisibility(View.GONE);
				show=false;
			}
		}
	}
	
	
	//添加监听
	class IvMinLitenner implements OnClickListener{
		private int position;
		public IvMinLitenner(int position){
			this.position=position;
		}
		
		@Override
		public void onClick(View v) {
			TextView tvCount=(TextView) listView.findViewWithTag("tvCount"+position);
			TextView tvCounts=(TextView) listView.findViewWithTag("tvCounts"+position);
			int number=Integer.parseInt(tvCount.getText().toString().substring(1));
			
			if(number==1){//如果这本书最后一本 点击减号  在购物车中移除这项
				cartPresenter.removeItem(position);
				tvPrice.setText(
						cartPresenter.getSumPrice()+"￥");
				notifyDataSetChanged();
				return;
			}
			number--;
			//通过位置得知哪个书  然后这本书的数量减一
			cartPresenter.removeBookCount(position);
			tvPrice.setText(
					cartPresenter.getSumPrice()+"￥");
			tvCount.setText("x"+number);
			tvCounts.setText(number+"");
		}
		
	}
	//删除监听
	class IvAddLitenner implements OnClickListener{
		private int position;
		public IvAddLitenner(int position){
			this.position=position;
		}

		@Override
		public void onClick(View v) {
			TextView tvCount=(TextView) listView.findViewWithTag("tvCount"+position);
			TextView tvCounts=(TextView) listView.findViewWithTag("tvCounts"+position);
			int number=Integer.parseInt(tvCount.getText().toString().substring(1));
			number++;
			//通过位置得知哪个书  然后这本书的数量加一
			cartPresenter.addaddBookCount(position);
			tvPrice.setText(
					cartPresenter.getSumPrice()+"￥");
			tvCount.setText("x"+number);
			tvCounts.setText(number+"");
		}
		
	}
	
	public void setCartPresenter(ICartPresenter cartPresenter) {
		this.cartPresenter=cartPresenter;
		
	}

	public void setTvPrice(TextView tvPrice) {
		this.tvPrice=tvPrice;
		
	}
	
}
