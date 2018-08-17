package com.dp.qa.users;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dp.qa.base.GlobalVars;
import com.dp.qa.base.TestBase;
import com.dp.qa.pages.GmailPage;
import com.dp.qa.pages.HomePage;
import com.dp.qa.pages.RegisterationPage;
import com.dp.qa.pages.SettingPage;
import com.dp.qa.pages.MyAccount.MyAccountPage;

public class UserForgetsPassword extends TestBase {
	protected HomePage homePage;
	protected RegisterationPage registerationPage;
	protected GmailPage gmailPage;
	protected MyAccountPage myAccountPage;
	protected SettingPage settingPage;

	@BeforeMethod
	public void setUp() {
		homePage = new HomePage(driver);
		registerationPage = new RegisterationPage(driver);
		myAccountPage = new MyAccountPage(driver);
		settingPage = new SettingPage(driver);
		gmailPage = new GmailPage(driver);
	}

	@Test(description = "User should request the system to resend An email message containing a link to reset your password")
	public void sendingPasswordResetLinkOnResgisteredEmail() {
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
		homePage.clickOnLostPassword();
		settingPage.isUserNavigateTo("Lost Your Password?");
		homePage.enterUserName("InvalidUserName");
		homePage.clickOnSubmitBtn();
		myAccountPage.alertSuccessMessage("The username you entered was not found in our records.");
		homePage.enterUserName(GlobalVars.registerUserName);
		homePage.clickOnSubmitBtn();
		homePage.messageOnSuccessfullResetEmail(
				"An email message containing a link to reset your password has been sent to your registered email address.");
	}

	@Test(description = "User reset password using password reset email", dependsOnMethods = { "sendingPasswordResetLinkOnResgisteredEmail" })
	public void resetPasswordUsingPasswordSentEmail() {
		gmailPage.openGmail();
		gmailPage.enterGmailUserName(props.getProperty("Gmail_Email"));
		gmailPage.clickOnNextButton();
		gmailPage.enterGmailPassword(props.getProperty("Gmail_Password"));
		gmailPage.clickOnNextButton();
		gmailPage.openPasswordResetEmailThread();
		settingPage.isUserNavigateTo("Reset Your Password");
		myAccountPage.updatePassword(props.getProperty("Admin_Password"));
		homePage.clickOnResetPwdSubmitBttn();
		myAccountPage.alertSuccessMessage("New password cannot be the same as the past {3} passwords.");
		myAccountPage.updatePassword(props.getProperty("Confirm_Password"));
		homePage.clickOnResetPwdSubmitBttn();
		homePage.isUserLoggedIn(GlobalVars.registerUserName);
		homePage.logOutFromApplication();

	}

	@Test(description = "Validating message <Your account is currently inactive and a password reset cannot be completed.> for inactive user", dependsOnMethods = {
			"resetPasswordUsingPasswordSentEmail" })
	public void verifyLosttPwdFunctionalityToInactiveUser() {
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
		homePage.clickOnLostPassword();
		settingPage.isUserNavigateTo("Lost Your Password?");
		homePage.enterUserName(GlobalVars.registerUserName);
		homePage.clickOnSubmitBtn();
		myAccountPage
				.alertSuccessMessage("Your account is currently inactive and a password reset cannot be completed.");
	}

	@Test(description = "Validating message <Your account is currently disabled and a password reset cannot be completed.> for disable user", dependsOnMethods = {
			"verifyLosttPwdFunctionalityToInactiveUser" })
	public void verifyLosttPwdFunctionalityToDisableUser() {
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
		homePage.clickOnLostPassword();
		settingPage.isUserNavigateTo("Lost Your Password?");
		homePage.enterUserName(GlobalVars.registerUserName);
		homePage.clickOnSubmitBtn();
		myAccountPage
				.alertSuccessMessage("Your account is currently disabled and a password reset cannot be completed.");
	}
}
