package com.appium.manager;

public final class DeviceNameManager {

	private DeviceNameManager() {
	}

	private static ThreadLocal<String> deviceName = new ThreadLocal<String>();

	public static String getDeviceName() {
		return deviceName.get();
	}

	public static void setDeviceName(String deviceName2) {
		deviceName.set(deviceName2);
	}

	public static void unload() {
		deviceName.remove();
	}

}
