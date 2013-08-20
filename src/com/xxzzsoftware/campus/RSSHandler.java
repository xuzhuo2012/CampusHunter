package com.xxzzsoftware.campus;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class RSSHandler extends DefaultHandler {
	
	private final static String TAG = "RSSHandler";

	private RSSFeed rssFeed;
	private RSSItem rssItem;
	
	// private final int RSS_FEED_TITLE = 1;
	// private final int RSS_FEED_LINK = 2;
	// private final int RSS_FEED_DESCRIPTION = 3;
	
	private final int RSS_TITLE = 11;
	private final int RSS_LINK = 12;
	private final int RSS_PUBDATE = 13;
	private final int RSS_DESCRIPTION = 14;
	
	private int currentstate = 0;

	public RSSHandler() {
	}

	public RSSFeed getFeed() {
		return rssFeed;
	}
	@Override
	public void startDocument() throws SAXException {
		
		Log.d(TAG, "Start Document");
		rssFeed = new RSSFeed();
		rssItem = new RSSItem();
	}
	
	@Override
	public void endDocument() throws SAXException {
		Log.d(TAG, "endDocument");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		
		Log.d(TAG, "startElement");
		
		if (localName.equals("channel")) {
			currentstate = 0;
			return;
		}
		if (localName.equals("title")) {
			currentstate = RSS_TITLE;
			return;
		}
		if (localName.equals("description")) {
			currentstate = RSS_DESCRIPTION;
			return;
		}
		
		if (localName.equals("item")) {
			rssItem = new RSSItem();
			rssFeed.addItem(rssItem);
			return;
		}
		if (localName.equals("link")) {
			currentstate = RSS_LINK;
			return;
		}
		if (localName.equals("pubDate")) {
			currentstate = RSS_PUBDATE;
			return;
		}
		currentstate = 0;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		Log.d(TAG, "endElement");
		if (localName.equals("item")) {
			return;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String theString = new String(ch, start, length);
		Log.d(TAG, "characters = "+theString);
		switch (currentstate) {
		case RSS_TITLE:
			rssItem.setTitle(theString);
			currentstate = 0;
			break;
		case RSS_DESCRIPTION:
			rssItem.setDescription(theString);
			currentstate = 0;
			break;
		case RSS_LINK:
			rssItem.setLink(theString);
			currentstate = 0;
			break;
		case RSS_PUBDATE:
			rssItem.setPubdate(theString);
			currentstate = 0;
			break;
		default:
			return;
		}
	}

}
