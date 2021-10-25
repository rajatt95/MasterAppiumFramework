package com.appium.utils;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.appium.constants.FrameworkConstants;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CapabilityUtils {

	private CapabilityUtils() {

	}

	public static AppiumDriver setCapabilityForAndroid(String emulator, String udid, String deviceName,
			String systemPort, String chromeDriverPort, URL url, DesiredCapabilities caps) {
		AppiumDriver driver;
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigLoader.getInstance().getAndroidAutomationName());
		caps.setCapability(MobileCapabilityType.UDID, udid);
		caps.setCapability(FrameworkConstants.CAPABILITY_ANDROID_APP_PACKAGE,
				ConfigLoader.getInstance().getAndroidAppPackage());
		caps.setCapability(FrameworkConstants.CAPABILITY_ANDROID_APP_ACTIVITY,
				ConfigLoader.getInstance().getAndroidAppActivity());
		/* This capability is used to install the application */
		caps.setCapability(FrameworkConstants.CAPABILITY_APP,
				FrameworkConstants.RESOURCES_TEST_PATH + ConfigLoader.getInstance().getAndroidApplocation());
		caps.setCapability(FrameworkConstants.CAPABILITY_ANDROID_SYSTEM_PORT, systemPort);
		caps.setCapability(FrameworkConstants.CAPABILITY_ANDROID_CHROME_DRIVER_PORT, chromeDriverPort);

		if (emulator.equals(FrameworkConstants.TRUE)) {
			caps.setCapability(FrameworkConstants.CAPABILITY_ANDROID_AVD, deviceName);
			// caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		}

		driver = new AndroidDriver(url, caps);
		return driver;
	}

	public static AppiumDriver setCapabilityFor_iOS(URL url, DesiredCapabilities caps) {
		AppiumDriver driver;
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigLoader.getInstance().getiOSAutomationName());
		caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");

		/* This capability is used to install the application */
		caps.setCapability(FrameworkConstants.CAPABILITY_APP,
				FrameworkConstants.RESOURCES_TEST_PATH + ConfigLoader.getInstance().getiOSAppLocation());

		driver = new IOSDriver(url, caps);
		return driver;
	}

}
