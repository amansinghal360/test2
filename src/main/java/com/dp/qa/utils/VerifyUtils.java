package com.dp.qa.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.dp.qa.base.TestBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Aman Singhal
 * This class is for for implenting Assertions
 */
public class VerifyUtils {

	private WebDriver driver;
	public final static Logger log = Logger.getLogger("VerifyUtils");
	private AssertionError error;
	private ExtentTest testReport;

	private boolean passStatus;

	VerifyUtils(WebDriver driver, ExtentTest testReport) {
		super();
		this.driver = driver;
		this.testReport = testReport;
	}

	private VerifyUtils(AssertionError error) {
		this.passStatus = false;
		this.error = error;
	}

	private VerifyUtils() {
		this.passStatus = true;
	}

	public boolean isPassStatus() {
		return passStatus;
	}

	public void exitOnFailure() {

		log.info("Checking for Assertion errors to exit the test");
		if (error != null) {
			log.info("Exiting the test on Assertion failure as exitOnFail flag is set to True");
			throw error;
		}
	}

	private void reportFailure(boolean screenshotOnFailure, String description, AssertionError e) {

		log.info(description + ": Assertion FAILED with exception " + e.getMessage());
		System.out.println(description + ": Fail");

		if (screenshotOnFailure) {
			String screenshotName = "image" + System.currentTimeMillis() + ".png";
			try {
				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, new File(TestBase.reportFolderPath + File.separator + screenshotName));
				log.info("Screenshot " + screenshotName + " taken at " + TestBase.reportFolderPath);

			} catch (IOException io) {
				log.error("Fail to take screenshot");
			}
			testReport.log(LogStatus.FAIL, description + "<br><b>Failed: </b>" + e.getMessage().replace("\n", "<br>")
					+ "<br><b>Snapshot:</b><br>" + testReport.addScreenCapture(screenshotName));

		} else {
			testReport.log(LogStatus.FAIL, description + "<br><b>Failed: </b>" + e.getMessage().replace("\n", "<br>"));
		}
	}

	/**
	 * @param actual
	 *            actual object value
	 * @param expected
	 *            expected object value
	 * @param description
	 *            description for extent report
	 * @param screenshotOnFailure
	 *            take screenshot
	 */
	public VerifyUtils verifyEquals(Object actual, Object expected, String description, boolean screenshotOnFailure) {

		try {
			Assert.assertEquals(actual, expected);
			log.info(description + ": Assertion is successful");
			testReport.log(LogStatus.PASS, description + ": <b>Success</b>");
			return new VerifyUtils();

		} catch (AssertionError e) {
			reportFailure(screenshotOnFailure, description, e);
			return new VerifyUtils(e);
		}
	}

	public VerifyUtils verifyNotEquals(Object actual, Object expected, String description,
			boolean screenshotOnFailure) {

		try {
			Assert.assertNotEquals(actual, expected);
			log.info(description + ": Assertion is successful");
			testReport.log(LogStatus.PASS, description + ": <b>Success</b>");
			return new VerifyUtils();

		} catch (AssertionError e) {
			reportFailure(screenshotOnFailure, description, e);
			return new VerifyUtils(e);
		}
	}

	/**
	 * @param condition
	 *            condition to be verified
	 * @param message
	 *            message shown on fail
	 * @param description
	 *            description for extent report
	 * @param screenshotOnFailure
	 *            take screenshot
	 */
	public VerifyUtils verifyTrue(boolean condition, String message, String description, boolean screenshotOnFailure) {

		try {
			Assert.assertTrue(condition, message);
			log.info(description + ": Assertion is successful");
			testReport.log(LogStatus.PASS, description + ": <b>Success</b>");
			return new VerifyUtils();

		} catch (AssertionError e) {
			reportFailure(screenshotOnFailure, description, e);
			return new VerifyUtils(e);
		}
	}

	public VerifyUtils verifyFalse(boolean condition, String message, String description, boolean screenshotOnFailure) {

		try {
			Assert.assertFalse(condition, message);
			log.info(description + ": Assertion is successful");
			testReport.log(LogStatus.PASS, description + ": <b>Success</b>");
			return new VerifyUtils();

		} catch (AssertionError e) {
			reportFailure(screenshotOnFailure, description, e);
			return new VerifyUtils(e);
		}
	}
}