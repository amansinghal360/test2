package com.dp.qa.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import java.util.concurrent.TimeUnit;

/**
 * @author Aman Singhal This class is for implenting various wait logic.
 * 
 */
public class WaitUtil {

	private WebDriver driver;
	private static WaitUtil Wait;
	public final static Logger log = Logger.getLogger(WaitUtil.class);
	protected static WebDriverWait ajaxWait;
	private int TIME_OUT = 10;
	private ExtentTest testReport;

	WaitUtil(WebDriver driver,ExtentTest testReport) {
		this.driver = driver;
		this.testReport = testReport;
		ajaxWait = new WebDriverWait(driver, TIME_OUT);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public WaitUtil getWait() {
		if (Wait == null) {
			Wait = new WaitUtil(this.driver,this.testReport);
		}
		return Wait;
	}

	/**
	 * Wait For Element To Display
	 *
	 * @param locator
	 *            locator
	 * @param maxSecondTimeout
	 *            time
	 */
	public void waitForElementToBeDisplay(WebElement element) {
		try {
			log.info("Waiting for an element to be visible using locator:" + element);
			ajaxWait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			log.error("Exception in waitForElementToBeDisplay: " + e.getMessage());
		}
	}

	/**
	 * waits for specified duration and checks that an element is present on DOM.
	 * Visibility means that the element is not only displayed but also has a height
	 * and width that is greater than 0.
	 *
	 * @param element
	 *            element
	 **/

	public void waitForElementToBeVisible(WebElement element) {
		try {
			log.info("Waiting for an element to be visible using locator: " + element);
			ajaxWait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			log.info("Exception while waiting for visibility. " + e.getMessage());
		}
	}

	/**
	 * An expectation for checking if the given text is present in the specified
	 * element
	 *
	 * @param element
	 *            element
	 * @param sText
	 *            text
	 */
	public void waitFortextToBePresentInElement(WebElement element, final String sText) {
		try {
			log.info("Waiting for text to be present at element using locator:" + element);
			ajaxWait.until(ExpectedConditions.textToBePresentInElement(element, sText));
		} catch (Exception e) {
			log.info("Exception found in waitFortextToBePresentInElement: " + e.getMessage());
		}
	}

	/**
	 * Wait For Element to Not Present
	 *
	 * @param element
	 *            element
	 * 
	 */
	public boolean waitForElementNotPresent(WebElement element) {
		try {
			log.info("Waiting for an element to be invisible at locator:" + element);
			WebDriverWait ajaxWait = new WebDriverWait(driver, 1);
			ajaxWait.until(ExpectedConditions.invisibilityOf(element));
			return true;
		} catch (Exception e) {
			log.info("Exception found in waitForElementNotPresent: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Wait For Element to Enable
	 *
	 * @param element
	 *            element
	 */
	public void waitForElementToBeEnable(WebElement element) {
		try {
			log.info("Waiting for an element to be enable using locator:" + element);
			ajaxWait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			log.info("Exception found in waitForElementToEnable: " + e.getMessage());
		}
	}

	/**
	 * Wait For Element to Disable
	 *
	 * @param element
	 *            element
	 * @param maxSecondTimeout
	 *            time
	 */
	public boolean waitForElementToDisable(WebElement element, int maxSecondTimeout) {

		try {
			log.info("INTO waitForElementToDisable METHOD");
			maxSecondTimeout = maxSecondTimeout * 20;
			while (element.isEnabled() && (maxSecondTimeout > 0)) {
				Thread.sleep(50L);
				maxSecondTimeout--;
			}
			if (maxSecondTimeout == 0) {
				log.error("Element is not disabled within " + (maxSecondTimeout / 20) + "Sec.");
			}
			log.info("OUT OF METHOD waitForElementToDisable");
			return true;

		} catch (Exception e) {
			log.info("Exception found in waitForElementToDisable: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Waits for an Element to get Stale or deleted from DOM
	 *
	 * @param iTimeOutInSeconds
	 *            time
	 * @param by
	 *            locator
	 */
	public boolean waitForElementToStale(int iTimeOutInSeconds, WebElement locator) {

		boolean isStale = true;
		int iAttempt = 0;
		try {
			iTimeOutInSeconds = iTimeOutInSeconds * 20;
			while (iTimeOutInSeconds > 0) {
				iAttempt++;
				log.info("Waiting for Element to Stale Attempt Number :" + iAttempt);
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
				log.info("Element :" + locator.isDisplayed());
				if (locator.getSize() == null) {
					isStale = false;
					break;
				}
				Thread.sleep(30L);
				iTimeOutInSeconds--;
			}
		} catch (NoSuchElementException e) {
			log.error("No Element Found.This Means Loader is no more in HTML. Moving out of waitForElementToStale!!!");
			isStale = false;
		} catch (StaleElementReferenceException s) {
			log.error("Given Element is stale from DOM Moving out of waitForElementToStale!!!");
			isStale = false;
		} catch (Exception e) {
			log.error("Some Exception ocurred Please check code!!!");
		} finally {
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(("browser.implicitwait")),
					// Need to Give Global implicit Timeout Value
					TimeUnit.SECONDS);
		}
		return isStale;
	}

	/**
	 * Waits for Page Load via Java Script Ready State
	 *
	 * @param iTimeOut
	 *            time
	 */
	public boolean waitForPageLoad(int iTimeOut) {

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
			return true;

		} catch (Exception e) {
			log.error("Error Occured waiting for Page Load " + driver.getCurrentUrl());
		}
		return false;
	}

	/**
	 * wait till tests present on element
	 *
	 * @param element
	 *            element
	 * @param maxSecondTimeout
	 *            time
	 * @param isFailOnExcaption
	 */
	public boolean waitForTextToBePresentOnElement(WebElement element, int maxSecondTimeout, String matchText,
			boolean... isFailOnExcaption) {
		try {
			log.info("INTO METHOD waitForTextToBePresentOnElement");
			maxSecondTimeout = maxSecondTimeout * 20;
			while ((!element.isDisplayed() && (maxSecondTimeout > 0)
					&& (element.getText().toLowerCase().equalsIgnoreCase(matchText.toLowerCase().trim())))) {
				log.info("Loading...CountDown=" + maxSecondTimeout);
				Thread.sleep(50L);
				maxSecondTimeout--;
			}
			if ((maxSecondTimeout == 0) && (isFailOnExcaption.length != 0)) {
				if (isFailOnExcaption[0]) {
					log.error("Element is not display within " + (maxSecondTimeout / 20) + "Sec.");
				}
			}
			log.info("OUT OF METHOD waitForTextToBePresentOnElement");
			return true;

		} catch (Exception e) {
			log.info("Exception found in waitForTextToBePresentOnElement: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Waits Until the Attribute of Element got Changed.
	 *
	 * @param webElement
	 *            element
	 * @param attribute
	 *            attribute
	 * @param value
	 *            value
	 * @param maxSecondTimeout
	 *            time
	 */
	public void waitTillElementAttributeChange(WebElement webElement, String attribute, String value,
			int maxSecondTimeout) {
		try {
			log.info("INTO METHOD waitTillElementAttributeChange");
			maxSecondTimeout = maxSecondTimeout * 20;
			while (webElement.getAttribute(attribute) != null) {
				if ((!webElement.getAttribute(attribute.trim()).toLowerCase().contains(value.trim().toLowerCase()))
						&& (maxSecondTimeout > 0)) {
					log.info("Loading...CountDown=" + maxSecondTimeout);
					Thread.sleep(50L);
					maxSecondTimeout--;
				}
			}
			log.info("OUT OF METHOD waitTillElementAttributeChange");
		} catch (Exception e) {
			log.error("SOME ERROR CAME IN METHOD->waitTillElementAttributeChange->" + e.getMessage());
		}
	}

	public WebElement customWaitToStale(int timeOut, WebElement locator) {

		int waitCounter = 0;
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);

		while (waitCounter <= timeOut) {
			try {
				Thread.sleep(1000);
				return locator;

			} catch (Exception e) {
				e.printStackTrace();
			}
			waitCounter = waitCounter + 1;
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
		return null;
	}
	
	/**
	 * Wait For Element To Disappear
	 *
	 * @param locator
	 *            locator
	 * @param maxSecondTimeout
	 *            time
	 */
	public boolean waitForElementToBeDisappear(By element,int maxSecondTimeout) {
		try {
			log.info("Waiting for an element to be invisible at locator:" + element);
			WebDriverWait wait = new WebDriverWait(driver, maxSecondTimeout);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
			return true;
		} catch (Exception e) {
			log.info("Exception found in waitForElementToBeDisappear: " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Wait For Element To Appear
	 *
	 * @param locator
	 *            locator
	 * @param maxSecondTimeout
	 *            time
	 */
	public boolean waitForElementToBePresent(By element,int maxSecondTimeout) {
		try {
			log.info("Waiting for an element to be visible at locator:" + element);
			WebDriverWait wait = new WebDriverWait(driver, maxSecondTimeout);
					wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			return true;
		} catch (Exception e) {
			log.info("Exception found in waitForElementToBePresent: " + e.getMessage());
			return false;
		}
	}

}