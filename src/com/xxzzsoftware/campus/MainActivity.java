package com.xxzzsoftware.campus;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private final static String url = "http://job.hust.edu.cn/show/rss/employment_rss.htm";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.position_list);
		RSSFeed feed = getFeed();
		
		String title = feed.getTitle();
		Log.d("TAG","title");
		Log.d("TAG","title"+title);
		
	}
	
	 public RSSFeed getFeed() {
	        try {
	        	
	        	HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(url);
				HttpResponse response = client.execute(get);
				InputStream is = response.getEntity().getContent();
	            SAXParserFactory factory = SAXParserFactory.newInstance();
	            SAXParser parser = factory.newSAXParser();
	            XMLReader reader = parser.getXMLReader();
	            RSSHandler handler = new RSSHandler();
	            reader.setContentHandler(handler);
	            InputSource source = new InputSource(is);
	            reader.parse(source);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
