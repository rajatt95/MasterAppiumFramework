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

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONUtils {

	public JSONObject getJSONObject(String jsonFileName) {
		JSONObject jsonObject;
		InputStream datais = null;
		try {
			datais = getClass().getClassLoader().getResourceAsStream(jsonFileName);
			JSONTokener tokener = new JSONTokener(datais);
			jsonObject = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				try {
					datais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return jsonObject;
	}

}
