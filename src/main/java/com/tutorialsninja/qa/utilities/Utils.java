package com.tutorialsninja.qa.utilities;

import java.util.Date;

public class Utils {
	
	public static String emailWithDateTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "seleniumpanda" + timeStamp + "@gmail.com";
	}
	
	public static final int IMPLICIT_WAIT = 100;
	public static final int PAGELOAD_TIME = 100;
	public static final int SCRIPTLOAD_TIME = 100;

}
