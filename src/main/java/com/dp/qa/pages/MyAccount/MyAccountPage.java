package com.dp.qa.pages.MyAccount;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.dp.qa.base.GlobalVars;
import com.dp.qa.base.TestBase;
import com.dp.qa.utils.UIUtil;
import com.relevantcodes.extentreports.LogStatus;

public class MyAccountPage extends TestBase {
	UIUtil util;
	public final static Logger logger = Logger.getLogger(MyAccountPage.class);

	@FindBy(xpath = "//ul[contains(@class,'navbar-right')]/descendant::a[contains(text(),'My Account')]")
	WebElement myAccount;

	@FindBy(xpath = "//li[@class='dropdown open']/descendant::a[contains(text(),'Companies & Users')]")
	WebElement companiesUsers;

	@FindBy(xpath = "//li[@class='dropdown open']/descendant::a[contains(text(),'Search Users')]")
	WebElement searchUsers;

	@FindBy(xpath = "//input[contains(@id,'keyword')]")
	WebElement keywordTextField;

	@FindBy(xpath = "(//input[@type='submit'][@name='search'])[2]")
	WebElement searchButton;

	@FindBy(xpath = "//h1")
	WebElement userSearchResultsTitle;

	@FindBys(@FindBy(xpath = "//td[contains(@class,'last-column')]//a[contains(text(),'Delete')]"))
	List<WebElement> deleteLink;

	@FindBy(xpath = "//td[contains(@class,'last-column')]//a")
	WebElement clickLinkUnderAdminColumn;

	@FindBy(xpath = "//div[@role='alert']/descendant::li")
	WebElement messageTextAfterDeletion;

	@FindBy(xpath = "//td[contains(@class,'footable-visible')]")
	WebElement noSearchFound;

	@FindBy(xpath = "//td[contains(@class,'footable-first-column')]/a")
	WebElement searchedUserLink;

	@FindBy(xpath = "//ul[contains(@class,'nav-pills')]/descendant::a[contains(text(),'Inactivate')]")
	WebElement inactiveLink;

	@FindBy(xpath = "//a[contains(text(),'Reactivate User')]")
	WebElement reactivateUserLink;

	@FindBy(id = "inactive")
	WebElement inactiveCheckbox;

	@FindBy(id = "deleted")
	WebElement deletedCheckbox;

	@FindBy(xpath = "//input[@value='Update']")
	WebElement updateButton;

	@FindBy(className = "chosen-search-input")
	WebElement assignRole;

	@FindBy(xpath = "//li[contains(@class,'active-result')]")
	WebElement chooseRoleUnderAutoSuggestionList;

	@FindBy(xpath = "//input[@name='engineerCalendarColor']/following::div[contains(@class,'sp-replacer')]")
	WebElement engineerCalendarColor;

	@FindBy(className = "sp-input")
	WebElement calendarColorTextField;

	@FindBy(xpath = "//a[contains(text(),'Disassociate')]")
	WebElement disassociateLink;

	@FindBy(linkText = "Update Your Password")
	WebElement updateYourPassword;

