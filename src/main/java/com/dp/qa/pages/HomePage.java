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

public class HomePage extends TestBase {
	WebDriver driver;
	UIUtil util;
	
	public final static Logger logger = Logger.getLogger(HomePage.class);

	@FindBy(xpath = "//a[contains(text(),' Login')]")
	WebElement loginLink;

	@FindBy(id = "log")
	WebElement log;

	@FindBy(id = "pwd")
	WebElement pwd;

	@FindBy(xpath = "//button[@type='submit'][@value='Login']")
	WebElement loginButton;

	@FindBy(xpath = "//div[@class='top-bar-dark']/descendant::li[1]")
	WebElement loggedInUser;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logout;

	@FindBy(xpath = "//a[text()='Register']")
	WebElement registerLink;
	
	@FindBy(linkText = "Edit My Profile")
	WebElement editMyProfile;
	
	@FindBy(linkText = "Edit My Company")
	WebElement editMyCompany;
	
	@FindBy(xpath = "//a[contains(text(),'Lost Password?')]")
	WebElement lostPassword;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	WebElement submitBttn;
	
	@FindBy(id ="username")
	WebElement username;
	
	@FindBy(id ="resetPwdSubmitBtn")
	WebElement resetPwdSubmitBtn;
	
	@FindBy(xpath = "//form[@id='forgotPasswordForm']/div/div")
	WebElement messageOnSuccessfullyResetEmail;
	
	@FindBy(id ="tcAgreeSubmitBtn")
	WebElement tcAgreeSubmitBtn;
	
	@FindBy(linkText="Home")
	WebElement home;
	
	By loader = By.className("tp-loader spinner0");

	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public HomePage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		util = new UIUtil(driver, testReport);
	}

	public void clickOnLoginFromHeader() {
		waitToLoaderRemove();
		util.getWaitUtil().waitForElementToBeVisible(loginLink);
		util.click(loginLink, "Click on login link from header");
	}

	public void loginWithCredentails(String username, String password) {
		util.fill(log, username, "Enter username into email field");
		util.fill(pwd, password, "Enter password into password field");
		util.click(loginButton, "Click on Login button");
	}

	public void isUserLoggedIn(String user) {
		util.getWaitUtil().waitForElementToBeVisible(loggedInUser);
		String getLoggedInUsername = util.getText(loggedInUser);
		String userFirstName = util.getSubString(getLoggedInUsername, ",", "!");
		util.getVerifyUtils().verifyEquals("Welcome back," + user + "!", "Welcome back," + userFirstName + "!",
				"User should logged into application", true);
	}

	public void logOutFromApplication() {
		util.getWaitUtil().waitForElementToBeEnable(logout);
		util.click(logout, "Logout from the application");
	}

	public void clickOnRegisterLink() {
		util.click(registerLink, "Click on Register link at Home Page");
	}

	public void waitToLoaderRemove() {
		util.getWaitUtil().waitForElementToBeDisappear(loader, 10);
	}
	
	public void clickOnEditMyProfile() {
		util.click(editMyProfile, "Click on Edit My Profile link at Home Page");
	}
	
	public void clickOnEditMyCompany() {
		util.click(editMyCompany, "Click on Edit My Company Link");
	}
	
	public void clickOnLostPassword() {
		util.click(lostPassword, "Click on Lost Password Link");
	}
	
	public void clickOnSubmitBtn() {
		util.click(submitBttn, "Click on Forget Password Submit Buttn");
	}
	
	public void enterUserName(String userName) {
		util.fill(username, userName, "Enter username");
	}
	
	public void clickOnResetPwdSubmitBttn() {
		util.click(resetPwdSubmitBtn, "Click on Reset Your Password Submit Buttn");
	}
	
	public void messageOnSuccessfullResetEmail(String messageText) {
		util.getVerifyUtils().verifyEquals(util.getText(messageOnSuccessfullyResetEmail), messageText,
				"Message '" + messageText + "' should be display", true);
	}
	
	public void clickOnIAgreeBttn() {
		util.click(tcAgreeSubmitBtn, "Click on I Agree button");
	}
	
	public void clickOnHomeLink() {
		util.click(home, "Click on Home Link");
	}
	
	public void opendpEngine(String url) {
		util.launchUrl(url);
	}
	
	
}
