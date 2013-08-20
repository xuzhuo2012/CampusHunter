package com.xxzzsoftware.campus;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RSSAdapter extends BaseAdapter {
	
	
	private List<RSSItem> rssList = new ArrayList<RSSItem>();
	private Context context;
	private LayoutInflater mInflater;
	
	
	public RSSAdapter(List<RSSItem> rssList, Context context){
		
		this.rssList = rssList;
		this.context = context;
		mInflater = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
		return rssList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = mInflater.inflate(R.layout.position_item, null);
		
		TextView title = (TextView) view.findViewById(R.id.title);
		
		title.setText(rssList.get(position).getTitle());
		
		return view;
	}

}
