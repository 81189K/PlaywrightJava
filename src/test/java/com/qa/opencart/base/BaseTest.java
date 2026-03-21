package com.qa.opencart.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	
	PlaywrightFactory playwrightFactory;
	Page page;
	protected Properties prop;
	
	protected HomePage homePage;
	protected LoginPage loginPage;
	
	@BeforeTest
	public void setup() {
		playwrightFactory = new PlaywrightFactory();
		prop = playwrightFactory.initProperties();
		page = playwrightFactory.initBrowser(prop);
		homePage = new HomePage(page);
		loginPage = new LoginPage(page);
	}
	
	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
