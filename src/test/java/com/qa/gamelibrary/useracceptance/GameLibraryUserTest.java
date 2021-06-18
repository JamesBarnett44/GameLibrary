package com.qa.gamelibrary.useracceptance;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.gamelibrary.GameLibraryApplication;

//@SpringBootTest(classes = GameLibraryApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest(classes = GameLibraryApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@Sql(scripts = {"classpath:game-schema.sql",
		"classpath:game-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class GameLibraryUserTest {

	private static WebDriver driver;
	private static WebElement targ;
	private static final String URL = "http://localhost:4578/";

	@BeforeAll
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		ChromeOptions config = new ChromeOptions();
		config.setHeadless(true);
		driver = new ChromeDriver(config);
//		driver = new ChromeDriver();		
		
	}	

	@AfterAll
	public static void cleanup() {
		driver.quit();
	}
	
	@Disabled
	@Test
	public void testformToggle() {
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		//driver.get(GameLibraryIndex.URL);
		driver.get(URL);		
		GameLibrary.toggleForm();
	}
	
	@Disabled
	@Test
	public void testReadGame( ) throws InterruptedException {		
		//GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		Thread.sleep(5000); //replace with explicit wait, needs a bit of time
		
		targ = driver.findElement(By.id("game1"));
				
		assertThat("game1").isEqualTo(targ.getAttribute("id"));	
	}
	
	@Disabled
	@Test
	public void testCreateGame( ) throws InterruptedException {		
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		Thread.sleep(5000); //replace with explicit wait, needs a bit of time
		
		targ = driver.findElement(By.id("game1"));
				
		assertThat("game1").isEqualTo(targ.getAttribute("id"));	
	}
	
	@Disabled
	@Test
	public void testUpdateGame( ) throws InterruptedException {		
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		Thread.sleep(5000); //replace with explicit wait, needs a bit of time
		
		
	}
	
	@Disabled
	@Test
	public void testDeleteGame( ) throws InterruptedException {		
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		Thread.sleep(5000); //replace with explicit wait, needs a bit of time
		
		
	}
	
	
	
	
	// Designed to return ChromeOptions to configure new ChromeDrivers in Selenium
	public static ChromeOptions chromeCfg() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
			 cOptions.setExperimentalOption("prefs", prefs);

		return cOptions;
	}

}
