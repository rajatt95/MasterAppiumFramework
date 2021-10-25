package com.appium.utils;

import org.testng.Assert;

import com.appium.constants.FrameworkConstants;
import com.appium.reports.ExtentLogger;

public class VerificationUtils {

	public static void validate(Object actual, Object expected, String message) {

		try {
			logFile(actual, expected);
			Assert.assertEquals(actual, expected, message);
			ExtentLogger.pass(FrameworkConstants.ASSERTION_FOR + " - <b> <u>" + message
					+ "</u> </b>   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected,
					true);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail(FrameworkConstants.ASSERTION_FOR + " - <b> <u>" + message
					+ "   |   <b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: </i> </b>" + expected);
			Assert.fail(message);
		}
	}

	private static void logFile(Object actual, Object expected) {
		TestUtils.log().info("Actual: " + actual);
		TestUtils.log().info("expected: " + expected);
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
