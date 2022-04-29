/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Appium Mobile Automation - Android & iOS + Frameworks + CICD (https://www.udemy.com/course/the-complete-appium-course-for-ios-and-android/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 */

/***************************************************/

package com.appium.utils;

import java.io.File;

import org.zeroturnaround.zip.ZipUtil;

import static com.appium.constants.FrameworkConstants.Zipped_ExtentReports_Folder_Name;
import static com.appium.constants.FrameworkConstants.EXTENT_REPORT_FOLDER_PATH;
import static com.appium.constants.FrameworkConstants.PROJECT_LOCATION;



public class ZipUtils {

	/* make Zip file of Extent Reports in Project Root folder */
	public static void zip() {

		/*
		 * FrameworkConstants.getExtentReportFolderPath():
		 * D:\Work_In_Local_Machine\OneDrive -
		 * Nagarro\Eclipse_Java_Developers_06_2021_Copy\LearningWS\Final_Code\
		 * OC_MasterSeleniumFramework/ExtentReports/
		 * FrameworkConstants.getZipped_ExtentReports_Folder_Name(): ExtentReports.zip
		 */

		ZipUtil.pack(new File(EXTENT_REPORT_FOLDER_PATH),
				new File(Zipped_ExtentReports_Folder_Name));

		System.out.println("Zipped ExtentReports folder successfuly");
	}

	public static void main(String[] args) {
		System.out.println(
				"FrameworkConstants.getExtentReportFolderPath(): " + EXTENT_REPORT_FOLDER_PATH);
		System.out.println("FrameworkConstants.getZipped_ExtentReports_Folder_Name(): "
				+ Zipped_ExtentReports_Folder_Name);
		String reportsLocation = PROJECT_LOCATION + "/ExtentReports";
		ZipUtil.pack(new File(reportsLocation), new File("ExtentReports.zip"));

	}

}
