package com.example.myappweixin;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{

	private int resourceID;
	private List<DialogBean> data;
	private Context context;
	private LayoutInflater mInflater;
	public MyAdapter(Context context,List<DialogBean> oject)
	{
		
		this.data = oject;
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		DialogBean dialogBean = (DialogBean) getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView == null)
		{
			viewHolder = new ViewHolder();
			view = mInflater.inflate(R.layout.list_item, null);
			viewHolder.image = (ImageView) view.findViewById(R.id.sculpture);
			viewHolder.lasttext = (TextView) view.findViewById(R.id.lasttext);
			viewHolder.time = (TextView) view.findViewById(R.id.time);
			viewHolder.username = (TextView) view.findViewById(R.id.username);
			view.setTag(viewHolder);
		}
		else
		{
			view  = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.image.setImageResource(dialogBean.getImageId());
		viewHolder.lasttext.setText(dialogBean.getLasttext());
		viewHolder.time.setText(dialogBean.getTime());
		viewHolder.username.setText(dialogBean.getUsername());
		return view;
	}

}
