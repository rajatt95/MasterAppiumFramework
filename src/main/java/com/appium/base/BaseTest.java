package com.appium.base;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.appium.constants.FrameworkConstants.EXPECTED_DATA_XML_FILE;
import static com.appium.constants.FrameworkConstants.ROUTINGKEY;
import static com.appium.constants.FrameworkConstants.PLATFORM_ANDROID;
import static com.appium.constants.FrameworkConstants.PLATFORM_iOS;
import static com.appium.constants.FrameworkConstants.EXPLICIT_WAIT;
import static com.appium.constants.FrameworkConstants.SERVER_LOGS;
import static com.appium.constants.FrameworkConstants.LOGS;

import com.appium.manager.DateTimeManager;
import com.appium.manager.DeviceNameManager;
import com.appium.manager.DriverManager;
import com.appium.manager.PlatformManager;
import com.appium.manager.StringsManager;
import com.appium.reports.ExtentLogger;
import com.appium.utils.AppiumServerUtils;
import com.appium.utils.CapabilityUtils;
import com.appium.utils.ConfigLoader;
import com.appium.utils.TestUtils;
import com.appium.utils.VideoRecordUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;

public class BaseTest {

	private static AppiumDriverLocalService server;

	public BaseTest() {
		/* This will initalize the UI elements */
		PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
	}

	@BeforeMethod
	public void beforeMethod() {
		VideoRecordUtils.startRecording();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		VideoRecordUtils.stopRecording(result);
	}

	/* Executes before any of the test method class is executed */
	@Parameters({ "emulator", "platformName", "udid", "deviceName", "systemPort", "chromeDriverPort", "wdaLocalPort",
			"webkitDebugProxyPort" })
	@BeforeTest
	public void beforeTest(@Optional("androidOnly") String emulator, String platformName, String udid,
			String deviceName, @Optional("androidOnly") String systemPort,
			@Optional("androidOnly") String chromeDriverPort, @Optional("iOSOnly") String wdaLocalPort,
			@Optional("iOSOnly") String webkitDebugProxyPort) throws Exception {
		/*
		 * @Optional("androidOnly") -> means we are setting the default value as
		 * androidOnly and it is Optional
		 */
		URL url;
		InputStream stringsIS;
		AppiumDriver driver;
		PlatformManager.setPlatform(platformName);
		DeviceNameManager.setDeviceName(deviceName);
		String xmlFileName = EXPECTED_DATA_XML_FILE;
		stringsIS = getClass().getClassLoader().getResourceAsStream(xmlFileName);
		DateTimeManager.setDateTime(TestUtils.dateTime());
		StringsManager.setStrings(TestUtils.parseStringXML(stringsIS));
		url = new URL(ConfigLoader.getInstance().getAppiumURL());

		String strFile = LOGS + File.separator + platformName + "_" + deviceName;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		// Route logs to separate file for each thread
		ThreadContext.put(ROUTINGKEY, strFile);
		TestUtils.log().debug("Log file path: " + strFile);

		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			/* Configuration for Android device(s) */
			if (platformName.equalsIgnoreCase(PLATFORM_ANDROID)) {
				driver = CapabilityUtils.setCapabilityForAndroid(emulator, udid, deviceName, systemPort,
						chromeDriverPort, url, caps);
			}
			/* Configuration for iOS device(s) */
			else if (platformName.equalsIgnoreCase(PLATFORM_iOS)) {
				driver = CapabilityUtils.setCapabilityFor_iOS(url, caps);
			} else {
				throw new Exception("Invalid Platform: " + platformName);
			}
			DriverManager.setDriver(driver);
			String sessionId = driver.getSessionId().toString();
			TestUtils.log().debug("sessionId: " + sessionId);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (stringsIS != null) {
				stringsIS.close();
			}
		}
	}

	@AfterTest
	public void afterTest() {
		DriverManager.getDriver().quit();
	}

	public void waitForVisibility(MobileElement mobileElement) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), EXPLICIT_WAIT);
		wait.until(ExpectedConditions.visibilityOf(mobileElement));
	}

	public void click(MobileElement mobileElement) {
		waitForVisibility(mobileElement);
		TestUtils.log().info(mobileElement.getText() + " is clicked");
		ExtentLogger.info("<b>" + mobileElement.getText() + "</b> is clicked");
		mobileElement.click();
	}

	public void click(MobileElement mobileElement, String elementName) {
		waitForVisibility(mobileElement);
		TestUtils.log().info(elementName + " is clicked");
		ExtentLogger.info("<b>" + elementName + "</b> is clicked");
		mobileElement.click();
	}

	public void sendKeys(MobileElement mobileElement, String txt) {
		waitForVisibility(mobileElement);
		TestUtils.log().info("Filling " + txt + " in " + mobileElement.getText());
		ExtentLogger.info("Filling <b>" + txt + "</b> in <b>" + mobileElement.getText() + "</b>");
		mobileElement.sendKeys(txt);
	}

	public void sendKeys(MobileElement mobileElement, String txt, String elementName) {
		waitForVisibility(mobileElement);
		TestUtils.log().info("Filling " + txt + " in " + elementName);
		ExtentLogger.info("Filling <b>" + txt + "</b> in <b>" + elementName + "</b>");
		mobileElement.sendKeys(txt);
	}

	public String getAttribute(MobileElement mobileElement, String attribute) {
		waitForVisibility(mobileElement);
		TestUtils.log().info("Attribute: " + attribute + " value for " + mobileElement.getText() + " is - "
				+ mobileElement.getAttribute(attribute));
		/*
		 * ExtentLogger.info("Attribute: <b>" + attribute + "</b> value for <b>" +
		 * mobileElement.getText() + "</b> is - <b>" +
		 * mobileElement.getAttribute(attribute) + "</b>");
		 */return mobileElement.getAttribute(attribute);
	}

	public void closeApp() {
		((InteractsWithApps) DriverManager.getDriver()).closeApp();
	}

	public void launchApp() {
		((InteractsWithApps) DriverManager.getDriver()).launchApp();
	}

	@BeforeSuite
	public void beforeSuite() throws AppiumServerHasNotBeenStartedLocallyException, Exception {

		ThreadContext.put(ROUTINGKEY, SERVER_LOGS);
		server = AppiumServerUtils.getAppiumService();
		if (!AppiumServerUtils.checkIfAppiumServerIsRunnning(4723)) {
			server.start();
			/* This will not print the Appium server Logs in Eclipse IDE console */
			server.clearOutPutStreams();
			TestUtils.log().debug("Appium Server started.................");
		} else {
			TestUtils.log().debug("Appium Server is already running................");
		}
	}

	@AfterSuite
	public void afterSuite() {
		server.stop();
		TestUtils.log().debug("Appium Server stopped.............");
	}

}
