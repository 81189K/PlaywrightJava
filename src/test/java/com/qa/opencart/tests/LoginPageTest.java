package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

public class LoginPageTest extends BaseTest{
	
	@Test(priority = 1)	//default alphabetical order.
	public void loginPageNavigationTest() {
		loginPage = homePage.navigateToLoginPage(); // Page chaining usage
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
		System.out.println("Successfully navigated to login page, having title: " + actualTitle);
	}
	
	@Test(priority = 2)
	public void forgotPwdLinkExistTest() {		//requires loginPage, dependent on loginPageNavigationTest. Use priority.
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
		System.out.println("Forgot password link exists");
	}
	
	@Test(priority = 3)
	public void validateLogin() {
		Assert.assertTrue( loginPage.login(prop.getProperty("username").trim(), prop.getProperty("password").trim()) );
		System.out.println("Successfully logged into the application.");
	}

}
