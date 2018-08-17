package com.dp.qa.users;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dp.qa.base.GlobalVars;
import com.dp.qa.base.TestBase;
import com.dp.qa.pages.HomePage;
import com.dp.qa.pages.RegisterationPage;
import com.dp.qa.pages.SettingPage;
import com.dp.qa.pages.MyAccount.MyAccountPage;

public class UserLogsInviaAccessDenied extends TestBase {
	protected HomePage homePage;
	protected RegisterationPage registerationPage;
	protected MyAccountPage myAccountPage;
	protected SettingPage settingPage;

	@BeforeMethod
	public void setUp() {
		homePage = new HomePage(driver);
		registerationPage = new RegisterationPage(driver);
		myAccountPage = new MyAccountPage(driver);
		settingPage = new SettingPage(driver);
	}

	@Test(description = "Validating login behavior with incorrect credentials")
	public void loginWithBadCredentials() {
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails("InvalidUsername", "InavlidPassword");
		myAccountPage.isUserNavigateTo("Access Denied");
	}

	@Test(description = "Account should locked if user attempts more than 5 invalid login")
	public void attemptingMoreThan5InavlidLogin() {
		homePage.clickOnLoginFromHeader();
		homePage.clickOnRegisterLink();
		registerationPage.isUserNavigateTo("Create Account");
		registerationPage.enterUserName();
		registerationPage.enterPassword(props.getProperty("Admin_Password"));
		registerationPage.enterConfirmPassword(props.getProperty("Admin_Password"));
		registerationPage.enterFirstName();
		registerationPage.enterLasttName();
		registerationPage.enterEmailAddress(props.getProperty("User_EmailDomain"));
		registerationPage.enterConfirmEmailAddress();
		registerationPage.enterForumUserName();
		registerationPage.enterAddress(props.getProperty("User_Address"));
		registerationPage.enterCity(props.getProperty("User_City"));
		registerationPage.selectStateName(props.getProperty("User_State"));
		registerationPage.enterPostalCode(props.getProperty("User_PostalCode"));
		registerationPage.selectCountryName(props.getProperty("User_Country"));
		registerationPage.acceptTermsAndCondition();
		registerationPage.clickOnSubmitButton();
		homePage.logOutFromApplication();

		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(GlobalVars.registerUserName, "IncorrectPassword");
		myAccountPage.isUserNavigateTo("Access Denied");
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(GlobalVars.registerUserName, "IncorrectPassword");
		myAccountPage.isUserNavigateTo("Access Denied");
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(GlobalVars.registerUserName, "IncorrectPassword");
		myAccountPage.isUserNavigateTo("Access Denied");
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(GlobalVars.registerUserName, "IncorrectPassword");
		myAccountPage.isUserNavigateTo("Access Denied");
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(GlobalVars.registerUserName, "IncorrectPassword");
		myAccountPage.isUserNavigateTo("Locked Screen");
	}

	@Test(description = "Validating login behavior for Inactive User", dependsOnMethods = {
			"attemptingMoreThan5InavlidLogin" })
	public void loginWithInactiveUser() {

		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(props.getProperty("Admin_Username"), props.getProperty("Admin_Password"));
		homePage.waitToLoaderRemove();
		homePage.isUserLoggedIn(props.getProperty("Admin_Username"));

		myAccountPage.clickMyAccountAtHeader();
		myAccountPage.clickSearchUserByHoverCompaniesAndUsers();
		settingPage.isUserNavigateTo("Search Users");
		myAccountPage.enterKeywords(GlobalVars.registerUserName);
		myAccountPage.clickOnSearchButton();
		myAccountPage.isUserNavigateTo("User Search Results");
		myAccountPage.openSearchedResult();
		settingPage.isUserNavigateTo("Edit My Profile");
		myAccountPage.clickOnInactiveLink();
		myAccountPage.alertSuccessMessage(
				"The user has been inactivated. The user will not be able to login unless reactivated.");
		homePage.logOutFromApplication();
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(GlobalVars.registerUserName, props.getProperty("Admin_Password"));
		myAccountPage.isUserNavigateTo("Access Denied");
	}

	@Test(description = "Validating login behavior for disabled User", dependsOnMethods = {
			"loginWithInactiveUser" }, alwaysRun = true)
	public void loginWithDisabledUser() {
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(props.getProperty("Admin_Username"), props.getProperty("Admin_Password"));
		homePage.waitToLoaderRemove();
		homePage.isUserLoggedIn(props.getProperty("Admin_Username"));
		myAccountPage.clickMyAccountAtHeader();
		myAccountPage.clickSearchUserByHoverCompaniesAndUsers();
		settingPage.isUserNavigateTo("Search Users");
		myAccountPage.enterKeywords(GlobalVars.registerUserName);
		myAccountPage.clickOnInactiveUserCheckBoxAtSearchUsers();
		myAccountPage.clickOnSearchButton();
		myAccountPage.isUserNavigateTo("User Search Results");
		myAccountPage.clickOnFirstRowLinkUnderAdminColumn();
		myAccountPage.alertSuccessMessage("Your item has been deleted successfully");
		homePage.logOutFromApplication();
	
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(GlobalVars.registerUserName, props.getProperty("Admin_Password"));
		myAccountPage.isUserNavigateTo("Access Denied");
	}
}
