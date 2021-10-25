package com.appium.manager;

import io.appium.java_client.AppiumDriver;

public final class DriverManager {

	private DriverManager() {
	}

	private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

	public static AppiumDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(AppiumDriver driverref) {
		driver.set(driverref);
	}

	public static void unload() {
		driver.remove();
	}

}
