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

public final class DateTimeManager {

	private DateTimeManager() {
	}

	private static ThreadLocal<String> dateTime = new ThreadLocal<String>();

	public static String getDateTime() {
		return dateTime.get();
	}

	public static void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public static void unload() {
		dateTime.remove();
	}

}
