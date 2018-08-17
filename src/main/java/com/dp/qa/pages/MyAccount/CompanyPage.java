package com.dp.qa.pages.MyAccount;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.dp.qa.base.TestBase;
import com.dp.qa.utils.UIUtil;

public class CompanyPage extends TestBase {
	UIUtil util;
	public final static Logger logger = Logger.getLogger(CompanyPage.class);

	@FindBy(xpath = "//input[@name='companyUrl']")
	WebElement companyURL;

	@FindBy(id = "companyPhone")
	WebElement companyPhone;

	@FindBy(id = "address1")
	WebElement corporateAddress;

	@FindBy(id = "newCompany")
	WebElement createNewCompany;

	@FindBy(xpath = "//td[contains(@class,'last-column')]//a")
	WebElement clickLinkUnderAdminColumn;

	@FindBy(xpath = "//select[descendant::option[@value='0-20']]")
	WebElement companySize;

	@FindBy(xpath = "//select[@multiple='true'][descendant::option[@value='Program 1']]")
	WebElement selectProgram;

	@FindBy(xpath = "//select[@multiple='true'][descendant::option[@value='Test1']]")
	WebElement multiSelectBox;

	@FindBy(linkText = "Grant Support Access")
	WebElement grantSupportAccess;

	@FindBy(linkText = "Grant Testing Access")
	WebElement grantTestingAccess;

	@FindBy(linkText = "Remove Support Access")
	WebElement removeSupportAccess;

	@FindBy(xpath = "//a[contains(text(),'Associate Company Member')]")
	WebElement associateCompanyMember;

	@FindBy(xpath = "//div[@class='modal-body']/descendant::a[contains(text(),'Search')]")
	WebElement searchLink;

	@FindBy(className = "selectLink")
	WebElement assignLink;

	@FindBy(id = "companyEmailDomain")
	WebElement companyEmailDomain;

	/***
	 * Constructor
	 * 
	 * @param driver
	 *            an instance of WebDriver
	 */
	public CompanyPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		util = new UIUtil(driver, testReport);
	}

	public void enterCompanyPhone(String companyPh) {
		util.fill(companyPhone, companyPh, "Enter Company Phone");
	}

	public void enterCompanyURL(String companyUrl) {
		util.fill(companyURL, "http://" + companyUrl + ".com", "Enter Company URL");
	}

	public void enterCompanyAddress(String compamyAddress) {
		util.fill(corporateAddress, compamyAddress, "Enter Company Address");
	}

	public void clickOnCreateNewCompanyLink() {
		util.click(createNewCompany, "Click on Create New Company link");
	}

	public void clickOnDeleteLinkAndAcceptPopUpAtSearchResult() {
		util.getWaitUtil().waitForElementToBeEnable(clickLinkUnderAdminColumn);
		util.click(clickLinkUnderAdminColumn, "Click on link under Admin Column");
		util.acceptAlertBox();
	}

	public void selectCompanySize(String companyRange) {
		util.selectByText(companySize, companyRange, "Select Company Size under the dropdown");
	}

	public void selectProgram(String program) {
		util.selectByText(selectProgram, program, "Select Program under the dropdown");
	}

	public void selectMultiSelectBoxLabel(String value) {
		util.selectByText(multiSelectBox, value, "Select Option at Index 1");
		util.selectByIndex(multiSelectBox, 1, "Select Option at Index 1");
		util.deselectFromDropdown(multiSelectBox, value);
	}

	public void clickOnGrantSupportAccessLink() {
		util.getWaitUtil().waitForElementToBeEnable(grantSupportAccess);
		util.click(grantSupportAccess, "Click on Grant Support Access link");
	}

	public void clickOnGrantTestingAccessLink() {
		util.getWaitUtil().waitForElementToBeEnable(grantTestingAccess);
		util.click(grantTestingAccess, "Click on Grant Testing Access link");
	}

	public void clickOnRemoveSupportAccessLink() {
		util.getWaitUtil().waitForElementToBeEnable(removeSupportAccess);
		util.click(removeSupportAccess, "Click on Remove Support Access link");
		util.waitForPageLoad();
	}

	public void clickOnAssociateCompanyMemberLink() {
		util.getWaitUtil().waitForElementToBeEnable(associateCompanyMember);
		util.click(associateCompanyMember, "Click on Associate Company Member link");
	}

	public void clickonSearchLink() {
		util.click(searchLink, "Click on Search Link");
	}

	public void clickOnAssignLinkAtAssociateCompanyMember() {
		util.getWaitUtil().waitForElementToBeEnable(assignLink);
		util.click(assignLink, "Click on Assign link");
		util.waitForPageLoad();
	}

	public void enterCompanyDomain(String compamyDomain) {
		util.fill(companyEmailDomain, compamyDomain, "Enter Company Email Domain");
	}
}
