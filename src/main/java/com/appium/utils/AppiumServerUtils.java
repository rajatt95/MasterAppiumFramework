package com.appium.utils;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import com.appium.constants.FrameworkConstants;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServerUtils {

	public static AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();
	}

	public static AppiumDriverLocalService getAppiumService() {
		String os = OSInfoUtils.getOSInfo().toLowerCase();

		if (os.contains(FrameworkConstants.PLATFORM_OS_WIN)) {
			return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
					.usingDriverExecutable(new File(FrameworkConstants.PLATFORM_OS_WIN_NODE_INSTALLATION_PATH))
					.withAppiumJS(new File(FrameworkConstants.PLATFORM_OS_WIN_APPIUM_INSTALLATION_PATH)).usingPort(4723)
					.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
					.withLogFile(new File(FrameworkConstants.APPIUM_SERVER_LOGS)));
			
//			return AppiumDriverLocalService.
//					buildService(new AppiumServiceBuilder().
//					usingAnyFreePort().
//					withArgument(GeneralServerFlag.SESSION_OVERRIDE).
//					withLogFile(new File(FrameworkConstants.APPIUM_SERVER_LOGS)));
			
			
		} else if (os.contains(FrameworkConstants.PLATFORM_OS_MAC)) {
			HashMap<String, String> environment = new HashMap<String, String>();
			environment.put(FrameworkConstants.PATH,
					FrameworkConstants.PLATFORM_OS_MAC_VAR_PATH_VALUE + System.getenv(FrameworkConstants.PATH));
			environment.put(FrameworkConstants.ANDROID_HOME, FrameworkConstants.PLATFORM_OS_MAC_VAR_ANDROID_HOME_VALUE);

			return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
					.usingDriverExecutable(new File(FrameworkConstants.PLATFORM_OS_MAC_NODE_INSTALLATION_PATH))
					.withAppiumJS(new File(FrameworkConstants.PLATFORM_OS_MAC_APPIUM_INSTALLATION_PATH)).usingPort(4723)
					.withArgument(GeneralServerFlag.SESSION_OVERRIDE).withEnvironment(environment)
					.withLogFile(new File(FrameworkConstants.APPIUM_SERVER_LOGS)));
		} else if (os.contains(FrameworkConstants.PLATFORM_OS_NUX)) {

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
