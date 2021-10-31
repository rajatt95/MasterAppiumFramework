package com.appium.components;

import com.appium.base.BasePage;
import com.appium.pages.SettingsPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MenuPage extends BasePage {

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")
	private MobileElement settingsBtn;
	private String settingsBtnTxt = "Settings button";

	public SettingsPage pressSettingsBtn() {
		click(settingsBtn, settingsBtnTxt);
		return new SettingsPage();
	}

}
