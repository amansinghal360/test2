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

public class RegisterNewCompanyFromCompanyMatchesPage extends TestBase {
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

	@Test(description = "How a user would register a new company from the Company Matches page ")
	public void registerNewCompanyFromCompanyMatchesPage() {
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
		companyPage.clickOnCreateNewCompanyLink();
		registerationPage.enterCompanyName(GlobalVars.registerUserName);
		companyPage.enterCompanyAddress(props.getProperty("User_Address"));
		registerationPage.enterCity(props.getProperty("User_City"));
		registerationPage.selectStateName(props.getProperty("User_State"));
		registerationPage.selectCountryName(props.getProperty("User_Country"));
		companyPage.enterCompanyPhone(props.getProperty("MainPhone"));
		companyPage.enterCompanyURL(GlobalVars.registerUserName);
		registerationPage.acceptTermsAndCondition();
		programToolPage.clickOnSubmitInputType();
		homePage.clickOnEditMyCompany();
		settingPage.isUserNavigateTo("Edit Company");
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
