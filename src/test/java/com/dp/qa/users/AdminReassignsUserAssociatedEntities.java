package com.dp.qa.users;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dp.qa.base.GlobalVars;
import com.dp.qa.base.TestBase;
import com.dp.qa.pages.HomePage;
import com.dp.qa.pages.NavTabAndUserAssociationsOnMyAccountPage;
import com.dp.qa.pages.RegisterationPage;
import com.dp.qa.pages.SettingPage;
import com.dp.qa.pages.MyAccount.DevicePage;
import com.dp.qa.pages.MyAccount.MyAccountPage;
import com.dp.qa.pages.ProgramTools.FAQpage;
import com.dp.qa.pages.ProgramTools.ProgramToolsPage;

public class AdminReassignsUserAssociatedEntities extends TestBase {
	protected HomePage homePage;
	protected RegisterationPage registerationPage;
	protected MyAccountPage myAccountPage;
	protected SettingPage settingPage;
	protected NavTabAndUserAssociationsOnMyAccountPage navTabOnMyAccountPage;
	protected DevicePage devicePage;
	protected ProgramToolsPage programToolsPage;
	protected FAQpage fAQPage;

	@BeforeMethod
	public void setUp() {
		homePage = new HomePage(driver);
		myAccountPage = new MyAccountPage(driver);
		settingPage = new SettingPage(driver);
		registerationPage = new RegisterationPage(driver);
		navTabOnMyAccountPage = new NavTabAndUserAssociationsOnMyAccountPage(driver);
		devicePage = new DevicePage(driver);
		fAQPage = new FAQpage(driver);
	}

	@Test(description = "How an admin would mass reassign all entities of a dataset for a user to another user")
	public void adminReassignsUserAssociatedEntities() {
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
		registerationPage.clickOnSelectLink();
		myAccountPage.isUserNavigateTo("Company Association Confirmation");
		homePage.logOutFromApplication();

		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(props.getProperty("Admin_Username"), props.getProperty("Admin_Password"));
		homePage.isUserLoggedIn(props.getProperty("Admin_Username"));
		homePage.waitToLoaderRemove();
		myAccountPage.clickMyAccountAtHeader();
		myAccountPage.clickSearchUserByHoverCompaniesAndUsers();
		myAccountPage.enterKeywords(props.getProperty("Site_Admin_Username"));
		myAccountPage.clickOnSearchButton();
		myAccountPage.isUserNavigateTo("User Search Results");
		myAccountPage.openSearchedResult();
		settingPage.isUserNavigateTo("Edit My Profile");
		myAccountPage.removalAllAddedRole();
		myAccountPage.assignRoleToUser("SITE_ADMIN");
		myAccountPage.clickOnUpdateButton();
		myAccountPage.alertSuccessMessage("Your changes have been saved successfully");
		homePage.logOutFromApplication();

		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(props.getProperty("Site_Admin_Username"), props.getProperty("Admin_Password"));
		homePage.isUserLoggedIn(props.getProperty("Site_Admin_Username"));
		homePage.waitToLoaderRemove();
		myAccountPage.clickMyAccountAtHeader();
		myAccountPage.clickSearchUserByHoverCompaniesAndUsers();
		settingPage.isUserNavigateTo("Search Users");
		myAccountPage.enterKeywords(GlobalVars.registerUserName);
		myAccountPage.clickOnSearchButton();
		myAccountPage.isUserNavigateTo("User Search Results");
		myAccountPage.openSearchedResult();
		settingPage.isUserNavigateTo("Edit My Profile");
		myAccountPage.clickOnCreateForUserLink();
		navTabOnMyAccountPage.isUserNavigateToModal("Create for User");
		navTabOnMyAccountPage.clickOnCreateFAQ();
		settingPage.isUserNavigateTo("Create FAQ");
		fAQPage.enterSummary("FAQ Summary");
		fAQPage.enterDescription("FAQ description");
		fAQPage.selectOne("No");
		devicePage.clickOnSubmitLink();
		myAccountPage.alertSuccessMessage("Your changes have been saved successfully");
		homePage.clickOnHomeLink();
		myAccountPage.clickMyAccountAtHeader();
		myAccountPage.clickSearchUserByHoverCompaniesAndUsers();
		settingPage.isUserNavigateTo("Search Users");
		myAccountPage.enterKeywords(GlobalVars.registerUserName);
		myAccountPage.clickOnSearchButton();
		myAccountPage.isUserNavigateTo("User Search Results");
		myAccountPage.openSearchedResult();
		settingPage.isUserNavigateTo("Edit My Profile");
		navTabOnMyAccountPage.clickOnReassignLink("FAQs");
		myAccountPage.enterKeywords(props.getProperty("Site_Admin_Username"));
		navTabOnMyAccountPage.clickOnSearchButton2();
		homePage.waitToLoaderRemove();
		registerationPage.clickOnSelectLink();
		homePage.waitToLoaderRemove();
		myAccountPage.alertSuccessMessage(
				"1 FAQs records have been associated to the user " + props.getProperty("Site_Admin_Username"));
		myAccountPage.clickOnDeleteBttnAtEditMyProfile();
		homePage.logOutFromApplication();
	}
}
