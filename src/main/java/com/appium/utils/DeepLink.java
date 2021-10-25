package com.appium.utils;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appium.constants.FrameworkConstants;
import com.appium.manager.DriverManager;
import com.appium.manager.PlatformManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

public class DeepLink {

	public static void OpenAppWith(String url) {
		AppiumDriver driver = DriverManager.getDriver();

		String platform = PlatformManager.getPlatform();
		if (platform.equalsIgnoreCase(FrameworkConstants.PLATFORM_ANDROID)) {
			HashMap<String, String> deepUrl = new HashMap<String, String>();
			deepUrl.put("url", url);
			deepUrl.put("package", "com.swaglabsmobileapp");
			driver.executeScript("mobile: deepLink", deepUrl);

		} else if (platform.equalsIgnoreCase(FrameworkConstants.PLATFORM_iOS)) {
			By urlBtn = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' && name CONTAINS 'URL'");
			By urlFld = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeTextField' && name CONTAINS 'URL'");
			By openBtn = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' && name CONTAINS 'Open'");
			driver.activateApp("com.apple.mobilesafari");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(urlBtn)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(urlFld)).sendKeys("" + url + "\uE007");
			wait.until(ExpectedConditions.visibilityOfElementLocated(openBtn)).click();
		}
	}
}
