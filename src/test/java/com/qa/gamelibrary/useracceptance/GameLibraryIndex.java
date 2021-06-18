package com.qa.gamelibrary.useracceptance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GameLibraryIndex {
	
	public static final String URL = "http://localhost:8080/";
	
	@FindBy(xpath = "/html/body/div/a")
	private WebElement gameformButton;

	public GameLibraryIndex(WebDriver driver) {
	}
	
	public void toggleForm() {
		gameformButton.click();
	}
	
	

}
