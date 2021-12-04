package com.appium.pages;

import com.appium.base.BasePage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SettingsPage extends BasePage {

	@AndroidFindBy(accessibility = "test-LOGOUT")
	@iOSXCUITFindBy (id = "test-LOGOUT")
	private MobileElement logOutBtn;
	private String logOutBtnTxt = "Logout button";

	public LoginPage pressLogoutBtn() {
		click(logOutBtn, logOutBtnTxt);
		return new LoginPage();
	}
}
