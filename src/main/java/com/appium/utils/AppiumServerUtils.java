package com.appium.utils;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import static com.appium.constants.FrameworkConstants.PLATFORM_OS_WIN;
import static com.appium.constants.FrameworkConstants.PLATFORM_OS_WIN_NODE_INSTALLATION_PATH;
import static com.appium.constants.FrameworkConstants.PLATFORM_OS_WIN_APPIUM_INSTALLATION_PATH;
import static com.appium.constants.FrameworkConstants.APPIUM_SERVER_LOGS;
import static com.appium.constants.FrameworkConstants.PATH;
import static com.appium.constants.FrameworkConstants.PLATFORM_OS_MAC_VAR_ANDROID_HOME_VALUE;
import static com.appium.constants.FrameworkConstants.ANDROID_HOME;
import static com.appium.constants.FrameworkConstants.PLATFORM_OS_MAC_VAR_PATH_VALUE;
import static com.appium.constants.FrameworkConstants.PLATFORM_OS_MAC_NODE_INSTALLATION_PATH;
import static com.appium.constants.FrameworkConstants.PLATFORM_OS_MAC_APPIUM_INSTALLATION_PATH;
import static com.appium.constants.FrameworkConstants.PLATFORM_OS_NUX;
import static com.appium.constants.FrameworkConstants.PLATFORM_OS_MAC;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServerUtils {

	public static AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();
	}

	public static AppiumDriverLocalService getAppiumService() {
		String os = OSInfoUtils.getOSInfo().toLowerCase();

		if (os.contains(PLATFORM_OS_WIN)) {
			return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
					.usingDriverExecutable(new File(PLATFORM_OS_WIN_NODE_INSTALLATION_PATH))
					.withAppiumJS(new File(PLATFORM_OS_WIN_APPIUM_INSTALLATION_PATH)).usingPort(4723)
					.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
					.withLogFile(new File(APPIUM_SERVER_LOGS)));
			
//			return AppiumDriverLocalService.
//					buildService(new AppiumServiceBuilder().
//					usingAnyFreePort().
//					withArgument(GeneralServerFlag.SESSION_OVERRIDE).
//					withLogFile(new File(FrameworkConstants.APPIUM_SERVER_LOGS)));
			
			
		} else if (os.contains(PLATFORM_OS_MAC)) {
			HashMap<String, String> environment = new HashMap<String, String>();
			environment.put(PATH,
					PLATFORM_OS_MAC_VAR_PATH_VALUE + System.getenv(PATH));
			environment.put(ANDROID_HOME, PLATFORM_OS_MAC_VAR_ANDROID_HOME_VALUE);

			return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
					.usingDriverExecutable(new File(PLATFORM_OS_MAC_NODE_INSTALLATION_PATH))
					.withAppiumJS(new File(PLATFORM_OS_MAC_APPIUM_INSTALLATION_PATH)).usingPort(4723)
					.withArgument(GeneralServerFlag.SESSION_OVERRIDE).withEnvironment(environment)
					.withLogFile(new File(APPIUM_SERVER_LOGS)));
		} else if (os.contains(PLATFORM_OS_NUX)) {

		}
		return null;
	}

	public static boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
		boolean isAppiumServerRunning = false;
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (IOException e) {
			isAppiumServerRunning = true;
		} finally {
			socket = null;
		}
		return isAppiumServerRunning;
	}

}
