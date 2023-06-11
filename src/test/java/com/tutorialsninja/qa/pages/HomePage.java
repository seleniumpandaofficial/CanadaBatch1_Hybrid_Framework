package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public WebDriver driver;
	
	//Create objects
	
	@FindBy(linkText = "My Account")
	private WebElement MyAccountLink;
	
	@FindBy(linkText = "Login")
	private WebElement LoginOption;
	
	@FindBy(linkText = "Register")
	private WebElement RegisterOption;
	
	@FindBy(name = "search")
	private WebElement searchTextBox;
	
	@FindBy(css = "button.btn.btn-default.btn-lg")
	private WebElement searchButton;
	
	//Initialize the Object
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//PageFactory.initElements(driver, HomePage.class);
	}
	
	
	
	//Actions
	public void clickOnMyAccountLink() {
		MyAccountLink.click();	
	}
	
	public LoginPage selectLoginOption() {
    	LoginOption.click();
    	return new LoginPage(driver);
    }
    
    public LoginPage navigateToLoginPage() {
    	MyAccountLink.click();
    	LoginOption.click();
    	return new LoginPage(driver);
    }
    
    public RegisterPage selectRegisterOption() {
    	RegisterOption.click();
    	return new RegisterPage(driver);
    }
    
    public void enterProductNameInSearchbox(String validProdtext) {
    	searchTextBox.sendKeys(validProdtext);
    }
    
    public SearchProductPage clickOnSearchButton() {
    	searchButton.click();
    	return new SearchProductPage(driver);
    }
}
