package com.appium.tests;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_FILE;
import com.appium.annotations.FrameworkAnnotation;
import com.appium.base.BaseTest;
import com.appium.constants.FrameworkConstants;
import com.appium.enums.AuthorType;
import com.appium.enums.CategoryType;
import com.appium.manager.StringsManager;
import com.appium.pages.LoginPage;
import com.appium.pages.ProductDetailsPage;
import com.appium.pages.ProductsPage;
import com.appium.pages.SettingsPage;
import com.appium.utils.TestUtils;
import com.appium.utils.VerificationUtils;

public class ProductTests extends BaseTest {

	LoginPage loginPage;
	ProductsPage productsPage;
	SettingsPage settingsPage;
	ProductDetailsPage productDetailsPage;

	JSONObject loginUsers;

	@BeforeClass
	public void beforeClass() throws Exception {
		InputStream datais = null;
		try {
			datais = getClass().getClassLoader().getResourceAsStream(TEST_DATA_JSON_FILE);
			JSONTokener tokener = new JSONTokener(datais);
			loginUsers = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				datais.close();
			}
		}
	}

	@AfterClass
	public void afterClass() {
//		closeApp();
//		launchApp();
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
		closeApp();
		launchApp();
	}

	@FrameworkAnnotation(author = { AuthorType.NISHANT, AuthorType.GAUTAM }, category = { CategoryType.SMOKE,
			CategoryType.REGRESSION })
	@Test(groups = { "SMOKE", "REGRESSION" })
	public void validateProductOnProductsPage() {

		JSONObject jsonObject_InvalidUser = loginUsers.getJSONObject(FrameworkConstants.TEST_DATA_JSON_VALID_USER);
		String username = jsonObject_InvalidUser.getString(FrameworkConstants.TEST_DATA_JSON_USERNAME).toString();
		String password = jsonObject_InvalidUser.getString(FrameworkConstants.TEST_DATA_JSON_PASSWORD).toString();
		productsPage = loginPage.login(username, password);

		String actualProductTitle = productsPage.getTitle();
		String expectedProductTitle = StringsManager.getStrings()
				.get(FrameworkConstants.EXPECTED_DATA_KEY_PRODUCT_TITLE);
		VerificationUtils.validate(actualProductTitle, expectedProductTitle, "Product Title");

		String actualSLBTitle = productsPage.getSLBTitle();
		String expectedSLBTitle = StringsManager.getStrings()
				.get(FrameworkConstants.EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_TITLE);
		VerificationUtils.validate(actualSLBTitle, expectedSLBTitle, "Title for Sauce Labs Backpack");

		String actualSLBPrice = productsPage.getSLBPrice();
		String expectedSLBPrice = StringsManager.getStrings()
				.get(FrameworkConstants.EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_PRICE);
		VerificationUtils.validate(actualSLBPrice, expectedSLBPrice, "Price for Sauce Labs Backpack");

//		settingsPage = productsPage.pressSettingsBtn();
//		loginPage = settingsPage.pressLogoutBtn();
	
		settingsPage = productsPage.
				getMenuPage().
				pressSettingsBtn();
		loginPage = settingsPage.pressLogoutBtn();

		Assert.fail("*******************************Failing intentionally");
		
	}

	@FrameworkAnnotation(author = { AuthorType.RAJAT, AuthorType.NISHANT }, category = { CategoryType.BVT,
			CategoryType.REGRESSION })
	@Test(groups = { "BVT", "REGRESSION" })

	public void validateProductOnProductDetailsPage() {

		JSONObject jsonObject_InvalidUser = loginUsers.getJSONObject(FrameworkConstants.TEST_DATA_JSON_VALID_USER);
		String username = jsonObject_InvalidUser.getString(FrameworkConstants.TEST_DATA_JSON_USERNAME).toString();
		String password = jsonObject_InvalidUser.getString(FrameworkConstants.TEST_DATA_JSON_PASSWORD).toString();
		productsPage = loginPage.login(username, password);

		String actualProductTitle = productsPage.getTitle();
		String expectedProductTitle = StringsManager.getStrings()
				.get(FrameworkConstants.EXPECTED_DATA_KEY_PRODUCT_TITLE);

		VerificationUtils.validate(actualProductTitle, expectedProductTitle, "Product Title");

		productDetailsPage = productsPage.pressSLBTitle();

		String actualSLBTitle = productDetailsPage.getSLBTitle();
		String expectedSLBTitle = StringsManager.getStrings()
				.get(FrameworkConstants.EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_TITLE);
		VerificationUtils.validate(actualSLBTitle, expectedSLBTitle, "Title for Sauce Labs Backpack");

		String actualSLBDescription = productDetailsPage.getSLBTxt();
		String expectedSLBDescription = StringsManager.getStrings()
				.get(FrameworkConstants.EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_DESCRIPTION);
		VerificationUtils.validate(actualSLBDescription, expectedSLBDescription,
				"Description for Sauce Labs Backpack");
		productDetailsPage.scrollToSLBPrice();

		String actualSLBPrice = productDetailsPage.getSLBPrice();
		String expectedSLBPrice = StringsManager.getStrings()
				.get(FrameworkConstants.EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_PRICE);
		VerificationUtils.validate(actualSLBPrice, expectedSLBPrice, "Price for Sauce Labs Backpack");
	}
}
