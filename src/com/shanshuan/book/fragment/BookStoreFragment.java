package com.shanshuan.book.fragment;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.shanshuan.book.R;
import com.shanshuan.book.activity.BookInfoActivity;
import com.shanshuan.book.adapter.BookAdapter;
import com.shanshuan.book.entity.Book;
import com.shanshuan.book.persenter.imp.StorePersenterImp;
import com.shanshuan.book.view.IStoreView;

public class BookStoreFragment extends Fragment implements IStoreView{
	@ViewInject(R.id.gv_bs_recommend)
	private GridView gvRecommend;
	@ViewInject(R.id.gv_bs_hot)
	private GridView gvHot;
	@ViewInject(R.id.gv_bs_new)
	private GridView gvNew;
	@ViewInject(R.id.tv_bs_recommend_more)
	private TextView tvRecommendMore;
	@ViewInject(R.id.tv_bs_hot_more)
	private TextView tvHotMore;
	@ViewInject(R.id.tv_bs_new_more)
	private TextView tvNewMore;
	@ViewInject(R.id.et_bs_top)
	private EditText etSearch;
	private List<Book> recommendBooks;
	private List<Book> hotBooks;
	private List<Book> newBooks;
	private StorePersenterImp sImp;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.bookstore_fragment, null);
		x.view().inject(this, view);
		//Çå³ý×Ô¶¯¾Û½¹
		etSearch.clearFocus();
		sImp=new StorePersenterImp(this, getActivity());
		sImp.getRecommendBook();
		sImp.getHotBook();
		sImp.getNewBook();
		setListenners();
		return view;
	}
	/**
	 * gridView ÉèÖÃ¼àÌýÆ÷
	 */
	private void setListenners() {
		gvRecommend.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Book b=recommendBooks.get(position);
				Intent i1=new Intent(getActivity(), BookInfoActivity.class);
				i1.putExtra("book", b);
				startActivity(i1);
				getActivity().overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
			}
		});
		gvHot.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Book b=hotBooks.get(position);
				 Intent i=new Intent(getActivity(), BookInfoActivity.class);
				 i.putExtra("book", b);
				startActivity(i);
				getActivity().overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
				
			}
		});
		gvNew.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Book b=newBooks.get(position);
				 Intent i2=new Intent(getActivity(), BookInfoActivity.class);
				 i2.putExtra("book", b);
				startActivity(i2);
				getActivity().overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
				
			}
		});
	}

	@Override
	public void showRecommendBook(List<Book> books) {
			this.recommendBooks=books;
			BookAdapter adapter=new BookAdapter(getActivity(), books);
			gvRecommend.setAdapter(adapter);
	}

	@Override
	public void showHotBook(List<Book> books) {
		this.hotBooks=books;
		BookAdapter adapter=new BookAdapter(getActivity(), books);
		gvHot.setAdapter(adapter);
	}

	@Override
	public void showNewBook(List<Book> books) {
		this.newBooks=books;
		BookAdapter adapter=new BookAdapter(getActivity(), books);
		gvNew.setAdapter(adapter);
	}
}
