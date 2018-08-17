package com.dp.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.dp.qa.base.GlobalVars;
import com.dp.qa.base.TestBase;
import com.dp.qa.utils.UIUtil;

public class RegisterationPage extends TestBase {
	UIUtil util; 
	public final static Logger logger = Logger.getLogger(RegisterationPage.class);

	@FindBy(xpath = "//h2")
	WebElement regesterationPageTitle;

	@FindBy(id = "username")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "password2")
	WebElement confirmPassword;

	@FindBy(id = "firstName")
	WebElement firstName;

	@FindBy(id = "lastName")
	WebElement lastName;

	@FindBy(id = "email")
	WebElement emailAddress;

	@FindBy(id = "confirmEmail")
	WebElement confirmEmail;

	@FindBy(id = "forumUsername")
	WebElement forumUsername;

	@FindBy(id = "address")
	WebElement address;

	@FindBy(id = "city")
	WebElement city;

	@FindBy(id = "state")
	WebElement state;

	@FindBy(id = "postalCode")
	WebElement postalCode;

	@FindBy(id = "country")
	WebElement country;

	@FindBy(id = "tcAgree")
	WebElement termsAndCondition;

	@FindBy(id = "save")
	WebElement Submit;

	@FindBy(xpath = "//div[@class='modal-dialog']/descendant::h4[contains(text(),'Your account is not activated.')]")
	WebElement accountNotActivatedModal;

	@FindBy(xpath = "//button[@data-dismiss='modal'][text()='Close']")
	WebElement closeModal;

	@FindBy(linkText = "Select")
	WebElement selectLink;

	@FindBy(id = "companyName")
	WebElement companyName;
	
	
	By activationModal = By
			.xpath("//div[@class='modal-dialog']/descendant::h4[contains(text(),'Your account is not activated.')]");

	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public RegisterationPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		util = new UIUtil(driver, testReport);
	}

	public void isUserNavigateTo(String pageTitle) {
		util.getWaitUtil().waitForElementToBeVisible(regesterationPageTitle);
		util.getVerifyUtils().verifyEquals(util.getText(regesterationPageTitle), pageTitle,
				"User should be '" + pageTitle + "' page", true);
	}

	public void enterUserName() {
		GlobalVars.registerUserName = util.getRandomUtil().getRandomString(10);
		util.fill(username, GlobalVars.registerUserName, "Enter Username at Create Account Page");
	}

	public void enterPassword(String pwd) {
		util.fill(password, pwd, "Enter Password at Create Account Page");
	}

	public void enterConfirmPassword(String confirmPwd) {
		util.fill(confirmPassword, confirmPwd, "Enter Password at Create Account Page");
	}

	public void enterFirstName() {
		util.fill(firstName, GlobalVars.registerUserName, "Enter First Name at Create Account Page");
	}

	public void enterLasttName() {
		util.fill(lastName, "automation", "Enter Last Name at Create Account Page");
	}

	public void enterEmailAddress(String domainName) {
		GlobalVars.userEmail = GlobalVars.registerUserName + "@" + domainName;
		util.fill(emailAddress, GlobalVars.userEmail, "Enter Email Address at Create Account Page");
	}

	public void enterConfirmEmailAddress() {
		util.fill(confirmEmail, GlobalVars.userEmail, "Enter Confirm Email at Create Account Page");
	}

	public void enterForumUserName() {
		util.fill(forumUsername, GlobalVars.registerUserName, "Enter Forum Username at Create Account Page");
	}

	public void enterAddress(String userAddress) {
		util.fill(address, userAddress, "Enter User Address at Create Account Page");
	}

	public void enterCity(String cityName) {
		util.fill(city, cityName, "Enter City at Create Account Page");
	}

	public void selectStateName(String stateName) {
		util.selectByText(state, stateName, "Select State Name from dropdown");
	}

	public void enterPostalCode(String userPostalCode) {
		util.fill(postalCode, userPostalCode, "Enter Postal Code at Create Account Page");
	}

	public void selectCountryName(String countyrName) {
		util.selectByText(country, countyrName, "Select Country Name from dropdown");
	}

	public void acceptTermsAndCondition() {
		util.click(termsAndCondition, "Accept Terms and Condition");
	}

	public void clickOnSubmitButton() {
		util.click(Submit, "Click on Submit Button at Create Account Page");
	}

	public void isAccountNotActivatedModal(String modalText) {
		util.getWaitUtil().waitForElementToBeVisible(accountNotActivatedModal);
		util.getVerifyUtils().verifyEquals(util.getText(accountNotActivatedModal), modalText,
				"'" + modalText + "' modal should appear", true);
	}

	public void noAccountActivatedModal() {
		util.getVerifyUtils().verifyTrue(util.getWaitUtil().waitForElementToBeDisappear(activationModal, 2),
				"Modal 'Account Not Activated Modal' should not display but it is",
				"User successfully activated their account. Modal 'Account Not Activated Modal' would not appear any more.",
				true);
	}

	public void closeActivationModal() {
		util.click(closeModal, "Close Activation Modal");
		util.getWaitUtil().waitForElementNotPresent(closeModal);
	}
	
	public void clickOnSelectLink() {
		util.waitForPageLoad();
		util.getWaitUtil().waitForElementToBeEnable(selectLink);
		util.click(selectLink, "Click on Select link at Company Registration Page");
	}
	
	public void enterCompanyName(String usersCompanyName) {
		util.fill(companyName, usersCompanyName, "Enter Company name");
	}
	
	
	
}
