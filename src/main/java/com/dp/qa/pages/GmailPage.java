package com.dp.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.dp.qa.base.TestBase;
import com.dp.qa.utils.UIUtil;

public class GmailPage extends TestBase {
	UIUtil util;
	public final static Logger logger = Logger.getLogger(GmailPage.class);

	@FindBy(id = "identifierId")
	WebElement email;

	@FindBy(xpath = "//span[text()='Next']")
	WebElement next;

	@FindBy(xpath = "//input[@type='password']")
	WebElement password;

	@FindBy(xpath = "//div[@role='main']/descendant::tr[@draggable='true']/descendant::span[@class='bog']/descendant::b[text()='Welcome to developerprogram.com - Registration Activation']")
	WebElement emailThread;

	@FindBy(xpath = "(//div[@role='listitem']/descendant::div[contains(@class,'a3s')]/descendant::td/descendant::b[text()='click here'])[last()]")
	WebElement clickHere;

	@FindBy(xpath = "//a[contains(text(),' Login')]")
	WebElement loginLink;

	@FindBy(xpath = "//div[@role='main']/descendant::tr[@draggable='true']/descendant::span[@class='bog'][descendant::text()='Password Reset']")
	WebElement passwordResetEmailThread;

	@FindBy(xpath = "(//div[@role='listitem']/descendant::div[contains(@class,'a3s')]/descendant::td/descendant::a[contains(text(),'here')])[last()]")
	WebElement here;

	@FindBy(xpath = "//div[@role='main']/descendant::tr[@draggable='true']/descendant::span[@class='bog'][descendant::text()='seleniumTestUser1Company has invited you to join their company at developerprogram.com']")
	WebElement companyInvitataion;

	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public GmailPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		util = new UIUtil(driver, testReport);
	}

	public void openGmail() {
		util.launchUrl("https://mail.google.com/mail/u/0/#inbox");
	}

	public void enterGmailUserName(String username) {
		util.fill(email, username, "Enter Gmail UserName at Gmail Login Page");
	}

	public void clickOnNextButton() {
		util.click(next, "Click On Next Button at Gmail Login Page");
	}

	public void enterGmailPassword(String pwd) {
		util.getWaitUtil().waitForElementToBeEnable(password);
		util.click(password, "Click On Next Button at Gmail Login Page");
		util.fill(password, pwd, "Enter Gmail Password at Gmail Login Page");
	}

	public void activateUserRegisteration() {
		util.getWaitUtil().waitForElementToBeVisible(emailThread);
		util.click(emailThread, "Open Require Email Thread");
		util.click(clickHere, "Click On Click Here at Gmail Page");
		util.getActionsUtil().goToSleep(5000);
	}

	public void openPasswordResetEmailThread() {
		util.getWaitUtil().waitForElementToBeVisible(passwordResetEmailThread);
		util.click(passwordResetEmailThread, "Open Require Email Thread");
		util.click(here, "Click On Here to rest your password");
		util.getActionsUtil().goToSleep(3000);
		util.switchtoNewWindow();
	}

	public void acceptCompanyInvitation() {
		util.getWaitUtil().waitForElementToBeVisible(emailThread);
		util.click(emailThread, "Open Require Email Thread");
		util.click(clickHere, "Click On Click Here at Gmail Page");
		util.getActionsUtil().goToSleep(3000);
	}

	public void openCompanyInvitataionEmailThread() {
		util.waitForPageLoad();
		util.getWaitUtil().waitForElementToBeVisible(companyInvitataion);
		util.click(companyInvitataion, "Click on Company Invitation email");

	}

	public void clickOnClickHereLinkUnderEmail() {
		util.getWaitUtil().waitForElementToBeVisible(here);
		util.click(here, "Click on 'Click Here' link");
		util.getActionsUtil().goToSleep(3000);
		util.switchtoNewWindow();
	}

	public void openGmailInToNewTab() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('https://mail.google.com/mail/u/0/#inbox','_blank');");
		util.switchtoNewWindow();
	}

	public void switchTONewWindow() {
		util.switchtoNewWindow();
	}
}
