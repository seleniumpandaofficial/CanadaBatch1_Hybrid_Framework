package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchProductPage {
	
	public WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
    private WebElement validProduct;
	
	@FindBy(xpath = "//p[text() = 'There is no product that matches the search criteria.']")
	private WebElement invalidOrNoproduct;
	
	public SearchProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean validateDisplayOfValidProduct() {
		boolean displayStatus = validProduct.isDisplayed();
		return displayStatus;
	}
	
	public boolean validateDisplayOfInvalidOrNoProduct() {
		boolean displayStatus = invalidOrNoproduct.isDisplayed();
		return displayStatus;
	}

	
	
}
