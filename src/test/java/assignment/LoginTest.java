package assignment;

import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class LoginTest {

	private ChromeDriver driver;

	@BeforeClass
	public void setup() {
		// System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://www.demo.rdekleijn.nl");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	/**
	 * Create a testcase which will do the following: <br>
	 * 
	 * 1. navigate to login page <br>
	 * 2. enter valid credentials <br>
	 * 3. verify that you are logged in
	 */
	@Test
	public void validCredentialsGiven_shouldLogin() {
		driver.findElement(By.cssSelector(".login")).click();

		driver.findElement(By.cssSelector("#email")).sendKeys("test@test.com");
		driver.findElement(By.cssSelector("#passwd")).sendKeys("qwerty");
		driver.findElement(By.cssSelector("#SubmitLogin")).click();

		final String title = driver.findElement(By.cssSelector("h1.page-heading")).getText();
		Assertions.assertThat(title).isEqualTo("MY ACCOUNT");
	}

	/**
	 * Create a testcase which will do the following: <br>
	 * 
	 * 1. navigate to login page <br>
	 * 2. enter invalid credentials <br>
	 * 3. verify error message
	 */
	@Test
	public void invalidCredentialsGiven_shouldShowErrorMessage() {
		driver.findElement(By.cssSelector(".login")).click();

		driver.findElement(By.cssSelector("#email")).sendKeys("test@test.com");
		driver.findElement(By.cssSelector("#passwd")).sendKeys("ytrewq");
		driver.findElement(By.cssSelector("#SubmitLogin")).click();

		final String errorMessage = driver.findElement(By.cssSelector("div.alert ol > li")).getText();
		Assertions.assertThat(errorMessage).isEqualTo("Authentication failed.");
	}

	/**
	 * Create a testcase which will do the following: <br>
	 * 
	 * 1. navigate to login page <br>
	 * 2. enter invalid emailaddress format <br>
	 * 3. verify error message
	 */
	@Test
	public void invalidEmailAddressGiven_shouldShowErrorMessage() {
		driver.findElement(By.cssSelector(".login")).click();

		driver.findElement(By.cssSelector("#email")).sendKeys("test@test");
		driver.findElement(By.cssSelector("#passwd")).sendKeys("qwerty");
		driver.findElement(By.cssSelector("#SubmitLogin")).click();

		final String errorMessage = driver.findElement(By.cssSelector("div.alert ol > li")).getText();
		Assertions.assertThat(errorMessage).isEqualTo("Invalid email address.");
	}

	/**
	 * Extra:
	 * 
	 * Move duplicated behaviors to separated before method
	 * 
	 */

}
