package com.appium.base;

import static com.appium.constants.FrameworkConstants.EXPECTED_DATA_XML_FILE;
import static com.appium.constants.FrameworkConstants.LOGS;
import static com.appium.constants.FrameworkConstants.PLATFORM_ANDROID;
import static com.appium.constants.FrameworkConstants.PLATFORM_iOS;
import static com.appium.constants.FrameworkConstants.ROUTINGKEY;
import static com.appium.constants.FrameworkConstants.SERVER_LOGS;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.appium.manager.DateTimeManager;
import com.appium.manager.DeviceNameManager;
import com.appium.manager.DriverManager;
import com.appium.manager.PlatformManager;
import com.appium.manager.StringsManager;
import com.appium.utils.AppiumServerUtils;
import com.appium.utils.CapabilityUtils;
import com.appium.utils.ConfigLoader;
import com.appium.utils.TestUtils;
import com.appium.utils.VideoRecordUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;

public class BaseTest {

	private static AppiumDriverLocalService server;

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
			/* This will not print the Appium server Logs in IDE console */
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
