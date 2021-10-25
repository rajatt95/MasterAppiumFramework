package com.appium.manager;

import java.util.HashMap;

public final class StringsManager {

	private StringsManager() {
	}

	private static ThreadLocal<HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();

	public static HashMap<String, String> getStrings() {
		return strings.get();
	}

	public static void setStrings(HashMap<String, String> driverref) {
		strings.set(driverref);
	}

	public static void unload() {
		strings.remove();
	}

}
