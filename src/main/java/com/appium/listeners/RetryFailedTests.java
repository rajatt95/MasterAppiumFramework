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

package com.appium.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.appium.constants.FrameworkConstants.YES;
import com.appium.utils.ConfigLoader;

public class RetryFailedTests implements IRetryAnalyzer {

	private int count = 0;
	private int retries = 1;

	public boolean retry(ITestResult result) {

		boolean value = false;
		if (ConfigLoader.getInstance().getRetryFailedTests().equalsIgnoreCase(YES)) {
			if (count < retries) {
				count++;
				return true;
			}
		}
		return value;
	}
}
