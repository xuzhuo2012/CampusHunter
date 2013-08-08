package com.xxzzsoftware.campus;

public class RSSItem {
	
	public static String TITLE    = "title";
	public static String LINK     = "link";
	public static String PUBDATE  = "pubdate";
	public static String DESC     = "description";
	
	public String title;
	public String link;
	public String pubdate;
	public String description;

	public RSSItem() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
}
