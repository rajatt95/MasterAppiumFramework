package com.appium.pages;

import com.appium.base.BasePage;
import com.appium.constants.FrameworkConstants;
import com.appium.manager.DriverManager;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

//public class ProductDetailsPage extends MenuPage
public class ProductDetailsPage extends BasePage {

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[1]")
	private MobileElement SLBTitle;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[2]")
	private MobileElement SLBTxt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Price\"]")
	@iOSXCUITFindBy (id = "test-Price")
	private MobileElement SLBPrice;

	@AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
	@iOSXCUITFindBy (id = "test-BACK TO PRODUCTS")
	private MobileElement backToProductsBtn;

	public String getSLBTitle() {
		return getAttribute(SLBTitle, FrameworkConstants.TEXT);
	}

	public String getSLBTxt() {
		return getAttribute(SLBTxt, FrameworkConstants.TEXT);
	}

	public String getSLBPrice() {
		return getAttribute(SLBPrice, FrameworkConstants.TEXT);
	}

	public ProductDetailsPage scrollToSLBPrice() {
		// scrollToElement();
		((FindsByAndroidUIAutomator) DriverManager.getDriver())
				.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
						+ ".scrollable(true)).scrollIntoView(" + "new UiSelector().description(\"test-Price\"));");
		return this;
	}

	public ProductDetailsPage pressBackToProductsBtn() {
		click(backToProductsBtn);
		return new ProductDetailsPage();
	}

}
