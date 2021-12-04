package com.appium.tests;

import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_FILE;
import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_INVALID_PASSWORD;
import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_VALID_USER;
import static com.appium.constants.FrameworkConstants.EXPECTED_DATA_KEY_PRODUCT_TITLE;
import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_INVALID_USER;
import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_PASSWORD;
import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_USERNAME;
import static com.appium.constants.FrameworkConstants.EXPECTED_DATA_KEY_ERR_INAVLID_CREDENTIALS;

import java.lang.reflect.Method;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.appium.annotations.FrameworkAnnotation;
import com.appium.base.BaseTest;
import com.appium.enums.AuthorType;
import com.appium.enums.CategoryType;
import com.appium.manager.StringsManager;
import com.appium.pages.LoginPage;
import com.appium.pages.ProductsPage;
import com.appium.utils.JSONUtils;
import com.appium.utils.TestUtils;
import com.appium.utils.VerificationUtils;

public class LoginTests extends BaseTest {

	LoginPage loginPage;
	ProductsPage productsPage;
	JSONObject loginUsers;

	@AfterClass
	public void afterClass() {
		closeApp();
		launchApp();
	}

	@BeforeMethod
	public void beforeMethod(Method method) {

		TestUtils.log().debug("---------------------------------------------------");
		TestUtils.log().debug("******************* Test started: " + method.getName() + "*******************");

		loginPage = new LoginPage();
		// productsPage = new ProductsPage();
	}

	@AfterMethod
	public void afterMethod(Method method) {
		TestUtils.log().debug("******************* Test ended: " + method.getName() + "*******************");
		TestUtils.log().debug("---------------------------------------------------");
		// closeApp();
		// launchApp();

	}

	@FrameworkAnnotation(author = { AuthorType.RAJAT, AuthorType.GAUTAM }, category = { CategoryType.SMOKE,
			CategoryType.SANITY, CategoryType.REGRESSION })
	@Test(groups = { "SANITY", "SMOKE", "REGRESSION" })
	public void invalidUserName() {

		JSONObject jsonObject_InvalidUser = 
				new JSONUtils()
					.getJSONObject(TEST_DATA_JSON_FILE)
					.getJSONObject(TEST_DATA_JSON_INVALID_USER);
		
		String username = jsonObject_InvalidUser.getString(TEST_DATA_JSON_USERNAME).toString();
		String password = jsonObject_InvalidUser.getString(TEST_DATA_JSON_PASSWORD).toString();
		
		loginPage.
			enterUsername(username).
			enterPassword(password).
			pressLoginBtn();

		String actualErrTxt = loginPage.getErrorTxt();
		String expectedErrTxt = StringsManager.getStrings()
				.get(EXPECTED_DATA_KEY_ERR_INAVLID_CREDENTIALS);
		VerificationUtils.validate(actualErrTxt, expectedErrTxt, "Error Message for Invalid Credentials");
	}

	@FrameworkAnnotation(author = { AuthorType.NISHANT, AuthorType.PANKAJ }, category = { CategoryType.SANITY,
			CategoryType.BVT, CategoryType.REGRESSION })
	@Test(groups = { "SANITY", "BVT", "REGRESSION" })
	public void invalidPassword() {

		JSONObject jsonObject_InvalidUser = 
				new JSONUtils()
					.getJSONObject(TEST_DATA_JSON_FILE)
					.getJSONObject(TEST_DATA_JSON_INVALID_PASSWORD);
		
		String username = jsonObject_InvalidUser.getString(TEST_DATA_JSON_USERNAME).toString();
		String password = jsonObject_InvalidUser.getString(TEST_DATA_JSON_PASSWORD).toString();

		loginPage.
			enterUsername(username).
			enterPassword(password).
			pressLoginBtn();

		String actualErrTxt = loginPage.getErrorTxt();
		String expectedErrTxt = StringsManager.getStrings()
				.get(EXPECTED_DATA_KEY_ERR_INAVLID_CREDENTIALS);

		VerificationUtils.validate(actualErrTxt, expectedErrTxt, "Error Message for Invalid Credentials");
	}

	@FrameworkAnnotation(author = { AuthorType.RAJAT, AuthorType.PANKAJ }, category = { CategoryType.SANITY,
			CategoryType.BVT, CategoryType.REGRESSION })
	@Test(groups = { "SANITY", "BVT", "REGRESSION" })
	public void successfulLogin() {

		JSONObject jsonObject_InvalidUser = 
				new JSONUtils()
					.getJSONObject(TEST_DATA_JSON_FILE)
					.getJSONObject(TEST_DATA_JSON_VALID_USER);
		
		String username = jsonObject_InvalidUser.getString(TEST_DATA_JSON_USERNAME).toString();
		String password = jsonObject_InvalidUser.getString(TEST_DATA_JSON_PASSWORD).toString();

		productsPage = loginPage.
							enterUsername(username).
							enterPassword(password).
							pressLoginBtn();

		String actualProductTitle = productsPage.getTitle();
		String expectedProductTitle = StringsManager.getStrings()
				.get(EXPECTED_DATA_KEY_PRODUCT_TITLE);

		VerificationUtils.validate(actualProductTitle, expectedProductTitle, "Product Title");
	}

}
