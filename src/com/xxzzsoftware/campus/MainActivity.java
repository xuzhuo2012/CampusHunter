package com.xxzzsoftware.campus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private final static String TAG = "MainActivity";
	private final static String url = "http://job.hust.edu.cn/show/rss/employment_rss.htm";
	public final static String RSS_ITEM = "KEY_RSS_ITEM";
	
	private Context context;
	private RSSFeed feed;
	private List<RSSItem> itemList = new ArrayList<RSSItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.position_list);
		context = this;
		final ListView posList = (ListView) findViewById(R.id.position_list);
		
		posList.post(new Runnable() {
			@Override
			public void run() {
				feed = getFeed();
				itemList = feed.getItemList();
				Log.d(TAG, "Size of Item = " + itemList.size());
				posList.setAdapter(new RSSAdapter(itemList, context));
			}
		});
		
		
		posList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				RSSItem rssItem = itemList.get(position);
				Intent intent = new Intent(context,DetailActivity.class);
				intent.putExtra(RSS_ITEM, rssItem);
				context.startActivity(intent);
			}
		});
	}
	
	public InputSource getIsNet() {
		HttpClient client = new DefaultHttpClient();
		try {
			HttpGet get = new HttpGet(url);
			HttpResponse response = client.execute(get);
			InputStream is = response.getEntity().getContent();

			return new InputSource(is);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			client = null;
		}

		return null;
	}

	public InputSource getIsFile() {
		try {
			InputStream is = this.getAssets().open("employment_rss.xml");
			return new InputSource(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public RSSFeed getFeed() {
		try {
			// InputSource is = getIsNet();
			InputSource is = getIsFile();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			RSSHandler handler = new RSSHandler();
			reader.setContentHandler(handler);
			reader.parse(is);

			return handler.getFeed();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String streamToString(InputStream is) {

		StringBuffer sb = new StringBuffer();
		try {
			byte[] b = new byte[1024];
			int c = 0;
			while ((c = is.read(b)) != -1) {
				sb.append(new String(b, 0, c));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
