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
