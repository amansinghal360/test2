package com.dp.qa.pages.ProgramTools;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.dp.qa.base.TestBase;
import com.dp.qa.utils.UIUtil;

public class ProgramToolsPage extends TestBase {
	UIUtil util;
	public final static Logger logger = Logger.getLogger(ProgramToolsPage.class);

	@FindBy(id = "summary")
	WebElement summary;

	@FindBy(id = "description")
	WebElement description;
	
	@FindBy(id = "priority.id")
	WebElement priorityDropdown;
	
	@FindBy(id = "createTicket")
	WebElement createTicket;
	
	@FindBy(id = "resource")
	WebElement resource;
	
	@FindBy(id = "resourceLocation")
	WebElement resourceLocation;
	
	@FindBy(id = "phone")
	WebElement phoneNumber;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement submitInput;

	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public ProgramToolsPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		util = new UIUtil(driver, testReport);
	}

	public void enterSummary(String summaryText) {
		util.fill(summary, summaryText, "Enter ticket summary");
	}

	public void enterDescription(String descriptionText) {
		util.fill(description, descriptionText, "Enter ticekt description");
	}

	public void selectTicketPriority(String priority) {
		util.selectByText(priorityDropdown, priority, "Select Ticket Priority under the dropdown");
	}
	
	public void  clickOnCreateButton() {
		util.click(createTicket, "Click on  Create Ticket button");
	}
	
	public void selectResourceType(String resourceType) {
		util.selectByText(resource, resourceType, "Select Resource Type under the dropdown");
	}
	
	public void selectLocation(String location) {
		util.goToSleep(1000);
		util.selectByText(resourceLocation, location, "Select Location under the dropdown");
	}
	
	public void enterPhoneNumber(String phone) {
		util.fill(phoneNumber, phone, "Enter ticekt description");
	}
	
	public void  clickOnSubmitInputType() {
		util.click(submitInput, "Click on  Submit bttn");
	}
}
