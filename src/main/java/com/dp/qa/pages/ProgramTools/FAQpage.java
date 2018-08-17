package com.dp.qa.pages.ProgramTools;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.dp.qa.base.TestBase;
import com.dp.qa.utils.UIUtil;

public class FAQpage extends TestBase {
	UIUtil util;
	public final static Logger logger = Logger.getLogger(FAQpage.class);

	@FindBy(xpath = "//label[contains(text(),'Description')]/following::textarea[1]")
	WebElement descriptionField;

	@FindBy(xpath = "//label[contains(text(),'Summary')]/following::input[1][@type='text']")
	WebElement summaryField;

	@FindBy(xpath = "//style[@type='text/css']/following::a[1][contains(text(),'Submit')]")
	WebElement submitLink;

	@FindBy(xpath = "//select[contains(@id,'select')]")
	WebElement selectOneDropdown;

	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public FAQpage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		util = new UIUtil(driver, testReport);
	}

	public void enterDescription(String description) {
		util.fill(descriptionField, description, "Enter Description");
	}

	public void enterSummary(String summary) {
		util.fill(summaryField, summary, "Enter Summary");
	}

	public void clickOnSubmitLink() {
		util.click(submitLink, "Click on Submit link");
	}

	public void selectOne(String option) {
		util.selectByText(selectOneDropdown, option, "Select option under the dropdown");
	}
}
