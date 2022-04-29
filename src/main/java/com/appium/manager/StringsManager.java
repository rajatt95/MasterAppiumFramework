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

import java.util.HashMap;

public final class StringsManager {

	private StringsManager() {
	}

	private static ThreadLocal<HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();

	public static HashMap<String, String> getStrings() {
		return strings.get();
	}

	public static void setStrings(HashMap<String, String> driverref) {
		strings.set(driverref);
	}

	public static void unload() {
		strings.remove();
	}

}
