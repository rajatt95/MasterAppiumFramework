/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Appium Mobile Automation - Android & iOS + Frameworks + CICD (https://www.udemy.com/course/the-complete-appium-course-for-ios-and-android/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 * Tutor: Amuthan Sakthivel (https://www.testingminibytes.com/)
 */

/***************************************************/

package com.appium.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.appium.enums.AuthorType;
import com.appium.enums.CategoryType;

/*This is an Custom Annotation*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FrameworkAnnotation {

	// This is not a method
	public AuthorType[] author();

	// public String[] category();
	public CategoryType[] category();

}
