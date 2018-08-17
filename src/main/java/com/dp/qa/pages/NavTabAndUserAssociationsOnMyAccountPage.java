package com.dp.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.dp.qa.base.TestBase;
import com.dp.qa.utils.UIUtil;

public class NavTabAndUserAssociationsOnMyAccountPage extends TestBase {
	UIUtil util;
	public final static Logger logger = Logger.getLogger(NavTabAndUserAssociationsOnMyAccountPage.class);

	@FindBy(xpath = "//li[@role='invitations']")
	WebElement invitations;
	
	@FindBy(xpath = "//table[@id='pendingInvitations']/descendant::a[contains(text(),'Invite a New User')]")
	WebElement inviteANewUser;
	
	@FindBy(xpath = "//h4[@class='modal-title']")
	WebElement modalHeader;
	
	@FindBy(id = "emailList")
	WebElement emailList;
	
	@FindBy(xpath = " //input[@value='Submit']")
	WebElement submit;
	
	@FindBy(linkText = "Submit Marketplace Solution Industries")
	WebElement submitMarketPlaceSolutionforIndustries;
	
	@FindBy(linkText = "Create Ticket")
	WebElement createTicket;
	
	@FindBy(linkText = "Create Non-Restricted Request")
	WebElement createNonRestrictedRequest;
	
	@FindBy(linkText = "Create Additional Support Hours Invoice")
	WebElement createAdditionalSupportHoursInvoice;
	
	@FindBy(linkText = "Create Additional Support Tickets Invoice")
	WebElement createAdditionalSupportTicketsInvoice;
	
	@FindBy(xpath = "(//a[contains(text(),'General Invoice')][2]")
	WebElement generalInvoiceLink;
	
	@FindBy(linkText = "Create FAQ")
	WebElement createFAQLink;
	
	@FindBy(xpath = "//input[@type='submit'][@value='Search']")
	WebElement search;
	
	@FindBy(xpath = "//a[contains(text(),'General Invoice')]")
	WebElement generalInvoiceLink2;
	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public NavTabAndUserAssociationsOnMyAccountPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		 util = new UIUtil(driver, testReport);
	}

	
	public void clickOnInvitationsLink() {
		util.click(invitations, "Click on Invitations Link");
	}
	
	public void  clickOnInviteANewUserLink() {
		util.click(inviteANewUser, "Click on  Invite a New User Link");
	}

	public void isUserNavigateToModal(String modalName) {
		util.getWaitUtil().waitForElementToBeVisible(modalHeader);
		util.getVerifyUtils().verifyEquals(util.getText(modalHeader), modalName,
				"User should be '" + modalName + "' page", true);
	}
	
	public void enterUserEmailAddressr(String emailAddress) {
		util.fill(emailList, emailAddress, "Enter Email Address");
	}
	
	public void  clickOnSubmitInput() {
		util.click(submit, "Click on  Submit");
	}
	
	public void clickOnSubmitMarketPlaceSolutionIndustriesLink() {
		util.click(submitMarketPlaceSolutionforIndustries, "Click on Submit Marketplace Entry for Publication link ");
	}

	public void isReassignLink(String userAssociationsName) {
		By linkName = By.xpath("//td[descendant::a[contains(text(),'"+userAssociationsName+"')]]/following::a[contains(text(),'Reassign')]");
		util.getVerifyUtils().verifyTrue(util.getWaitUtil().waitForElementToBePresent(linkName, 1),
				"Reassign link w.r.t '" + userAssociationsName + "' should be present at page",
				"Reassign link w.r.t '" + userAssociationsName + "' should be present at page", true);
	}
	
	public void clickOnCreateTicket() {
		util.click(createTicket, "Click on Create Public Ticket link");
	}
	
	public void clickOnCreateNonRestrictedRequest() {
		util.click(createNonRestrictedRequest, "Click on Create Non-Restricted Request link");
	}
	
	public void clickOnAdditionalSupportHoursInvoice() {
		util.click(createAdditionalSupportHoursInvoice, "Click on Create Additional Support Hours Invoice link");
	}
	
	public void clickOnAdditionalSupportTicketsInvoice() {
		util.click(createAdditionalSupportTicketsInvoice, "Click on CCreate Additional Support Tickets Invoice link");
	}
	
	public void clickOnGeneralInvoiceLink() {
		util.click(generalInvoiceLink, "Click on General Invoice link");
	}
	
	public void clickOnCreateFAQ() {
		util.click(createFAQLink, "Click on Create FAQ link ");
	}
	
	public void clickOnReassignLink(String userAssociationsName) {
	By linkName = By.xpath("//td[descendant::a[contains(text(),'"+userAssociationsName+"')]]/following::a[contains(text(),'Reassign')]");
	util.clickOn(linkName, "Click on Reassign link for" +userAssociationsName);
	}
	
	public void clickOnSearchButton2() {
		util.getWaitUtil().waitForElementToBeEnable(search);
		util.click(search, "Click on Search Button");
	}
	
	public void clickOnGeneralInvoiceLink2() {
		util.click(generalInvoiceLink2, "Click on General Invoice link");
	}
}
