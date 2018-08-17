package com.dp.qa.users;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dp.qa.base.GlobalVars;
import com.dp.qa.base.TestBase;
import com.dp.qa.pages.GmailPage;
import com.dp.qa.pages.HomePage;
import com.dp.qa.pages.RegisterationPage;
import com.dp.qa.pages.SettingPage;
import com.dp.qa.pages.TitaniumToolsPage;
import com.dp.qa.pages.MyAccount.MyAccountPage;

public class UserAcceptsUpdatedTermsAndConditions extends TestBase {
	protected HomePage homePage;
	protected RegisterationPage registerationPage;
	protected GmailPage gmailPage;
	protected MyAccountPage myAccountPage;
	protected SettingPage settingPage;
	protected TitaniumToolsPage titaniumPage;

	@BeforeMethod
	public void setUp() {
		homePage = new HomePage(driver);
		registerationPage = new RegisterationPage(driver);
		myAccountPage = new MyAccountPage(driver);
		settingPage = new SettingPage(driver);
		titaniumPage = new TitaniumToolsPage(driver);
	}

	@Test(description = "How a user would accept an updated T&C (if T&C is set to force reacceptance).")
	public void updateTermsAndConditionFromCM() {
		titaniumPage.openChannalManager();

		titaniumPage.loginChannelManagerWithCredentails(props.getProperty("Channel_Username"),
				props.getProperty("Channel_Password"));
		titaniumPage.clickOnTitaniumToolLink();

		settingPage.isUserNavigateTo("Search Message Resources");
		titaniumPage.clickSearchTCByHoverTermsAndCondition();

		settingPage.isUserNavigateTo("Search Terms and Conditions");
		titaniumPage.enterTAndCText("Update 6.0 Lorem ipsum dolor sit amet");
		titaniumPage.clickOnSearchBttn();

		myAccountPage.isUserNavigateTo("Terms and Conditions Search Results");
		titaniumPage.clickOnSiteTermsConditionsLink();

		settingPage.isUserNavigateTo("Edit T&C Details");
		titaniumPage.updateAcceptText();
		titaniumPage.pushTheChangesToStage("Your changes have been saved successfully", "Terms and Conditions");
		homePage.opendpEngine(props.getProperty("Base_URL"));
	
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(props.getProperty("Admin_Username"), props.getProperty("Admin_Password"));
		homePage.isUserLoggedIn(props.getProperty("Admin_Username"));

		settingPage.isUserNavigateTo("Terms and Conditions Have Changed");
		registerationPage.acceptTermsAndCondition();
		homePage.clickOnIAgreeBttn();
		homePage.logOutFromApplication();
		
		titaniumPage.openChannalManager();
		titaniumPage.clickOnTitaniumToolLink();

		settingPage.isUserNavigateTo("Search Message Resources");
		titaniumPage.clickSearchTCByHoverTermsAndCondition();

		settingPage.isUserNavigateTo("Search Terms and Conditions");
		titaniumPage.enterTAndCText("Update 6.0 Lorem ipsum dolor sit amet");
		titaniumPage.clickOnSearchBttn();

		myAccountPage.isUserNavigateTo("Terms and Conditions Search Results");
		titaniumPage.clickOnSiteTermsConditionsLink();

		settingPage.isUserNavigateTo("Edit T&C Details");
		titaniumPage.removalTheUpdatedText(GlobalVars.updatedTermsAndConditionText);
		titaniumPage.pushTheChangesToStage("Your changes have been saved successfully", "Terms and Conditions");

		homePage.opendpEngine(props.getProperty("Base_URL"));
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(props.getProperty("Admin_Username"), props.getProperty("Admin_Password"));
		homePage.isUserLoggedIn(props.getProperty("Admin_Username"));

		settingPage.isUserNavigateTo("Terms and Conditions Have Changed");
		registerationPage.acceptTermsAndCondition();
		homePage.clickOnIAgreeBttn();
		homePage.logOutFromApplication();

		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(props.getProperty("Site_Admin_Username"), props.getProperty("Admin_Password"));
		homePage.isUserLoggedIn(props.getProperty("Site_Admin_Username"));
		settingPage.isUserNavigateTo("Terms and Conditions Have Changed");
		registerationPage.acceptTermsAndCondition();
		homePage.clickOnIAgreeBttn();
		homePage.logOutFromApplication();
	}
}
