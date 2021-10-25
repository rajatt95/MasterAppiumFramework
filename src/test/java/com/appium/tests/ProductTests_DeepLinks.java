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
import org.testng.asserts.SoftAssert;

import com.appium.base.BaseTest;
import com.appium.manager.StringsManager;
import com.appium.pages.LoginPage;
import com.appium.pages.ProductDetailsPage;
import com.appium.pages.ProductsPage;
import com.appium.pages.SettingsPage;
import com.appium.utils.DeepLink;
import com.appium.utils.TestUtils;

public class ProductTests_DeepLinks extends BaseTest {

	LoginPage loginPage;
	ProductsPage productsPage;
	SettingsPage settingsPage;
	ProductDetailsPage productDetailsPage;

	JSONObject loginUsers;

	@BeforeClass
	public void beforeClass() throws Exception {
		// InputStream dataIS;
		InputStream datais = null;
		try {
			String dataFileName = "data/loginUsers.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
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
		// closeApp();
		// launchApp();
	}

	// @Test
	public void validateProductOnProductsPage_DeepLinks() {
		SoftAssert sa = new SoftAssert();
		/*
		 * ProductsPage productsPage = new
		 * LoginPage().login(loginUsers.getJSONObject("validUser").getString("username")
		 * , loginUsers.getJSONObject("validUser").getString("password"));
		 */
		DeepLink.OpenAppWith("swaglabs://swag-overview/0,5");
		ProductsPage productsPage = new ProductsPage();
		sa.assertEquals(productsPage.getSLBTitle(), StringsManager.getStrings().get("products_page_slb_title"));
		sa.assertEquals(productsPage.getSLBPrice(), StringsManager.getStrings().get("products_page_slb_price"));
		sa.assertAll();
	}

	// @Test
	public void validateProductOnProductDetailsPage_DeepLinks() {
		/*
		 * ProductsPage productsPage = new
		 * LoginPage().login(loginUsers.getJSONObject("validUser").getString("username")
		 * , loginUsers.getJSONObject("validUser").getString("password"));
		 */
		DeepLink.OpenAppWith("swaglabs://swag-overview/0,5");
		ProductsPage productsPage = new ProductsPage();
		ProductDetailsPage productDetailsPage = productsPage.pressSLBTitle();
		Assert.assertEquals(productDetailsPage.getSLBTitle(),
				StringsManager.getStrings().get("product_details_page_slb_title"));
	}
}
