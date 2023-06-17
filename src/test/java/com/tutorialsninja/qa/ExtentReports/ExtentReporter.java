package com.tutorialsninja.qa.ExtentReports;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports extentReport;
	public static File extentReportFile;
	public static ExtentSparkReporter sparkReporter;
	public static Properties prop;
	public static FileInputStream ip;
	
	public static ExtentReports generateExtentReports() throws Exception {
		//Step1: We need to get the Maven dependency of Extent Reports in pom.xml file
		//Step2: Create the Object of ExtentReports Class
		extentReport = new ExtentReports();
		
		//Step3: Create the object of File Class and pass the path of the .html file inside the constructor
		extentReportFile = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentreport.html");
		
		//Step4: Create the object of ExtentSparkReporter Class and pass the File reference in the constructor
		sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		//Step5: Using the sparkReporter, we can configure a lot of things
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TN Automation Results");
		sparkReporter.config().setDocumentTitle("TNReportTitle|Automation|Results");
		sparkReporter.config().setTimeStampFormat("MM/dd/yyyy hh:mm:ss");
		
		//Step6: We need to attach the ExtentReport with the sparkReporter
		extentReport.attachReporter(sparkReporter);
		
		//Step7: Additional information and create a Properties file and read from it.
		prop = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
	    prop.load(ip);
	    
	    //Application url, browser, username, password, Operating System, Java version, name of the SDET
	    
	    extentReport.setSystemInfo("application url", prop.getProperty("url"));
	    extentReport.setSystemInfo("browser name", prop.getProperty("browser"));
	    extentReport.setSystemInfo("username", prop.getProperty("validEmail"));
	    extentReport.setSystemInfo("password", prop.getProperty("validPassword"));
	    extentReport.setSystemInfo("operating sytem", System.getProperty("os.name"));
	    extentReport.setSystemInfo("operating sytemversion", System.getProperty("os.version"));
	    extentReport.setSystemInfo("SDET Name", System.getProperty("user.name"));
	    extentReport.setSystemInfo("Java Runtime Version", System.getProperty("java.runtime.version"));
	    
	    //Step8: Return the extentReport
	    
	    return extentReport;
	    
	}

}
