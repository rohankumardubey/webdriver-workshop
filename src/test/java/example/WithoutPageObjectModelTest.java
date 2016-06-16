package example;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class WithoutPageObjectModelTest extends AbstractTestBase {

	@Test
	public void openWebPage() {
		System.out.println(driver.getTitle());
	}

	@Test
	public void register() {
		driver.findElement(By.cssSelector(".login")).click();

		final String newEmailAddress = RandomStringUtils.randomAlphabetic(8).concat("@mailinator.com");

		driver.findElement(By.cssSelector("input#email_create")).sendKeys(newEmailAddress);
		driver.findElement(By.cssSelector("button#SubmitCreate")).click();

		driver.findElement(By.cssSelector("input#id_gender1")).click();
		driver.findElement(By.cssSelector("input#customer_firstname")).sendKeys("Test");
		driver.findElement(By.cssSelector("input#customer_lastname")).sendKeys("Tester");
		driver.findElement(By.cssSelector("input#passwd")).sendKeys("1qazxsw2");

		final Select daysSelect = new Select(driver.findElement(By.cssSelector("select#days")));
		daysSelect.selectByValue("1");

		final Select monthsSelect = new Select(driver.findElement(By.cssSelector("select#months")));
		monthsSelect.selectByValue("1");

		final Select yearsSelect = new Select(driver.findElement(By.cssSelector("select#years")));
		yearsSelect.selectByValue("1986");

		driver.findElement(By.cssSelector("button#submitAccount")).click();

		Assertions.assertThat(driver.findElement(By.cssSelector("p.alert-success")).getText()).as("sucess message")
				.isEqualTo("Your account has been created.");
	}

	@Test
	public void login() {
		driver.findElement(By.cssSelector(".login")).click();

		driver.findElement(By.cssSelector("input#email")).sendKeys("test@test.com");
		driver.findElement(By.cssSelector("input#passwd")).sendKeys("1qazxsw2");
		driver.findElement(By.cssSelector("button#SubmitLogin")).click();

		Assertions.assertThat(driver.findElement(By.cssSelector("p.info-account")).getText()).as("success message")
				.contains("Welcome to your account.");
	}
}
