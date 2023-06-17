package com.tutorialsninja.qa.ExtentReports;

public class SystemClassProperties {

	public static void main(String[] args) {
		System.getProperties().list(System.out);
		System.out.println("the SDET who is doing this is:-> " + System.getProperty("user.name"));

	}

}
