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

public class TitaniumToolsPage extends TestBase {
	UIUtil util; 
	public final static Logger logger = Logger.getLogger(TitaniumToolsPage.class);

	@FindBy(name = "j_username")
	WebElement log;

	@FindBy(name = "j_password")
	WebElement pwd;

	@FindBy(xpath = "//span[text()='login']/parent::a")
	WebElement loginBttn;

	@FindBy(xpath = "//div[@id='tool_console']/ul/li[4]/a")
	WebElement TitaniumTools;

	@FindBy(linkText = "Terms and Conditions")
	WebElement TermsAndConditions;

	@FindBy(linkText = "Search T & C's")
	WebElement SearchTC;

	@FindBy(xpath = "//label[contains(text(),'T&C Text')]/following::input[1]")
	WebElement TANDCText;

	@FindBy(id = "searchBtn")
	WebElement searchBtn;

	@FindBy(linkText = "Site Terms & Conditions")
	WebElement SiteTermsConditions;

	@FindBy(id = "text")
	WebElement acceptText;

	@FindBy(id = "pushQaFlag")
	WebElement pushdpqastageCheckbox;

	@FindBy(id = "updateBtn")
	WebElement updateBtn;

	@FindBy(className = "message")
	WebElement successfullMessage;

	@FindBy(linkText = "Push Data")
	WebElement pushDataLink;

	@FindBy(xpath = "//div[contains(text(),'dpqa-stage')]/following::a[1]")
	WebElement pushFlagedDpQa1;

	@FindBy(linkText = "Start Data Push")
	WebElement startDataPush;

	@FindBy(id = "completedDiv")
	WebElement completedDiv;

	@FindBy(id = "tool_console")
	WebElement toolConsole;

	@FindBy(xpath = "//p[@class='heading_1'][text()='dpEngine Portal Toolbox']")
	WebElement dpEnginePortalToolbox;

	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public TitaniumToolsPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		util = new UIUtil(driver, testReport);
	}

	public void openChannalManager() {
		util.launchUrl("https://viewer:bR5Tt7u9@dpqa-stage-cm.developerprogram.org/favicon.ico");
	}

	public void loginChannelManagerWithCredentails(String username, String password) {
		util.waitForPageLoad();
		util.getWaitUtil().waitForElementToBeVisible(dpEnginePortalToolbox);
		util.fill(log, username, "Enter username into email field");
		util.fill(pwd, password, "Enter password into password field");
		util.click(loginBttn, "Click on Login button");
	}

	public void clickOnTitaniumToolLink() {
		util.waitForPageLoad();
		util.getWaitUtil().waitForElementToBeVisible(toolConsole);
		util.click(TitaniumTools, "Click on Titanium Tool link");
	}

	public void clickSearchTCByHoverTermsAndCondition() {
		util.getActionsUtil().performMouseHoverAndClick(TermsAndConditions, SearchTC);
	}

	public void enterTAndCText(String text) {
		util.fill(TANDCText, text, "Enter search T&C Text");
	}

	public void clickOnSearchBttn() {
		util.click(searchBtn, "Click on Search Button");
	}

	public void clickOnSiteTermsConditionsLink() {
		util.click(SiteTermsConditions, "Click on Site Terms & Conditions link");
	}

	public void updateAcceptText() {
		GlobalVars.updatedTermsAndConditionText = util.getRandomUtil().getRandomString(10);
		util.fillWithOutClear(acceptText, GlobalVars.updatedTermsAndConditionText, "Updated Accept Text");
	}

	public void removalTheUpdatedText(String removalText) {
		logger.info(util.getText(acceptText));
		String defaultText = util.getText(acceptText).replaceAll(removalText, "");
		util.fill(acceptText, defaultText, "Updated Accept Text");
	}

	public void pushTheChangesToStage(String messageText, String ByModule) {
		util.click(pushdpqastageCheckbox, "Checked Push dpqa-stage Flag checkbox");
		util.click(updateBtn, "Click on Update Button");
		util.acceptAlertBox();
		util.getVerifyUtils().verifyEquals(util.getText(successfullMessage), messageText,
				"Message '" + messageText + "' should be display", true);
		util.click(pushDataLink, "Click on Push Data link");
		WebElement e = driver
				.findElement(By.xpath("//fieldset[@id='modules']/descendant::a[contains(text(),'" + ByModule + "')]"));
		e.click();
		util.click(pushFlagedDpQa1, "Click on Push flagged link");
		util.click(startDataPush, "Click on Start Data Push Bttn");
		util.getWaitUtil().waitForElementToBeVisible(completedDiv);
	}

}
