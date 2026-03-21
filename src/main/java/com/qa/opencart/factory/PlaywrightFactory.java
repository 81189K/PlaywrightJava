package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	
	Playwright playwright;
	Browser browser;
	BrowserContext bowserContext;
	Page page;
	Properties prop;
	
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	
	
	public static Playwright getTLPlaywright() {
		return tlPlaywright.get();
	}
	
	public static Browser getTLBrowser() {
		return tlBrowser.get();
	}
	
	public static BrowserContext getTLBrowserContext() {
		return tlBrowserContext.get();
	}
	
	public static Page getTLPage() {
		return tlPage.get();
	}
	
	
	
	public Page initBrowser(Properties prop) {
		
		String browserName= prop.getProperty("browser").trim();
		System.out.println("Browser name is : " + browserName);
		
//		playwright = Playwright.create();
		tlPlaywright.set(Playwright.create());
		
		switch (browserName.toLowerCase()) {
		case "chromium": {
//			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getTLPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		}
		case "firefox": {
//			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getTLPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		}
		case "safari": {
//			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getTLPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		}
		case "chrome": {
//			browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			tlBrowser.set(getTLPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		}
		default:
			System.out.println("Please pass the right browser name,...");
			break;
		}
		
//		bowserContext = browser.newContext();
		tlBrowserContext.set(getTLBrowser().newContext());
//		page = bowserContext.newPage();
		tlPage.set(getTLBrowserContext().newPage());
//		page.navigate(prop.getProperty("url").trim());
		getTLPage().navigate(prop.getProperty("url").trim());
		return getTLPage();
	}
	
	/***
	 * Initialize the properties from config.properties file.
	 * @return 
	 */
	public Properties initProperties() {
		try {
			FileInputStream fip = new FileInputStream("D:\\Workspace\\PlaywrightJava\\src\\test\\resources\\config\\config.properties");
			prop = new Properties();
			prop.load(fip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

}
