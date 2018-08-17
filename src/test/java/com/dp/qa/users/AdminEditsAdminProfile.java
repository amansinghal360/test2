package com.dp.qa.users;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dp.qa.base.TestBase;
import com.dp.qa.pages.HomePage;
import com.dp.qa.pages.RegisterationPage;
import com.dp.qa.pages.SettingPage;
import com.dp.qa.pages.MyAccount.MyAccountPage;

public class AdminEditsAdminProfile extends TestBase {
	protected HomePage homePage;
	protected RegisterationPage registerationPage;
	protected MyAccountPage myAccountPage;
	protected SettingPage settingPage;

	@BeforeMethod
	public void setUp() {
		homePage = new HomePage(driver);
		myAccountPage = new MyAccountPage(driver);
		settingPage = new SettingPage(driver);
	}

	@Test(description = "How an admin user without admin grant permissions would edit a user profile for an admin user")
	public void adminEditsAdminProfile() {
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
		myAccountPage.assignRoleToUser("BDM");
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
		myAccountPage.enterKeywords(props.getProperty("Admin_Username"));
		myAccountPage.clickOnSearchButton();
		myAccountPage.isUserNavigateTo("User Search Results");
		myAccountPage.openSearchedResult();
		settingPage.isUserNavigateTo("Edit My Profile");
		myAccountPage.isRoleFieldReadOnly();
		myAccountPage.forumAdminLinkNotAvailable();
		homePage.logOutFromApplication();
	}
}
