package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page;

	// 1. String locators - OR
	private String emailIDInputLocator = "#input-email";
	private String passwordInputLocator = "#input-password";
	private String loginBtnLocator = "//input[@value='Login']";
	private String forgotPwdLinkLocator = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
	private String logoutLinkLocator = "//a[@class='list-group-item' and normalize-space()='Logout']";
	
	//2. Page constructor
	public LoginPage(Page page){
		this.page = page;
	}
	
	//3. page actions
	public String getLoginPageTitle() {
		return page.title();
	}
	
	public boolean isForgotPwdLinkExist() {
		return page.isVisible(forgotPwdLinkLocator);
	}
	
	public boolean login(String username, String password) {
		page.fill(emailIDInputLocator, username);
		page.fill(passwordInputLocator, password);
		page.click(loginBtnLocator);
		if(page.isVisible(logoutLinkLocator)) {
			return true;
		}
		return false;
	}
}
