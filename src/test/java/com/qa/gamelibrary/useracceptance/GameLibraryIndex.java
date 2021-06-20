package com.qa.gamelibrary.useracceptance;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GameLibraryIndex {

	public static final String URL = "http://localhost:8080/";

	@FindBy(xpath = "/html/body/main/div[1]/a")
	private WebElement gameformButton;

	@FindBy(id = "gameName")
	private WebElement gameNameField;

	@FindBy(id = "genre")
	private WebElement genreField;

	@FindBy(id = "submitGameButton")
	private WebElement submitGameButton;	
	
	@FindBy(xpath = "//*[@id=\"gameUpdateForm\"]/button")
	private WebElement gameUpdateFormButton;
	//*[@id="1"]/div[2]/a[1]
	@FindBy(id = "updatedGameName")
	private WebElement updatedGameNameField;	
	
	@FindBy(id = "updatedGenre")
	private WebElement updatedGenreField;	
	
	@FindBy(xpath = "//*[@id=\"gameModal\"]/div/div/div[1]/button")
	private WebElement updateModalCloseButton;	
	
	@FindBy(id = "platformInput")
	private WebElement platformNameField;	
	
	@FindBy(xpath = "//*[@id=\"addplatformForm\"]/div[2]/button[2]")
	private WebElement addPlatformSubmitButton;
	
	@FindBy(xpath = "//*[@id=\"addplatformForm\"]/div[2]/button[1]")
	private WebElement addPlatformCloseButton;	

	public GameLibraryIndex(WebDriver driver) {
	}

	public void toggleForm() {
		gameformButton.click();
	}

	public void createGame(String gameName, String genre) {
		gameNameField.sendKeys(gameName);
		genreField.sendKeys(genre);
		submitGameButton.click();
	}
	
	public void updateGame(String gameName, String genre) {
		updatedGameNameField.sendKeys(gameName);
		updatedGenreField.sendKeys(genre);
		gameUpdateFormButton.click();
		updateModalCloseButton.click();
	}
	
	public void CreatePlatform(String platformName) {		
		platformNameField.sendKeys(platformName);
		addPlatformSubmitButton.click();
		addPlatformCloseButton.click();
	}
	
	

}
