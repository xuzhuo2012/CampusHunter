package com.xxzzsoftware.campus;

import java.util.ArrayList;
import java.util.List;

public class RSSFeed {
	
	private String title;
	private String link;
	private String description;
	private List<RSSItem> itemList;

	public RSSFeed() {
		itemList = new ArrayList<RSSItem>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RSSItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<RSSItem> itemList) {
		this.itemList = itemList;
	}
	
	public void addItem(RSSItem item){
		itemList.add(item);
	}

}
