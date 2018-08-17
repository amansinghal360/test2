package com.dp.qa.invoice;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dp.qa.base.GlobalVars;
import com.dp.qa.base.TestBase;
import com.dp.qa.pages.HomePage;
import com.dp.qa.pages.NavTabAndUserAssociationsOnMyAccountPage;
import com.dp.qa.pages.RegisterationPage;
import com.dp.qa.pages.SettingPage;
import com.dp.qa.pages.MyAccount.InvoicesPage;
import com.dp.qa.pages.MyAccount.MyAccountPage;

public class GeneralInvoice extends TestBase {
	protected HomePage homePage;
	protected RegisterationPage registerationPage;
	protected MyAccountPage myAccountPage;
	protected SettingPage settingPage;
	protected NavTabAndUserAssociationsOnMyAccountPage navTabOnMyAccountPage;
	protected InvoicesPage invoicePage;

	@BeforeMethod
	public void setUp() {
		homePage = new HomePage(driver);
		registerationPage = new RegisterationPage(driver);
		myAccountPage = new MyAccountPage(driver);
		settingPage = new SettingPage(driver);
		navTabOnMyAccountPage = new NavTabAndUserAssociationsOnMyAccountPage(driver);
		invoicePage = new InvoicesPage(driver);
	}

	@Test(description = "Admin user search General Invoices")
	public void generalInvoiceFunctionality() {
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
		homePage.isUserLoggedIn(props.getProperty("Admin_Username"));
		homePage.waitToLoaderRemove();
		myAccountPage.clickMyAccountAtHeader();
		myAccountPage.clickSearchUserByHoverCompaniesAndUsers();
		myAccountPage.enterKeywords(GlobalVars.registerUserName);
		myAccountPage.clickOnSearchButton();
		myAccountPage.isUserNavigateTo("User Search Results");
		myAccountPage.openSearchedResult();
		settingPage.isUserNavigateTo("Edit My Profile");
		myAccountPage.assignRoleToUser("BDM");
		myAccountPage.clickOnUpdateButton();
		myAccountPage.alertSuccessMessage("Your changes have been saved successfully");
		homePage.logOutFromApplication();
		
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(GlobalVars.registerUserName, props.getProperty("Admin_Password"));
		homePage.isUserLoggedIn(GlobalVars.registerUserName);
		homePage.waitToLoaderRemove();
		homePage.clickOnEditMyProfile();
		myAccountPage.clickOnCreateForUserLink();
		navTabOnMyAccountPage.isUserNavigateToModal("Create for User");
		navTabOnMyAccountPage.clickOnGeneralInvoiceLink2();
		settingPage.isUserNavigateTo("General Invoice");
		invoicePage.enterStartEndDate("9", "25");
		invoicePage.enterInvoiceDescriptionDetails(props.getProperty("InvoiceDescription"));
		invoicePage.enterQuotedPrice(props.getProperty("QuotedPRice"));
		invoicePage.enterAddress(props.getProperty("User_Address"));
		invoicePage.enterCityName(props.getProperty("User_City"));
		invoicePage.selectStateName(props.getProperty("StateName"));
		invoicePage.enterPostalCode(props.getProperty("User_PostalCode"));
		invoicePage.selectCountry(props.getProperty("User_Country"));
		invoicePage.enterMainPhone(props.getProperty("MainPhone"));
		invoicePage.enterBillingContactName(props.getProperty("BillingContactName"));
		invoicePage.enterBillingContactPhone(props.getProperty("BillingContactPhone"));
		invoicePage.enterbillingContactEmail(props.getProperty("BillingContactEmail"));
		invoicePage.clickOnCreateInvoice();
		myAccountPage.alertSuccessMessage("success!");
		invoicePage.getInvoiceNumber();
		settingPage.isUserNavigateTo("Generic Invoice");
		homePage.logOutFromApplication();
		
		homePage.clickOnLoginFromHeader();
		homePage.loginWithCredentails(props.getProperty("Admin_Username"), props.getProperty("Admin_Password"));
		homePage.isUserLoggedIn(props.getProperty("Admin_Username"));
		homePage.waitToLoaderRemove();
		myAccountPage.clickMyAccountAtHeader();
		invoicePage.clickSearchInvoiceByHoverInvoices();
		settingPage.isUserNavigateTo("Search Invoices");
		invoicePage.enterInvoiceId(GlobalVars.invoiceNumber);
		navTabOnMyAccountPage.clickOnSearchButton2();
		settingPage.isUserNavigateTo("Invoice Search Results");
		myAccountPage.openSearchedResult();
		settingPage.isUserNavigateTo("Membership Invoice");
		homePage.clickOnHomeLink();
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
