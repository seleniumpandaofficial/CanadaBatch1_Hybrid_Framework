package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.qa.testBase.TestBase;

public class SearchProductTest extends TestBase{
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication("chrome");
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		softassert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		softassert.assertAll();
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		driver.findElement(By.name("search")).sendKeys("DELL");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		softassert.assertTrue(driver.findElement(By.xpath("//p[text() = 'There is no product that matches the search criteria.']")).isDisplayed());
		softassert.assertAll();
	}
	
	@Test(priority = 3)
	public void verifySearchWithNoProduct() {
		
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		softassert.assertTrue(driver.findElement(By.xpath("//p[text() = 'There is no product that matches the search criteria.']")).isDisplayed());
		softassert.assertAll();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
