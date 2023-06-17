package com.tutorialsninja.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utils {
	
	
	public static String emailWithDateTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "seleniumpanda" + timeStamp + "@gmail.com";
	}
	
	public static final int IMPLICIT_WAIT = 100;
	public static final int PAGELOAD_TIME = 100;
	public static final int SCRIPTLOAD_TIME = 100;

	public static String captureScreenShotCode(WebDriver driver, String testName) {
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir") + "\\test-output\\Screenshots\\" + testName + ".png";
		try {
			FileHandler.copy(source, new File(destinationPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return destinationPath;
	}
}
