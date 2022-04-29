/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Appium Mobile Automation - Android & iOS + Frameworks + CICD (https://www.udemy.com/course/the-complete-appium-course-for-ios-and-android/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 */

/***************************************************/

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
