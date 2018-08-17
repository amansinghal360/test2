package com.dp.qa.users;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dp.qa.base.GlobalVars;
import com.dp.qa.base.TestBase;
import com.dp.qa.pages.GmailPage;
import com.dp.qa.pages.HomePage;
import com.dp.qa.pages.NavTabAndUserAssociationsOnMyAccountPage;
import com.dp.qa.pages.RegisterationPage;
import com.dp.qa.pages.SettingPage;
import com.dp.qa.pages.MyAccount.MyAccountPage;

public class CRUDAdminUserSearch extends TestBase {
	protected HomePage homePage;
	protected RegisterationPage registerationPage;
	protected GmailPage gmailPage;
	protected MyAccountPage myAccountPage;
	protected SettingPage settingPage;
	protected NavTabAndUserAssociationsOnMyAccountPage navTabPage;

	@BeforeMethod
	public void setUp() {
		homePage = new HomePage(driver);
		registerationPage = new RegisterationPage(driver);
		myAccountPage = new MyAccountPage(driver);
		settingPage = new SettingPage(driver);
	}

	@Test(description = "How an admin would save,execut,modify and delete the saved search name")
	public void cRUDAdminUserSearch() {
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
		settingPage.isUserNavigateTo("Company Registration (Matches Found)");
		homePage.logOutFromApplication();

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
		myAccountPage.clickOnsaveSearchButton();
		myAccountPage.isMyMessage("Search Name is required to save.");
		myAccountPage.enterSaveText();
		myAccountPage.clickOnsaveSearchButton();
		myAccountPage.isMyMessage("Your changes have been successfully saved.");
		myAccountPage.isSavedUserSearchDisplay(GlobalVars.userSaveSearchName);
		myAccountPage.reEnterSaveText(GlobalVars.userSaveSearchName);
		myAccountPage.clickOnsaveSearchButton();
		myAccountPage.isMyMessage("Search Name must be unique.");
		myAccountPage.clickOnSearchUsersLink();
		settingPage.isUserNavigateTo("Search Users");
		myAccountPage.clickOnAvailableSavedSearchOption();
		myAccountPage.isUserNameDisplay(GlobalVars.registerUserName);
		myAccountPage.enterSaveText();
		myAccountPage.clickOnsaveSearchButton();
		myAccountPage.isMyMessage("Your changes have been successfully saved.");
		myAccountPage.isSavedUserSearchDisplay(GlobalVars.userSaveSearchName);
		myAccountPage.clickOnRemoveThisSearch();
		myAccountPage.isMyMessage("Your saved search has been deleted successfully.");
		myAccountPage.deleteAutomationCreatedUser("Your item has been deleted successfully");
	}
}
