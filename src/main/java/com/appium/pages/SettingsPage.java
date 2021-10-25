package com.appium.pages;

import com.appium.base.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingsPage extends BaseTest {

	@AndroidFindBy(accessibility = "test-LOGOUT")
	private MobileElement logOutBtn;

	public LoginPage pressLogoutBtn() {
		click(logOutBtn);
		return new LoginPage();
	}
}
