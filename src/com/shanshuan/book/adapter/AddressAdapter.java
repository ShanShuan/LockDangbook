package com.shanshuan.book.adapter;

import java.util.List;

import com.shanshuan.book.R;
import com.shanshuan.book.entity.Address;
import com.shanshuan.book.persenter.IAddressPersenter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 *Created by Zifeng Wang 2016-6-28ÏÂÎç5:05:12
 */
public class AddressAdapter extends BaseAdapter{
	private Context context;
	private List<Address> adds;
	private IAddressPersenter persenter;
	public AddressAdapter(Context context,List<Address> adds,IAddressPersenter persenter){
		this.context=context;
		this.adds=adds;
		this.persenter=persenter;
	}

	@Override
	public int getCount() {
		return adds.size();
	}

	@Override
	public Address getItem(int position) {
		return adds.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewHolder holder=null;
		Address add=adds.get(position);
		if(convertView==null){
			convertView=View.inflate(context, R.layout.item_lv_address, null);
			holder=new viewHolder();
			holder.tvNickName=(TextView) convertView.findViewById(R.id.tvReceiveName);
			holder.tvPhone=(TextView) convertView.findViewById(R.id.tvPhone);
			holder.tvUserAddress=(TextView) convertView.findViewById(R.id.tvReceiveAddress);
			holder.rbDefault=(RadioButton) convertView.findViewById(R.id.radioDefault);
			convertView.setTag(holder);
		}
		holder=(viewHolder) convertView.getTag();
		holder.tvNickName.setText(add.getReceiveName());
		holder.tvPhone.setText(add.getPhone());
		holder.tvUserAddress.setText(add.getFull_address());
		holder.rbDefault.setChecked(add.getIs_default()==1);
		holder.rbDefault.setOnCheckedChangeListener(new MOnCheckedChangeListenner(position));
		return convertView;
	}
	class MOnCheckedChangeListenner implements OnCheckedChangeListener{
		private int position;
		public MOnCheckedChangeListenner(int position){
			this.position=position;
		}
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			Address a=adds.get(position);
			int id=a.getId();
			persenter.setDefaultAddress(id);
		}
		
	}
	class viewHolder{
		TextView tvNickName;
		TextView tvPhone;
		TextView tvUserAddress;
		RadioButton rbDefault;
		
	}
}
