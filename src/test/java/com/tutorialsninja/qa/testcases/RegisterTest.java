package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.testBase.TestBase;
import com.tutorialsninja.qa.testData.ExcelData;
import com.tutorialsninja.qa.utilities.Utils;

public class RegisterTest extends TestBase{
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication("chrome");
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccountLink();
		homepage.selectRegisterOption();
		
	}
	
	@Test(priority = 1, dataProvider = "TN Register", dataProviderClass = ExcelData.class)
	public void registerWithMandatoryFields(String firstname, String lastname, String email, String telephone, String password, String confirmPassword) {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(firstname);
		driver.findElement(By.id("input-lastname")).sendKeys(lastname);
		driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(telephone);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.id("input-confirm")).sendKeys(confirmPassword);
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		softassert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Congratulations! Your new account has been successfully created!']")).isDisplayed());
		softassert.assertAll();
	}
	
	@Test(priority = 2)
	public void registerWithAllFields() {
		driver.findElement(By.id("input-firstname")).sendKeys(testdataProp.getProperty("firstname"));
		driver.findElement(By.id("input-lastname")).sendKeys(testdataProp.getProperty("lastname"));
		driver.findElement(By.id("input-email")).sendKeys(Utils.emailWithDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(testdataProp.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name = 'newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		softassert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Congratulations! Your new account has been successfully created!']")).isDisplayed());
		softassert.assertAll();
	}
	
	@Test(priority = 3)
	public void registerWithExistingEmailID() {
		driver.findElement(By.id("input-firstname")).sendKeys(testdataProp.getProperty("firstname"));
		driver.findElement(By.id("input-lastname")).sendKeys(testdataProp.getProperty("lastname"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-telephone")).sendKeys(testdataProp.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name = 'newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = testdataProp.getProperty("duplicateEmailWarning");
		softassert.assertEquals(actualWarningMessage, expectedWarningMessage);
		softassert.assertAll();
	}
	
	@Test(priority = 4)
	public void registerWithNoFields() {
		
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualFirstNameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-firstname']/following-sibling::div")).getText();
		String expectedFirstNameWarningMessage = testdataProp.getProperty("firstnameWarning");
		softassert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));
		
		String actualLastNameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-lastname']/following-sibling::div")).getText();
		String expectedLastNameWarningMessage = testdataProp.getProperty("lastnameWarning");
		softassert.assertTrue(actualLastNameWarningMessage.contains(expectedLastNameWarningMessage));
		
		String actualEmailWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-email']/following-sibling::div")).getText();
		String expectedEmailWarningMessage = testdataProp.getProperty("emailWarning");
		softassert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-telephone']/following-sibling::div")).getText();
		String expectedTelephoneWarningMessage = testdataProp.getProperty("telephoneWarning");
		softassert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));
		
		String actualPasswordWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-password']/following-sibling::div")).getText();
		String expectedPasswordWarningMessage = testdataProp.getProperty("passwordWarning");
		softassert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
		
		String actualPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedPrivacyPolicyWarningMessage = testdataProp.getProperty("privacypolicyWarning") ;
		softassert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage));
		
		softassert.assertAll();	
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
