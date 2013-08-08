package com.xxzzsoftware.campus;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler extends DefaultHandler {

	RSSFeed rssFeed;
	RSSItem rssItem;
	
	final int RSS_TITLE = 1;
	final int RSS_LINK = 2;
	final int RSS_PUBDATE = 3;
	final int RSS_DESCRIPTION = 4;
	
	int currentstate = 0;

	public RSSHandler() {
	}

	public RSSFeed getFeed() {
		return rssFeed;
	}
	@Override
	public void startDocument() throws SAXException {
		rssFeed = new RSSFeed();
		rssItem = new RSSItem();
	}
	
	@Override
	public void endDocument() throws SAXException {
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if (localName.equals("channel")) {
			currentstate = 0;
			return;
		}
		if (localName.equals("item")) {
			rssItem = new RSSItem();
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
		if (localName.equals("item")) {
			return;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String theString = new String(ch, start, length);
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
