package com.dp.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.dp.qa.base.TestBase;
import com.dp.qa.utils.UIUtil;

public class SettingPage extends TestBase {
	WebDriver driver;
	UIUtil util;
	public final static Logger logger = Logger.getLogger(SettingPage.class);

	@FindBy(id = "booleanValue")
	WebElement booleanValue;

	@FindBy(id = "submitButton")
	WebElement submitButton;

	@FindBy(xpath = "//h2")
	WebElement settingPageTitle;

	@FindBy(linkText = "Reload Settings")
	WebElement reloadSettings;

	@FindBy(xpath = "//div[@role='alert']/descendant::li")
	WebElement alertSuccess;

	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public SettingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		util = new UIUtil(driver, testReport);
	}

	public void openDpSettingURL() {
		driver.get(props.getProperty("Base_URL") + "/dpSetting/edit/88");
	}

	public void isUserNavigateTo(String pageTitle) {
		util.getWaitUtil().waitForElementToBeVisible(settingPageTitle);
		util.getVerifyUtils().verifyEquals(util.getText(settingPageTitle), pageTitle,
				"User should be '" + pageTitle + "' page", true);
	}

	public void checkBooleanValueCheckbox(String value) {
		util.waitForPageLoad();
		util.getWaitUtil().waitForElementToBeVisible(booleanValue);
		util.checkBooleanValue(booleanValue, value);
		util.getActionsUtil().goToSleep(2000);
	}

	public void clickOnSubmitButton() {
		util.getWaitUtil().waitForElementToBeEnable(submitButton);
		util.click(submitButton, "Click On Submit Button at 'DpSetting - Edit' page");
	}

	public void isSettingSaved() {
		util.getWaitUtil().waitForElementToBeVisible(alertSuccess);
		util.getVerifyUtils().verifyEquals(util.getText(alertSuccess), "Your changes have been saved successfully",
				"User should saved setting scuessfully", true);
	}

	public void clickOnReloadSettings() {
		util.click(reloadSettings, "Click on Reload Settings link at 'DpSetting - Edit' page");
	}
}
