package com.dp.qa.pages.MyAccount;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.dp.qa.base.TestBase;
import com.dp.qa.utils.UIUtil;

public class DevicePage extends TestBase {
	UIUtil util;
	public final static Logger logger = Logger.getLogger(DevicePage.class);

	@FindBy(xpath = "//label[contains(text(),'Additional Comments')]/following::input[1][@type='text']")
	WebElement additionalComments;

	@FindBy(xpath = "//style[@type='text/css']/following::a[1][contains(text(),'Submit')]")
	WebElement submitLink;

	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public DevicePage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		util = new UIUtil(driver, testReport);
	}

	public void enterAdditionalComments(String comment) {
		util.fill(additionalComments, comment, "Enter Additional Comment");
	}

	public void clickOnSubmitLink() {
		util.click(submitLink, "Click on Submit link");
	}

}
