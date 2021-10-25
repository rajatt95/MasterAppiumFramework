package com.appium.manager;

public final class PlatformManager {

	private PlatformManager() {
	}

	private static ThreadLocal<String> platform = new ThreadLocal<String>();

	public static String getPlatform() {
		return platform.get();
	}

	public static void setPlatform(String driverref) {
		platform.set(driverref);
	}

	public static void unload() {
		platform.remove();
	}

}
