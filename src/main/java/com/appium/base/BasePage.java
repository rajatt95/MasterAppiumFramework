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

package com.appium.base;

import static com.appium.constants.FrameworkConstants.EXPLICIT_WAIT;
import static com.appium.constants.FrameworkConstants.LABEL;
import static com.appium.constants.FrameworkConstants.PLATFORM_ANDROID;
import static com.appium.constants.FrameworkConstants.PLATFORM_iOS;
import static com.appium.constants.FrameworkConstants.TEXT;
import static com.appium.constants.FrameworkConstants.WAIT;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appium.manager.DriverManager;
import com.appium.manager.PlatformManager;
import com.appium.reports.ExtentLogger;
import com.appium.reports.ExtentManager;
import com.appium.utils.ScreenshotUtils;
import com.appium.utils.TestUtils;
import com.appium.utils.VerificationUtils;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.common.util.concurrent.Uninterruptibles;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage {
//	private AppiumDriver<?> driver;
//	TestUtils utils = new TestUtils();

	public BasePage() {
//		this.driver = DriverManager.getDriver();
//		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);

		PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
	}

	public void waitForVisibility(MobileElement mobileElement) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), EXPLICIT_WAIT);
		wait.until(ExpectedConditions.visibilityOf(mobileElement));
	}

//	public void scrollToElement(MobileElement from, MobileElement to) {
//
//		TouchAction touchAction = new TouchAction(DriverManager.getDriver());
//		touchAction.press(ElementOption.element(from)).
//		/* It'll take 2 seconds to complete swipe operation */
//				waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).
//				moveTo(ElementOption.element(to))
//				.release()
//				.perform();
//	
//		}

	protected void swipeDown(int swipeCount) {

		Dimension dimension = DriverManager.getDriver().manage().window().getSize();
		int startX = dimension.width / 2;
		int endX = startX;
		int startY = (int) (dimension.height * 0.8);
		int endY = (int) (dimension.height * 0.2);

		for (int i = 0; i < swipeCount; i++) {
			TouchAction touchAction = new TouchAction(DriverManager.getDriver());
			touchAction.
			/*
			 * Using the PointOption is worst practice : Varies with device-to-device
			 * (Screen resolution)
			 */
			// press(PointOption.point(477, 1891)).
					press(PointOption.point(startX, startY)).
					/* It'll take 2 seconds to complete swipe operation */
					waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).
					// moveTo(PointOption.point(494, 386))
					moveTo(PointOption.point(endX, endY)).release().perform();
		}
	}

	public void click(MobileElement mobileElement) {
		waitForVisibility(mobileElement);
		TestUtils.log().info(mobileElement.getText() + " is clicked");
		ExtentLogger.info("<b>" + mobileElement.getText() + "</b> is clicked");
		mobileElement.click();
	}

	public void click(MobileElement mobileElement, String elementName) {
		waitForVisibility(mobileElement);
		TestUtils.log().info(elementName + " is clicked");
		ExtentLogger.info("<b>" + elementName + "</b> is clicked");
		mobileElement.click();
	}

	public void clear(MobileElement mobileElement, String elementName) {
		waitForVisibility(mobileElement);
		TestUtils.log().info("Clearing: " + elementName);
		mobileElement.clear();
	}

	public void sendKeys(MobileElement mobileElement, String txt) {
		waitForVisibility(mobileElement);
		TestUtils.log().info("Filling " + txt + " in " + mobileElement.getText());
		ExtentLogger.info("Filling <b>" + txt + "</b> in <b>" + mobileElement.getText() + "</b>");
		// mobileElement.clear();
		clear(mobileElement, txt);
		mobileElement.sendKeys(txt);

	}

	public void sendKeys(MobileElement mobileElement, String txt, String elementName) {
		waitForVisibility(mobileElement);
		TestUtils.log().info("Filling " + txt + " in " + elementName);
		ExtentLogger.info("Filling <b>" + txt + "</b> in <b>" + elementName + "</b>");
		// mobileElement.clear();
		clear(mobileElement, elementName);
		mobileElement.sendKeys(txt);

	}

	public String getAttribute(MobileElement mobileElement, String attribute) {
		waitForVisibility(mobileElement);
		TestUtils.log().info("Attribute: " + attribute + " value for " + mobileElement.getText() + " is - "
				+ mobileElement.getAttribute(attribute));
		/*
		 * ExtentLogger.info("Attribute: <b>" + attribute + "</b> value for <b>" +
		 * mobileElement.getText() + "</b> is - <b>" +
		 * mobileElement.getAttribute(attribute) + "</b>");
		 */return mobileElement.getAttribute(attribute);
	}

	/**
	 * Attribute
	 * 
	 * Android -> text
	 * 
	 * iOS -> label
	 * 
	 */
	public String getElementText(MobileElement mobileElement) {
		waitForVisibility(mobileElement);

		String platformName = PlatformManager.getPlatform();
		if (platformName.equalsIgnoreCase(PLATFORM_ANDROID)) {
			TestUtils.log().info("Attribute: text - value for " + mobileElement.getText() + " is - "
					+ mobileElement.getAttribute(TEXT));
			return mobileElement.getAttribute(TEXT);

		} else if (platformName.equalsIgnoreCase(PLATFORM_iOS)) {
			TestUtils.log().info("Attribute: label - value for " + mobileElement.getText() + " is - "
					+ mobileElement.getAttribute(LABEL));
			return mobileElement.getAttribute(LABEL);
		}
		return null;

	}

	protected void captureScreenshot() {
		ExtentManager.getExtentTest().info("Capturing Screenshot",
				MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
	}

	protected void waitForSomeTime() {
		Uninterruptibles.sleepUninterruptibly(WAIT, TimeUnit.SECONDS);
	}

	protected void waitForGivenTime(long time) {
		Uninterruptibles.sleepUninterruptibly(time, TimeUnit.SECONDS);
	}

	protected void webElementPresent(MobileElement mobileElement, String elementName) {
		VerificationUtils.validate(isDisplayed(mobileElement), true, elementName + " presence");
	}

	protected void webElementAbsent(MobileElement mobileElement, String elementName) {
		VerificationUtils.validate(isDisplayed(mobileElement), false, elementName + " absence");
	}

	private boolean isDisplayed(MobileElement element) {
		try {
			waitForVisibility(element);
			return element.isDisplayed();
		} catch (NoSuchElementException | TimeoutException exception) {
			ExtentLogger.fail("Element is not present");
			return false;
		}
	}

}
