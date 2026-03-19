package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	private Page page;

	// 1. String locators - OR
	private String searchInputLocator = "input[name='search']";
	private String searchButtonLocator = "div#search button";
	private String searchPageHeaderLocator = "div#content h1";
	
	//2. Page constructor
	public HomePage(Page page){
		this.page = page;
	}
	
	//3. page actions
	public String getHomePageTitle() {
		String title = page.title();
		System.out.println("Page title is: " + title);
		return title;
	}
	
	public String getHomePageURL() {
		String url = page.url();
		System.out.println("Page URL is: " + url);
		return url;
	}
	
	public String doSearch(String productName) {
		page.fill(searchInputLocator, productName);
		page.click(searchButtonLocator);
		String header =  page.textContent(searchPageHeaderLocator);
		System.out.println("Search header is: "+ header);
		return header;
	}
}
