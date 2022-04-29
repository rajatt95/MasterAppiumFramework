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

public final class PlatformManager {

	private PlatformManager() {
	}

	private static ThreadLocal<String> platform = new ThreadLocal<String>();

	public static String getPlatform() {
		return platform.get();
	}

	public static void setPlatform(String driverref) {
		platform.set(driverref);
	}

	public static void unload() {
		platform.remove();
	}

}
