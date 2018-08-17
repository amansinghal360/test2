package com.dp.qa.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dp.qa.base.TestBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Aman Singhal
 *
 */
public class UIUtil {

	public WebDriver driver;
	private String winHandle;
	private VerifyUtils verifyUtils;
	private WaitUtil waitUtil;
	private RandomUtil randomUtil;
	private ActionsUtil actionsUtil;
	private ExtentTest testReport;
	public static SimpleDateFormat sdf;
	public final static Logger log = Logger.getLogger(UIUtil.class);

	public UIUtil(WebDriver driver, ExtentTest testReport) {
		super();
		this.driver = driver;
		this.testReport = testReport;
		verifyUtils = new VerifyUtils(driver, testReport);
		waitUtil = new WaitUtil(driver,testReport);
		actionsUtil = new ActionsUtil(driver);
		randomUtil = new RandomUtil();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public WaitUtil getWaitUtil() {
		return waitUtil;
	}

	public VerifyUtils getVerifyUtils() {
		return verifyUtils;
	}

	public RandomUtil getRandomUtil() {
		return randomUtil;
	}
	
	public ActionsUtil getActionsUtil() {
		return actionsUtil;
	}

	/**
	 * Method Description: It add screenshot for failing test script
	 *
	 * @param description
	 * @param takescreenshot
	 * 
	 */
	void reportTestStepFailure(String description, Exception e, boolean takeScreenshot) {
		if (takeScreenshot) {
			testReport.log(LogStatus.ERROR, description + "<br><b>Failed: </b>" + e.getMessage().replace("\n", "<br>")
					+ "<br><b>Snapshot:</b><br>" + testReport.addScreenCapture(takeScreenShot()));
		} else {
			testReport.log(LogStatus.ERROR, description + "<br><b>Failed: </b>" + e.getMessage().replace("\n", "<br>"));
		}
	}

	/**
	 * Method Description: Capture screenshot
	 *
	 */
	public String takeScreenShot() {

		String screenshotName = "image" + System.currentTimeMillis() + ".png";

		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(TestBase.reportFolderPath + File.separator + screenshotName));
			log.info("Screenshot " + screenshotName + " taken at " + TestBase.reportFolderPath);

		} catch (IOException e) {
			log.error("Fail to take screenshot");
		}
		return screenshotName;
	}

	/**
	 * Method Description: It applies a hard wait
	 *
	 * @param TimeInMillis
	 * 
	 */
	public void goToSleep(int TimeInMillis) {
		try {
			Thread.sleep(TimeInMillis);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**
	 * Method Description: Clicks on a mentioned web-element
	 * 
	 * @param locator
	 *            locator
	 * @param description
	 *            description
	 */
	public void click(WebElement locator, String description) {
		try {
			locator.click();
			log.info("Step: '" + description + "' successful");
			testReport.log(LogStatus.PASS, description);
		} catch (StaleElementReferenceException se) {
			se.printStackTrace();
			locator.click();
			log.error("Either element has been deleted entirely or no longer attached to DOM. " + se.getMessage());
			reportTestStepFailure(description, se, true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not perform mouse click. " + e.getMessage());
			reportTestStepFailure(description, e, true);
		}
	}

	/**
	 * Method Description: Accepts string input and fill in the mentioned
	 *
	 * @param locator
	 *            locator
	 * @param inputdata
	 *            input
	 * @param description
	 *            description
	 */
	public void fill(WebElement locator, String inputdata, String description) {
		try {
			locator.clear();
			locator.sendKeys(inputdata);
			log.info("Step: '" + description + "' with input '" + inputdata + "' in locator '" + locator.toString()
					+ "' successful");
			testReport.log(LogStatus.PASS, description + ", with input <b>" + inputdata + "</b>");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not perform fill operation. " + e.getMessage());
			reportTestStepFailure(description, e, true);
		}
	}

	/**
	 * Method Description: Accepts string input and fill in the mentioned
	 *
	 * @param locator
	 *            locator
	 * @param inputdata
	 *            input
	 * @param description
	 *            description
	 */
	public void fillWithOutClear(WebElement locator, String inputdata, String description) {
		try {
			locator.sendKeys(inputdata);
			log.info("Step: '" + description + "' with input '" + inputdata + "' in locator '" + locator.toString()
					+ "' successful");
			testReport.log(LogStatus.PASS, description + ", with input <b>" + inputdata + "</b>");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not perform fill operation. " + e.getMessage());
			reportTestStepFailure(description, e, true);
		}
	}

	
	/**
	 * Method Description: It fetches and returns the text of a web-element
	 * 
	 * @param locator
	 * 
	 */
	public String getText(WebElement locator) {
		try {
			return locator.getText();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in returning text of webelement!" + e.getMessage());
			return "Error in returning text of a webelement!" + e.getMessage();
		}
	}

	/**
	 * Method Description: Waits for Page Load via Java Script Ready State
	 */
	public boolean waitForPageLoad() {

		boolean isLoaded = false;
		int iTimeOut = 60; // Need to Give Global Timeout Value;

		try {
			Thread.sleep(2000);
			log.info("Waiting For Page load via JS");
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, iTimeOut);
			wait.until(pageLoadCondition);
			isLoaded = true;
		} catch (Exception e) {
			log.error("Error Occured waiting for Page Load " + driver.getCurrentUrl());
		}
		return isLoaded;
	}

	/**
	 * Method Description: It fetches and returns the text of all list
	 * 
	 * @param locator
	 * 
	 */
	public List<String> getListText(List<WebElement> locator) {
		List<String> listTexts = new ArrayList<>();
		List<WebElement> list = locator;
		for (WebElement ele : list) {
			listTexts.add(ele.getText());
		}
		return listTexts;
	}

	/**
	 * Method Description: Clicks on a mentioned web-element via Java Script
	 */

	public void safeJavaScriptClick(WebElement locator) {
		try {
			if (locator.isEnabled() && locator.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", locator);
			} else {
				log.error("Unable to click on element in locator'");
			}
		} catch (Exception e) {
			log.error("Unable to click on element " + e.getMessage());
		}
	}

	/**
	 * Method Description: Checks if an alert pop of browser is present
	 */
	public boolean isAlertPresent() {
		try {
			Alert alert = new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
			if (alert != null) {
				driver.switchTo().alert();
				log.info("Alert is present");
				return true;
			} else {
				log.info("No alert present.");
				return false;
			}
		} catch (Exception e) {
			log.error("Error occured while verifying alert presence. " + e.getMessage());
			return false;
		}
	}

	/**
	 * Method Description: It returns text present on Alert box
	 */
	public String getAlertMessage() {
		String message = "";
		try {
			Alert alert = driver.switchTo().alert();
			message = alert.getText();
			log.info("Fetched message present on alert box: " + message);
			alert.accept();
			log.info("Pressed on OK/Yes button present to close the alert box.");
			return message;
		} catch (NoAlertPresentException na) {
			na.printStackTrace();
			log.error("No alert present. " + na.getMessage());
			return message;
		} catch (Exception e) {
			log.error("Error occured while fetching message present on alert box. " + e.getMessage());
			return message;
		}
	}

	/**
	 * Method Description: Clicks on OK present on the alert
	 */
	public void acceptAlertBox() {

		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			log.info("Pressed on OK/Yes button present on the alert box.");
		} catch (NoAlertPresentException na) {
			log.error("No alert present. " + na.getMessage());
		} catch (Exception e) {
			log.error("Error occured while accepting alert box. " + e.getMessage());
		}
	}

	/**
	 * Method Description: Clicks on Cancel/No present on the alert
	 */
	public void dismissAlertBox() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			log.info("Pressed on Cancel/No button present on the alert box.");
		} catch (NoAlertPresentException na) {
			na.printStackTrace();
			log.error("No alert present. " + na.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occured while dismissing alert box. " + e.getMessage());
		}
	}

	/**
	 * Method Description: Closes the browser completely
	 */
	public void stopDriver() {
		try {
			driver.quit();
			log.info("Browser has been completely closed.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Browser could not be closed. " + e.getMessage());

		}
	}

	/**
	 * Method Description: Gets the handle for the current window
	 */
	private void getWindowHandle() {
		try {
			winHandle = driver.getWindowHandle();
			log.info("Got the handle for the current window");
		} catch (NoSuchWindowException ns) {
			ns.printStackTrace();
			log.error("No window exist. " + ns.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occured while getting window handle. " + e.getMessage());
		}
	}

	/**
	 * Method Description: Switches to the most recent window opened
	 */
	public void switchtoNewWindow() {
		try {
			getWindowHandle();
			for (String windowsHandle : driver.getWindowHandles()) {
				driver.switchTo().window(windowsHandle);
				log.info("Switched to window: " + windowsHandle);
			}
		} catch (NoSuchWindowException ns) {
			ns.printStackTrace();
			log.error("No window exist. " + ns.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occuring while switching to most recent new window. " + e.getMessage());
		}
	}

	/**
	 * Method Description: Closes the Current Active Window
	 */
	public void closeNewWindow() {
		try {
			driver.close();
			log.info("Current Active window has been closed");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Current Active Window could not be closed. " + e.getMessage());
		}
	}

	/**
	 * Method Description: Switches back to original window
	 */
	public void switchtoOriginalWindow() {
		try {
			driver.switchTo().window(winHandle);
			log.info("Switched back to original window");
		} catch (NoSuchWindowException ns) {
			ns.printStackTrace();
			log.error("No window exist. " + ns.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occuring while switching to most recent new window. " + e.getMessage());
		}
	}

	/**
	 * Method Description: It checks the presence of an element on the page of given
	 * path
	 * 
	 * @param locator
	 * 
	 */

	public Boolean isElementPresent(WebElement locator) {
		try {

			if (locator.getSize() != null && locator.isDisplayed()) {
				log.info("Locator: " + locator.toString() + " is visible on page");
				return true;
			} else {
				log.info("Locator: " + locator.toString() + " not present on page");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while checking the presence of an element on page" + e.getMessage());
			return false;
		}
	}

	/**
	 * Method Description: It checks the presence and visibility of an element on
	 * page of given path
	 *
	 * @param locator
	 *            locator
	 */
	public Boolean isVisible(WebElement locator) {
		try {
			if (locator.getSize() != null && locator.isDisplayed()) {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				log.info("Locator: " + locator.toString() + " is visible on page");
				return true;
			} else {
				log.info("Locator: " + locator.toString() + " is not visible on page");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Element is not visible on page. " + e.getMessage());
			return false;
		}
	}

	/**
	 * Method Description: It checks the element is enable on page of given path
	 *
	 * @param locator
	 * 
	 */
	public Boolean isEnabled(WebElement locator) {
		try {
			if (locator.getSize() != null && locator.isEnabled()) {
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				log.info("Locator: " + locator.toString() + " is visible on page");
				return true;
			} else {
				log.info("Locator: " + locator.toString() + " is not visible on page");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Element is not visible on page. " + e.getMessage());
			return false;
		}
	}

	/**
	 * Method Description: Return the value/text w.r.t locator and attirbute
	 * 
	 * @param locator
	 * @param attribute
	 * 
	 */
	public String getAttribute(WebElement locator, String attribute) {
		try {
			return locator.getAttribute(attribute);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in returning the desired attribute of webelement! " + e.getMessage());
			return "Error in returning the desired attribute of webelement! " + e.getMessage();
		}
	}

	/**
	 * Method Description: It selects a value from the dropdown basis text
	 * 
	 * @param locator
	 *            locator
	 * @param text
	 *            text
	 * @param description
	 *            description
	 */
	public void selectByText(WebElement locator, String text, String description) {
		try {
			Select select = new Select(locator);
			select.selectByVisibleText(text);
			testReport.log(LogStatus.PASS, description);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in selecting a value from dropdown of webelement! " + e.getMessage());
			reportTestStepFailure(description, e, true);
		}
	}

	/**
	 * Method Description: It selects a value from the dropdown basis index
	 */
	public void selectByIndex(WebElement locator, int index, String description) {
		try {
			Select select = new Select(locator);
			select.selectByIndex(index);
			testReport.log(LogStatus.PASS, description);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in selecting a value from dropdown of webelement! " + e.getMessage());
			reportTestStepFailure(description, e, true);
		}
	}

	/**
	 * Method Description: It selects a value from the dropdown basis Value
	 */
	public void selectByValue(WebElement locator, String value, String description) {
		try {
			Select select = new Select(locator);
			select.selectByValue(value);
			testReport.log(LogStatus.PASS, description);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in selecting a value from dropdown of webelement! " + e.getMessage());
			reportTestStepFailure(description, e, true);
		}
	}

	/**
	 * Method Description: Deselect selectd value under dropdown and attirbute
	 * 
	 * @param locator
	 * @param value
	 * 
	 */
	public void deselectFromDropdown(WebElement locator, String value) {
		try {
			Select select = new Select(locator);
			select.deselectByVisibleText(value);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Method Description: Returnt text w.r.t selected element
	 * 
	 * @param locator
	 * 
	 */
	public String getSelectedTextFromDropdown(WebElement locator) {
		try {
			Select select = new Select(locator);
			return select.getFirstSelectedOption().getText();
		} catch (Exception e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
	}

	/**
	 * Method Description: First verify radio button state and then checked radio
	 * button
	 * 
	 * @param locator,value
	 * 
	 */
	public void checkBooleanValue(WebElement locator, String value) {

		switch (value) {
		case "True":
			if (locator.isSelected()) {
				log.info("Button is already selected");
			} else {
				locator.click();
				log.info("Button is selected successfully");
			}
			break;
		case "False":
			if (locator.isSelected()) {
				locator.click();
				log.info("Button is un-selected successfully");
			} else {
				log.info("Button is already un-selected");
			}
		default:
			log.error("Error in un-selected button! ");

		}
	}

	/**
	 * Method Description: Switch to parent window
	 * 
	 */
	public void switchToParentWindow() {
		try {
			driver.switchTo().defaultContent();
			log.info("Switched to Parent window");
		} catch (NoSuchWindowException ns) {
			ns.printStackTrace();
			log.error("No window exist. " + ns.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while switching to parent window. " + e.getMessage());
		}
	}

	/**
	 * Method Description: Scrolling in one go
	 * 
	 */
	public void scrollBottomOnce() {
		for (int count = 0;; count++) {
			if (count >= 1) { // count value can be changed depending on number
				// of times you want to scroll
				break;
			}
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)", ""); // y value '800' can be
			// changed
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method Description: Scroll page to bottom in slow motion
	 */
	public void pageScrollToBottomInSlowMotion() {
		for (int count = 0;; count++) {
			if (count >= 5) { // count value can be changed depending on number of times you want to scroll
				break;
			}
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,800)", ""); // y value '800' can be
			// changed
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method Description: Scroll page to top in slow motion
	 */
	public void pageScrollToUpInSlowMotion() {
		for (int count = 0;; count++) {
			if (count >= 5) { // count value can be changed depending on number
				// of times you want to scroll
				break;
			}
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-400)", ""); // y value '400' can be
			// changed
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method Description: Return substring from input string w.r.t passed first
	 * char and second char
	 */
	public String getSubString(String parentString, String firstChar, String secondChar) {
		int firstSpace = parentString.indexOf(",");
		int lastSpace = parentString.lastIndexOf("!");
		String substring = parentString.substring(firstSpace + 2, lastSpace);
		return substring;

	}

	public void clickHiddenElement(WebElement locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", locator);
	}

	public void launchUrl(String url) {
		driver.get(url);

	}
	
	/**
	 * Method Description: Clicks on a mentioned web-element
	 * 
	 * @param locator
	 *            locator
	 * @param description
	 *            description
	 */
	public void clickOn(By locator, String description) {
		try {
			driver.findElement(locator).click();
			log.info("Step: '" + description + "' successful");
			testReport.log(LogStatus.PASS, description);
		} catch (StaleElementReferenceException se) {
			se.printStackTrace();
			driver.findElement(locator).click();
			log.error("Either element has been deleted entirely or no longer attached to DOM. " + se.getMessage());
			reportTestStepFailure(description, se, true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not perform mouse click. " + e.getMessage());
			reportTestStepFailure(description, e, true);
		}
	}

}