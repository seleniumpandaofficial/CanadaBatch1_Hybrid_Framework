package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchProductPage;
import com.tutorialsninja.qa.testBase.TestBase;

public class SearchProductTest extends TestBase{
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	public SearchProductPage searchproductpage;
	public HomePage homepage;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		homepage = new HomePage(driver);
		homepage.enterProductNameInSearchbox(testdataProp.getProperty("validProduct"));
		searchproductpage = homepage.clickOnSearchButton(); //this returns a SearchProductPage
		
		softassert.assertTrue(searchproductpage.validateDisplayOfValidProduct());
		softassert.assertAll();
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		homepage = new HomePage(driver);
		homepage.enterProductNameInSearchbox(testdataProp.getProperty("invalidProduct"));
		searchproductpage = homepage.clickOnSearchButton();
		
		softassert.assertTrue(searchproductpage.validateDisplayOfInvalidOrNoProduct());
		softassert.assertAll();
	}
	
	@Test(priority = 3)
	public void verifySearchWithNoProduct() {
		homepage = new HomePage(driver);
		searchproductpage = homepage.clickOnSearchButton();
		
		softassert.assertTrue(searchproductpage.validateDisplayOfInvalidOrNoProduct());
		softassert.assertAll();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
