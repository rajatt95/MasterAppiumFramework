package com.appium.listeners;

import java.util.Arrays;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.appium.annotations.FrameworkAnnotation;
import com.appium.reports.ExtentLogger;
import com.appium.reports.ExtentReport;
import com.appium.utils.EmailSendUtils;
import com.appium.utils.ZipUtils;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ListenerClass implements ITestListener, ISuiteListener {

	static int count_passedTCs;
	static int count_skippedTCs;
	static int count_failedTCs;
	static int count_totalTCs;

	public void onStart(ISuite suite) {
		ExtentReport.initReports();
	}

	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();
		ZipUtils.zip();
		EmailSendUtils.sendEmail(count_totalTCs, count_passedTCs, count_failedTCs, count_skippedTCs);
	}

	public void onTestStart(ITestResult result) {

		// System.out.println("onTestStart() ");
		count_totalTCs = count_totalTCs + 1;
		ExtentReport.createTest(result.getMethod().getMethodName());
		// ExtentReport.createTest(result.getMethod().getDescription());

		ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).author());

		ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).category());

		ExtentReport.addDevices();
		// ExtentLogger.info("<b>" +
		// BrowserOSInfoUtils.getOS_Browser_BrowserVersionInfo() + "</b>");

	}

	public void onTestSuccess(ITestResult result) {
		count_passedTCs = count_passedTCs + 1;
		// TMB
		// ExtentLogger.pass(result.getMethod().getMethodName() + " is passed");

		// Rajat
		String logText = "<b>" + result.getMethod().getMethodName() + " is passed.</b>";
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		// ExtentLogger.pass(markup_message, true);
		ExtentLogger.pass(markup_message);

	}

	public void onTestFailure(ITestResult result) {
		count_failedTCs = count_failedTCs + 1;
		// TMB
		// ExtentLogger.fail(result.getMethod().getMethodName() + " is failed");
		// ExtentLogger.fail(result.getMethod().getMethodName() + " is failed", true);
		ExtentLogger.fail("<b><i>" + result.getThrowable().toString() + "</i></b>");
		// ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));

		// ExtentLogger.info("------------------------------------------------");

		// Rajat
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
	
		String message = "<details><summary><b><font color=red> Exception occured, click to see details: <i class='fa fa-frown-o'></i> </font></b>"
				+ "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n";
		ExtentLogger.fail(message);
		
		/*
		 * ExtentLogger.
		 * fail("<details><summary><b><font color=red> Exception occured, click to see details: </font></b>"
		 * + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
		 */		String logText = "<b>" + result.getMethod().getMethodName() + " is failed.</b>";
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.RED);
		ExtentLogger.fail(markup_message, true);

	}

	public void onTestSkipped(ITestResult result) {

		count_skippedTCs = count_skippedTCs + 1;
		// TMB
		// ExtentLogger.skip(result.getMethod().getMethodName() + " is skipped");
		// ExtentLogger.skip(result.getMethod().getMethodName() + " is skipped", true);

		// ExtentLogger.skip(result.getThrowable().toString());
		ExtentLogger.skip("<b><i>" + result.getThrowable().toString() + "</i></b>");
		// Rajat
		String logText = "<b>" + result.getMethod().getMethodName() + " is skipped.</b>";
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		ExtentLogger.skip(markup_message, true);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		/*
		 * As of now, we are not using it
		 */
	}

	public void onStart(ITestContext result) {
		/*
		 * As of now, we are not using it.
		 * 
		 * We have only 1 <test> in suite. We do not have any implementation for it.
		 */
	}

	public void onFinish(ITestContext result) {
		/*
		 * As of now, we are not using it.
		 * 
		 * We have only 1 <test> in suite. We do not have any implementation for it.
		 */
	}

}
