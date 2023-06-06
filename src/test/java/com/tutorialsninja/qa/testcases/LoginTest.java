package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.qa.testBase.TestBase;
import com.tutorialsninja.qa.utilities.Utils;

public class LoginTest extends TestBase{
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication("chrome");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {
	
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		softassert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		softassert.assertAll();
		
	}
	
	@Test(priority = 2)
	public void verifyLoginWithValidEmailInvalidPassword() {

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123456");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		SoftAssert softassert = new SoftAssert();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();
		
	}
	
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailValidPassword() {

		driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		SoftAssert softassert = new SoftAssert();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();
		
	}
	
	@Test(priority = 4)
	public void verifyLoginWithInvalidCredentials() {

		driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123456");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		SoftAssert softassert = new SoftAssert();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();
		
	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {

		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		SoftAssert softassert = new SoftAssert();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
