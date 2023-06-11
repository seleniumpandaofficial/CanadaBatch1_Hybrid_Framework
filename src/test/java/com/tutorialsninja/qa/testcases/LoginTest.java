package com.tutorialsninja.qa.testcases;

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
	public LoginPage loginpage;
	public AccountPage accountpage;
	public HomePage homepage;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication("chrome");
		homepage = new HomePage(driver);
		loginpage = homepage.navigateToLoginPage();
	
	}

	@Test(priority = 1, dataProvider = "TN Login", dataProviderClass = ExcelData.class)
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		accountpage = loginpage.navigateToAccountPage(email, password);
		softassert.assertTrue(accountpage.verifyEditAccountInfoLinkIsDisplayed());
		softassert.assertAll();

	}

	@Test(priority = 2)
	public void verifyLoginWithValidEmailInvalidPassword() {
		
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

		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveEmailPasswordMismatch();
		String expectedWarningMessage = testdataProp.getProperty("emailPasswordMismatch");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
