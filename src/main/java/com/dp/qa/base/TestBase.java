package com.dp.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.dp.qa.utils.UIUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public WebDriver driver;
	protected static Properties props;
	public final static Logger log = Logger.getLogger("TestBase");
	public static String reportFolderPath;
	public static String fileDownloadPath;
	static File directory = new File(".");
	protected static ExtentReports extent;
	public static ExtentTest testReport;
	protected UIUtil util;

	@BeforeSuite
	public void configure() {
		String extentConfigPath, reportPath, log4jConfigPath;
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		PropertyConfigurator.configure("log4j.properties");
		try {

			// Log4j path
			log4jConfigPath = directory.getCanonicalPath() + File.separator + "src" + File.separator + "main"
					+ File.separator + "java" + File.separator + "com" + File.separator + "dp" + File.separator
					+ "resources" + File.separator + "log4j.properties";
			PropertyConfigurator.configure(log4jConfigPath);
			log.info("Log4j config path: " + log4jConfigPath);

			// Extent report path
			reportFolderPath = directory.getCanonicalPath() + File.separator + "AutomationReport" + File.separator
					+ "TestRun_" + timeStamp;
			FileUtils.forceMkdir(new File(reportFolderPath));
			reportPath = reportFolderPath + File.separator + "AutomationReport.html";
			log.info("Extent report folder path: " + reportPath);

			// Extent config path
			extentConfigPath = directory.getCanonicalPath() + File.separator + "src" + File.separator + "main"
					+ File.separator + "java" + File.separator + "com" + File.separator + "dp" + File.separator
					+ "resources" + File.separator + "extent-config.xml";
			log.info("Extent report config path: " + extentConfigPath);

			extent = new ExtentReports(reportPath, false);
			extent.loadConfig(new File(extentConfigPath));
		} catch (IOException e) {
			log.error("IO exception occur: " + e.getMessage());
		}
	}

	@BeforeTest(alwaysRun = true)
	public void testStart() throws IOException {
		extent.addSystemInfo("Project Name", "Developer Program");
		extent.addSystemInfo("Time Zone", System.getProperty("user.timezone"));
		extent.addSystemInfo("User Location", System.getProperty("user.country"));
		extent.addSystemInfo("OS version", System.getProperty("os.version"));
		extent.addSystemInfo("Java Version", System.getProperty("java.version"));
	}

	@BeforeClass(alwaysRun = true)
	@Parameters("browserName")
	public void setUpDriver(String browserName) throws MalformedURLException {
		loadPropertiesFile();
		initDriver(browserName);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(props.getProperty("Base_URL") + "/site/global/home/index.gsp");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		log.info("Application is loading: " + props.getProperty("Base_URL"));
	}

	@BeforeMethod(alwaysRun = true)
	public void startReporting(Method method) {
		String testName = this.getClass().getSimpleName() + " : " + method.getName();
		testReport = extent.startTest(testName, method.getAnnotation(Test.class).description());
		log.info("Extent report logging started for " + testName);
		System.out.println(">>>>> Execution started: " + testName);
		testReport.log(LogStatus.INFO, "Test execution started.");
	}

//	@AfterMethod(alwaysRun = true)
	public void reportFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				String screenshotName = util.takeScreenShot();
				testReport.log(LogStatus.FAIL,
						"Test case failed with exception: <br>" + result.getThrowable().toString().replace("\n", "<br>")
								+ "<br><b>Snapshot:</b><br>" + testReport.addScreenCapture(screenshotName));
			} catch (Exception e) {
				log.error("Unable to add screenshot to reports");
			}
		}
		testReport.log(LogStatus.INFO, "Test execution completed.");
		extent.endTest(testReport);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		if (driver != null) {
		driver.quit();
		 }
	}

//	@AfterSuite(alwaysRun = true)
	public void endReporting() {
		extent.flush();
		extent.close();

		try {
			// copy the extend reports to new folder dedicated for Jenkins integration
			File srcDir = new File(reportFolderPath);
			File destDir = new File(
					directory.getCanonicalPath() + File.separator + "AutomationReport" + File.separator + "LatestRun");
			FileUtils.forceMkdir(destDir);
			FileUtils.cleanDirectory(destDir);
			FileUtils.copyDirectory(srcDir, destDir);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initDriver(String browserName) throws MalformedURLException {
		//String browserName = System.getProperty("browser");
		log.info("Browser set to: " + browserName);
	//	String hubUrl = "http://localhost:4447/wd/hub";
	//	DesiredCapabilities cap = new DesiredCapabilities();
	//	cap.setBrowserName(browserName);
		//cap.setPlatform(Platform.LINUX);
		
		
		if (browserName.equals("chrome") || browserName.equals("chromeMac")) {
			if (browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/drivers/ChromeDriver/chromedriver.exe");
			} else if (browserName.equals("chromeMac")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/drivers/ChromeDriver/chromedriver");
			}
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-web-security");
			options.addArguments("--no-proxy-server");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.default_content_setting_values.notifications", 2);
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
		//	driver = new RemoteWebDriver(new URL(hubUrl), cap);
			log.info("Initializing Chrome Browser");

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/drivers/FireFoxGeckoDriver/geckodriver.exe");
			driver = new FirefoxDriver();
			//driver = new RemoteWebDriver(new URL(hubUrl), cap);
			log.info("Initializing Firefox Browser");
		}
		else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			options.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 10000);
			options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
			options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

			driver = new InternetExplorerDriver(options);
			log.info("Initializing Internet explorer Browser");
		} else {
			System.err.println("Please Enter Correct Browser");
			log.info("Please Enter Correct Browser");
		}
	}

	private void loadPropertiesFile() {
		props = new Properties();
		InputStream input = null;
	//	String environment = System.getProperty("env");
		String environment = "qa";
		log.info("Environment set to: " + environment);
		try {
			input = new FileInputStream(environment + ".properties");

			// load a properties file
			props.load(input);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
