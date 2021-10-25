package com.appium.utils;

import java.io.File;

import org.zeroturnaround.zip.ZipUtil;

import com.appium.constants.FrameworkConstants;

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

		ZipUtil.pack(new File(FrameworkConstants.EXTENT_REPORT_FOLDER_PATH),
				new File(FrameworkConstants.Zipped_ExtentReports_Folder_Name));

		System.out.println("Zipped ExtentReports folder successfuly");
	}

	public static void main(String[] args) {
		System.out.println(
				"FrameworkConstants.getExtentReportFolderPath(): " + FrameworkConstants.EXTENT_REPORT_FOLDER_PATH);
		System.out.println("FrameworkConstants.getZipped_ExtentReports_Folder_Name(): "
				+ FrameworkConstants.Zipped_ExtentReports_Folder_Name);
		String reportsLocation = FrameworkConstants.PROJECT_LOCATION + "/ExtentReports";
		ZipUtil.pack(new File(reportsLocation), new File("ExtentReports.zip"));

	}

}
