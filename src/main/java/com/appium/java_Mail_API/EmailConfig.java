package com.appium.java_Mail_API;

import static com.appium.constants.FrameworkConstants.Project_Name;

/**
 * Data for Sending EMail after execution
 */
public class EmailConfig {

	public static final String SERVER = "smtp.gmail.com";
	public static final String PORT = "587";

	public static final String FROM = "testtmail95@gmail.com";
	public static final String PASSWORD = "HelloSher";

	/* "**********@gmail.com", */
	public static final String[] TO = { "testtmail95@gmail.com" };
	public static final String SUBJECT = Project_Name;
}
