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
