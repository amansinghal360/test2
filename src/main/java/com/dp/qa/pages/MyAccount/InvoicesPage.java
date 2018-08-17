package com.dp.qa.pages.MyAccount;

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

public class InvoicesPage extends TestBase {
	UIUtil util;
	public final static Logger logger = Logger.getLogger(InvoicesPage.class);

	@FindBy(id = "bdm.id")
	WebElement BDM;

	@FindBy(id = "grantSupportHours")
	WebElement grantSupportHours;

	@FindBy(id = "grantSupportTickets")
	WebElement grantSupportTickets;

	@FindBy(xpath = "//textarea[contains(@id,'textBox')]")
	WebElement invoiceDescription;

	@FindBy(id = "quotedPrice")
	WebElement quotedPrice;

	@FindBy(id = "billingAddress1")
	WebElement billingAddress1;

	@FindBy(id = "billingCity")
	WebElement billingCity;

	@FindBy(id = "billingState")
	WebElement billingState;

	@FindBy(id = "billingPostalCode")
	WebElement billingPostalCode;

	@FindBy(id = "billingCountry")
	WebElement billingCountry;

	@FindBy(id = "billingMainPhone")
	WebElement billingMainPhone;

	@FindBy(id = "billingContactName")
	WebElement billingContactName;

	@FindBy(id = "billingContactPhone")
	WebElement billingContactPhone;

	@FindBy(id = "billingContactEmail")
	WebElement billingContactEmail;

	@FindBy(xpath = "//input[@value='Create Invoice']")
	WebElement createInvoiceBttn;

	@FindBy(xpath = "//a[contains(text(),'Approve Invoice')]")
	WebElement approveInvoiceLink;

	@FindBy(id = "benefitStartDate")
	WebElement startDate;

	@FindBy(id = "benefitEndDate")
	WebElement endDate;

	@FindBy(xpath = "//input[contains(@id,'textBox')]")
	WebElement invoiceDescriptionDetails;
	
	@FindBy(xpath = "//li[@class='dropdown open']/descendant::a[contains(text(),'Invoices')]")
	WebElement invoices;

	@FindBy(xpath = "//li[@class='dropdown open']/descendant::a[contains(text(),'Search Invoices ')]")
	WebElement searchInvoices ;
	
	@FindBy(xpath = "//label[contains(text(),'Invoice Number')]/following::p[1]")
	WebElement invoiceNumber ;
	
	@FindBy(id = "invoiceId")
	WebElement invoiceID ;
	

	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public InvoicesPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		util = new UIUtil(driver, testReport);
	}

	public void selectBDM(String bdmText) {
		util.selectByText(BDM, bdmText, "Select BDM under the dropdown");
	}

	public void enterGrantSupportHours(String hours) {
		util.fill(grantSupportHours, hours, "Enter Grant Support Hours");
	}

	public void enterGrantSupportTickets(String ticket) {
		util.fill(grantSupportTickets, ticket, "Enter Grant Support Ticket");
	}

	public void enterInvoiceDescription(String desc) {
		util.fill(invoiceDescription, desc, "Enter Invoice Description");
	}

	public void enterQuotedPrice(String price) {
		util.fill(quotedPrice, price, "Enter Quoted Price");
	}

	public void enterAddress(String addrs) {
		util.fill(billingAddress1, addrs, "Enter Billing Address");
	}

	public void enterCityName(String cityName) {
		util.fill(billingCity, cityName, "Enter City Name");
	}

	public void selectStateName(String stateName) {
		util.selectByText(billingState, stateName, "Enter City Name");
	}

	public void enterPostalCode(String postalCode) {
		util.fill(billingPostalCode, postalCode, "Enter Postal Code");
	}

	public void selectCountry(String countryName) {
		util.selectByText(billingCountry, countryName, "Select Country under the dropdown");
	}

	public void enterMainPhone(String mainPhone) {
		util.fill(billingMainPhone, mainPhone, "Enter Postal Code");
	}

	public void enterBillingContactName(String billingContact) {
		util.fill(billingContactName, billingContact, "Enter Billing Contact Name");
	}

	public void enterBillingContactPhone(String billingContactPhoneNo) {
		util.fill(billingContactPhone, billingContactPhoneNo, "Enter Billing Contact Number");
	}

	public void enterbillingContactEmail(String billingContactEmailId) {
		util.fill(billingContactEmail, billingContactEmailId, "Enter Billing Contact Email Id");
	}

	public void clickOnCreateInvoice() {
		util.click(createInvoiceBttn, "Create Invoice Button");
	}

	public void clickOnApproveInvoice() {
		util.click(approveInvoiceLink, "Click on Approve Invlice Link");
	}

	public void enterStartEndDate(String startdate, String enddate) {
		util.click(startDate, "Open datepicker w.r.t start date");
		By sdate = By.xpath("//td[@data-handler='selectDay']/descendant::a[text()='" + startdate + "']");
		util.clickOn(sdate, "Choose Start date under date picker");

		util.click(endDate, "Open datepicker w.r.t end date");
		By edate = By.xpath("//td[@data-handler='selectDay']/descendant::a[text()='" + enddate + "']");
		util.clickOn(edate, "Choose End date under date picker");
	}

	public void enterInvoiceDescriptionDetails(String desc) {
		util.fill(invoiceDescriptionDetails, desc, "Enter Invoice Description");
	}
	
	public void clickSearchInvoiceByHoverInvoices() {
		util.getWaitUtil().waitForElementToBeEnable(invoices);
		util.getActionsUtil().performMouseHoverAndClick(invoices, searchInvoices);
	}
	
	public void getInvoiceNumber() {
		GlobalVars.invoiceNumber=util.getText(invoiceNumber);
		logger.info("Getting the invoice number");
	}
	
	public void enterInvoiceId(String invoiceNumber) {
		util.fill(invoiceID, invoiceNumber, "Enter Invoice Id");
	}
}
