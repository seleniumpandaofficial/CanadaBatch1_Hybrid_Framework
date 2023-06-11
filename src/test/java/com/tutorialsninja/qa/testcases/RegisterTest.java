package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.testBase.TestBase;
import com.tutorialsninja.qa.testData.ExcelData;
import com.tutorialsninja.qa.utilities.Utils;

public class RegisterTest extends TestBase{
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	public RegisterPage registerpage;
	public HomePage homepage;
	public AccountSuccessPage accountsuccesspage;
	

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication("chrome");
		homepage = new HomePage(driver);
		homepage.clickOnMyAccountLink();
		registerpage = homepage.selectRegisterOption(); //this returns a new RegisterPage
		
	}
	
	@Test(priority = 1, dataProvider = "TN Register", dataProviderClass = ExcelData.class)
	public void registerWithMandatoryFields(String firstname, String lastname, String email, String telephone, String password, String confirmPassword) {
		
		registerpage.enterFirstName(firstname);
		registerpage.enterLastName(lastname);
		registerpage.enterEmail(Utils.emailWithDateTimeStamp());
		registerpage.enterTelephone(telephone);
		registerpage.enterPassword(password);
		registerpage.enterConfirmPassword(confirmPassword);
		registerpage.clickOnPrivacyPolicyCheckBox();
	    accountsuccesspage = registerpage.clickOnContinueButton(); //returns a AccountSuccessPage
	    
		softassert.assertTrue(accountsuccesspage.validateDisplayAccountCreatedSuccessMessage());
		softassert.assertAll();
	}
	
	@Test(priority = 2)
	public void registerWithAllFields() {
		
		registerpage.enterFirstName(testdataProp.getProperty("firstname"));
        registerpage.enterLastName(testdataProp.getProperty("lastname"));
		registerpage.enterEmail(Utils.emailWithDateTimeStamp());
		registerpage.enterTelephone(testdataProp.getProperty("telephone"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.selectYesNewsLetterRadioButton();
		registerpage.clickOnPrivacyPolicyCheckBox();
		accountsuccesspage  = registerpage.clickOnContinueButton();
		
		softassert.assertTrue(accountsuccesspage.validateDisplayAccountCreatedSuccessMessage());
		softassert.assertAll();
	}
	
	@Test(priority = 3)
	public void registerWithExistingEmailID() {
		
		registerpage.enterFirstName(testdataProp.getProperty("firstname"));
		registerpage.enterLastName(testdataProp.getProperty("lastname"));
		registerpage.enterEmail(prop.getProperty("validEmail"));
		registerpage.enterTelephone(testdataProp.getProperty("telephone"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.selectYesNewsLetterRadioButton();
		registerpage.clickOnPrivacyPolicyCheckBox();
		registerpage.clickOnContinueButton();
		
		String actualWarningMessage = registerpage.retrieveDuplicateEmailWarningMessage();
		String expectedWarningMessage = testdataProp.getProperty("duplicateEmailWarning");
		softassert.assertEquals(actualWarningMessage, expectedWarningMessage);
		softassert.assertAll();
	}
	
	@Test(priority = 4)
	public void registerWithNoFields() {
		
		registerpage.clickOnContinueButton();
		
		String actualFirstNameWarningMessage = registerpage.retrieveFirstNameWarningMessage();
		String expectedFirstNameWarningMessage = testdataProp.getProperty("firstnameWarning");
		softassert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));
		
		String actualLastNameWarningMessage = registerpage.retrieveLastNameWarningMessage();
		String expectedLastNameWarningMessage = testdataProp.getProperty("lastnameWarning");
		softassert.assertTrue(actualLastNameWarningMessage.contains(expectedLastNameWarningMessage));
		
		String actualEmailWarningMessage = registerpage.retrieveEmailWarningMessage();
		String expectedEmailWarningMessage = testdataProp.getProperty("emailWarning");
		softassert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = registerpage.retrieveTelephoneWarningMessage();
		String expectedTelephoneWarningMessage = testdataProp.getProperty("telephoneWarning");
		softassert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));
		
		String actualPasswordWarningMessage = registerpage.retrievePasswordWarningMessage();
		String expectedPasswordWarningMessage = testdataProp.getProperty("passwordWarning");
		softassert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
		
		String actualPrivacyPolicyWarningMessage = registerpage.retrievePrivacyPolicyWarningMessage();
		String expectedPrivacyPolicyWarningMessage = testdataProp.getProperty("privacypolicyWarning") ;
		softassert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage));
		
		softassert.assertAll();	
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
