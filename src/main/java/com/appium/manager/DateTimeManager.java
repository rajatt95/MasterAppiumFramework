package com.appium.manager;

public final class DateTimeManager {

	private DateTimeManager() {
	}

	private static ThreadLocal<String> dateTime = new ThreadLocal<String>();

	public static String getDateTime() {
		return dateTime.get();
	}

	public static void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public static void unload() {
		dateTime.remove();
	}

}
