package com.dp.qa.common;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dp.qa.base.TestBase;
import com.dp.qa.pages.HomePage;

public class MyLoginTest extends TestBase {
	protected HomePage homePage;

	@BeforeMethod
	public void setUp() {
		homePage = new HomePage(driver);
		
	}
	@Test(description = "Login with User in dPEngine")
	public void myLoginTest() {
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(props.getProperty("Admin_Username"), props.getProperty("Admin_Password"));
		homePage.isUserLoggedIn(props.getProperty("Admin_Username"));
		homePage.logOutFromApplication();
	}
}
