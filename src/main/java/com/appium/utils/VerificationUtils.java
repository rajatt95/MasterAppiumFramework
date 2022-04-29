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

package com.appium.utils;

import org.testng.Assert;

import static com.appium.constants.FrameworkConstants.ASSERTION_FOR;
import com.appium.reports.ExtentLogger;

public class VerificationUtils {

	public static void validate(Object actual, Object expected, String message) {

		try {
			logFile(actual, expected);
			Assert.assertEquals(actual, expected, message);
			ExtentLogger.pass(ASSERTION_FOR + " - <b> <u>" + message
					+ "</u> </b>   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected,
					true);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail(ASSERTION_FOR + " - <b> <u>" + message
					+ "   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
			Assert.fail(message);
		}
	}

	private static void logFile(Object actual, Object expected) {
		TestUtils.log().info("Actual: " + actual);
		TestUtils.log().info("Expected: " + expected);
	}

	public static void validateResponse(boolean result, String message) {
		try {
			Assert.assertTrue(result);
			ExtentLogger.pass("<b><i>" + message + "</b></i>", true);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail("<b><i>" + message + "</b></i>");
			Assert.fail(message);
		}
	}

}