	@FindBy(id = "oldPassword")
	WebElement currentPassword;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "password2")
	WebElement confirmPassword;

	@FindBy(id = "updatePasswordBtn")
	WebElement updatePasswordButton;

	@FindBy(linkText = "Resend Activation Email")
	WebElement resendActivationLink;

	@FindBy(linkText = "Invitations")
	WebElement invitations;

	@FindBy(linkText = " Invite a New User")
	WebElement inviteANewUser;

	@FindBy(id = "name")
	WebElement rememberSearchAs;

	@FindBy(id = "saveSearchSave")
	WebElement saveSearch;

	@FindBy(id = "myMsg")
	WebElement myMsg;

	@FindBy(xpath = "//ol[@class='breadcrumb']/descendant::a[contains(text(),'Search Users')]")
	WebElement searchUserLink;

	@FindBy(xpath = "//div[@id='savedSearches']/descendant::a[last()]")
	WebElement userSavedSearch;

	@FindBy(id = "saveSearchDelete")
	WebElement removeThisSearch;

	@FindBy(xpath = "//input[@type='submit'][@value='Delete']")
	WebElement deleteBttn;

	@FindBys(@FindBy(xpath = "//li[@class='search-choice']/descendant::a"))
	List<WebElement> removalRole;

	@FindBy(className = "no-results")
	WebElement noRespectiveRoleMatch;
	
	@FindBy(linkText = "Create for User")
	WebElement createForUserLink;

	@FindBy(linkText = "Create for User")
	WebElement createUserLink;
	
	@FindBy(xpath = "//li[@class='dropdown open']/descendant::a[contains(text(),'Search Companies')]")
	WebElement searchCompany;
	
	@FindBy(xpath = "//li[@class='dropdown open']/descendant::a[contains(text(),'Create Company')]")
	WebElement createCompany;
	
	
	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public MyAccountPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		util = new UIUtil(driver, testReport);
	}

	public void clickMyAccountAtHeader() {
		util.click(myAccount, "Click on My Account option at header");
	}

	public void clickSearchUserByHoverCompaniesAndUsers() {
		util.getWaitUtil().waitForElementToBeEnable(companiesUsers);
		util.getActionsUtil().performMouseHoverAndClick(companiesUsers, searchUsers);
	}

	public void enterKeywords(String keyword) {
		util.fill(keywordTextField, keyword, "Enter Keywords at Search Users Page");
	}

	public void clickOnSearchButton() {
		util.getWaitUtil().waitForElementToBeEnable(searchButton);
		util.click(searchButton, "Click on Search Button");
	}

	public void isUserNavigateTo(String pageTitle) {
		util.getWaitUtil().waitForElementToBeVisible(userSearchResultsTitle);
		util.getVerifyUtils().verifyEquals(util.getText(userSearchResultsTitle), pageTitle,
				"User should be '" + pageTitle + "' page", true);
	}

	public void deleteAutomationCreatedUser(String messgeText) {
		util.pageScrollToUpInSlowMotion();
		if (util.getText(noSearchFound).equalsIgnoreCase("No search results found")) {
			logger.info("Oops! No result Found");
			testReport.log(LogStatus.PASS, " No result Found" + ": <b>Success</b>");
		} else {
			for (int i = deleteLink.size(); i >= 1; i--) {
				driver.findElement(
						By.xpath("(//td[contains(@class,'last-column')]//a[contains(text(),'Delete')])[" + i + "]"))
						.click();
				alertSuccessMessage(messgeText);
			}
		}
	}

	public void openSearchedResult() {
		util.getWaitUtil().waitForElementToBeEnable(searchedUserLink);
		util.click(searchedUserLink, "Click on Searched Results Option");
	}

	public void clickOnInactiveLink() {
		util.click(inactiveLink, "Click on Inactive User");
		util.acceptAlertBox();
	}

	public void alertSuccessMessage(String messageText) {
		util.getWaitUtil().waitFortextToBePresentInElement(messageTextAfterDeletion, messageText);
		util.getVerifyUtils().verifyEquals(util.getText(messageTextAfterDeletion), messageText,
				"Message '" + messageText + "' should display", true);
	}

	public void clickOnInactiveUserCheckBoxAtSearchUsers() {
		util.getWaitUtil().waitForElementToBeEnable(inactiveCheckbox);
		util.click(inactiveCheckbox, "Click on Inactive Checkbox at 'Search Users' Page");
	}

	public void clickOnDeletedUserCheckBoxAtSearchUsers() {
		util.getWaitUtil().waitForElementToBeEnable(deletedCheckbox);
		util.click(deletedCheckbox, "Click on Deleted User Checkbox at 'Search Users' Page");
	}

	public void clickOnFirstRowLinkUnderAdminColumn() {
		util.getWaitUtil().waitForElementToBeEnable(clickLinkUnderAdminColumn);
		util.click(clickLinkUnderAdminColumn, "Click on link under Admin Column");
	}

	public void clickOnUpdateButton() {
		util.click(updateButton, "Click on Update Button");
	}

	public void assignRoleToUser(String roleName) {
		util.click(assignRole, "Click on Roles filed");
		util.getWaitUtil().waitForElementToBeEnable(assignRole);
		util.fill(assignRole, roleName, "Add new role '" + roleName + " ' to searched user");
		util.getActionsUtil().goToSleep(1000);
		util.click(chooseRoleUnderAutoSuggestionList, "Choose Role under auto-suggestion box");
	}

	public void enterCalendarColor(String colorCode) {
		util.click(engineerCalendarColor, "Click on Engineer Calendar Color");
		util.fill(calendarColorTextField, colorCode, "Enter Color Code");
	}

	public void clickOnDisassociateLink() {
		util.click(disassociateLink, "Click on Disassociate Link");
		util.acceptAlertBox();
	}

	public void clickOnUpdateYourPasswordLink() {
		util.click(updateYourPassword, "Click on Update Your Password Link");
	}

	public void enterOldPassword(String oldPwd) {
		util.fill(currentPassword, oldPwd, "Enter your current password");

	}

	public void updatePassword(String newPwd) {
		util.fill(password, newPwd, "Enter new password");
		util.fill(confirmPassword, newPwd, "Enter confirm password");
	}

	public void clickOnUpdatePasswordButton() {
		util.click(updatePasswordButton, "Click on Update Password Button");
	}

	public void clickOnResendActivationEmail() {
		util.click(resendActivationLink, "Click on Resend Activation Link");
	}

	public void clickOnInvitationsLink() {
		util.click(invitations, "Click on Invitations Link");
	}

	public void clickOnInviteANewUserLink() {
		util.click(inviteANewUser, "Click on  Invite a New User Link");
	}

	public void enterSaveText() {
		GlobalVars.userSaveSearchName = util.getRandomUtil().getRandomString(10);
		util.fill(rememberSearchAs, GlobalVars.userSaveSearchName, "Enter Save search text");
	}

	public void reEnterSaveText(String savedText) {
		util.fill(rememberSearchAs, savedText, "Enter Save search text");
	}

	public void clickOnsaveSearchButton() {
		util.click(saveSearch, "Click on Create button");
	}

	public void isMyMessage(String messageText) {
		util.getVerifyUtils().verifyEquals(util.getText(myMsg), messageText,
				"Message '" + messageText + "' should display", true);
	}

	public void clickOnSearchUsersLink() {
		util.pageScrollToUpInSlowMotion();
		util.click(searchUserLink, "Click on Search Users link at breadcrumb");
	}

	public void clickOnAvailableSavedSearchOption() {
		util.click(userSavedSearch, "Click on Saved Search under Saved Searches Section");
	}

	public void isUserNameDisplay(String userName) {
		util.getVerifyUtils().verifyEquals(util.getText(searchedUserLink), userName,
				"User '" + userName + "' should display", true);
	}

	public void isSavedUserSearchDisplay(String savedSearch) {
		util.getVerifyUtils().verifyEquals(util.getText(userSavedSearch), savedSearch,
				"Saved Searches '" + savedSearch + "' should be display", true);
	}

	public void clickOnRemoveThisSearch() {
		util.click(removeThisSearch, "Click on Remove This Search Button");
	}

	public void clickOnDeleteBttnAtEditMyProfile() {
		util.click(deleteBttn, "Click on Delete Button at Edit My Profile");
		util.acceptAlertBox();
	}

	public void removalAllAddedRole() {
		for (WebElement ele : removalRole) {
			util.click(ele, "Removed added roles under role text field");
		}
	}

	public void clickOnReactivateUserLink() {
		util.click(reactivateUserLink, "Click on Reactivate User Link");
		util.acceptAlertBox();
	}
	public void isRoleFieldReadOnly() {
		By assignRole = By.className("chosen-search-input");
		util.getVerifyUtils().verifyTrue(util.getWaitUtil().waitForElementToBeDisappear(assignRole, 1),
				"Role text field should be in non-editable mode", "Role text field should be in non-editable mode",
				true);
	}

	public void forumAdminLinkNotAvailable() {
		By forumAdmin = By.id("forumAdminText");
		util.getVerifyUtils().verifyTrue(util.getWaitUtil().waitForElementToBeDisappear(forumAdmin, 1),
				"Make Forum Admin button should not present", "Make Forum Admin button should not present", true);

	}

	public void tryToAddNonPermitRole(String roleName, String messageText) {
		util.click(assignRole, "Click on Roles filed");
		util.getWaitUtil().waitForElementToBeEnable(assignRole);
		util.fill(assignRole, roleName, "Add new role '" + roleName + " ' to searched user");
		util.getActionsUtil().goToSleep(2000);
		util.getVerifyUtils().verifyEquals(util.getText(noRespectiveRoleMatch), messageText + " " + roleName,
				"Message '" + messageText + " " + roleName + "' should display", true);
	}

	public void clickOnCreateUserLink() {
		util.click(createUserLink, "Click on Create User Link");
	}

	public void noTextFieldAvailable(String textFieldName) {
		By firstName = By.xpath("//input[@name='" + textFieldName + "']");
		util.getVerifyUtils().verifyTrue(util.getWaitUtil().waitForElementToBeDisappear(firstName, 1),
				"Text field i.e. " + textFieldName + "should not available at page",
				"Text field i.e. " + textFieldName + "should not available at page", true);
	}

	public void noButtonAvailable(String buttonValue) {
		By firstName = By.xpath("//input[@name='" + buttonValue + "']");
		util.getVerifyUtils().verifyTrue(util.getWaitUtil().waitForElementToBeDisappear(firstName, 1),
				"Button '" + buttonValue + "' should not available at page",
				"Button '" + buttonValue + "' should not available at page", true);
	}

	public void noLinkAvailable(String link) {
		By firstName = By.xpath("//a[contains(text(),'" + link + "')]");
		util.getVerifyUtils().verifyTrue(util.getWaitUtil().waitForElementToBeDisappear(firstName, 1),
				"Link '" + link + "' should should not available at page",
				"Link '" + link + "' should should not available at page", true);
	}
	
	public void clickOnCreateForUserLink() {
		util.click(createForUserLink, "Click on Create for User link");
	}
	
	public void clickSearchUCompanyByHoverCompaniesAndUsers() {
		util.getWaitUtil().waitForElementToBeEnable(companiesUsers);
		util.getActionsUtil().performMouseHoverAndClick(companiesUsers, searchCompany);
	}
	
	public void clickCreateCompanyByHoverCompaniesAndUsers() {
		util.getWaitUtil().waitForElementToBeEnable(companiesUsers);
		util.getActionsUtil().performMouseHoverAndClick(companiesUsers, createCompany);
	}
}
