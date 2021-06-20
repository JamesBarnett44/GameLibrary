package com.qa.gamelibrary.useracceptance;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.gamelibrary.GameLibraryApplication;

@SpringBootTest(classes = GameLibraryApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
	}

	@AfterAll
	public static void cleanup() {
		driver.quit();
	}	

	@Test
	void testReadGame() {
		driver.get(URL);

		new WebDriverWait(driver, 1)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"1\"]/div[1]/p[1]")));

		targ = driver.findElement(By.xpath("//*[@id=\"1\"]/div[1]/p[1]"));

		assertThat(targ.getText()).isEqualTo("Name: Bloodborne");
	}

	@Test
	void testCreateGame() {
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		GameLibrary.toggleForm();
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id("submitGameButton")));

		GameLibrary.createGame("Brigador", "Action");
		new WebDriverWait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"2\"]/div[1]/p[1]")));

		targ = driver.findElement(By.xpath("//*[@id=\"2\"]/div[1]/p[1]"));
		assertThat(targ.getText()).isEqualTo("Name: Brigador");
	}

	@Test
	void testUpdateGame() {
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		new WebDriverWait(driver, 1)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"1\"]/div[2]/a[1]")));

		targ = driver.findElement(By.xpath("//*[@id=\"1\"]/div[2]/a[1]"));
		targ.click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("gameModal")));

		GameLibrary.updateGame("Subnautica", "Survival");
		new WebDriverWait(driver, 1)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"1\"]/div[1]/p[1]")));

		targ = driver.findElement(By.xpath("//*[@id=\"1\"]/div[1]/p[1]"));
		assertThat(targ.getText()).isEqualTo("Name: Subnautica");
	}

	@Test
	void testDeleteGame() {
		driver.get(URL);
		new WebDriverWait(driver, 1)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"1\"]/div[1]/p[1]")));

		targ = driver.findElement(By.xpath("//*[@id=\"1\"]/div[1]/p[1]"));

		assertThat(targ.getText()).isEqualTo("Name: Bloodborne");

		targ = driver.findElement(By.xpath("//*[@id=\"1\"]/div[2]/a[2]"));
		targ.click();
		LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(1000));

		List<WebElement> games = driver.findElements(By.xpath("//*[@id=\"1\"]/div[1]/p[1]"));
		assertThat(games.size()).isZero();
	}
	
	@Test
	void testReadPlatform() {
		driver.get(URL);
		new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOfElementLocated(By.name("platform1")));

		targ = driver.findElement(By.name("platform1"));

		assertThat(targ.getText()).isEqualTo("Steam");
	}

	@Test
	void testCreatePlatform() {
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		GameLibrary.toggleForm();
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id("addPlatformButton")));

		targ = driver.findElement(By.id("addPlatformButton"));
		targ.click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("platformModal")));

		GameLibrary.CreatePlatform("GOG");
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.name("platform2")));

		targ = driver.findElement(By.name("platform2"));
		assertThat(targ.getText()).isEqualTo("GOG");
	}

	@Test
	void testUpdatePlatform() {
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		GameLibrary.toggleForm();
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id("updatePlatformButton")));

		driver.findElement(By.name("platform1")).click();
		driver.findElement(By.id("updatePlatformButton")).click();

		Alert alert = driver.switchTo().alert();
		alert.sendKeys("Origin");
		alert.accept();
		new WebDriverWait(driver, 5).until(ExpectedConditions.textToBe(By.name("platform1"), "Origin"));

		targ = driver.findElement(By.name("platform1"));
		assertThat(targ.getText()).isEqualTo("Origin");
	}

	@Test
	void testDeletePlatform() {
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		GameLibrary.toggleForm();
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id("deletePlatformButton")));

		driver.findElement(By.name("platform1")).click();
		driver.findElement(By.id("deletePlatformButton")).click();

		Alert alert = driver.switchTo().alert();
		alert.sendKeys("delete");
		alert.accept();
		LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(2000));

		List<WebElement> platform = driver.findElements(By.name("platform1"));
		assertThat(platform.size()).isZero();
	}
}
