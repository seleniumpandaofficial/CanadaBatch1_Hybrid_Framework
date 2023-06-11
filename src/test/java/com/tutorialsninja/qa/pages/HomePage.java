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
	
	
    public void selectLoginOption() {
    	LoginOption.click();
    }
    
    public void selectRegisterOption() {
    	RegisterOption.click();
    }
}
