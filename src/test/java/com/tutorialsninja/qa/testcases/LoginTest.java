package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.testBase.TestBase;
import com.tutorialsninja.qa.testData.ExcelData;
import com.tutorialsninja.qa.utilities.Utils;

public class LoginTest extends TestBase {
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication("chrome");
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccountLink();
		homepage.selectLoginOption();
	}

	@Test(priority = 1, dataProvider = "TN Login", dataProviderClass = ExcelData.class)
	public void verifyLoginWithValidCredentials(String email, String password) {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailInEmailTextBox(email);
		loginpage.enterPasswordInPasswordTextBox(password);
		loginpage.clickOnLoginButton();

		AccountPage accountpage = new AccountPage(driver);
		softassert.assertTrue(accountpage.verifyEditAccountInfoLinkIsDisplayed());
		softassert.assertAll();

	}

	@Test(priority = 2)
	public void verifyLoginWithValidEmailInvalidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailInEmailTextBox(prop.getProperty("validEmail"));
		loginpage.enterPasswordInPasswordTextBox(testdataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveEmailPasswordMismatch();
		String expectedWarningMessage = testdataProp.getProperty("emailPasswordMismatch");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailValidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailInEmailTextBox(Utils.emailWithDateTimeStamp());
		loginpage.enterPasswordInPasswordTextBox(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveEmailPasswordMismatch();
		String expectedWarningMessage = testdataProp.getProperty("emailPasswordMismatch");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();

	}

	@Test(priority = 4)
	public void verifyLoginWithInvalidCredentials() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailInEmailTextBox(Utils.emailWithDateTimeStamp());
		loginpage.enterPasswordInPasswordTextBox(testdataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveEmailPasswordMismatch();
		String expectedWarningMessage = testdataProp.getProperty("emailPasswordMismatch");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();

	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {

		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]"))
				.getText();
		String expectedWarningMessage = testdataProp.getProperty("emailPasswordMismatch");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
