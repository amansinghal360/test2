package com.dp.qa.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ActionsUtil {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor executor;
	private Actions action;
	public final static Logger log = Logger.getLogger(ActionsUtil.class);

	public ActionsUtil(WebDriver driver) {

		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, 10);
		executor = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}

	public void sendKeysChord(String keyChord) {
		action.sendKeys(keyChord).build().perform();
	}

	/**
	 * Method description: Closes the current opened window
	 */
	public void closeCurrentTab() {
		try {
			action.keyDown(Keys.CONTROL).sendKeys(Keys.chord("w")).build().perform();
			log.info("Closed Current tab by pressing Ctrl+w");

		} catch (NoSuchWindowException ns) {
			ns.printStackTrace();
			log.error("No window exist. " + ns.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while closing current window's tab. " + e.getMessage());
		}
	}

	/**
	 * @param locator
	 *            Method description: It performs mouse hover
	 */
	public void performMouseHover(WebElement element) {
		try {
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not perform mouse hover. " + e.getMessage());
		}
	}

	/**
	 * @param WebElement
	 *            element
	 * @param elementTobeClicked
	 *            Method description: It performs mouse hover and clicks on the
	 *            element
	 */
	public void performMouseHoverAndClick(WebElement element, WebElement elementTobeClicked) {

		try {
			action.moveToElement(element).build().perform();
			log.info("Mouse hover performed on locator: " + element);
			elementTobeClicked.click();
			log.info("Element clicked successfully");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not perform mouse hover and click operation.");
		}
	}

	/**
	 * Method Description: MoveSlider - Method to move slider from one position to
	 * other.
	 * 
	 * @param WebElement
	 *            element
	 * @param xAxis
	 *            x
	 * @param yAxis
	 *            y
	 */
	public void moveSlider(WebElement element, int xAxis, int yAxis) {
		try {
			action.dragAndDropBy(element, xAxis, yAxis).build().perform();
			log.info("Moved slider successfully");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not move slider from one position to other.");
		}
	}

	/**
	 * Method Description: Method clicks and holds the source webelement, moves to
	 * destination webelement and release the element
	 * 
	 * @param WebElement
	 *            source
	 * @param WebElement
	 *            destination
	 */
	public void dragAndDrop(WebElement source, WebElement destination) {

		try {
			// For each action we need to build and Perform
			action.clickAndHold(source).build().perform();
			action.moveToElement(destination).build().perform();
			action.release(destination).build().perform();
			log.info("Performed drag and drop from source to destination");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not drag and drop an element from source to destination.");
		}
	}

	/**
	 * @param WebElement
	 *            element
	 */
	public void rightClick(WebElement element) {
		try {
			action.contextClick(element).build().perform(); // Context Click
			action.sendKeys(Keys.ARROW_RIGHT).build().perform();
			goToSleep(1000);
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
			goToSleep(1000);
			action.sendKeys(Keys.ENTER).build().perform();
			log.info("Right click performed successfully");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not right click.");
		}
	}

	/**
	 * Method Description: Select multiple values in Multiple Select dropdown
	 * 
	 * @param WebElement
	 *            element
	 * @param inputdata
	 *            input
	 * @param splitVia
	 *            text
	 */
	public void selectMulipleDropDownValues(WebElement element, String inputdata, String splitVia) {

		int sleepTime = 4000;
		String[] input = inputdata.split(splitVia);
		Actions builder = new Actions(driver);
		for (String option : input) {
			builder.keyDown(Keys.CONTROL);
			try {
				new Select(element).selectByVisibleText(option);
			} catch (Exception e) {
				goToSleep(sleepTime);
				log.error(e.getMessage());
			}
		}
	}

	/**
	 * Method Description: Double clicks at the last known mouse coordinates
	 * 
	 * @param locator
	 *            locator
	 */
	public void doubleClickTheLocator(WebElement element) {

		try {
			action.doubleClick(element);
			log.info("Performed double click on locator: " + element);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not double click the locator.");
		}
	}

	/**
	 * Method Description: Clicks at the last known mouse coordinates
	 * 
	 * @param locator
	 *            locator
	 */
	public void clickTheLocator(WebElement element) {
		try {
			action.click(element);
			log.info("Performed click operation on locator: " + element);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Couldn't click the locator.");
		}
	}

	/**
	 * @param key
	 *            key
	 */
	public void pressKey(Keys key) {
		try {
			action.keyDown(key);
			log.info("Key is pressed successfully");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not press key.");
		}
	}

	/**
	 * @param key
	 *            key
	 */
	public void releaseKey(Keys key) {
		try {
			action.keyUp(key);
			log.info("Key is pressed successfully");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not release key");
		}
	}

	/**
	 * @param WebElement
	 *            locator
	 */
	public void clickandhold(WebElement element) {
		try {
			action.clickAndHold(element);
			log.info("Clicked and held on locator: " + element);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Couldn't click and hold locator");
		}
	}

	public void goToSleep(int TimeInMillis) {
		try {
			Thread.sleep(TimeInMillis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
