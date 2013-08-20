package com.xxzzsoftware.campus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	public final static String TAG = "DetailActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pos_detail);
		
		Intent intent = this.getIntent();
		RSSItem rssItem = (RSSItem)intent.getExtras().getSerializable(MainActivity.RSS_ITEM);
		
		TextView title = (TextView)findViewById(R.id.title);
		title.setText(rssItem.getTitle());
		TextView pubDate = (TextView)findViewById(R.id.pub_date);
		pubDate.setText(rssItem.getPubdate());
		TextView link = (TextView)findViewById(R.id.link);
		link.setText(rssItem.getLink());
		TextView desc = (TextView)findViewById(R.id.description);
		desc.setText(rssItem.getDescription());
	}
}
