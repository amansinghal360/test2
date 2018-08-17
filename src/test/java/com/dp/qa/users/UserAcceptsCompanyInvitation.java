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

public class UserAcceptsCompanyInvitation extends TestBase {
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
		navTabPage = new NavTabAndUserAssociationsOnMyAccountPage(driver);
		gmailPage = new GmailPage(driver);
	}

	@Test(description = "How an invited user can accept an email invitation and complete site registration ")
	public void userAcceptCompanyInvitationEmail() {
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
		homePage.clickOnEditMyCompany();
		settingPage.isUserNavigateTo("Edit Company");
		navTabPage.clickOnInvitationsLink();
		navTabPage.clickOnInviteANewUserLink();
		navTabPage.isUserNavigateToModal("Invite Users");
		navTabPage.enterUserEmailAddressr(GlobalVars.userEmail);
		navTabPage.clickOnSubmitInput();
		myAccountPage.alertSuccessMessage("The following emails have been sent invitations: " + GlobalVars.userEmail);
		homePage.logOutFromApplication();

		gmailPage.openGmailInToNewTab();
		gmailPage.enterGmailUserName(props.getProperty("Gmail_Email"));
		gmailPage.clickOnNextButton();
		gmailPage.enterGmailPassword(props.getProperty("Gmail_Password"));
		gmailPage.clickOnNextButton();
		gmailPage.openCompanyInvitataionEmailThread();
		gmailPage.clickOnClickHereLinkUnderEmail();
		myAccountPage.isUserNavigateTo("Company Association Confirmation");

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
		homePage.logOutFromApplication();
	}
}
