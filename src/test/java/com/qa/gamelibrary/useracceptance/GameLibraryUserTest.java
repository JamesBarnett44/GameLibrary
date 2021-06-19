package com.qa.gamelibrary.useracceptance;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
	public void testformToggle() {
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		GameLibrary.toggleForm();
	}

	@Test
	public void testReadGame() throws InterruptedException {
		driver.get(URL);

		new WebDriverWait(driver, 1)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"1\"]/div[1]/p[1]")));

		targ = driver.findElement(By.xpath("//*[@id=\"1\"]/div[1]/p[1]"));

		assertThat("Name: Bloodborne").isEqualTo(targ.getText());
	}

	@Test
	public void testCreateGame() throws InterruptedException {
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		GameLibrary.toggleForm();
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id("submitGameButton")));

		GameLibrary.createGame("Brigador", "Action");
		new WebDriverWait(driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"2\"]/div[1]/p[1]")));

		targ = driver.findElement(By.xpath("//*[@id=\"2\"]/div[1]/p[1]"));
		assertThat("Name: Brigador").isEqualTo(targ.getText());
	}

	@Test
	public void testUpdateGame() throws InterruptedException {
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		new WebDriverWait(driver, 1)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"1\"]/div[1]/a")));

		targ = driver.findElement(By.xpath("//*[@id=\"1\"]/div[1]/a"));
		targ.click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("gameModal")));

		GameLibrary.updateGame("Subnautica", "Survival");
		new WebDriverWait(driver, 1)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"1\"]/div[1]/p[1]")));

		targ = driver.findElement(By.xpath("//*[@id=\"1\"]/div[1]/p[1]"));
		assertThat("Name: Subnautica").isEqualTo(targ.getText());
	}

	@Test
	public void testDeleteGame() throws InterruptedException {
		driver.get(URL);
		new WebDriverWait(driver, 1)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"1\"]/div[1]/p[1]")));

		targ = driver.findElement(By.xpath("//*[@id=\"1\"]/div[1]/p[1]"));

		assertThat("Name: Bloodborne").isEqualTo(targ.getText());

		targ = driver.findElement(By.xpath("//*[@id=\"1\"]/div[2]/a"));
		targ.click();
		Thread.sleep(1000);

		List<WebElement> games = driver.findElements(By.xpath("//*[@id=\"1\"]/div[1]/p[1]"));
		assertThat(games.size()).isEqualTo(0);
	}

	@Test
	public void testReadPlatform() throws InterruptedException {
		driver.get(URL);
		new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOfElementLocated(By.name("platform1")));

		targ = driver.findElement(By.name("platform1"));

		assertThat("Steam").isEqualTo(targ.getText());
	}

	@Test
	public void testCreatePlatform() throws InterruptedException {
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
		assertThat("GOG").isEqualTo(targ.getText());
	}

	@Test
	public void testUpdatePlatform() throws InterruptedException {
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
		assertThat("Origin").isEqualTo(targ.getText());
	}

	@Test
	public void testDeletePlatform() throws InterruptedException {
		GameLibraryIndex GameLibrary = PageFactory.initElements(driver, GameLibraryIndex.class);
		driver.get(URL);
		GameLibrary.toggleForm();
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id("deletePlatformButton")));

		driver.findElement(By.name("platform1")).click();
		driver.findElement(By.id("deletePlatformButton")).click();

		Alert alert = driver.switchTo().alert();
		alert.sendKeys("delete");
		alert.accept();
		Thread.sleep(2000);

		List<WebElement> platform = driver.findElements(By.name("platform1"));
		assertThat(platform.size()).isEqualTo(0);
	}
}
