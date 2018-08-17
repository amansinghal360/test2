package com.dp.qa.companies;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dp.qa.base.GlobalVars;
import com.dp.qa.base.TestBase;
import com.dp.qa.pages.HomePage;
import com.dp.qa.pages.RegisterationPage;
import com.dp.qa.pages.SettingPage;
import com.dp.qa.pages.MyAccount.CompanyPage;
import com.dp.qa.pages.MyAccount.MyAccountPage;
import com.dp.qa.pages.ProgramTools.ProgramToolsPage;

public class UserRegistersNewCompany extends TestBase {
	protected HomePage homePage;
	protected RegisterationPage registerationPage;
	protected MyAccountPage myAccountPage;
	protected SettingPage settingPage;
	protected CompanyPage companyPage;
	protected ProgramToolsPage programToolPage;

	@BeforeMethod
	public void setUp() {
		homePage = new HomePage(driver);
		registerationPage = new RegisterationPage(driver);
		myAccountPage = new MyAccountPage(driver);
		settingPage = new SettingPage(driver);
		companyPage = new CompanyPage(driver);
		programToolPage = new ProgramToolsPage(driver);
	}

	@Test(description = "How a user would register a new company where the user has already specified their company name during user registration")
	public void userRegistersNewCompany() {
		homePage.clickOnLoginFromHeader();
		homePage.clickOnRegisterLink();
		registerationPage.isUserNavigateTo("Create Account");
		registerationPage.enterUserName();
		registerationPage.enterPassword(props.getProperty("Admin_Password"));
		registerationPage.enterConfirmPassword(props.getProperty("Admin_Password"));
		registerationPage.enterFirstName();
		registerationPage.enterLasttName();
		registerationPage.enterEmailAddress(GlobalVars.registerUserName + ".com");
		registerationPage.enterConfirmEmailAddress();
		registerationPage.enterForumUserName();
		registerationPage.enterAddress(props.getProperty("User_Address"));
		registerationPage.enterCity(props.getProperty("User_City"));
		registerationPage.selectStateName(props.getProperty("User_State"));
		registerationPage.enterPostalCode(props.getProperty("User_PostalCode"));
		registerationPage.selectCountryName(props.getProperty("User_Country"));
		registerationPage.enterCompanyName(GlobalVars.registerUserName);
		registerationPage.acceptTermsAndCondition();
		registerationPage.clickOnSubmitButton();
		settingPage.isUserNavigateTo("Company Registration");
		companyPage.enterCompanyAddress(props.getProperty("User_Address"));
		registerationPage.enterCity(props.getProperty("User_City"));
		registerationPage.selectStateName(props.getProperty("User_State"));
		registerationPage.selectCountryName(props.getProperty("User_Country"));
		companyPage.enterCompanyPhone(props.getProperty("MainPhone"));
		companyPage.enterCompanyURL(GlobalVars.registerUserName);
		companyPage.selectCompanySize("51-100");
		companyPage.selectProgram("Program 3");
		companyPage.selectMultiSelectBoxLabel("Test1");
		registerationPage.acceptTermsAndCondition();
		programToolPage.clickOnSubmitInputType();
		homePage.clickOnEditMyCompany();
		settingPage.isUserNavigateTo("Edit Company");
		registerationPage.enterCompanyName("updated " + GlobalVars.registerUserName);
		myAccountPage.clickOnUpdateButton();
		myAccountPage.alertSuccessMessage("Your changes have been saved successfully");
		companyPage.clickOnGrantSupportAccessLink();
		myAccountPage.alertSuccessMessage("Support Access has been granted for the user.");
		companyPage.clickOnGrantTestingAccessLink();
		myAccountPage.alertSuccessMessage("Testing Access has been granted for the user.");
		companyPage.clickOnRemoveSupportAccessLink();
		myAccountPage.alertSuccessMessage("Support Access has been removed for the user.");
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
		myAccountPage.clickOnFirstRowLinkUnderAdminColumn();
		myAccountPage.alertSuccessMessage("Your item has been deleted successfully");
		homePage.clickOnHomeLink();
		myAccountPage.clickMyAccountAtHeader();
		myAccountPage.clickSearchUCompanyByHoverCompaniesAndUsers();
		settingPage.isUserNavigateTo("Search Companies");
		myAccountPage.enterKeywords(GlobalVars.registerUserName);
		myAccountPage.clickOnSearchButton();
		settingPage.isUserNavigateTo("Company Search Results");
		companyPage.clickOnDeleteLinkAndAcceptPopUpAtSearchResult();
		myAccountPage.alertSuccessMessage("Your item has been deleted successfully");
		homePage.logOutFromApplication();
	}
}
