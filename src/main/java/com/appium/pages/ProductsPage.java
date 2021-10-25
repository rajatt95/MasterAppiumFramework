package com.appium.pages;

import com.appium.base.MenuPage;
import com.appium.constants.FrameworkConstants;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends MenuPage {

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
	private MobileElement productTitle;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
	private MobileElement SLBTitle;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
	private MobileElement SLBPrice;

	public String getTitle() {
		// return getAttribute(productTitle, "text");
		return getAttribute(productTitle, FrameworkConstants.TEXT);
	}

	public String getSLBTitle() {
		return getAttribute(SLBTitle, FrameworkConstants.TEXT);
	}

	public String getSLBPrice() {
		return getAttribute(SLBPrice, FrameworkConstants.TEXT);
	}

	public ProductDetailsPage pressSLBTitle() {
		click(SLBTitle);
		return new ProductDetailsPage();
	}
}
